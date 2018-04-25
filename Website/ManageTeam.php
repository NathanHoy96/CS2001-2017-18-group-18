<?php

include 'dbconnect.php';
session_start();

echo($_SESSION['teamname']);
echo($_SESSION['team_id']);

$team_id = $_SESSION['team_id'];

function returnTeamMembers(){
  global $connect, $user, $team_id;

  $query = "SELECT * FROM `users` INNER JOIN `users_has_team` ON
            users_has_team.users_user_id = users.user_id WHERE
            users_has_team.team_team_id = '$team_id' AND users.type LIKE 'Player'";

            $result = mysqli_query($connect, $query);

            while ($row = mysqli_fetch_assoc($result)) {
            $username = $row['username'];
       echo ("<br/><input type=\"submit\" name=\"username\" value=$username>");
   }
}

 ?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>

      <form action="ManagePlayer.php" method="post">
          <?php returnTeamMembers() ?>
      </form>

  </body>
</html>
