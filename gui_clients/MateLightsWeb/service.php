<?php
  const ip = "mariuszero";
  const port = "1337";
  if(isset($_GET["text"])) {
    if($_GET["text"] != "") {
      if(substr($_GET["text"], 0, 2) != "d=") {
        if(sendTelnet($_GET["text"])) {
          echo "success";
        }
        /*Specific error message by the sendTelnet function*/
      }
      else {
        echo "Zeit darf nicht verändert werden!";
      }
    }
    else {
      echo "Von nichts kommt nichts!";
    }
  }
  if(isset($_GET["xml"])) {
    if($_GET["xml"] != "") {
      $xml = simplexml_load_string($_GET["xml"]);
      $command = "";
      $command .= "d=" . $xml->frame["duration"] . ";";

      foreach ($xml->frame->row as $row) {
        $arr1 = str_split($row, 6);
        for($i = 0; $i < count($arr1); $i ++) {
          $command .= $arr1[$i] . ";";
        }
      }
      if(sendTelnet($command)) {
        echo "sucess";
      }
      else {
        echo "Ein Fehler ist aufgereten!";
      }
    }
  }

  function sendTelnet($msg) {
    /*Create the socket*/
    $socket = socket_create(AF_INET, SOCK_STREAM, IPPROTO_IP);
    if(!$socket) {
      $errorcode = socket_last_error();
      $errormsg = socket_strerror($errorcode);
      echo "[ERROR] Could not create socket: [$errorcode] $errormsg";
      return false;
    }
    /*Connect to the socket*/
    if(!socket_connect($socket, ip, port)) {
      $errorcode = socket_last_error();
      $errormsg = socket_strerror($errorcode);
      echo "[ERROR] Could not connect to server: [$errorcode] $errormsg";
      return false;
    }
    /*Send the message*/
    if(!socket_send($socket, $msg, strlen($msg), 0)) {
      $errorcode = socket_last_error();
      $errormsg = socket_strerror($errorcode);
      echo "[ERROR] Could not send message: [$errorcode] $errormsg";
      return false;
    }
    return true;
  }
?>
