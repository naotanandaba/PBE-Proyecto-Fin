<?php
session_start();
?>
<!DOCTYPE html>


<html>
    <head>
        <title>PBE</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="estilo/estilo.css">
        <link rel="shortcut icon" href="estilo/logo.png">
    </head>
    <body>
        <img src="estilo/titulo.png" alt="titulo" height="82"> <img src="estilo/logo.png" alt="logo" height="82">
        <hr>
        <div class="container">
            <section class="row">
                <form role="form" id="register" action="php/indexphp.php" method="post">

                    <div class="form-group">
                        <label for="nameuser">Nombre Usuario:</label>
                        <input type="text" name="nameuser" required="true" id="nameuser" class="form-control" placeholder="Nombre de usuario">
                    </div>

                    <div class="form-group">
                        <label for="contrasenya1" >Contraseña Usuario:</label>
                        <input name='contrasenya' type="password" required="true" class="form-control" id="contrasenya" placeholder="Contraseña">
                    </div>

                    <input type="submit" name="inicia" value="Iniciar Sesión!" class="btn btn-success" >

                </form>
                <input type="submit" id="loginRegister" value="Registrate!" class="btn btn-primary btn-lg" onClick="location.href = 'http://localhost/Proyecto/registro.php'"/>

        </div> 
    </body>
</html>

