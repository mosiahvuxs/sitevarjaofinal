// JavaScript Document

$(document).ready(function(){
	$('html, body').on('touchstart touchmove', function(e){ 
		 $('.texto-descricao').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
		 $('.texto-servicos').css({
			 'overflow': 'hidden',
			 'height': 'auto',
		 });
	});
});