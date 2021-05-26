<?php
session_start();
/* Attempt MySQL server connection. Assuming you are running MySQL
  server with default setting (user 'root' with no password) */
include("bbddCon.php");
// Escape user inputs for security


parse_str(rawurldecode($_SERVER['QUERY_STRING']), $constraints);
if ($constraints['nameuser'] && isset($constraints['contrasenya'])) {

// attempt insert query execution

        $salt = 'SHIFLETT';
        $password_hash = hash('sha256', $salt . hash('sha256', $constraints['contrasenya'] . $salt));

        $contrasenya_bbdd = "SELECT * FROM students WHERE name LIKE " . '"'. $constraints['nameuser'] .'"';

        $result = mysqli_query($conn, $contrasenya_bbdd);
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc() ) {
                if (strcmp($password_hash, $row["password"]) == 0) {
                    $data[] = $row;

                } else {
                    echo $password_hash . "  comparar         ";
                    echo $row["password"];
                }
            }
            echo json_encode ($data);
        } else {

             echo "err";
        }
}else {
        echo "Faltan campos por rellenar1";

    }
// close connection
mysqli_close($conn);
?>