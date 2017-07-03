<?php

$kd="";
include './config/dbcon.php';
//$kd = $_GET['idberita'];
if(isset($_GET['id_wisata'])){
 $kd=$_GET['id_wisata'];

}

$query = mysql_query('SELECT * FROM wisata where id_wisata="'.$kd.'"');
$json  = '{"wisata": [';

while($row=mysql_fetch_array($query))
{

//tanda kutip dua (") tidak diijinkan oleh string json, maka akan kita replace dengan karakter `
//strip_tag berfungsi untuk menghilangkan tag-tag html pada string  

$char = '"';

$json .='{"id":"'.$row['id_wisata'].'",
"judul":"'.str_replace($char,'`',strip_tags($row["judul"])).'",
"isi":"'.str_replace($char,'`',strip_tags($row["deskripsi"])).'",
"voice":"http://androidproject.890m.com/suara/'.$row['voice'].'",
"gambar":"http://androidproject.890m.com/images/'.$row['gambar'].'"},';



}
// buat menghilangkan koma diakhir array
$json = substr($json,0,strlen($json)-1);

$json .= ']}';
// print json
echo $json;
?>