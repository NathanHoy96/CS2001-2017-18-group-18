<?php

include 'dbconnect.php';
$user_id = $_POST['id'];

returnTrainingPlans();

function returnTrainingPlans(){
  global $user_id, $connect;

  $query = "SELECT * FROM trainingplans WHERE users_user_id = '$user_id'";
  $result = mysqli_query($connect, $query);
  $row_data = array();
  $output = array();
  $return_array = array();

  while($row_data = mysqli_fetch_assoc($result)){
    $output['tp_id'] = $row_data['tp_id'];
    $output['tpname'] = $row_data['tpname'];
    $output['tptext'] = $row_data['tptext'];

      array_push($return_array,$output);
  }

echo json_encode($return_array);

}

 ?>
