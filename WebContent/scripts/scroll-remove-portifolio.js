// JavaScript Document

$(document).ready(function(){
	$('html, body').on('touchstart touchmove', function(e){ 
		 $('.content-page-portifolio').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
	});
});