/**
 * 
 */
    	

        function toggleChevron(e) {
		$(e.target)
				.prev('.panel-heading')
				.find("i.indicator")
				.toggleClass('fa-caret-down fa-caret-right');
	}
	$('#accordion').on('hidden.bs.collapse', toggleChevron);
	$('#accordion').on('shown.bs.collapse', toggleChevron);
	$(document).ready(function() {
	    $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
	});