
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
function addNewUser(){
    console.log("add user");
    jQuery('#addUserModal').modal();
}

function populateProjects (projectsData){

    let projectsUl = document.getElementById("projectsUl")

    for (let i =0;i < projectsData.length;i++){
        const projectName = (projectsData[i].header);

        let liElement = document.createElement("li");
        liElement.className = "windowItemBox"

        let projectButton = document.createElement("button");
        projectButton.className = "btn btn-primary windowItemHeader";
        projectButton.innerText = projectName;

        let editButton = document.createElement("button");
        editButton.className = "btn btn-primary windowItem";
        editButton.innerText = "edit";
        editButton.value = projectName;

        const funcString = "showEditModal(\"" +projectName+ "\")";
        editButton.setAttribute("onclick",funcString);

        let form = document.createElement("form");
        form.action = "/AdminPage/deleteProject";
        form.className = "btn btn-primary windowItem";

        let input = document.createElement("input");
        input.type = "hidden";
        input.name = "projectHeader"
        input.value = projectName;

        let deleteButton = document.createElement("button");
        deleteButton.className = "btn btn-primary";
        deleteButton.type = "submit";
        deleteButton.innerText = "Del";



        liElement.appendChild(projectButton);
        liElement.appendChild(editButton);
        form.appendChild(input);
        form.appendChild(deleteButton);
        liElement.appendChild(form);
        projectsUl.appendChild(liElement);


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







