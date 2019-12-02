//Relates to only button
$( "#patientSubmit" ).click(function() {
    addNewPatient();
});
$( "#editSubmit" ).click(function() {
    editPatientJS();
});
$( "#deletePatientbtn" ).click(function() {
    deletePatientJS();
});


//Function is used to complete login. First checks are made to make sure that both text fields are filled in.
//Then use DataService to make call to server with request.
//Finally once the response is received, it will either tell the user they have not received
function addNewPatient(){
    var name = $("#name").val();
    /*var emergencyId = $("#emergencyid").val();*/
    var age = $("#age").val();
    var address = $("#address").val();
    if (name === "")
        bootstrap_alert.warning("No name entered. Please enter name and try again");
    /*else if (emergencyId === "")
        bootstrap_alert.warning("No emergencyid. Please enter emergencyid and try again");*/
    else if (age === "")
        bootstrap_alert.warning("No age. Please enter age and try again");
    else if (address === "")
        bootstrap_alert.warning("No address. Please enter address and try again");
    else{
        var input = {};
        input.name = name;
        /*input.emergencyId = emergencyId;*/
        input.age = age;
        input.extraNotes = "";
        input.address = address;

        var response = addPatient(input);
        if (response.valid === true){
            alert("Patient added sucessfully!!");
            bootstrap_alert.success("User added");
        }
        else{
            alert("Patient failed!!")
            bootstrap_alert.warning("Invalid username or password was given.");
        }
    }
}

function listPatient(){
    var result = getPatients();
    for (patients in result["list"]){
        if (result["list"][patients].userId != 0){
            console.log(result["list"][patients].name)
            var html =
                '<li class="list-group-item">'+
                '<p class="col-md-offset-11">'+
                '<img id="editPatientById" src="" data-toggle="modal" href="/doctor/edit?userid=' + result["list"][patients].userId + '" data-target="#editModal" class="edit glyphicon glyphicon-pencil"   />'+
                '<img id="deletePatientById" src="" data-toggle="modal" href="/doctor/delete?userid=' + result["list"][patients].userId + '" data-target="#myDelete" class="glyphicon glyphicon-trash" />'+
                '</p>'+
                '<h4><b>' + result["list"][patients].name + '</b></h4>'+
                '<p>Age:'+ result["list"][patients].age +'</p>'+
                '<p>Emergency Id:'+ result["list"][patients].emergencyId +'</p>'+
                '<p>Address:'+ result["list"][patients].address +'</p>'+
                '</li>';
            $('.list-group').append(html);
        }
    }

}
listPatient();
/*var id;
function edit(clickedID){
    id = clickedID;
    console.log(id);
}*/
function editPatientJS(){
    var uri= $("#editPatientById").attr('href');
    var name = $("#editname").val();
    /*var emergencyId = $("#emergencyid").val();*/
    var age = $("#editage").val();
    var address = $("#editaddress").val();
    if (name === "")
        bootstrap_alert.warning("No name entered. Please enter name and try again");
    /*else if (emergencyId === "")
        bootstrap_alert.warning("No emergencyid. Please enter emergencyid and try again");*/
    else if (age === "")
        bootstrap_alert.warning("No age. Please enter age and try again");
    else if (address === "")
        bootstrap_alert.warning("No address. Please enter address and try again");
    else{
        var input = {};
        input.name = name;
        /*input.emergencyId = emergencyId;*/
        input.age = age;
        input.extraNotes = "";
        input.address = address;
        var response = editPatientDS(input,uri);
        if (response.valid === true){
            alert("Patient edited sucessfully!!");
            bootstrap_alert.success("User added");
        }
        else{
            alert("Patient edited failed!!")
            bootstrap_alert.warning("Invalid username or password was given.");
        }
    }
}
function deletePatientJS(){
    var uri= $("#deletePatientById").attr('href');
     var response = deletePatientDS(uri);
}


//Hiding the box that will be used to display warning
$("#alert-box").hide();
//Function to get rid of alert after displaying it.
// let bootstrap_alert;
bootstrap_alert = function() {}
bootstrap_alert.warning = function(message) {
    document.getElementById("alert-box").innerHTML = message;
    $("#alert-box").fadeTo(2000, 500).slideUp(500, function() {
        $("#alert-box").slideUp(500);
    });
};
