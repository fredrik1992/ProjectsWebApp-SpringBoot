
function populateMainWindow(pageData){
    let headerText = document.getElementById("projectHeaderText").getElementsByTagName("h4")[0];
    let descriptionTextHeader = document.getElementById("descriptionTextHeader").getElementsByTagName("h4")[0];
    let description = document.getElementById("descriptionText").getElementsByTagName("div")[1];
    let picture = document.getElementById("projectPicture");

    console.log(headerText);
    headerText.innerText = pageData["header"];
    descriptionTextHeader.innerText = pageData["descHeader"];
    description.innerText = pageData["description"];
    console.log(pageData["pictureUrl"]+".jpg")
    picture.src="/pictures/"+pageData["pictureUrl"]+".jpg";


}

function  cleanJsonString (jsonString) {
    let cleanNavData = jsonString.replaceAll("&quot",'"');
    let cleanNavData2 = cleanNavData.replaceAll(";",'');
    let result =JSON.parse(cleanNavData2);
    return result;
}