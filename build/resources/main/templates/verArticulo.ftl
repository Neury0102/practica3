<!DOCTYPE html>
<html lang="en">

<head>
    <link href="/css/bootstrap.css" rel="stylesheet">
</head>

<body>

<#include "/header.ftl">

<div class="container">

    <div class="row">
        <div class="col-lg-8">
            <h1>${articulo.titulo}</h1>

            <p class="lead">
                por ${articulo.autor.nombre}
            </p>

            <p> ${articulo.fecha}</p>
            <hr>
            <p>${articulo.cuerpo}</p>
            <hr>

            <div class="well">
                <h4>¿Quieres decir algo?</h4>
                <form role="form">
                    <div class="form-group">
                        <textarea class="form-control" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Dejar comentario</button>
                </form>
            </div>
            <hr>
            <!-- Posted Comments -->
            <#list articulo.comentarios as comentario>
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading">${comentario.autor.nombre}</h4>
                        ${comentario.comentario}
                    </div>
                </div>
                <hr>
            </#list>
        </div>
    </div>
    <!-- /.row -->





</body>

</html>
