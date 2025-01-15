/**
 * Plugin functions file
 */
(function ($) {
    'use strict';

    var $datepickerInputs = $( 'input[data-datepicker="true"]' );
    $datepickerInputs.each( function() {
        
        var $this = $( this );
        var dateFormat = $this.data( 'date-format' ) || 'Y-m-d';
        var mode = $this.data( 'mode' ) || 'single';
        
        $this.flatpickr({
            'dateFormat': dateFormat,
            'allowInput': true,
            'mode': mode,
        } );

    });

})(jQuery);