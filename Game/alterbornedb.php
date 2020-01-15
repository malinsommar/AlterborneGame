<?php
// Creating a php file which connects to the database
$servername = "localhost";
$username = "Alterborne";
$password = "YEET";
$dbname = "alterborne";

// Create connection to server and database
$conn = new mysqli($servername, $username, $password, $dbname);
// If unable to connect
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
// Retrieve data from specific table
$sql = "SELECT * FROM abtable ORDER BY XP DESC LIMIT 10";
$result = $conn->query($sql);

// Check if retrieved data is greater than 0 rows

if ($result->num_rows > 0) {
    echo "<table id=dbtable><tr class=dbtr><th class=dbth>Username</th><th class=dbth>XP</th></tr>";
    // Output data of each row into a HTML table
    while($row = $result->fetch_assoc()) {
        echo "<tr class=dbtr><td class=dbtd>".$row["Username"]."</td><td class=dbtd>".$row["XP"]."</td></tr>";
    }
    echo "</table>";
    
    // Notify user that retrieved data is empty
} else {
    echo "0 results";
}
// Close connection
$conn->close();
?> 
