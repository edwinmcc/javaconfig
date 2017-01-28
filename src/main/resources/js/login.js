
$(document).ready(function() {
	$('#txtEmail').focus(function() {
		var textValue=$('#txtEmail').val().trim();
		if(textValue.length===0)
		{
			$('#txtEmail').val('Enter your email-id');
		}
	});
	
	$('#txtEmail').click(function() {
		var textValue=$('#txtEmail').val().trim();
		//console.log("I am inside OnClick");
		if(textValue==='Enter your email-id')
		{
			$('#txtEmail').val('');
		}
	});
	
	function callback() {
      setTimeout(function() {
        $( "#txtEmail" ).removeAttr( "style" ).hide().fadeIn();
      }, 1000 );
    };
	    
	$('#user').submit(function(event) {
		console.log("submit button clicked");
		var textValue=$('#txtEmail').val().trim();
		if(textValue.match(/(\w|_){1,}@(\w|_){1,}\.(\w){1,}/) === null)
		{
			//console.log("Please enter a valid email-id");
			event.preventDefault();
			$('#txtEmail').effect('shake');
			console.log("Please enter a valid email-id");
		    $( "#txtEmail" ).focus();
		}
	});    
	
});