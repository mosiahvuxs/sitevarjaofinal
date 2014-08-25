// JavaScript Document

$(document).ready(function(){
	$('.seta-case-1').click(function(){
		$('.content-cases').hide();
		$('.ver-case-1').show();
	});
	$('.seta-case-2').click(function(){
		$('.content-cases').hide();
		$('.ver-case-2').show();
	});
	$('.seta-case-3').click(function(){
		$('.content-cases').hide();
		$('.ver-case-3').show();
	});
	$('.seta-case-voltar').click(function(){
		$('.ver-case-1').hide();
		$('.ver-case-2').hide();
		$('.ver-case-3').hide();
		$('.content-cases').show();
	});
});