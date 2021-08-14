package com.example.webapp.Repositorys;

import com.example.webapp.DatabseClasses.ProjectsLinks;
import com.mysql.cj.protocol.InternalTime;
import org.springframework.data.repository.CrudRepository;

public interface ProjectLinksRepository extends CrudRepository<ProjectsLinks,Integer> {
}
