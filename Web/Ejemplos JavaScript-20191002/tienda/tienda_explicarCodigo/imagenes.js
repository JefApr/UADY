catalogo1 = new Image(95,39);
catalogo1.src="figuras/catalogo.gif";
catalogo2 = new Image(95,39);
catalogo2.src="figuras/catalogoa.gif";
catalogo3 = new Image(122,122);
catalogo3.src="figuras/portada7a.gif";
catalogom = "Catálogo en línea";

compras1 = new Image(95,40);
compras1.src="figuras/compras.gif";
compras2 = new Image(95,40);
compras2.src="figuras/comprasa.gif";
compras3 = new Image(122,122);
compras3.src="figuras/portada7b.gif";
comprasm = "Compra nuestros productos en línea";

personal1 = new Image(95,43);
personal1.src="figuras/personal.gif";
personal2 = new Image(95,43);
personal2.src="figuras/personala.gif";
personal3 = new Image(122,122);
personal3.src="figuras/portada7c.gif";
personalm = "Información sobre nuestro personal";

ninguno = new Image(122,122);
ninguno.src="figuras/portada7.gif";

function ActivarEnlace(nombre){
	imagen = eval(nombre +"2.src");
	document[nombre].src = imagen;

	foto = eval(nombre +"3.src");
	document["circulo"].src = foto;
	window.status = eval(nombre+"m");
}

function DesactivarEnlace(nombre){
	imagen = eval(nombre +"1.src");
	document[nombre].src = imagen;
	
	document["circulo"].src = ninguno.src;
	window.status = "";
}