jQuery(function($) {
	$('textarea').keyup(function() {
		var maxLength = 120;
		var length = $(this).val().length;
		var length = maxLength-length;
		$('.character-counter').text(length + "/" + maxLength);
	});
	
	$('textarea').focus(function() {
		$('.character-counter').show();
		
	});
	
	$('textarea').blur(function() {
		$('.character-counter').hide();
	});
});

jQuery(function($) {
	
//	$('.button-collapse').sideNav({
//	      menuWidth: 300, 
//	      edge: 'right',
//	      closeOnClick: true, 
//	      draggable: true, 
//	      onOpen: function(el) {},
//	      onClose: function(el) {},
//	  }
//	);
});

jQuery(function($) {
 $('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 15, // Creates a dropdown of 15 years to control year,
	    today: 'Today',
	    clear: 'Clear',
	    close: 'Ok',
	    closeOnSelect: false // Close upon selecting a date,
  });
});

jQuery(function($) {
	var slider = document.getElementById('test-slider');
	noUiSlider.create(slider, {
	 start: [20, 80],
	 connect: true,
	 step: 1,
	 orientation: 'horizontal', // 'horizontal' or 'vertical'
	 range: {
	   'min': 0,
	   'max': 100
	 },
	 format: wNumb({
	   decimals: 0
	 })
	});
});

