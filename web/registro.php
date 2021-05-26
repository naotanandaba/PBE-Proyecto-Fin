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
                <form role="form" id="register" action="php/registrophp.php" method="post">
                    <div class="form-group">
                        <label for="nombreuser">Nombre Usuario:</label>
                        <input type="text" name="nombreuser" required="true" id="nombreuser" class="form-control">
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

