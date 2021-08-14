
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

function populateLinksWindow (linksData){

    for (let i =0;i<linksData.length;i++){
        let link = linksData[i]["link"];
        let linkWindow = document.getElementById("linkBox");

        let linkElement = document.createElement("a");
        linkElement.className = "link-primary";
        linkElement.href = link;
        let shortLink = link.substring(0,18);
        linkElement.innerText = shortLink;

        linkWindow.appendChild(linkElement);

    }
}
