function setUserCookie(user) {
    document.cookie = "id = " + user.id + ";";
    document.cookie = "name =" + user.name + ";";
    document.cookie = "userRole =" + user.userRole + ";";
}

function getName() {
    return getValue("userType");
}

function getId() {
    return getValue("id");
}

function getUserRole() {
    return getValue("userRole");
}

function getValue(value) {
    var valueToFind = value + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var separatedValues = decodedCookie.split(';');
    for(var i = 0; i <separatedValues.length; i++) {
        var first = separatedValues[i];
        while (first.charAt(0) == ' ') {
            first = first.substring(1);
        }
        if (first.indexOf(valueToFind) == 0) {
            return first.substring(valueToFind.length, first.length);
        }
    }
    return "";
}

//Easiest way to delete cookie is by setting to expired date
function deleteCookie(){
    document.cookie = "name=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    document.cookie = "id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    document.cookie = "userRole=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}
