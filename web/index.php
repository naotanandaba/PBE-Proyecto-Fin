<?php
session_start();
?>
<!DOCTYPE html>


<html>
    <head>
        <title>PBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="css/maincss.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" media="screen" href="https://fontlibrary.org/face/sniglet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.min.css" />
        <link rel="shortcut icon" href="img/logo1.PNG" />
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href='https://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <header>
            <h2 id="titulo">PBE</h2>
            <hr>
        </header>
        <div class="container">
            <section class="row">
                <form role="form" id="register" action="php/indexphp.php" method="post">

                    <div class="form-group">
                        <label for="mailuser">Nombre Usuario:</label>
                        <input type="text" name="nameuser" required="true" id="nameuser" class="form-control">
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

