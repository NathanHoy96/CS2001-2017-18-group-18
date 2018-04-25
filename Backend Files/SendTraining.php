<?php
include 'dbconnect.php';

echo ($_GET['username']);
echo ($_POST['trainingplan']);
echo ($_POST['name']);

$username = $_GET['username'];
$tptext = $_POST['trainingplan'];
$tpname = $_POST['name'];
$user_id;

var_dump($username,$tptext,$tpname);

getUserId();
insertTrainingPlan();

function getUserId(){
  global $username, $user_id,$connect;
  $query = "SELECT user_id FROM users WHERE username = '$username'";
  $result = mysqli_query($connect, $query);
  $row = mysqli_fetch_assoc($result);
  $user_id = (int) $row['user_id'];

  echo($user_id);

}

function insertTrainingPlan(){
global $username, $user_id, $connect, $tptext,$tpname;

$query = mysqli_query($connect,"INSERT INTO trainingplans(tpname, tptext, users_user_id) VALUES ('$tpname','$tptext','$user_id')");
}
?>
