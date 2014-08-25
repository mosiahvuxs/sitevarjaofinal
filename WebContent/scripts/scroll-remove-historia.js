// JavaScript Document

$(document).ready(function(){
	$('html, body').on('touchstart touchmove', function(e){ 
		 $('.texto-apresentacao-historia').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
	});
});