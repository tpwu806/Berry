function highliteMenu(mod, func) {

	if (mod === 'm1') {
		$('#mod1').addClass("active");
	} else if (mod === 'm2') {
		$('#mod2').addClass("active");
	} else if (mod === 'm3') {
	  $('#mod3').addClass("active");
	} else if (mod === 'm4') {
	  $('#mod4').addClass("active");
	}
	  		
	
	if (func === 'f1') {
		$('#fxn1').addClass("active");
	} else if (func === 'f2') {
	  	$('#fxn2').addClass("active");
	} else if (func === 'f3') {
	  	$('#fxn3').addClass("active");
	} else if (func === 'f4') {
	  	$('#fxn4').addClass("active");
	}
}
  		
  		
	