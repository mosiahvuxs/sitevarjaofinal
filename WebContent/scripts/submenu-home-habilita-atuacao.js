// JavaScript Document

$(document).ready(function(){
		$('.item-menu-atuacao a').mouseenter(function(){
			$('.submenu-atuacao').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-clientes').hide();
			$('.submenu-home').hide();
			$('.item-menu-atuacao a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.item-menu-home a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			});			
		$('.submenu-atuacao').mouseenter(function(){
			$('.submenu-atuacao').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-clientes').hide();
			$('.submenu-home').hide();
			$('.item-menu-atuacao a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.item-menu-home a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			});
			
		$('.submenu-atuacao').mouseleave(function(){
			$('.item-menu-atuacao a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.item-menu-home a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.submenu-atuacao').hide();
			$('.sombra-wrapper-submenu').hide();
			$('.submenu-home').show();
			$('.sombra-wrapper-submenu').show();
			});
			
		$('.item-menu-atuacao a').mouseleave(function(){
			$('.item-menu-atuacao a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.item-menu-home a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.submenu-atuacao').hide();
			$('.sombra-wrapper-submenu').hide();
			$('.submenu-home').show();
			$('.sombra-wrapper-submenu').show();
			});
		});