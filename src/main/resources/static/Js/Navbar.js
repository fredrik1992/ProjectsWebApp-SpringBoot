


window.onload = function (){
    if (pageDataJsonString){
        let pageData =cleanJsonString(pageDataJsonString);
        populateMainWindow(pageData)
    }
    if (navbarDataJsonString){
        populateNavbar();

    }else{
        console.log("in else");
           const Http = new XMLHttpRequest();
           const url = 'http://localhost:8080/MainPage/getMainPage?Project=Project1';
           Http.open("GET",url,false);
           Http.send(null);
           alert(Http.response);
    }



}
function populateNavbar(){

    let navData =cleanJsonString(navbarDataJsonString);

    let navChoices = document.getElementById("navDropdown");

    for (let i =0;i < navData.length;i++){
        let inputElement = document.createElement("input");
        inputElement.className = "dropdown-item";
        inputElement.id = "submit";
        inputElement.type = "submit";
        inputElement.name = "Project";
        inputElement.value = navData[i];

        navChoices.appendChild(inputElement);

    }
}

function  cleanJsonString (jsonString) {
    let cleanNavData = jsonString.replaceAll("&quot",'"');
    let cleanNavData2 = cleanNavData.replaceAll(";",'');
    let result =JSON.parse(cleanNavData2);
    return result;
}
function populateMainWindow(pageData){
    let headerText = document.getElementById("headerText").getElementsByTagName("h4")[0];
    let descriptionTextHeader = document.getElementById("descriptionTextHeader").getElementsByTagName("h4")[0];
    let description = document.getElementById("descriptionText").getElementsByTagName("div")[1];
    let picture = document.getElementById("projectPicture");


    headerText.innerText = pageData["header"];
    descriptionTextHeader.innerText = pageData["descHeader"];
    description.innerText = pageData["description"];
    console.log(pageData["pictureUrl"]+".jpg")
    picture.src="/pictures/"+pageData["pictureUrl"]+".jpg";


}



