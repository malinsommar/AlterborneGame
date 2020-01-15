/* Toggle between adding and removing the "responsive" class to topnav when the user clicks on the icon */
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}

/*Retrieving the PHP document so that it can be read in the HTML file */
function loadDoc() {
    console.log("entered loadDoc");
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          console.log(this.responseText);
          document.getElementById("text").innerHTML = this.responseText;
          
          
      }
  };
  xhttp.open("GET", "alterbornedb.php", true);
  xhttp.send();
}