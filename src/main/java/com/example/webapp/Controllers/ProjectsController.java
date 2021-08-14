package com.example.webapp.Controllers;

import com.example.webapp.DatabseClasses.Projects;
import com.example.webapp.DatabseClasses.ProjectsLinks;
import com.example.webapp.DatabseClasses.Users;
import com.example.webapp.Repositorys.ProjectsRepository;
import com.example.webapp.Repositorys.ProjectLinksRepository;
import com.example.webapp.Repositorys.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/MainPage")
public class ProjectsController {

   @Autowired
   private ProjectsRepository projectsRepository;

   @Autowired ProjectLinksRepository projectLinksRepository;

   @Autowired
    UserRepository userRepository;

   @GetMapping ("getProject") // gets a project along with the navdata for projects button
    public  ModelAndView getMainPage(@ModelAttribute Projects model  , @RequestParam("Project") String projectName){
       ModelAndView result = new ModelAndView("Pages/HomePage");
       ObjectMapper objectMapper = new ObjectMapper();

       List<Projects> Projects = ((ArrayList<Projects>) projectsRepository.findAll()).stream()
              .filter(page ->page.getHeader().equals(projectName))
              .collect(Collectors.toList());


       List<String> navbarChoices = getNavData();
       try {

           result.addObject("links",objectMapper.writeValueAsString(Projects.get(0).testgetProjectsLinks()));
           result.addObject("pageData",objectMapper.writeValueAsString(Projects.get(0)));
           result.addObject("navData",objectMapper.writeValueAsString(navbarChoices));
       }catch (Exception e){

       }


       return result;
    }

    @GetMapping ("getHomePage")// gets homePage and fills the project nav
    public  ModelAndView getMainPage(@ModelAttribute Projects model){

        ModelAndView result = new ModelAndView("Pages/HomePage");
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> navbarChoices = getNavData();

        try {
            result.addObject("navData",objectMapper.writeValueAsString(navbarChoices));
        }catch (Exception e){

        }
        return result;
    }

    @GetMapping(path="/allProjects")
    public ModelAndView getAllPages(HttpServletRequest request ) {
       ;

        final String referer = request.getHeader("referer");

        ModelAndView result;
       // if(referer.equals("http://localhost:8080/AdminPage/getAdminPage?") || referer.equals("http://localhost:8080/MainPage/allProjects?")){
            result = new ModelAndView("Pages/AdminPage");
       // }//else {
          //  result = new ModelAndView("Pages/HomePage");
      //  }

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            result.addObject("allProjects",objectMapper.writeValueAsString(projectsRepository.findAll()));
        }catch (Exception e){
            System.out.print("error");
        }
        return result;



    }


    @GetMapping("adminLogin")
    public String adminLoginCheck(@RequestParam("username") String username,@RequestParam("password") String password){

        List<Users> users = (List<Users>) userRepository.findAll();
        for (Users user:users){
            if (user.checkUserValid(username,password)){
                return "Pages/AdminPage";
            }
        }

        return "Pages/AdminPage";  //remove this later and use pages/homepage below
        //return "Pages/HomePage";
    }

    public List<String> getNavData (){

        ArrayList<String> navbarChoices = new ArrayList<String>();
        List<Projects> Projects = ((ArrayList<Projects>) projectsRepository.findAll());
        for (Projects page : Projects){
            navbarChoices.add(page.getHeader());
        }
        return navbarChoices;
    }

    private List<Projects> findProjectByHeader (String projectHeader){

        List<Projects> projects = ((ArrayList<Projects>) projectsRepository.findAll()).stream()
                .filter(page ->page.getHeader().equals(projectHeader))
                .collect(Collectors.toList());
        return projects;
    }
}
//https://spring.io/guides/gs/accessing-data-mysql/