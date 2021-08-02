package com.example.webapp.Controllers;

import com.example.webapp.DatabseClasses.mainPage;
import com.example.webapp.Repositorys.MainpageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/MainPage")
public class ProjectMainController {
   @Autowired
   private MainpageRepository mainpageRepository;

   @GetMapping ("getMainPage")
    public  ModelAndView getMainPage(@ModelAttribute mainPage model  ,@RequestParam("Project") String projectName){

        // can proberly make this database query better

       List<mainPage> mainPage = ((ArrayList<mainPage>) mainpageRepository.findAll()).stream()
              .filter(page ->page.getHeader().equals(projectName))
              .collect(Collectors.toList());

       ModelAndView result = new ModelAndView("Pages/HomePage");
       ObjectMapper objectMapper = new ObjectMapper();

       List<String> navbarChoices = getNavData();
       System.out.print(navbarChoices.get(0));
       System.out.print(mainPage.get(0));


       try {
           result.addObject("pageData",objectMapper.writeValueAsString(mainPage.get(0)));
           result.addObject("navData",objectMapper.writeValueAsString(navbarChoices));
       }catch (Exception e){

       }


       return result;
    }



    @GetMapping ("addMainPage")
    public @ResponseBody
    String addMainPage (){
       mainPage newPage = new mainPage();
       newPage.setDescription("This is project 2");
       newPage.setDescHeader("project2 description:");
       newPage.setPictureUrl("url");
       newPage.setHeader("Project2");
       mainpageRepository.save(newPage);
       return "HomePage";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<mainPage> getAllPages() {
        // This returns a JSON or XML with the users
        return mainpageRepository.findAll();
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
//https://spring.io/guides/gs/accessing-data-mysql/