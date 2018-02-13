<?php
  session_start();
?>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="stylesheets/registerstyles.css">
</head>

<body>
  <div class="header">
  		<h2>Login</h2>
  	</div>

  	<form method="post" action="LoginSecure.php">

  		<div class="input-group">
  			<label>Username</label>
  			<input type="text" name="username" >
  		</div>
  		<div class="input-group">
  			<label>Password</label>
  			<input type="password" name="password">
  		</div>
      <div class="input-group">
        <label>User Type</label>
        <select name="type">
  <option value="Player">Player</option>
  <option value="Coach">Coach</option>
</select>
      </div>
  		<div class="input-group">
  			<button type="submit" class="btn" name="reg_user">Login</button>
  		</div>
  		<p>
  			Not a member?	<a href="register.html">Register</a>
  		</p>
  	</form>

</body>

</html>
