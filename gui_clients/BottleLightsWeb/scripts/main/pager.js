function Pager(root) {
  this.names = [];

  this.add = function(name, obj) {
    this.names[this.names.lenght] = name;
    var x = document.createTextNode(this.names[this.names.lenght]);
    root.appendChild(x);
  }
}
