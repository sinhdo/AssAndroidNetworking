<?php


$response = array();

$servername = "localhost";
$username = "id20965563_wp_4f61346535125ed80067cdb68874fee7";
$password = "Sinhdc123@";
$dbname = "id20965563_wp_4f61346535125ed80067cdb68874fee7";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// check for required fields
if (isset($_POST['id']) && isset($_POST['orderf'])) {

    $id = $_POST['id'];
    $orderf = $_POST['orderf'];
   

    
    $sql = "UPDATE foods SET orderf = '$orderf' WHERE id = $id";

    if ($conn->query($sql) === TRUE) {
      //echo "Record updated successfully";
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {
      echo "Error updating record: " . $conn->error;
    }

    $conn->close();

} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
