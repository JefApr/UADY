var libro;

function Libro(titulo, autor, tema){
    this.titulo= titulo;
    this.autor= autor;
    this.tema=tema;
}

function comprar(nombre, autor, tema){
    libro= new Libro(nombre, autor, tema);
}

function ver(){
   alert(libro.titulo+" " +libro.autor+ " "+libro.tema);
}