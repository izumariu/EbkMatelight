function init() {
  var pager = new Pager(document.body);
  var testcontent1 = document.createElement("div");
  testcontent1.setAttribute("style", "background-color: red;")
  testcontent1.appendChild(document.createTextNode("TestContent1"));

  var testcontent2 = document.createElement("div");
  testcontent2.setAttribute("style", "background-color: blue;")
  testcontent2.appendChild(document.createTextNode("TestContent2"));

  var testcontent3 = document.createElement("div");
  testcontent3.setAttribute("style", "background-color: green;")
  testcontent3.appendChild(document.createTextNode("TestContent3, herzlich willkommen auf meiner Seite des Pagers. Hier geht es alles rund um den Raspberry pi zero, der gerade im Hintergrund per (ssh sehe ich zu) updates macht."));

  pager.add("Test1", testcontent1);
  pager.add("Test2", testcontent2);
  pager.add("Test3", testcontent3);
  pager.setSelected(1);
}
