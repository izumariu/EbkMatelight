<?php
  const ip = "localhost";
  const port = "80";

  if(isset($_GET["text"])) {
    if($_GET["text"] != "") {
      if(substr($_GET["text"], 0, 2) != "d=") {
        if(sendTelnet($_GET["text"])) {
          echo "sucess";
        }
        /*Specific error message by the sendTelnet function*/
      }
      else {
        echo "Chronos allein bestimmt über die Zeit! (Eigentlich habe ich nichts für griechische Mythologie übrig... Ist auch nicht wirklich lustig. Aber wer ist schon so schlau und versucht d= einzugeben?! {Hi Marius der es natürlich testen muss. xD})";
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
        $arr1 = str_split($row, 2);
        for($i = 0; $i < count($arr1); $i ++) {
          $command .= $arr1[$i] . ";";
        }
      }
      if(sendTelnet($command)) {
        echo "sucess";
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
