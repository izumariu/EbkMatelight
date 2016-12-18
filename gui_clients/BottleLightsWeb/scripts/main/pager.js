function Pager(root) {
  var names = [];
  var divs = [];
  var contents = [];
  var selected = 0;
  var pager = this;
  var contentdiv;

  this.add = function(name, obj) {
    names[names.length] = name;
    contents[contents.length] = obj;
    this.update();
  }

  this.update = function() {
    root.innerHTML = "";

    //Header:
    var maindiv = document.createElement("div");
    for(i = 0; i < names.length; i ++) {
      divs[i] = document.createElement("div");
      divs[i].setAttribute("style", "width: " + ((100 / names.length)) + "%;");
      divs[i].className = "headerdiv";
      divs[i].setAttribute("id", i + "_headerdiv");
      divs[i].appendChild(document.createTextNode(names[i]));

      divs[i].addEventListener("click", this.onClickDiv, false);
      maindiv.appendChild(divs[i]);
    }

    //Content:
    contentdiv = document.createElement("div");
    contentdiv.className = "contentdiv";
    var wrapper = document.createElement("div");
    wrapper.className = "wrapper";
    var scrollwrapper = document.createElement("div");
    scrollwrapper.className = "scrollwrapper";

    for(var i = 0; i < contents.length; i ++) {
      var item = document.createElement("div");
      item.className = "item";
      item.appendChild(contents[i]);
      scrollwrapper.appendChild(item);
    }
    wrapper.appendChild(scrollwrapper);
    contentdiv.appendChild(wrapper);
    maindiv.appendChild(contentdiv);

    root.appendChild(maindiv);
  }

  this.setSelected = function(position) {
    if(position < names.length) {
      divs[selected].className = "headerdiv";
      selected = position;
      divs[position].className = "selected";

      /*
      //Test without scrolling
      contentdiv.innerHTML = "";
      contentdiv.appendChild(contents[selected]);*/
    }
  }

  this.onClickDiv = function(event) {
    pager.setSelected(event.target.getAttribute("id").substring(0, 1));
  }
}
