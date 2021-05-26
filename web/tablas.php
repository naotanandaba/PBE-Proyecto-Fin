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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/maincss.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/flipBox.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="img/logo1.PNG" />
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>



        <header>
            <h1 >PBE</h1>
            <nav class = "navbar navbar-default" role = "navigation">
                <form role="form" id="tablas" action="tablas.php" method="post">
                   
                    <input type="submit" id="logout" value="Log Out!" class="btn btn-primary btn-lg" onClick="location.href = 'http://localhost/Proyecto/index.php'"/>
                    <input type="submit" id="save" value="Save!" class="btn btn-primary btn-lg" />

                    <div class="form-group">
                            <input placeholder= "Query!" type="text" name="query" required="true" id="query" class="form-control">
                    </div>
                </form>
           </nav>
            
        </header>
        <div class="container">

            <legend>Su Query:</legend>

            <?php
            include 'php/bbddCon.php';
            if(!empty($_POST['query'])){
                $query = mysqli_real_escape_string($conn, $_POST['query']);
                $query_separada = explode("?",$query);
                $tabla = $query_separada[0];
                ?>
                <table class="table table-striped" >

                    <thead>
                        <tr>
                        <?php
                        
                            if(strcmp($tabla, "marks")== 0){
                                include 'php/marks/index.php';
                                $datos = json_encode($data);
                                foreach ($data as $value) {
                                
                                ?>
                                    <th>Subject</th>
                                    <th>Name</th>
                                    <th>Mark</th>                            
                                    <tr>
                                        <td><?php echo $value["subject"]?></td>
                                        <td><?php echo $value["name"]?></td>
                                        <td><?php echo $value["mark"]?></td>
                                        
                                    </tr>
                                    <?php
                                }
                            }else if(strcmp($tabla, "tasks")== 0){
                                include 'php/tasks/index.php';
                                $datos = json_encode($data);
                                foreach ($data as $value) {
                                
                                ?>
                                    <th>Date</th>
                                    <th>Subject</th>
                                    <th>Name</th>                            
                                    <tr>
                                        <td><?php echo $value["date"]?></td>
                                        <td><?php echo $value["subject"]?></td>
                                        <td><?php echo $value["name"]?></td>
                                        
                                    </tr>
                                    
                                <?php
                                }   
                            }else if(strcmp($tabla, "timetables")== 0){
                                 include 'php/timetables/index.php';
                                $datos = json_encode($data);
                                foreach ($data as $value) {
                                
                                ?>
                                    <th>Day</th>
                                    <th>Hour</th>
                                    <th>Subject</th>  
                                    <th>Room</th>                          
                                    <tr>
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