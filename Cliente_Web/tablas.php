<?php
session_start();
?>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>PBE</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="estilo/tabla.css">
        <link rel="shortcut icon" href="estilo/logo.png">
    </head>

    <body>

        <header>
            <img src="estilo/titulo.png" alt="titulo" height="82"> <img src="estilo/logo.png" alt="logo" height="82">
            <hr>

            <nav class = "navbar navbar-default" role = "navigation">
                <form role="form" id="tablas" action="tablas.php" method="post">

                    <div class="form-group">
                        <input placeholder= "Query!" type="text" name="query" required="true" id="query" class="form-control">
                    </div>

                    <input type="submit" id="save" value="Save!" class="btn btn-primary btn-lg" />
                    <input type="submit" id="logout" value="Log Out!" class="btn btn-primary btn-lg" onClick="location.href = 'http://localhost/Proyecto/index.php'"/>

                </form>
           </nav>
        </header>

        <div class="container">

            <?php
            include 'php/bbddCon.php';
            if(!empty($_POST['query'])){
                $query = mysqli_real_escape_string($conn, $_POST['query']);
                $query_separada = explode("?",$query);
                $tabla = $query_separada[0];
                if(!empty($query_separada[1])){
                    $query_comple = $query_separada[1];
                }else{
                    $query_comple=null;  
                }                

                if(strcmp($tabla, "marks")== 0){
                    ?>
                    <legend>MARKS:</legend>
                    <?php
                }else if(strcmp($tabla, "tasks")== 0){
                    ?>
                    <legend>TASKS:</legend>
                    <?php
                }else if(strcmp($tabla, "timetables")== 0){
                    ?>
                    <legend>TIMETABLES</legend>
                    <?php
                }else{
                    ?>
                    <h3>¡¡¡Query erronea!!!</h3>
                    <?php
                }
                                
                ?>
                <table class="table table-striped" >

                    <thead>
                        <tr>
                        <?php
                        
                            if(strcmp($tabla, "marks")== 0){
                                include 'php/marks/index.php';
                                $datos=tablamark($query_comple,$conn);
                                ?>
                                    <th>Subject</th>
                                    <th>Name</th>
                                    <th>Mark</th>                            
                                    <tr>
                                    <?php
                                foreach ($datos as $value) {                                
                                ?>
                                    <td><?php echo $value["subject"]?></td>
                                    <td><?php echo $value["name"]?></td>
                                    <td><?php echo $value["mark"]?></td>
                                    </tr>
                                    <?php
                                }
                            }else if(strcmp($tabla, "tasks")== 0){
                                include 'php/tasks/index.php';
                                $datos=tablatask($query_comple,$conn);
                                ?>
                                    <th>Date</th>
                                    <th>Subject</th>
                                    <th>Name</th>                            
                                    <tr>                                    
                                <?php
                                foreach ($datos as $value) {                                
                                ?>
                                    <td><?php echo $value["date"]?></td>
                                    <td><?php echo $value["subject"]?></td>
                                    <td><?php echo $value["name"]?></td>
                                    </tr>                                    
                                <?php
                                }   
                            }else if(strcmp($tabla, "timetables")== 0){
                                include 'php/timetables/index.php';
                                $datos=tablatimetable($query_comple,$conn);
                                ?>
                                    <th>Day</th>
                                    <th>Hour</th>
                                    <th>Subject</th>  
                                    <th>Room</th>                          
                                    <tr>
                                <?php
                                foreach ($datos as $value) {                                
                                ?>
                                    <td><?php echo $value["day"]?></td>
                                    <td><?php echo $value["hour"]?></td>
                                    <td><?php echo $value["subject"]?></td>
                                    <td><?php echo $value["room"]?></td>
                                    </tr>                                    
                                <?php
                                }
                            }
                            
                        mysqli_close($conn);
                        ?>
                        </tr>
                    </thead>  
                    <?php 
            }else{
                echo "Aún no ha introducido su Query";
            }
            ?>
            

        </div>
    </body>
</html>