package com.example.webapp.Controllers;




import com.example.webapp.Repositorys.MainpageRepository;
import com.example.webapp.DatabseClasses.mainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private MainpageRepository mainpageRepository;
    @RequestMapping("/")
    public ModelAndView loadHomePage(@ModelAttribute mainPage model){


            ProjectMainController projectMainController = new ProjectMainController();
        ArrayList<String> navData = (ArrayList<String>) getNavData();
        ModelAndView result = new ModelAndView("Pages/HomePage");
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            result.addObject("navData",objectMapper.writeValueAsString(navData));
        }catch (Exception e){

        }
    return result;
    }

    public List<String> getNavData (){

        ArrayList<String> navbarChoices = new ArrayList<String>();
        List<mainPage> mainPages = ((ArrayList<mainPage>) mainpageRepository.findAll());
        for (mainPage page : mainPages){
            navbarChoices.add(page.getHeader());
        }
        return navbarChoices;
    }
}
/*
    public ModelAndView loadHomePage(@ModelAttribute mainPage model){
       ProjectMainController projectMainController = new ProjectMainController();
       List<String> navbarValues = projectMainController.getNavData();
        ModelAndView result = new ModelAndView("Homepage");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result.addObject("pageData",objectMapper.writeValueAsString(navbarValues));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result ;
    }
 */