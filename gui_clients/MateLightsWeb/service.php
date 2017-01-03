<?php
  const ip = "localhost";
  const port = "80";

  if($_GET["text"] != "") {
    if(substr($_GET["text"], 0, 2) != "d=") {
      if(sendTelnet($_GET["text"])) {
        echo "sucess";
      }
      /*Specific error message by the sendTelnet function*/
    }
    else {
      echo "Chronos allein bestimmt über die Zeit! (Eigentlich habe ich nichts für griechische Mythologie übrig...)";
    }
  }
  else {
    echo "Von nichts kommt nichts!";
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
