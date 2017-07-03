    <html>
	<head> 
	<title>Insert Data</title>
	</head>
	<body>  
	<h1>Insert Data</h1>  
<form method="post" enctype="multipart/form-data" action="upload.php"> 
 <table border = '1' cellspacing = '1' cellpadding = '10'>
 <tr>
  <td> Id wisata </td>
  <td width = '5' align = 'center'> : </td>
  <td> <input type = 'text' name = 'id_wisata' /> </td>
  </tr>
 <tr>
  <td> Judul </td>
  <td align = 'center'> : </td>
  <td> <input type = 'text' name = 'judul' /> </td>
  </tr>
 <tr>
  <td> Deskripsi </td>
  <td width = '5' align = 'center'> : </td>
  <td> <textarea name = 'deskripsi' ></textarea> </td>
  </tr>
  <tr>
  <td> Gambar </td>
  <td align = 'center'> : </td>
  <td> <input type = 'file' name = 'gambar' /> </td>
  </tr>
  <tr>
  <td> Latitude </td>
  <td width = '5' align = 'center'> : </td>
  <td> <textarea name = 'latitude' ></textarea> </td>
  </tr>
  <tr>
  <td> Longitude </td>
  <td width = '5' align = 'center'> : </td>
  <td> <textarea name = 'longitude' ></textarea> </td>
  </tr>
 <td> Kategori</td>
  <td width = '5' align = 'center'> : </td>
  <td> <textarea name = 'kategori' ></textarea> </td>
  </tr>
 
 <tr>
 <td colspan = '3' align = 'center'>
 <input type="submit" value="Upload"> 
 </td>
 </tr>
</table>
</form>
	</body>
	</html>