$(document).ready(function(){
	$('.pwd').on('click',function(){
		$('#password').toggleClass('active');
		if($('#password').hasClass('active')){
			$(this).attr('class',"fa fa-eye fa-lg");
			$('#password').attr('type',"text");
		}else{
			$(this).attr('class',"fa fa-eye-slash fa-lg");
			$('#password').attr('type',"password");
		}
	});
	
	$('.pwd-confirm').on('click',function(){
		$('#pwd-confirm').toggleClass('active');
		if($('#pwd-confirm').hasClass('active')){
			$(this).attr('class',"fa fa-eye fa-lg");
			$('#pwd-confirm').attr('type',"text");
		}else{
			$(this).attr('class',"fa fa-eye-slash fa-lg");
			$('#pwd-confirm').attr('type',"password");
		}
	});
	
	
	
	
	
	
});
