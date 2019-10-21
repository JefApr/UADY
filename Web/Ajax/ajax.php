<?php

// Ejemplo de llamada
// http://localhost/ajax.php?url=https://intranet.matematicas.uady.mx/enlinea2/

$cadena = file_get_contents($_REQUEST["url"]);
echo $cadena;

?>