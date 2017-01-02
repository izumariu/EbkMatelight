<?php
  if($_GET["text"] != "") {
    if(substr($_GET["text"], 0, 2) != "d=") {
      echo "sucess";
    }
    else {
      echo "Chronos allein bestimmt über die Zeit! (Eigentlich habe ich nichts für griechische Mythologie übrig...)";
    }
  }
  else {
    echo "Von nichts kommt nichts!";
  }

?>
