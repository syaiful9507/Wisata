<?php

include './config/dbcon.php';
$query = mysql_query("SELECT * FROM wisata where kategori = 'religi' ORDER BY id_wisata desc");
//$query = mysql_query("SELECT judul from wisata where kategori = 'alam');
$json  = '{"wisata": [';

// bikin looping dech array yang di fetch
while ($row = mysql_fetch_array ($query)) {

//tanda kutip dua (") tidak diijinkan oleh string json, 
//maka akan kita replace dengan karakter `
//strip_tag berfungsi untuk menghilangkan tag-tag html pada string  

$char = '"';

$json .= '{"id":"'.$row['id_wisata'].'",
"judul":"'.str_replace($char,'`',strip_tags($row['judul'])).'",
"gambar":"http://androidproject.890m.com/images/'.$row['gambar'].'"},';
}

// buat menghilangkan koma diakhir array
$json = substr($json,0,strlen($json)-1);

$json .= ']}';

// print json
echo $json;

?>