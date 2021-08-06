



function populateNavbar(){

    let navData =cleanJsonString(navbarDataJsonString);

    let navChoices = document.getElementById("navDropdown");

    for (let i =0;i < navData.length;i++){
        let inputElement = document.createElement("input");
        inputElement.className = "btn btn-primary";
        inputElement.id = "submit";
        inputElement.type = "submit";
        inputElement.name = "Project";
        inputElement.value = navData[i];

        navChoices.appendChild(inputElement);

    }
}

function hideNavButtons (buttonId){
    const button = document.getElementById(buttonId);
    button.id = "hiddenButton";
}





