function Pager(root) {
  this.names = [];
  this.divs = [];

  this.add = function(name, obj) {
    this.names[this.names.length] = name;
    this.update();
  }
  this.update = function() {
    console.log("upd8" + this.names.length);
    root.innerHTML = "";
    var maindiv = document.createElement("div");
    maindiv.setAttribute("style", "background-color: green;");
    for(i = 0; i < this.names.length; i ++) {
      this.divs[i] = document.createElement("div");
      this.divs[i].setAttribute("style", "display: inline-block; width: " + ((100 / this.names.length)) + "%;");
      this.divs[i].appendChild(document.createTextNode(this.names[i]));
      maindiv.appendChild(this.divs[i]);
    }
    root.appendChild(maindiv);
  }
}
