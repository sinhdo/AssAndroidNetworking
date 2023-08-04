<?php

/*
 * Following code will list all the products
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

$result = $conn->query("SELECT *FROM foods");

if ($result->num_rows > 0) {
    // looping through all results
    // products node
    $response["foods"] = array();
    while($row = $result->fetch_assoc()) {
    //while ($row = mysql_fetch_array($result)) {
        // temp user array
        $foods = array();
        $foods["id"] = $row["id"];
        $foods["img"] = $row["img"];
        $foods["price"] = $row["price"];
        $foods["nameFoods"] = $row["nameFoods"];
        $foods["time"] = $row["time"];
        $foods["order"] = $row["order"];

        // push single product into final response array
        array_push($response["foods"], $foods);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";

    // echo no users JSON
    echo json_encode($response);
}
?>
