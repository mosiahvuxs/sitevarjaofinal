// JavaScript Document

$(document).ready(function(){
		$('.submenu').hide();
		$('.item-menu-submenu').mouseenter(function(){
			$('.submenu').show();
			$('.item-menu-submenu a').css({
				'background-color': '#0b5c9c',
				'padding': '10px 4px 10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px' 
				});
			$('.widget-header').css('margin-top', '10px');
			});
			$('.item-menu-submenu').mouseenter(function(){
			$('.item-menu-submenu a').css({
				'background-color': '#0b5c9c',
				'padding': '10px 4px 10px 4px',
				'color': 'white',
				'margin': '0px 0px 0px -8px',
				'border-radius': '10px 10px 0px 0px' 
				});
			});
			
			$('.submenu').mouseleave(function(){
			$('.submenu').hide();
			$('.item-menu-submenu').show();
			$('.item-menu-submenu a').css({
				'background-color': 'transparent',
				'color': '#636466',
				});
			$('.widget-header').css('margin-top', '-15px');	
			});
		});