<?php
	$headers = apache_request_headers();
	if($headers['Polling'] == "new?")
	{
		header("Polling: Something Else");
	}
	echo "Hello from server";

?>