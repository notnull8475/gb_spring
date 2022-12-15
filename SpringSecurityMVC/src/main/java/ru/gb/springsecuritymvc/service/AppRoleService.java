package ru.gb.springsecuritymvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springsecuritymvc.model.AppRole;
import ru.gb.springsecuritymvc.repository.AppRoleRepository;

import java.util.List;

@Service
public class AppRoleService {
    @Autowired
    private AppRoleRepository repository;

    public List<AppRole> rolesList(){
        return repository.findAll();
    }
}
