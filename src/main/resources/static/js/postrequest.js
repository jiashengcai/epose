$(document).ready(function () {
    $('#search-form').submit(function (event) {

        
    })

    
})

function ajax_submit() {
    var sub={"name" : "name"};
    $("#bth-search").prop("disable", true);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "sendAjax",
        data: JSON.stringify(sub),
        dataType: "json",
        cache: false,
        timeout: 600,
        success:  console.log("success")
            /*function(data){
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);
            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);}*/
        ,
        error: console.log("error")
        /*function(e){
            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }*/

    })
}