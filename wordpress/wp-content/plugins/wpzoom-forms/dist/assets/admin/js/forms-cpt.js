( function( window, wp ){
    var unsubscribe = wp.data.subscribe( function () { 

        const labels = document.querySelectorAll('.wpzoom-forms_form label label');
        labels.forEach(label => {
            // Remove all attributes from the label
            Array.from(label.attributes).forEach(attr => {
                label.removeAttribute(attr.name);
            });
        });
    } );
} )( window, wp );