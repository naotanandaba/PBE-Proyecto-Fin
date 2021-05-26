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
                <form role="form" id="register" action="php/registrophp.php" method="post">
                    <div class="form-group">
                        <label for="nombreuser">Nombre Usuario:</label>
                        <input type="text" name="nombreuser" required="true" id="nombreuser" class="form-control" placeholder="Nombre de usuario">
                    </div>

                    <div class="form-group">
                        <label for="contrasenya1">Contraseña Usuario:</label>
                        <input type="password" class="form-control" name="contrasenya1" required="true" id="contrasenya1" placeholder="Contraseña">
                    </div>
                    
                     <div class="form-group">
                        <label for="contrasenya2">Vuelva a introducir la Contraseña:</label>
                        <input type="password" class="form-control" name="contrasenya2" required="true" id="contrasenya2" placeholder="Contraseña">
                    </div>
                   
                    <input type="submit" value="Registrame!" class="btn btn-success" >
                    
                </form>
        </div> 
    </body>
</html>

