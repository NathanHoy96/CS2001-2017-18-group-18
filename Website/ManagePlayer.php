
<?php
  include 'dbconnect.php';

  echo($_POST['username']);

 ?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>

    <a href="TrainingPlan.php?varname=<?php echo $_POST['username'] ?>">Create training plan</a>
    <a href="NutritionPlan.php?varname=<?php echo $_POST['username'] ?>">Create nutrition plan</a>
  </body>
</html>
