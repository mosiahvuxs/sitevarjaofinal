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
			$('.item-menu-atuacao a').css({
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
			$('.item-menu-atuacao a').css({
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
			$('.submenu-atuacao').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-quem-somos').hide();
			$('.submenu-clientes').hide();
			$('.item-menu-atuacao a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
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
			$('.submenu-atuacao').show();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-quem-somos').hide();
			$('.submenu-clientes').hide();
			$('.item-menu-atuacao a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			});
		});