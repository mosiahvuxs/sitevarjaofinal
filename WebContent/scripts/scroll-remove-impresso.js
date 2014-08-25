// JavaScript Document

$(document).ready(function(){
	$('html, body').on('touchstart touchmove', function(e){ 
		 $('.content-clipping-impresso').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
	});
});