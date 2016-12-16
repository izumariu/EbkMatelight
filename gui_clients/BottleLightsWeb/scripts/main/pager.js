function Pager(root) {
  this.names = [];
  this.divs = [];
  var selected = 0;
  var pager = this;

  this.add = function(name, obj) {
    this.names[this.names.length] = name;
    this.update();
  }

  this.update = function() {
    console.log("upd8" + this.names.length);
    root.innerHTML = "";
    var maindiv = document.createElement("div");
    for(i = 0; i < this.names.length; i ++) {
      this.divs[i] = document.createElement("div");
      this.divs[i].setAttribute("style", "width: " + ((100 / this.names.length)) + "%;");
      this.divs[i].setAttribute("class", "headerdiv");
      this.divs[i].setAttribute("id", i + "_headerdiv");
      this.divs[i].appendChild(document.createTextNode(this.names[i]));

      this.divs[i].addEventListener("click", this.onClickDiv, false);
      maindiv.appendChild(this.divs[i]);
    }
    root.appendChild(maindiv);
  }

  this.setSelected = function(position) {
    if(position < this.names.length) {
      this.divs[selected].className = "headerdiv";
      selected = position;
      this.divs[position].className = "selected";
    }
  }

  this.onClickDiv = function(event) {
    pager.setSelected(event.target.getAttribute("id").substring(0, 1));
  }
}
