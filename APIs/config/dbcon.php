<?php

// definisikan koneksi ke database
error_reporting(0);
$server = "localhost";
$username = "u470714963_ok";
$password = "123456";
$database = "u470714963_ok";

// Koneksi dan memilih database di server
mysql_connect($server,$username,$password) or die("Koneksi gagal");
mysql_select_db($database) or die("Database tidak bisa dibuka");
?>