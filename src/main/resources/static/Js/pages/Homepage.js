window.onload = function (){
    hideNavButtons("HomeButton");

    if (pageDataJsonString){

        let pageData =cleanJsonString(pageDataJsonString);
        populateMainWindow(pageData)
    }
    if (navbarDataJsonString){
        populateNavbar();

    if (linksDataJsonString){
        let linksData = cleanJsonString(linksDataJsonString)
        populateLinksWindow(linksData);

    }
    }else{

        const Http = new XMLHttpRequest();
        const url = 'http://localhost:8080/MainPage/getMainPage?Project=Project1';
        Http.open("GET",url,false);
        Http.send(null);

    }





}