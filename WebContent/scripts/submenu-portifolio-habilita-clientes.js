// JavaScript Document

$(document).ready(function(){
		$('.item-menu-clientes').mouseenter(function(){
			$('.submenu-portifolio').hide();
			$('.sombra-wrapper-submenu').show();
			$('.submenu-clientes').show();
			$('.item-menu-clientes a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px',
				});
			$('.item-menu-portifolio a').css({
				'background-color': 'transparent',
				'color': '#636466', 
				});
			});
		$('.item-menu-clientes').mouseleave(function(){
			$('.submenu-portifolio').show();
			$('.submenu-clientes').hide();
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',  
				});
			$('.item-menu-portifolio a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px',
				});
			});
		$('.submenu-clientes').mouseenter(function(){
			$('.submenu-portifolio').hide();
			$('.item-menu-portifolio a').css({
				'background-color': 'transparent',
				'color': '#636466', 
				});
			$('.sombra-wrapper-submenu').show();
			$('.submenu-clientes').show();
			$('.item-menu-clientes a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px',
				});
		});
		$('.submenu-clientes').mouseleave(function(){
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.item-menu-portifolio a').css({
				'background-color': '#0c5d9e',
				'padding': '10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			$('.submenu-clientes').hide();
			$('.item-menu-clientes a').css({
				'background-color': 'transparent',
				'color': '#636466',  
				});
			$('.submenu-portifolio').show();			
			});
		});