//Simple function will redirect user to correct page depending on cookie. If cookie
function redirect() {
    var frontendPath = window.location.pathname.replace('/login/login.html','');
    var roleFromCookie = getUserRole();

    if (roleFromCookie !== "") {
        var roleNum = parseInt(roleFromCookie);
        switch (roleNum) {
            case 1:
                window.location.replace(frontendPath + "/login/dummy_pages/dummy1.html");
                break;
            case 2:
                window.location.replace(frontendPath + "/login/dummy_pages/dummy2.html");
                break;
            case 3:

                window.location.replace(frontendPath + "/login/dummy_pages/dummy3.html");
                break;
            case 4:
                window.location.replace(frontendPath + "/login/dummy_pages/dummy4.html");
                break;
            case 5:
                window.location.replace(frontendPath + "/login/dummy_pages/dummy5.html");
                break;
        }
    } else {
        alert ("in here");
        window.location.replace(frontendPath + "login/login.html");
    }
}
