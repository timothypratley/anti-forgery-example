var token = "No token yet";

function getToken () {
  var a = new XMLHttpRequest();
  a.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      token = this.responseText;
      document.getElementById("token").innerHTML = token;
    }
  };
  a.open("GET", "token", true);
  a.send();
}

function useToken () {
  var b = new XMLHttpRequest();
  b.onreadystatechange = function() {
    if (this.readyState == 4) {
      console.log("B", this.responseText);
      document.getElementById("response").innerHTML = this.status + ":" + this.responseText;
    }
  };
  b.open("POST", "/vote", true);
  b.setRequestHeader("X-CSRF-Token", token)
  b.send({"__anti-forgery-token": token});
}