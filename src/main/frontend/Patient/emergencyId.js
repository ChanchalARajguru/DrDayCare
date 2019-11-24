//input.patient_id =  $("#user-menu-link").attr("title");;

$("#emergency").on('click',(function ()
{
    emergency();
}));

function emergency() {
    //var patient_id =  $("#user-menu-link").attr("title");

    var patient_id = "24"
    if (patient_id === "") {

        alert("Not able to retrieve patient id");
    } else {
        var input = {};
        input.patient_id = patient_id;
        var response = getemergencyId(input);
        if (response.valid === true) {
            var emergencyID = response.emergencyId;
            //document.write(JSON.stringify(prescription));;
            // alert(JSON.stringify(prescription));
            //document.write(prescription);
            //redirect();
            document.getElementById("result1").innerHTML = JSON.stringify(emergencyID)
        }


    }

//Hiding the box that will be used to display warning
    $("#alert-box").hide();

//Function to get rid of alert after displaying it.
// let bootstrap_alert;
    bootstrap_alert = function () {
    }
    bootstrap_alert.warning = function (message) {
        document.getElementById("alert-box").innerHTML = message;
        $("#alert-box").fadeTo(2000, 500).slideUp(500, function () {
            $("#alert-box").slideUp(500);
        });
    }
}