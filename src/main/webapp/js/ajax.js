function makeRequest(){
    //send ajax request
    var password = document.getElementById("pw").value
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === xhr.DONE) {
            if (xhr.status === 200) {
                
                document.getElementById('response').innerText = xhr.responseText;
            }
        }
    }
    xhr.open("POST", "/pw", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
      pass: password
    }));
}
function handleResponse(response){
}