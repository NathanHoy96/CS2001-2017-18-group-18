<?php
  include 'dbconnect.php';
  session_start();
  $username = $_GET['username'];
 ?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Training Plan</title>
    <link rel="stylesheet" href="stylesheets/registerstyles.css">
  </head>
  <body>
<form method="post" action="SendTraining.php?username=<?php echo $username ?>">
<div class="input-group">
  <label>Name:</label>
  <input type="text" name="name">
</div>

    <div class="input-group">
      <label>Enter Training Plan</label>
      <textarea name="trainingplan" style="width:550px;height:600px;"></textarea>
    </div>
    <div class="input-group">
      <button type="submit" class="btn" name="send_plan">Send</button>
    </div>
  	</form>
  </body>
</html>
