function Imprimir() {
	texto= "<B>Clave:</B> " + this.Clave + "<BR>"
	texto += "<B>Descripción:</B> " + this.Descripcion + "<BR>"
	texto += "<B>Precio:</B> $" + this.Precio + ".00<BR>"
	return texto
}

function VerImagen() {
	return "<IMG SRC=\"" + this.Ruta + "\" WIDTH=150 HEGHT=150 ALT=\"Fotografía\" BORDER=1>"
}

function Total(Cantidad) {
	return this.Precio * Number(Cantidad)
}

function Producto(Clave,Descripcion,Precio,Ruta,Imagen) {
	this.Clave = Clave
	this.Descripcion = Descripcion
	this.Precio = Precio
	this.Ruta = Ruta
	this.Imagen = Imagen
	this.Imprimir = Imprimir
	this.Total = Total
	this.VerImagen = VerImagen
}

Planta1 = new Image(150,150)
Planta1.src = "figuras/catalogo/planta1.gif"
Planta2 = new Image(150,150)
Planta2.src = "figuras/catalogo/planta2.gif"
Planta3 = new Image(150,150)
Planta3.src = "figuras/catalogo/planta3.gif"
Planta4 = new Image(150,150)
Planta4.src = "figuras/catalogo/planta4.gif"
Planta5 = new Image(150,150)
Planta5.src = "figuras/catalogo/planta5.gif"
Planta6 = new Image(150,150)
Planta6.src = "figuras/catalogo/planta6.gif"

Productos = new Array()
Productos[0] = new Producto("0001","Anémona",3,"figuras/catalogo/planta1.gif",Planta1)
Productos[1] = new Producto("0002","Jacinto",5,"figuras/catalogo/planta2.gif",Planta2)
Productos[2] = new Producto("0003","Clematis",50,"figuras/catalogo/planta3.gif",Planta3)
Productos[3] = new Producto("0004","Maceta italiana",100,"figuras/catalogo/planta4.gif",Planta4)
Productos[4] = new Producto("0005","Iris",15,"figuras/catalogo/planta5.gif",Planta5)
Productos[5] = new Producto("0006","Tulipán",10,"figuras/catalogo/planta6.gif",Planta6)

function Mensaje(Texto) {
	window.status = Texto
}

function CambiarProducto(indice) {
	if(isNaN(Number(compras.cantidad.value))) {
		compras.cantidad.value = 0
	}	
	document["planta"].src = Productos[indice].Imagen.src
	compras.precio.value = Productos[indice].Precio
	compras.total.value = Productos[indice].Total(compras.cantidad.value)
}

function CalcularTotal() {
	if(isNaN(Number(compras.cantidad.value))) {
		alert("La cantidad debe ser un número entero mayor que cero")
		compras.cantidad.value = 0
		compras.cantidad.focus()
	}	
	compras.total.value = compras.cantidad.value * compras.precio.value
}

function DatosCompletos() {
	if (usuario.nombre.value == "") {
		alert("Debe escribir su nombre completo.")
		usuario.nombre.focus()
		return false
	}
	if (usuario.login.value == "") {
		alert("Debe escribir su nombre de usuario.")
		usuario.login.focus()
		return false
	}
	if (usuario.clave1.value == "") {
		alert("Debe escribir su clave de acceso.")
		usuario.clave1.value = ""
		usuario.clave2.value = ""
		usuario.clave1.focus()
		return false
	}
	if (usuario.clave1.value != usuario.clave2.value) {
		alert("Las claves de acceso deben ser iguales.")
		usuario.clave1.value = ""
		usuario.clave2.value = ""
		usuario.clave1.focus()
		return false
	}
}

function CrearMensaje(titulo, mensaje) {
	dialogo = open("","mensaje","toolbar=no,directories=no,menubar=0,width=150,height=150")
	dialogo.document.open()
	dialogo.document.writeln("<HTML><HEAD><TITLE>" + titulo + "</TITLE></HEAD>")
	dialogo.document.writeln("<BODY onLoad='timerID=setTimeout(\"window.close()\",5000)'>")
	dialogo.document.writeln("<FONT FACE='Arial'>" + mensaje + "</FONT><P><CENTER><INPUT TYPE=BUTTON VALUE='Cerrar' onClick='self.close()'></CENTER></BODY></HTML>")
	dialogo.document.close()
	dialogo.focus();
}

function VerProducto(indice) {
	catalogo = open("","catalogo","toolbar=no,directories=no,menubar=0,width=400,height=200")
	catalogo.document.open()
	catalogo.document.writeln("<HTML><HEAD><TITLE>" + Productos[indice].Descripcion + "</TITLE></HEAD>")
	catalogo.document.writeln("<BODY><TABLE ALIGN=CENTER><TR><TD><FONT FACE='Arial'>")
	catalogo.document.writeln(Productos[indice].Imprimir())
	catalogo.document.writeln("</FONT></TD><TD>")
	catalogo.document.writeln(Productos[indice].VerImagen())
	catalogo.document.writeln("</TD></TR></TABLE></BODY></HTML>")
	catalogo.document.close()
	catalogo.focus();	
}

function GetCookie (name, InCookie) {
	var prop = name + "=";
	var plen = prop.length;
	var clen = InCookie.length;
	var i=0;
	if (clen>0) { 
		i = InCookie.indexOf(prop,0);
		if (i!=-1) { 
			j = InCookie.indexOf(";",i+plen);
			if(j!=-1)
				return unescape(InCookie.substring(i+plen,j));
			else
				return unescape(InCookie.substring(i+plen,clen));
		}
		else
			return "";
	}
	else
		return "";
}

function SetCookie (name, value) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null
	var path = (argc > 3) ? argv[3] : null
	var domain = (argc > 4) ? argv[4] : null
	var secure = (argc > 5) ? argv[5] : false
	document.cookie = name + "=" + escape(value) +
		((expires==null) ? "" : ("; expires=" + expires.toGMTString())) +
		((path==null) ? "" : (";path=" + path)) +
		((domain==null) ? "" : ("; domain=" + domain)) +
		((secure==true) ? "; secure" : "");
}

function Agregar(indice,cantidad){
	productos = GetCookie("productos",document.cookie)
	if (productos == "") productos = 0
	productos = Number(productos) + 1
	SetCookie("productos",productos)
	SetCookie("prod" + productos,indice)
	SetCookie("cant" + productos,cantidad)
}

function Vaciar() {
	SetCookie("productos", 0)
}

function Pagar(){
	var total = 0
	productos = GetCookie("productos",document.cookie)
	if (productos == "")
		alert("La canasta de compras está vacía")
	else{
		lista = open("","pago","scrollbars=1,width=300,height=200");
		lista.document.open();
		lista.document.writeln("<HTML><HEAD><TITLE>Canasta de Compras</TITLE></HEAD<BODY><FONT FACE='Arial'><P>")
		productos = Number(productos)
		for(i = 1; i <= productos; i ++){
			indice = Number(GetCookie("prod" + i, document.cookie))
			cantidad = Number((GetCookie("cant" + i, document.cookie)))
			lista.document.write(Productos[indice].Imprimir())
			lista.document.write("<B>Cantidad:</B> " + cantidad + "<BR>")
			lista.document.writeln("<B>SubTotal:</B> $" + Productos[indice].Total(cantidad) + ".00<P><HR>")				
			total = total + Productos[indice].Total(cantidad)
		}
		lista.document.writeln("<P><B>Total: $</B>"+total+".00<P></BODY></HTML>")
		lista.document.close();
		lista.focus();
	}
}

