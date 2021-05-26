<?php
session_start();
/* Attempt MySQL server connection. Assuming you are running MySQL
  server with default setting (user 'root' with no password) */
include("bbddCon.php");
// Escape user inputs for security
$nameuser = mysqli_real_escape_string($conn, $_POST['nameuser']);
$contrasenya = mysqli_real_escape_string($conn, $_POST['contrasenya']);

if (isset($nameuser) || isset($contrasenya)) {
// attempt insert query execution

        $salt = 'SHIFLETT';
        $password_hash = hash('sha256', $salt . hash('sha256', $contrasenya . $salt));

        $contrasenya_bbdd = "SELECT password FROM students WHERE name = $nameuser ";
        $result = mysqli_query($conn, $contrasenya_bbdd);
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                if (strcmp($password_hash, $row["password"]) == 0) {
                    $_SESSION['nombreuser'] = mysqli_real_escape_string($conn, $_POST['nameuser']);
                    header('Location: ../tablas.php');
                } else {
                    echo "<script> alert('ERROR de contraseña');
                            window.location= '../index.php'
                          </script>";
                    break;
                }
            }
        } else {
               
             echo "<script> alert('ERROR usuario no encontrado');
                            window.location= '../index.php'
                          </script>";
        }       
    
}else {
        echo "Faltan campos por rellenar1";

    }
// close connection
mysqli_close($conn);
?>