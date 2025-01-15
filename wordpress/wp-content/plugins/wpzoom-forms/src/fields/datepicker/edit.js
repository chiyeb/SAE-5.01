import { useBlockProps, InspectorControls, RichText } from '@wordpress/block-editor';
import { Fragment, useEffect, useState } from '@wordpress/element';
import { __ } from '@wordpress/i18n';
import { PanelBody, TextControl, ToggleControl, SelectControl } from '@wordpress/components';

const Edit = props => {
	const blockProps = useBlockProps();
	const { attributes, setAttributes, clientId } = props;
	const { id, name, mode, format, customFormat, label, showLabel, required } = attributes;

	useEffect( () => {
		if ( ! id ) {
			setAttributes( { id: 'input_' + clientId.substr( 0, 8 ) } );
		}
	}, [] );

	const [ uniqueId ] = useState( id );

	const handleNameChange = newValue => {
		const newId = newValue.replace(/\s/g, '_').toLowerCase();
		setAttributes( { name: newValue, id: 'input_' + clientId.substr( 0, 8 ) } );		
	};

	return <>
		<InspectorControls>
			<PanelBody title={ __( 'Options', 'wpzoom-forms' ) }>
				<TextControl
					label={ __( 'Name', 'wpzoom-forms' ) }
					value={ name }
					placeholder={ __( 'e.g. My Text Field', 'wpzoom-forms' ) }
					onChange={ handleNameChange }
				/>

				<SelectControl
					label={ __( 'Mode', 'wpzoom-forms' ) }
					value={ mode }
					options={ [
						{
							label: __( 'Single', 'wpzoom-forms' ),
							value: 'single'
						},
						{
							label: __( 'Multiple', 'wpzoom-forms' ),
							value: 'multiple'
						},
						{
							label: __( 'Range', 'wpzoom-forms' ),
							value: 'range'
						},
					] }
					onChange={ value => setAttributes( { mode: value } ) }
				/>

				<SelectControl
					label={ __( 'Format', 'wpzoom-forms' ) }
					value={ format }
					options={ [
						{
							label: __( 'Y-m-d', 'wpzoom-forms' ),
							value: 'Y-m-d'
						},
						{
							label: __( 'F j, Y', 'wpzoom-forms' ),
							value: 'F j, Y'
						},
						{
							label: __( 'm/d/Y', 'wpzoom-forms' ),
							value: 'm/d/Y'
						},
						{
							label: __( 'd/m/Y', 'wpzoom-forms' ),
							value: 'd/m/Y'
						},
						{
							label: __( 'Custom', 'wpzoom-forms' ),
							value: 'custom_format'
						},
					] }
					onChange={ value => setAttributes( { format: value } ) }
				/>
				{ 'custom_format' == format && 
					<TextControl
						label={ __( 'Custom Format', 'wpzoom-forms' ) }
						value={ customFormat }
						onChange={ value => setAttributes( { customFormat: value } ) }
					/>
				}

				<ToggleControl
					label={ __( 'Show Label', 'wpzoom-forms' ) }
					checked={ !! showLabel }
					onChange={ value => setAttributes( { showLabel: !! value } ) }
				/>

				{ showLabel && <TextControl
					label={ __( 'Label', 'wpzoom-forms' ) }
					value={ label }
					onChange={ value => setAttributes( { label: value } ) }
				/> }

				<ToggleControl
					label={ __( 'Required', 'wpzoom-forms' ) }
					checked={ !! required }
					onChange={ value => setAttributes( { required: !! value } ) }
				/>
			</PanelBody>
		</InspectorControls>

		<Fragment>
			{ showLabel && <label htmlFor={ uniqueId }>
				<RichText
					tagName="label"
					placeholder={ __( 'Label', 'wpzoom-forms' ) }
					value={ label }
					htmlFor={ uniqueId }
					onChange={ value => setAttributes( { label: value } ) }
				/>
				{ required && <sup className="wp-block-wpzoom-forms-required">{ __( '*', 'wpzoom-forms' ) }</sup> }
			</label> }

			<input
				data-datepicker="true"
				data-date-format={ 'custom_format' == format ? customFormat : format }
				data-mode={ mode }
				type="text"
				name={ uniqueId }
				id={ uniqueId }
				placeholder={ 'custom_format' == format ? customFormat : format }
				required={ !! required }
				{ ...blockProps }
			/>
		</Fragment>
	</>;
};

export default Edit;