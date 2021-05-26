<?php

session_start();

/* Attempt MySQL server connection. Assuming you are running MySQL
  server with default setting (user 'root' with no password) */
include 'bbddCon.php';
// Escape user inputs for security
$nombreuser = mysqli_real_escape_string($conn, $_POST['nombreuser']);
$contrasenya1 = mysqli_real_escape_string($conn, $_POST['contrasenya1']);
$contrasenya2 = mysqli_real_escape_string($conn, $_POST['contrasenya2']);

if (strcmp($contrasenya1, $contrasenya2) == 0) {
    if (isset($nombreuser) && isset($contrasenya1) && isset($contrasenya2)) {
// attempt insert query execution
        $name_bbdd = "SELECT name FROM students WHERE name = $nombreuser";

        if(!mysqli_query($conn, $name_bbdd)){
        $salt = 'SHIFLETT';
        $password_hash = hash('sha256', $salt . hash('sha256', $contrasenya1 . $salt));
        echo $password_hash;
        $sql = "INSERT INTO students (id_student, name, password) VALUES ('', '$nombreuser',  '$password_hash')";
            if (mysqli_query($conn, $sql)) {
                $_SESSION['nombreuser'] = $nombreuser;
                header('Location: ../tablas.php');
            } else {
                echo "ERROR: Could not able to execute $sql. " . mysqli_error($conn);
            }
        }else{
            echo "<script> alert('ERROR usuario existente');
                            window.location= '../registro.php'
                          </script>";
        }

    } else {
        echo "Faltan campos por rellenar";
    }
} else {
    echo "<script> alert('ERROR en las contraseña');
                                window.location= '../registro.php'
                            </script>";
}
// close connection
mysqli_close($conn);
?>