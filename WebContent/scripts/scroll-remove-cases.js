// JavaScript Document

$(document).ready(function(){
	$('html, body').on('touchstart touchmove', function(e){ 
		 $('.texto-case').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
	});
});