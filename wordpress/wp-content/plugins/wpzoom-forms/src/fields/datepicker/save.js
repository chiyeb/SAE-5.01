
import { useBlockProps, RichText } from '@wordpress/block-editor';
import { __ } from '@wordpress/i18n';

const Save = ( { attributes } ) => {
	const blockProps = useBlockProps.save();
	const { id, name, mode, format, customFormat, label, showLabel, required } = attributes;

	return <>
		{ showLabel && <label htmlFor={ id }>
			<RichText.Content
				tagName="label"
				value={ label }
				htmlFor={ id }
			/>
			{ required && <sup className="wp-block-wpzoom-forms-required">{ __( '*', 'wpzoom-forms' ) }</sup> }
		</label> }

		<input
			data-datepicker="true"
			data-date-format={ 'custom_format' == format ? customFormat : format }
			data-mode={ mode }
			type="text"
			name={ id }
			id={ id }
			placeholder={ 'custom_format' == format ? customFormat : format }
			required={ !! required }
			{ ...blockProps }
		/>
	</>;
};

export default Save;