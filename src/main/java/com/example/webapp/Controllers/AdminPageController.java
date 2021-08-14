package com.example.webapp.Controllers;

import com.example.webapp.DatabseClasses.Projects;
import com.example.webapp.DatabseClasses.ProjectsLinks;
import com.example.webapp.DatabseClasses.Users;
import com.example.webapp.Repositorys.ProjectLinksRepository;
import com.example.webapp.Repositorys.ProjectsRepository;
import com.example.webapp.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/AdminPage")
public class AdminPageController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    ProjectLinksRepository projectLinksRepository;
    @GetMapping("getAdminPage")
    public String getAdminPage (){
        return "Pages/AdminPage";
    }
    @GetMapping("addUser")
    public String addUser (@RequestParam("username") String username,@RequestParam("password") String password){
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPassword(password);

        userRepository.save(newUser);
        return "Pages/AdminPage";
    }

    @GetMapping("/addProject")
    public String addProject(@RequestParam("projectHeader") String projectHeader,
                             @RequestParam("descHeader") String descHeader,@RequestParam("desc") String desc,
                             @RequestParam("pictureLink") String picLink,@RequestParam("links") String links){

        String[] linksArray = links.split(",");

        Projects newProject = new Projects();
        newProject.setHeader(projectHeader);
        newProject.setDescHeader(descHeader);
        newProject.setDescription(desc);
        newProject.setPictureUrl(picLink);
        projectsRepository.save(newProject);

        for (int i = 0;i<linksArray.length;i++){
            ProjectsLinks projectsLink = new ProjectsLinks();
            projectsLink.setProjects(newProject);
            projectsLink.setLink(linksArray[i]);
            projectLinksRepository.save(projectsLink);

        }

        return "Pages/AdminPage";
    }

    @GetMapping("/editProject")

    public String editProject(@RequestParam("originalProjectName") String projectName,@RequestParam("projectHeader") String projectHeader,
                              @RequestParam("descHeader") String descHeader,@RequestParam("desc") String desc,
                              @RequestParam("pictureLink") String picLink){

        List<Projects> projectList = findProjectByHeader(projectName);
        Projects project = projectList.get(0);

        project.setHeader(projectHeader);
        project.setDescHeader(descHeader);
        project.setDescription(desc);
        project.setPictureUrl(picLink);
        projectsRepository.save(project);

        return "Pages/AdminPage";
    }



    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("projectHeader") String projectHeader){

        System.out.print("in delete " + projectHeader);
        List <Projects> projectList = findProjectByHeader(projectHeader);
        System.out.print(projectList.get(0));
        Projects project = projectList.get(0);
        projectsRepository.delete(project);


        return "Pages/AdminPage";
    }

    private List<Projects> findProjectByHeader (String projectHeader){

        List<Projects> projects = ((ArrayList<Projects>) projectsRepository.findAll()).stream()
                .filter(page ->page.getHeader().equals(projectHeader))
                .collect(Collectors.toList());
        return projects;
    }
    //need to make it so when admin button is clicked a promped asks for name and password if it is empty or wrong an error
    //needs to be given
}
