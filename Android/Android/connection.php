<?php
$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "";
$mysql_database = "set4";
$bd = mysql_connect ($mysql_hostname, $mysql_user, $mysql_password) or die ("Could not connect to database");
mysql_select_db ($mysql_database, $bd) or die ("Could not select the database");
?>
