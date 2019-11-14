
//Relates to only button
$( "Prescription" ).click(function() {
    prescription();
})

var baseUrl = "http://localhost:63343";
var final_response;
var input = {};
//input.patient_id =  $("#user-menu-link").attr("title");;
input.patient_id =  24;

//Basic method to make call to server, using the id info offered on login GUI.
function prescription(input) {
    $.ajax({
        type: "POST",
        url: baseUrl + "/prescription",
        data: JSON.parse(input),
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

