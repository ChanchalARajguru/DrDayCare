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
        document.getElementById("user"+user_number).innerHTML = user.name

        document.getElementById("email"+user_number).innerHTML = user.email


        var active_ = "";
        if(user.active == true){

            active_ = "active";

            document.getElementById("active-status-display"+user_number).innerHTML = "Deactivate";
        }else{
            active_ = "inactive";

            document.getElementById("active-status-display"+user_number).innerHTML = "Activate";
        }
        document.getElementById("activeStatus"+user_number).innerHTML = active_;

        var u_role = ""
        if(user.userRole === 1){

            u_role = "GP";
        }else if(user.userRole === 2){

            u_role = "Patient";
        }else if(user.userRole === 3){

            u_role = "Pharmacist";
        }else if(user.userRole === 0){

            u_role = "Admin";
        }else{
            u_role = "Other Staff";
        }

        document.getElementById("userRole"+user_number).innerHTML = u_role;
        document.getElementById("userid"+user_number).innerHTML = user.id;

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

function deactivateUser(index) {

    var id = document.getElementById('userid'+index).textContent

    var st = document.getElementById('activeStatus'+index).textContent
    var st1;
    if(st === "active"){
        st1 = false;
    }else{
        st1 = true;
    }
    var response = deactivateUserStatus(id, st1)
    if(response.isActive === "true"){
         document.getElementById('active-status-display'+index).innerHTML = "Deactivate"
        document.getElementById('activeStatus'+index).innerText = "active"
    }else{
        document.getElementById('active-status-display'+index).innerHTML = "Activate"
        document.getElementById('activeStatus'+index).innerText = "inactive"

    }
    console.log(id+"=="+st1);
    return true;
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
