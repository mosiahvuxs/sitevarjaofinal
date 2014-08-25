// JavaScript Document

$(document).ready(function(){
		$('.item-menu-clientes').mouseenter(function(){
			$('.submenu-clientes').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-atuacao').hide();
			$('.submenu-quem-somos').hide();
			$('.item-menu-clientes a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin-left': '-8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			});
			$('.submenu-clientes').mouseenter(function(){
			$('.submenu-clientes').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-atuacao').hide();
			$('.submenu-quem-somos').hide();
			$('.item-menu-clientes a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin-left': '-8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			});
			
			$('.submenu-clientes').mouseleave(function(){
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.submenu-clientes').hide();
			$('.sombra-wrapper-submenu').hide();
			});
			$('.item-menu-clientes a').mouseleave(function(){
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.submenu-clientes').hide();
			$('.sombra-wrapper-submenu').hide();
			});
		});