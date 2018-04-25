<?php

	include 'dbconnect.php';
	session_start();
	echo("test");
	echo($_SESSION['id']);

	$user_id = $_SESSION['id'];

	returnTeams();

	function returnTeams(){
		global $connect, $user_id,$teamname;
		$query = "SELECT * FROM `team` INNER JOIN `users_has_team` ON
							users_has_team.team_team_id = team.team_id WHERE
							users_has_team.users_user_id = '$user_id'";
		$result = mysqli_query($connect, $query);
		$row = mysqli_fetch_assoc($result);

		echo($row['teamname']);
		echo ($row['team_id']);

		$teamname = $row['teamname'];
		$_SESSION['teamname'] = $teamname;
		$_SESSION['team_id'] = $row['team_id'];

	}


?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>My Teams</title>
	</head>
	<body>
		<ul>
			<li><a href="CreateTeam.php">Create a Team</a></li>

			<li><a href="ManageTeam.php"><?php echo($teamname)?><a/></li>
		</ul>


	</body>
</html>
