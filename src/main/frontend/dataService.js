var baseUrl = "http://localhost:8080";
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
            alert("Error on server side!");
        }
    });
    return final_response;
}

function getallPatientdetails(){
    $.ajax({
        type: "GET",
        url: baseUrl + "/MedicalStaff/allPatientdetails",

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

function getUsers() {

    $.ajax({
        url: baseUrl + "/getAllUsers",
        type: "GET",
        responseType : 'json',
        async: false,
        cache: false,

        success: function (response) {
            final_response = response;
            return response;
        },
        error: function (jqXHR,  textStatus,  errorThrown) {
            alert("Error on server side!")
        }
    });
    return final_response;
}
function deactivateUserStatus(id, status) {
    $.ajax({
        url: baseUrl + "/deactivateUser?id=" + id+"&status="+status,
        type: "GET",
        responseType: 'json',
        async: false,
        cache: false,

        success: function (response) {
            //console.log(final_response)
            final_response = response;
            return response;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Error on server side!")
        }
    });

    return final_response;
}

function addUser(input) {
    $.ajax({
        type: "POST",
        url: baseUrl + "/addUser",
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

function getAllPrescriptions() {
    $.ajax({
        url: baseUrl + "/pharmacist/getAllPrescriptions",
        type: "GET",
        context: document.body,
        async: false,
        cache: false,
        success: function (response) {
            final_response = response;
        },
        error: function () {
            alert("Error on server side!");
        }
    });
    return final_response;
}

function addPatient(input) {

    $.ajax({
        type:"POST",
        url: baseUrl + "/doctor/addPatient",
        data: JSON.stringify(input),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (response) {
            final_response = response;
            // alert("Success on server side!")
        },
        error: function (jqXHR,  textStatus,  errorThrown) {
            // alert("Error on server side!"+ textStatus + "=="+errorThrown +"=="+jqXHR)
        }
    });
    return final_response;
}

function getPatients() {
    $.ajax({
        type:"GET",
        url: baseUrl + "/doctor/getAllPatients",
        /*data: JSON.stringify(input),*/
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (response) {
            final_response = response;
            // alert("Success on server side!")
        },
        error: function (jqXHR,  textStatus,  errorThrown) {
            // alert("Error on server side!"+ textStatus + "=="+errorThrown +"=="+jqXHR)
        }
    });
      return final_response;
}

function editPatientDS(input,uri) {
        $.ajax({
        type:"POST",
//        url: baseUrl + "/doctor/edit",
        url: baseUrl + uri,
        data: JSON.stringify(input),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (response) {
            final_response = response;
            // alert("Success on server side!")
        },
        error: function (jqXHR,  textStatus,  errorThrown) {
            // alert("Error on server side!"+ textStatus + "=="+errorThrown +"=="+jqXHR)
        }
    });
        return final_response;
}
function deletePatientDS(uri) {
    console.log("deletePatientDS");
    $.ajax({
        type:"POST",
//        url: baseUrl + "/doctor/edit",
        url: baseUrl + uri,
//        data: JSON.stringify(input),
  //      dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (response) {
            final_response = response;
            // alert("Success on server side!")
        },
        error: function (jqXHR,  textStatus,  errorThrown) {
            // alert("Error on server side!"+ textStatus + "=="+errorThrown +"=="+jqXHR)
        }
    });
    location.reload();
    return final_response;
}