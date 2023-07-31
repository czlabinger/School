
let form = document.querySelector("#upload");
let file = document.querySelector("#file");
let text = document.querySelector("#text");
let downloadB = document.querySelector("#download");
let out = document.querySelector("#out");

let str;


function upload(event) {
    str = event.target.result;
    text.value = str;
    validate();
}

function handleSubmit (event) {
    text.value = "";
    event.preventDefault();
    if (!file.value.length || file.files[0].name.substring(file.files[0].name.indexOf("."),  file.files[0].name.length) != ".json"){
        out.innerHTML = "Not a JSON-File!";
        return;
    } 
    let reader = new FileReader();
    reader.onload = upload;
    reader.readAsText(file.files[0]);
}

function validate(){
    str = text.value;
    try{
        JSON.parse(str);
        out.innerHTML = "";
        downloadB.disabled = false;
    }catch(e){
        out.innerHTML = e;
        downloadB.disabled = true;
        return false;
    }
    return true;
}

function download(filename, text) {
    if(!validate()) {
        return;
    }
    
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}

form.addEventListener('change', handleSubmit);
