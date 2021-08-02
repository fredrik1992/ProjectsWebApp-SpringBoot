package com.example.webapp.DatabseClasses;

import javax.persistence.*;
import java.util.List;

@Entity
public class mainPage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer projectID;
    @Column(unique=true)
    private String header;
    private String descHeader;
    private String description;
    private String pictureUrl;

   // @OneToMany(mappedBy = "mainPage")
   // private List<ProjectsLinks> projectsLinks;

    public Integer projectID() {
        return projectID;
    }

    public void setprojectID(Integer id) {
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
