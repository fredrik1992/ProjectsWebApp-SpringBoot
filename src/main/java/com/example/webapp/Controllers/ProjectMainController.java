package com.example.webapp.Controllers;

import com.example.webapp.DatabseClasses.Projects;
import com.example.webapp.Repositorys.MainpageRepository;
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
public class ProjectMainController {

   @Autowired
   private MainpageRepository mainpageRepository;

   @GetMapping ("getProject") // gets a project along with the navdata for projects button
    public  ModelAndView getMainPage(@ModelAttribute Projects model  , @RequestParam("Project") String projectName){
       ModelAndView result = new ModelAndView("Pages/HomePage");
       ObjectMapper objectMapper = new ObjectMapper();

       List<Projects> Projects = ((ArrayList<Projects>) mainpageRepository.findAll()).stream()
              .filter(page ->page.getHeader().equals(projectName))
              .collect(Collectors.toList());


       List<String> navbarChoices = getNavData();

       try {

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

        final String referer = request.getHeader("referer");

        ModelAndView result;
        if(referer.equals("http://localhost:8080/AdminPage/getAdminPage?")){
            result = new ModelAndView("Pages/AdminPage");
        }else {
            result = new ModelAndView("Pages/HomePage");
        }

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            result.addObject("allProjects",objectMapper.writeValueAsString(mainpageRepository.findAll()));
        }catch (Exception e){
            System.out.print("error");
        }
        return result;



    }
    @GetMapping("/editProject")

    public String editProject(@RequestParam("originalProjectName") String projectName,@RequestParam("projectHeader") String projectHeader,
                              @RequestParam("descHeader") String descHeader,@RequestParam("desc") String desc,
                              @RequestParam("pictureLink") String picLink){

       List <Projects> projectList = findProjectByHeader(projectName);
       Projects project = projectList.get(0);

       project.setHeader(projectHeader);
       project.setDescHeader(descHeader);
       project.setDescription(desc);
       project.setPictureUrl(picLink);
        mainpageRepository.save(project);

       return "Pages/AdminPage";
    }

    @GetMapping("/addProject")
    public String addProject(@RequestParam("projectHeader") String projectHeader,
                              @RequestParam("descHeader") String descHeader,@RequestParam("desc") String desc,
                              @RequestParam("pictureLink") String picLink){
        Projects newProject = new Projects();
        newProject.setHeader(projectHeader);
        newProject.setDescHeader(descHeader);
        newProject.setDescription(desc);
        newProject.setPictureUrl(picLink);
        mainpageRepository.save(newProject);
        return "Pages/AdminPage";
    }

    public List<String> getNavData (){

        ArrayList<String> navbarChoices = new ArrayList<String>();
        List<Projects> Projects = ((ArrayList<Projects>) mainpageRepository.findAll());
        for (Projects page : Projects){
            navbarChoices.add(page.getHeader());
        }
        return navbarChoices;
    }

    private List<Projects> findProjectByHeader (String projectHeader){

        List<Projects> projects = ((ArrayList<Projects>) mainpageRepository.findAll()).stream()
                .filter(page ->page.getHeader().equals(projectHeader))
                .collect(Collectors.toList());
        return projects;
    }
}
//https://spring.io/guides/gs/accessing-data-mysql/