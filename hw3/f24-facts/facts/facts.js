 // Function borrowed from Thilo Rusche
 function ClearForm(){  // Set all the form values to blank.
    var form = document.forms[1];
    for (i=0; i < form.elements.length; i++){
       if (form.elements[i].name != "submit" &&
           form.elements[i].name != "clear")
           form.elements[i].value = "";
  }
  return false;
 }

 function setFocus(){
    document.FactServlet.searchText.focus();
 }
