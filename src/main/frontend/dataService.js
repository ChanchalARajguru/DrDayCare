var baseUrl = "http://localhost:8080";
//var baseUrl = "http://localhost:63342"
var final_response;

//Basic method to make call to server, using the login info offered on login GUI.
function checkLogin(input) {
    $.ajax({
        type: "POST",
        url: baseUrl + "/login",
        data: JSON.stringify(input),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (response) {
            final_response = response;
        },
        error: function () {
            alert("Error on server side!")
        }
    });
    return final_response;
}

//Basic method to make call to server, using the id info offered on login GUI.
function getPrescription(input) {
    $.ajax({
       type: "POST",
        url: baseUrl + "/prescription",
        data: JSON.stringify(input),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (response) {
          final_response = response;
                    },
        error: function () {
            alert("Error on server side!")
   }
    });
     //alert("The Prescription was clicked.");
     return final_response;

}