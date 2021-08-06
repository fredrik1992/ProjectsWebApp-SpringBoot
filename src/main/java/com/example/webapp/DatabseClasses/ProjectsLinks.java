package com.example.webapp.DatabseClasses;

import javax.persistence.*;

@Entity
@Table(name = "project_links")
public class ProjectsLinks {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long linkID;


    @Column(nullable=false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "projectID")
    private Projects Projects;

    public long getLinkID() {
        return linkID;
    }

    public void setlinkID(long id) {
        this.linkID = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
