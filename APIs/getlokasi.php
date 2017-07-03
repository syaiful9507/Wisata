<?php
include './config/dbcon.php';

$sql = "SELECT * FROM wisata";

//excecute Above Query
$query = mysql_query($sql);
while($dt=mysql_fetch_array($query)){
	$data[] = array(
	"id_wisata"		=>$dt["id_wisata"],
	"judul"		=>$dt["judul"],
        "gambar"	=>$dt["gambar"],
	"latitude"		=>$dt["latitude"],
	"longitude"		=>$dt["longitude"]
	);
}
/*$json = array(
'result'=>'success',
'item'	=>$item
);*/
echo json_encode($data);
?>