
function sendPostRequestToServer(fncallback) {

}

function processBatchResult(data) {

}

function getbatchdetails(batchId) {
    var url="/user/home/getbatchdetails/"+batchId;
    window.location = "http://localhost:8080/javaconfig"+url;

    //$.get(url,processBatchResult);
}

$(".showbatch").click(function(event) {
    getbatchdetails(event.target.parentElement.id);
});