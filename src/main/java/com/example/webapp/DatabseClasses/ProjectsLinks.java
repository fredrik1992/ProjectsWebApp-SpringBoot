package com.example.webapp.DatabseClasses;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "project_links")
public class ProjectsLinks {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int linkID;


    @Column(nullable=false)
    private String link;



    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "projectID", nullable = false)
    private Projects projects;

    public long getLinkID() {
        return linkID;
    }

    public void setlinkID(int id) {
        this.linkID = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public com.example.webapp.DatabseClasses.Projects getProjects() {
        return projects;
    }

    public void setProjects(com.example.webapp.DatabseClasses.Projects projects) {
        this.projects = projects;
    }
}
