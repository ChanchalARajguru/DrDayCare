//Simple function will redirect user to correct page depending on cookie. If cookie
function redirect() {
    var frontendPath = window.location.pathname;
    frontendPath = frontendPath.substr(0, frontendPath.indexOf('frontend') + 8);
    var roleFromCookie = getUserRole();

    if (roleFromCookie !== "") {
        var roleNum = parseInt(roleFromCookie);
        switch (roleNum) {
            case 1:
                window.location.replace(frontendPath + "/Doctor-PatientDashboard/docdashboard.html");
                break;
            case 2:
                window.location.replace(frontendPath + "/Patient/patient.html");
                break;
            case 3:
                window.location.replace(frontendPath + "/admin/admin.html");
                break;
            case 4:
                window.location.replace(frontendPath + "/Pharmacist/PharmacistDashboard.html");
                break;
            case 5:
                window.location.replace(frontendPath + "/otherMedicalStaff/otherMedicalStaff.html");
                break;
        }
    } else {
        window.location.replace(frontendPath + "/login/login.html");
    }
}
