
let cleanProjectsData;
window.onload = function (){

    const adminButton = document.getElementById("adminButton");
    hideNavButtons("adminButton");
    hideNavButtons("projectsButton");
    changeHeader("Admin page");


    if(projectsJsonString){
       cleanProjectsData =  cleanJsonString(projectsJsonString);
       populateProjects(cleanProjectsData);


    }
}



function showEditModal(projectName){
    addProjectDataToEditModal(projectName)
    jQuery('#editModal').modal();

}
function addNewProject(){
    console.log("in add")
    jQuery('#addProjectModal').modal();

}

function populateProjects (projectsData){
    let window = document.getElementById("showWindow")
    for (let i =0;i < projectsData.length;i++){
        const projectName = (projectsData[i].header);

        let liElement = document.createElement("li");
        liElement.className = "windowItem"

        let projectButton = document.createElement("button");
        projectButton.className = "btn btn-primary";
        projectButton.innerText = projectName;

        let editButton = document.createElement("button");
        editButton.className = "btn btn-primary";
        editButton.innerText = "edit";
        editButton.value = projectName;

        const funcString = "showEditModal(\"" +projectName+ "\")";

        editButton.setAttribute("onclick",funcString);




        liElement.appendChild(projectButton);
        liElement.appendChild(editButton);
        window.appendChild(liElement);


    }

}

function addProjectDataToEditModal(projectName) {
    for (let i = 0; i < cleanProjectsData.length; i++) {

        if(cleanProjectsData[i].header == projectName){

            let header = document.getElementById("projectHeader");
            let descriptionHeader = document.getElementById("descriptionHeader");
            let description = document.getElementById("description");
            let originalProjectName = document.getElementById("originalProjectName");
            let pictureLink = document.getElementById("pictureLink");


            originalProjectName.value = cleanProjectsData[i].header;
            header.value = cleanProjectsData[i].header;
            descriptionHeader.value = cleanProjectsData[i].descHeader;
            description.innerText = cleanProjectsData[i].description;
            pictureLink.value  =cleanProjectsData[i].pictureUrl;
        }

    }
}







