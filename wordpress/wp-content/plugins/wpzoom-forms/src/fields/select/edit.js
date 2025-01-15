import isEmpty from 'lodash/isEmpty';
import { useState, useEffect } from '@wordpress/element';
import { useBlockProps, InspectorControls, RichText } from '@wordpress/block-editor';
import { Fragment } from '@wordpress/element';
import { __, sprintf } from '@wordpress/i18n';
import { 
	PanelBody, 
	TextControl, 
	TextareaControl,
	ToggleControl, 
	SelectControl, 
	Card, 
	CardBody, 
	CardHeader, 
	Button, 
	Flex, 
	FlexBlock, 
	FlexItem,
	Modal,
} from '@wordpress/components';

import { SortableContainer, SortableElement, SortableHandle } from 'react-sortable-hoc';
import { arrayMoveImmutable } from 'array-move';

// Import the lists from the lists.js file
import { countries, states } from './lists';

const DragHandle = SortableHandle( () => <Button
	icon="move"
	label={ __( 'Re-arrange Item', 'wpzoom-forms' ) }
	className="wpzoom-forms-move-button"
/> );

const SortableItem = SortableElement( ( { value, optsId, options, changeCallback, removeCallback } ) => <Fragment>
	<Flex>
		<FlexBlock>
			<TextControl
				value={ value }
				onChange={ val => changeCallback( val, optsId ) }
			/>
		</FlexBlock>

		{ options.length > 1 && <FlexItem>
			<DragHandle />

			<Button
				icon="no-alt"
				label={ __( 'Delete Item', 'wpzoom-forms' ) }
				onClick={ () => removeCallback( optsId ) }
			/>
		</FlexItem> }
	</Flex>
</Fragment> );

const SortableList = SortableContainer( ( { items, changeCallback, removeCallback } ) => <div>
	{ items.map( ( value, index ) => <SortableItem
		index={ index }
		optsId={ index }
		value={ value }
		options={ items }
		changeCallback={ changeCallback }
		removeCallback={ removeCallback }
	/> ) }
</div> );

