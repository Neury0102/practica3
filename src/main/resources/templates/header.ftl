
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="">Pr&aacute;ctica 3</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="">Art&iacute;culos</a></li>
                <#if usuario.autor || usuario.administrador>
                    <li><a href="">Redactar</a></li>
                </#if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <#if usuario.administrador>
                        <li><a href="">Administraci&oacute;n</a></li>
                    </#if>
                    <#if usuario.autor || usuario.administrador>
                        <li><a href="">Cerrar Sesi&oacute;n</a></li>
                    <#else>
                        <li><a href="/login">Iniciar Sesi&oacute;n</a></li>
                    </#if>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div>

</body>
</html>