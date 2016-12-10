function init() {

  var pager = new Pager(document.body);
  pager.add("Test1");
  pager.add("Test2");

  console.log(pager.name);
}
