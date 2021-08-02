package com.example.webapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/AdminPage")
public class AdminPageController {

    @GetMapping("getAdminPage")
    public String getAdminPage (){
        return "Pages/AdminPage";
    }
    //need to make it so when admin button is clicked a promped asks for name and password if it is empty or wrong an error
    //needs to be given
}
