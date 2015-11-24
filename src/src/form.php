<?php

echo 'Consulta recibida';

$metodo = "";

$dato1 = isset($_GET['dato1']) ? ($_GET['dato1']) && $metodo="GET" : ($_POST['dato1']) && $metodo="POST";
    
$dato2 = isset($_GET['dato2']) ? ($_GET['dato2']) && $metodo="GET" : ($_POST['dato2']) && $metodo="POST";
    
echo "Se recibio el dato 1 como " + $dato1 + " y el dato 2 como " + $dato2 + " por " + $metodo;
