package com.example.webapp.Controllers;

import com.example.webapp.DatabseClasses.mainPage;
import com.example.webapp.Repositorys.MainpageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/MainPage")
public class ProjectMainController {
   @Autowired
   private MainpageRepository mainpageRepository;

   @GetMapping ("getMain")
    public  ModelAndView getMainPage(@ModelAttribute mainPage model  ,@RequestParam("Project") String projectName){

        // can proberly make this database query better

       List<mainPage> mainPage = ((ArrayList<mainPage>) mainpageRepository.findAll()).stream()
              .filter(page ->page.getHeader().equals(projectName))
              .collect(Collectors.toList());

       ModelAndView result = new ModelAndView("Pages/HomePage");
       ObjectMapper objectMapper = new ObjectMapper();

       List<String> navbarChoices = getNavData();

       try {

           result.addObject("pageData",objectMapper.writeValueAsString(mainPage.get(0)));
           result.addObject("navData",objectMapper.writeValueAsString(navbarChoices));
       }catch (Exception e){

       }


       return result;
    }

    @GetMapping ("getHomePage")
    public  ModelAndView getMainPage(@ModelAttribute mainPage model){

        ModelAndView result = new ModelAndView("Pages/HomePage");
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> navbarChoices = getNavData();

        try {


            result.addObject("navData",objectMapper.writeValueAsString(navbarChoices));
        }catch (Exception e){

        }
        return result;
    }


    @GetMapping ("addMainPage") //should be post ?
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

    @GetMapping(path="/allProjects") // must fix redirect so that it gives the info correctly
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



       List <mainPage> projectList = findProjectByHeader(projectName);
       mainPage project = projectList.get(0);

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
        mainPage newProject = new mainPage();
        newProject.setHeader(projectHeader);
        newProject.setDescHeader(descHeader);
        newProject.setDescription(desc);
        newProject.setPictureUrl(picLink);
        mainpageRepository.save(newProject);
        return "Pages/AdminPage";
    }

    public List<String> getNavData (){

        ArrayList<String> navbarChoices = new ArrayList<String>();
        List<mainPage> mainPages = ((ArrayList<mainPage>) mainpageRepository.findAll());
        for (mainPage page : mainPages){
            navbarChoices.add(page.getHeader());
        }
        return navbarChoices;
    }

    private List<mainPage> findProjectByHeader (String projectHeader){

        List<mainPage> projects = ((ArrayList<mainPage>) mainpageRepository.findAll()).stream()
                .filter(page ->page.getHeader().equals(projectHeader))
                .collect(Collectors.toList());
        return projects;
    }
}
//https://spring.io/guides/gs/accessing-data-mysql/