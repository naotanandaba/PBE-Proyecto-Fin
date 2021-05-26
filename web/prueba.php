<?php

include 'php/bbddCon.php';
            
            $query = "tasks?subject=PBE";
            $query_separada = explode("?",$query);
            $tabla = $query_separada[0];
            echo $tabla;
            $_SESSION['nombreuser']="jose";

           
                if(strcmp($tabla, "marks")== 0){
                    include 'php/marks/index.php';
                    $datos=$data;
                    echo json_encode($datos);
                    echo "\r";
                    echo "3333333333333333";
                    echo "\r";
                }elseif(strcmp($tabla, "tasks") == 0){
                    echo "entro";
                    include 'php/tasks/index.php';
                    $datos=$data;
                    echo json_encode($datos);
                    echo "\r";
                    echo "555555555555555";
                    echo "\r";
                }else{
                    include 'php/timetables/index.php';
                    $datos=$data;
                }
                
        mysqli_close($conn);
          
?> 

