// JavaScript Document
$(document).ready(function(){

	$('.imagem-clipping img').mouseover(function(){
		$('.tarja-img').show();
	});
	
	$('.tarja-img').mouseover(function(){
		$(this).show();
	});
	
	$('.imagem-clipping img').mouseout(function(){
		$('.tarja-img').hide();
	});
	
	$('.tarja-img').mouseout(function(){
		$(this).hide();
	});
});