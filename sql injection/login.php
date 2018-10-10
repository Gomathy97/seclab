<?php
	$db = mysqli_connect('localhost', 'system', 'gomathy');
	if (!$db){
		exit("Error: Could not connect to MySQL");
	}
	$un=$_POST['un'];
	$pwd = $_POST['pwd'];
	$query = "SELECT uname, pwd FROM sample WHERE uname = '$un' AND pwd = '$pwd'";
	$result =NULL;
	$result = mysqli_query($db, $query);
	$num_rows = mysqli_num_rows($result);
	if($num_rows){
		header("Location : welcome.html")
	}		
	else{
		header("location : welcome.html")
	}
?>