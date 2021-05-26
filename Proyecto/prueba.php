<?php
session_start();
include 'php/bbddCon.php';
            
            $query = "tasks?name=werwq";
            $query_separada = explode("?",$query);
            $tabla = $query_separada[0];
            
            $_SESSION['nombreuser']="jose";
            $query_final = '?'.$query_separada[1];
        //    $url = "192.168.1.37/"+$query_final;
            //echo $url;
                if(strcmp($tabla, "marks")== 0){
                    include 'php/marks/index.php';
                    $datos=tablamark($query_separada[1],$conn);
                    
                    echo json_encode($datos);
                    echo "\r";
                    echo "3333333333333333";
                    echo "\r";
                }elseif(strcmp($tabla, "tasks") == 0){
                    include 'php/tasks/index.php';
                    $datos=tablatask($query_separada[1],$conn);
                    echo json_encode($datos);

                    echo "\r";
                    echo "555555555555555";
                    echo "\r";
                }else{
                    include 'php/timetables/index.php';
                    echo $query;
                    $datos=tablatimetable($query_separada[1],$conn);
                }
                
        mysqli_close($conn);
          
?> 

