
<?php
include "bbddCON.php";

session_start();

$nombreuser = mysqli_real_escape_string($conn, $_POST['nombreuser']);
$password1 = mysqli_real_escape_string($conn, $_POST['password1']);
$password2 = mysqli_real_escape_string($conn, $_POST['password2']);

/*$nombreuser = "xavi";
$password1 = "45367";
$password2 = "45367";*/

if(strcmp($password1,$password2) == 0){
    if (isset($nombreuser) && isset($password1) && isset($password2)) {
        $name_bbdd = "SELECT name FROM students WHERE name = '$nombreuser'";
        $result = mysqli_query($conn, $name_bbdd);
        if(!($result -> num_rows > 0)){
            $salt = 'SHIFLETT';
            $password_hash = hash('sha256', $salt . hash('sha256', $password1 . $salt));
            echo $password_hash;
            $sql = "INSERT INTO students (id_student, name, password) VALUES ('', '$nombreuser',  '$password_hash')";
            if (mysqli_query($conn, $sql)) {
                $_SESSION['nombreuser'] = $nombreuser;
                echo "Success";
            } else {
                echo "ERROR: Could not able to execute $sql. " . mysqli_error($conn);
            }
        }else{
            echo "user is already on the database";
        }

    } else {
        echo "missing fields";
    }
} else {
    echo "Password is not the same";
}
// close connection
mysqli_close($conn);
?>