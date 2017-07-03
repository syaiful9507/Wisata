    <html><head>  
	<title>Data Gambar</title>
	</head>
	<body><h1>Data Gambar</h1>
	<hr><a href="form.php">Tambah Gambar</a>
	<br><br>
	<table border="1" cellpadding="8"><tr> 
	<th>ID</th>
	<th>Judul</th>
	<th>Deskripsi</th> 
	<th>Gambar</th> 
	<th>Latitude</th> 	
	<th>Longitude</th> 
	<th>Kategori</th> 

	</tr>
	<?php
	// Load file koneksi.php
	include "koneksi.php";
	$query = "SELECT * FROM wisata"; 
	// Tampilkan semua data gambar
	$sql = mysqli_query($connect, $query);
	// Eksekusi/Jalankan query dari variabel 
	$row = mysqli_num_rows($sql);
	// Ambil jumlah data dari hasil eksekusi
	if($row > 0){ 
	// Jika jumlah data lebih dari 0 (Berarti jika data ada)  
	while($data = mysqli_fetch_array($sql)){
		// Ambil semua data dari hasil eksekusi
		echo "<tr>";  
		echo "<td>".$data['id_wisata']."</td>"; 
		echo "<td>".$data['judul']."</td>";
		echo "<td>".$data['deskripsi']."</td>";
		echo "<td><img src='images/".$data['gambar']."' width='100' height='100'></td>";   
		echo "<td>".$data['latitude']."</td>"; 
		echo "<td>".$data['longitude']."</td>";	
        echo "<td>".$data['kategori']."</td>";		
		echo "</tr>";  }}else{ // Jika data tidak ada 
		echo "<tr><td colspan='4'>Data tidak ada</td></tr>";}
		?></table></body></html>