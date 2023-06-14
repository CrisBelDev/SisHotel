$(window).on('load', function() {
	// funcion de loading 
	$(".loader").fadeOut(); 
	$("#preloder").delay(400).fadeOut("slow");
	// efectos
    $('.primera_linea').fadeOut();
    $('.primera_linea').fadeToggle(3000);
    $('.segunda_linea').fadeOut();
    $('.segunda_linea').fadeToggle(4500);
    $('.contenedor-btns').fadeOut();
    $('.contenedor-btns').fadeToggle(5500);
    
});