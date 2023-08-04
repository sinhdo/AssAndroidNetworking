<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
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
if (isset($_POST['img']) && isset($_POST['nameFoods']) && isset($_POST['price']) && isset($_POST['time'])) {
  
    $img = $_POST['img'];
    $nameFoods = $_POST['nameFoods'];
    $price = $_POST['price'];
    $time = $_POST['time'];

    // include db connect class
    //require_once __DIR__ . '/db_connect.php';

    // connecting to db
    //$db = new DB_CONNECT();

    $sql = "INSERT INTO foods(img,nameFoods, price, time,order) VALUES('$img','$nameFoods', '$price', '$time','$order')";

if ($conn->query($sql) === TRUE) {
    $response["success"] = 1;
    $response["message"] = "Product successfully created.";
    // echoing JSON response
    echo json_encode($response);
    //echo "New record created successfully";
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
  //echo "Error: " . $sql . "<br>" . $conn->error;
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
