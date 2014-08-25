// JavaScript Document

$(document).ready(function(){
	$('.texto-servicos').hide();
	$('.item-menu-box').click(function(){
		$('.item-menu-box').css('color', '#FFF');
		$('.item-menu-box-selected').css('color', '#00a1e1');
		$('.texto-descricao').hide();
		$('.texto-servicos').show();
	});
	
	$('.item-menu-box-selected').click(function(){
		$('.item-menu-box').css('color', '#00a1e1');
		$('.item-menu-box-selected').css('color', '#FFF');
		$('.texto-descricao').show();
		$('.texto-servicos').hide();
	});
});