function populatePage() {
    var response = getAllPrescriptions();

    alert(JSON.stringify(response));

    var html = "";

    jQuery(response).each(function(i, prescription){
        html+= '<div class="jumbotron">';
        html+= '    <div class = "row">';
        html+= '    <div class = "column">';
        html+= '        <p><b>Patient name:</b>'+ prescription.patientName + '</p>';
        html+= '    </div>';
        html+= '    <div class = "column">';
        html+= '        <p><b>Prescribed by: </b> ' + prescription.prescriberName + '</p>';
        html+= '    </div>';
        html+= '</div>';
        html+= '<hr>';
        html+= '<p>' + prescription.prescriptionDetails + '</p>';
        html+= '</div>';
    });

    $("#outer").html(html);
}