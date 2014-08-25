// JavaScript Document

$(document).ready(function(){
		$('.item-menu-quem-somos').mouseenter(function(){
			$('.submenu-quem-somos').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-atuacao').hide();
			$('.submenu-clientes').hide();
			$('.item-menu-quem-somos a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			});
			
			$('.submenu-quem-somos').mouseenter(function(){
			$('.submenu-quem-somos').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-atuacao').hide();
			$('.submenu-clientes').hide();
			$('.item-menu-quem-somos a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			});
			
			$('.submenu-quem-somos').mouseleave(function(){
			$('.item-menu-quem-somos a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.submenu-quem-somos').hide();
			$('.sombra-wrapper-submenu').hide();
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
			
			$('.item-menu-quem-somos a').mouseleave(function(){
			$('.item-menu-quem-somos a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.submenu-quem-somos').hide();
			$('.sombra-wrapper-submenu').hide();
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
		});