$(document).ready(function() {
    allUsers();
});


function allUsers(){


    var response = getUsers();
    console.log(response);
    //document.getElementById("root").innerHTML = response.list[0].name
    //$('root').innerHTML(response);


    var list = response.list
    for (var i = 0; i < list.length; i++) {
        var user = list[i];
        console.log(user.name + "===" +user.email);

        var user_number = i + 1;
        document.getElementById("user"+user_number).innerHTML = user.name + " " + user.userRole

        document.getElementById("email"+user_number).innerHTML = user.email


    }

}

function addUserData(){

    var name = $("#name").val();
    var email = $("#email-id").val();
    var type = $("#type").val();
    var userid = $("#userid").val();


    var input = {};


    input.name = name;
    input.email = email;
    input.type = type;
    input.id = userid;

    var response = addUser(input);

    if (response.result === "success"){
        //var user = response.user;

        alert("success");

    }
    else {
        alert("Server error");
    }

    //var response = addUser();
    console.log(response);
    //document.getElementById("root").innerHTML = response.list[0].name
    //$('root').innerHTML(response);



}
// //Hiding the box that will be used to display warning
// $("#alert-box").hide();
//
// //Function to get rid of alert after displaying it.
// bootstrap_alert = function() {}
// bootstrap_alert.warning = function(message) {
//     document.getElementById("alert-box").innerHTML = message;
//     $("#alert-box").fadeTo(2000, 500).slideUp(500, function() {
//         $("#alert-box").slideUp(500);
//     });
// };
