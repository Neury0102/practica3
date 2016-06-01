<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante </title>
    <link href="/css/bootstrap.css" rel="stylesheet" >
</head>
<body>
<div class="container">
    <#include "/header.ftl">
</div>

<div class="container">
    <#list articulos as articulo>
        <div class="col-lg-8">
            <h4>${articulo.titulo}</h4>
            <p>${articulo.fecha}</p>
            <p>${articulo.cuerpo} </p>
            <hr>
        </div>
    </#list>

</div>


    <!-- /.row -->






</body>
</html>