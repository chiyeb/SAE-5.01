jQuery(document).ready(function(){
	( function($) {

		// If there are no forms, show the modal
		if ( $('#the-list').find('.no-items').length > 0 ) {
			$('#wpzoom-forms-modal').show();
		}

		// Show modal on click of "Add New" button
		$('.wpzf_settings-add-new a, #menu-posts-wpzf-form a[href$="post-new.php?post_type=wpzf-form"]').on('click', function(e){
			e.preventDefault();
			$('#wpzoom-forms-modal').show();
		});

		// Add active class to the selected template
		$('.wpzoom-forms-templates-list li').on('click', function(e){
			e.preventDefault();
			$('.wpzoom-forms-templates-list li').removeClass('active');
			$(this).addClass('active');
		});

		let templates_list = $('.wpzoom-forms-templates-list'),
			templates = templates_list.children();

		templates.find('a').on('click', function(e){
			e.preventDefault();
			let template_id = $(this).data('template-id');
			if( template_id ){
				updateUrlAttribute( 'template', template_id );
			}
		});

		// Close modal
		$('#wpzoom_close_modal, .wpzoom-forms-modal-close').on('click', function(e){
			e.preventDefault();
			$('#wpzoom-forms-modal').hide();
		});

		$(document).keydown(function(evt) {
			evt = evt || window.event;
			var isEscape = false;
			if ("key" in evt) {
				isEscape = (evt.key === "Escape" || evt.key === "Esc");
			} else {
				isEscape = (evt.keyCode === 27);
			}
			if (isEscape) {
				$('#wpzoom-forms-modal').hide();
			}
		});

		// Get current URL
		var proceedButton = $('#wpzoom_proceed_template');
		var currentUrl = proceedButton.attr('href');

		// Function to update URL attribute
		function updateUrlAttribute( attributeName, attributeValue ) {
			
			var url = new URL( currentUrl );
			url.searchParams.set( attributeName, attributeValue );
			proceedButton.attr( 'href', url.toString() );
		
		}


	})(jQuery);
});