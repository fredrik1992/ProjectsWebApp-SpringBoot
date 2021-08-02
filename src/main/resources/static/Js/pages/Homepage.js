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