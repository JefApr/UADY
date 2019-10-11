// JavaScript Document

Figura = new Array();
Figura[0] = "imagenes/catalogo/planta1.gif";
Figura[1] = "imagenes/catalogo/planta2.gif";
Figura[2] = "imagenes/catalogo/planta3.gif";
Figura[3] = "imagenes/catalogo/planta4.gif";
Figura[4] = "imagenes/catalogo/planta5.gif";
Figura[5] = "imagenes/catalogo/planta6.gif";

Miniatura = new Array();
Miniatura[0] = "imagenes/miniatura/planta1.png";
Miniatura[1] = "imagenes/miniatura/planta2.png";
Miniatura[2] = "imagenes/miniatura/planta3.png";
Miniatura[3] = "imagenes/miniatura/planta4.png";
Miniatura[4] = "imagenes/miniatura/planta5.png";
Miniatura[5] = "imagenes/miniatura/planta6.png";

Descripcion = new Array();
Descripcion[0] = "Texto descriptivo de la planta1";
Descripcion[1] = "Texto descriptivo de la planta2";
Descripcion[2] = "Texto descriptivo de la planta3";
Descripcion[4] = "Texto descriptivo de la planta4";
Descripcion[5] = "Texto descriptivo de la planta5";
Descripcion[6] = "Texto descriptivo de la planta6";

function verCatalogo(indice) {
	
	ventana = window.open("","ventana", "width=350,height=250");
	ventana.document.open();
	ventana.document.writeln("<html><head><title>Descripci&oacute;n del art&iacute;culo " + indice + "</title></head><body>");
	ventana.document.writeln("<p><img src=\"" + Figura[indice] + "\" /></p>");
	ventana.document.writeln("<p>Descripci&oacute;n: " + Descripcion[indice] + "</p>");	
	ventana.document.writeln("</body></html>");	
	ventana.document.close();
	ventana.focus();
	
}


function generarCatalogo(fila, columna) {
	
	indice = 0;	
	
	document.open();
	document.writeln("<table align='center'>");
	for(i=0; i<fila; i++) {
		document.writeln("<tr>");
		for(j=0; j<columna; j++) {
			document.writeln("<td>");
			document.writeln("<a href=\"#\" onclick=\"verCatalogo(" + indice + ")\"><img src=\"" + Miniatura[indice] + "\" /></a>");			
			document.writeln("</td>");
			indice++;
			
			if (indice/columna == 0 ) break;
		}
		document.writeln("</tr>");
	}
	document.writeln("</table>");
	document.close();
}