const Edit = props => {
	const blockProps = useBlockProps();
	const { attributes, setAttributes, clientId } = props;
	const { id, name, options, defaultValue, label, showLabel, multiple, required } = attributes;

	const [isOpen, setOpen] = useState(false);
	const [bulkOptions, setBulkOptions] = useState('');

	const openModal = () => setOpen(true);
	const closeModal = () => setOpen(false);

	const optionAdd = () => {
		const opts = [...options];
		opts.push(sprintf(__('Item #%s', 'wpzoom-forms'), options.length + 1));
		setAttributes({ options: opts });
	};

	const optionRemove = (index) => {
		const opts = [...options];
		opts.splice(index, 1);
		setAttributes({ options: opts });
	};

	const optionChange = (name, index) => {
		const opts = [...options];
		opts[index] = name;
		setAttributes({ options: opts });
	};

	const optionsSort = (oldIndex, newIndex) => {
		const sorted = arrayMoveImmutable(options, oldIndex, newIndex);
		setAttributes({ options: sorted });
	};

	useEffect(() => {
		if (!id) {
			setAttributes({ id: 'input_' + clientId.substr(0, 8) });
		}
	}, []);

	useEffect(() => {
		if (options) {
			let _items = '';
			options.forEach((item) => {
				_items += item + '\n';
			});
			setBulkOptions(_items);
		}
	}, [options]);

	const onBulkAddItems = () => {
		const _options = bulkOptions.split('\n').filter(Boolean);
		setAttributes({ options: _options });
		setOpen(false);
	};

	const [ uniqueId ] = useState( id );

	const handleNameChange = newValue => {
		const newId = newValue.replace(/\s/g, '_').toLowerCase();
		setAttributes( { name: newValue, id: 'input_' + clientId.substr( 0, 8 ) } );		
	};

	return (
		<>
			<InspectorControls>
				<PanelBody title={__('Options', 'wpzoom-forms')}>
					<TextControl
						label={__('Name', 'wpzoom-forms')}
						value={name}
						placeholder={__('e.g. My Dropdown Select Field', 'wpzoom-forms')}
						onChange={ handleNameChange }
					/>

					<Card size="small">
						<CardHeader>
							{__('Items', 'wpzoom-forms')}
							<Button
								icon="insert"
								label={__('Add Item', 'wpzoom-forms')}
								onClick={optionAdd}
							/>
						</CardHeader>
						<CardBody>
							<SortableList
								items={options}
								changeCallback={optionChange}
								removeCallback={optionRemove}
								lockAxis="y"
								useDragHandle={true}
								onSortEnd={({ oldIndex, newIndex }) => optionsSort(oldIndex, newIndex)}
							/>
						</CardBody>
					</Card>

					<Button
						icon="admin-settings"
						label={__('Add Bulk Options', 'wpzoom-forms')}
						onClick={openModal}
					>
						{__('Add Bulk Options', 'wpzoom-forms')}
					</Button>

					{ isOpen && (
						<Modal
							title={__('Add Bulk Options', 'wpzoom-forms')}
							onRequestClose={closeModal}
							shouldCloseOnClickOutside={true}
						>
						<div className="wpzoom-forms-extra-options" style={{ maxWidth: 720 + 'px', maxHeight: 525 + 'px' } } >
							<div className="form-group">
								<div className="wrap-content">
									<TextareaControl
										label={__('Bulk Options', 'wpzoom-forms')}
										help={__('Each line break is a new option.', 'wpzoom-forms')}
										className="bulk-add-enter-options"
										rows="5"
										value={ bulkOptions }
										onChange={value => setBulkOptions(value)}
									/>
								</div>
							</div>
							<div className="form-group">
								<div class="predefined-lists">
									<p>{ __('Predefined Lists', 'wpzoom-forms') }
										<br />
										<Button isLink onClick={ () => setBulkOptions( countries.join('\n') ) }>{ __( 'Countries', 'wpzoom-forms' ) }</Button>
										<Button isLink onClick={ () => setBulkOptions( states.join('\n') ) }>{ __( 'US States', 'wpzoom-forms' ) }</Button>
									</p>
								</div>
								<div class="action-buttons">
								<Button isDefault onClick={() => setOpen(false)}>
									{ __('Cancel', 'recipe-card-blocks-by-wpzoom')}
								</Button>
								{ ! isEmpty( bulkOptions ) && (
									<Button isPrimary onClick={ () => { onBulkAddItems(); } }>
										{__('Bulk Add', 'recipe-card-blocks-by-wpzoom')}
									</Button>
								)}
								</div>
							</div>
						</div>
						</Modal>
					)}

					<SelectControl
						label={__('Default Value', 'wpzoom-forms')}
						value={defaultValue}
						options={options.map((option, index) => ({ label: option, value: option }))}
						onChange={value => setAttributes({ defaultValue: value })}
					/>

					<ToggleControl
						label={__('Show Label', 'wpzoom-forms')}
						checked={!!showLabel}
						onChange={value => setAttributes({ showLabel: !!value })}
					/>

					{showLabel && (
						<TextControl
							label={__('Label', 'wpzoom-forms')}
							value={label}
							onChange={value => setAttributes({ label: value })}
						/>
					)}

					<ToggleControl
						label={__('Allow Multiple Selections', 'wpzoom-forms')}
						checked={!!multiple}
						onChange={value => setAttributes({ multiple: !!value })}
					/>

					<ToggleControl
						label={__('Required', 'wpzoom-forms')}
						checked={!!required}
						onChange={value => setAttributes({ required: !!value })}
					/>
				</PanelBody>
			</InspectorControls>

			<Fragment>
				{showLabel && (
					<label htmlFor={ uniqueId }>
						<RichText
							tagName="label"
							placeholder={__('Label', 'wpzoom-forms')}
							value={label}
							htmlFor={ uniqueId }
							onChange={value => setAttributes({ label: value })}
						/>
						{required && <sup className="wp-block-wpzoom-forms-required">{__('*', 'wpzoom-forms')}</sup>}
					</label>
				)}

				<select
					name={ uniqueId }
					id={ uniqueId }
					required={!!required}
					multiple={!!multiple}
					defaultValue={defaultValue}
					{...blockProps}
				>
					{options.map((option, index) => (
						<option key={index} value={option}>
							{option}
						</option>
					))}
				</select>
			</Fragment>
		</>
	);
};

export default Edit;
