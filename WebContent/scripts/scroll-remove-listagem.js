// JavaScript Document

$(document).ready(function(){
	$('html, body').on('touchstart touchmove', function(e){ 
		 $('.clipping-listagem-wrapper').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
	});
});