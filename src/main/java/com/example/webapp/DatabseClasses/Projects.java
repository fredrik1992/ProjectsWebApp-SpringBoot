package com.example.webapp.DatabseClasses;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Projects {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer projectID;
    @Column(unique=true)
    private String header;
    private String descHeader;
    private String description;
    private String pictureUrl;




    public Set<ProjectsLinks> testgetProjectsLinks() {
        return projectsLinks;
    }

    public void setProjectsLinks(Set<ProjectsLinks> projectsLinks) {
        this.projectsLinks = projectsLinks;
    }

    @OneToMany(mappedBy = "projects",cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
   //@Fetch(value = FetchMode.SELECT)  need to check this i want the links to load with project
   private Set<ProjectsLinks> projectsLinks = new HashSet<>();

    public Integer getprojectid() {
        return projectID;
    }

    public void setprojectid(Integer id) {
        this.projectID = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescHeader() {
        return descHeader;
    }

    public void setDescHeader(String descHeader) {
        this.descHeader = descHeader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }






}
