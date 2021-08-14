package com.example.webapp.Repositorys;

import com.example.webapp.DatabseClasses.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,Integer> {
}
