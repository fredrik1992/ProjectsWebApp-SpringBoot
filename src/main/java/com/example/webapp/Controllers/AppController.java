package com.example.webapp.Controllers;




import com.example.webapp.Repositorys.ProjectsRepository;
import com.example.webapp.DatabseClasses.Projects;
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
    private ProjectsRepository projectsRepository;
    @RequestMapping("/")
    public ModelAndView loadHomePage(@ModelAttribute Projects model){


        ProjectsController projectsController = new ProjectsController();
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
        List<Projects> Projects = ((ArrayList<Projects>) projectsRepository.findAll());
        for (Projects page : Projects){
            navbarChoices.add(page.getHeader());
        }
        return navbarChoices;
    }
}
