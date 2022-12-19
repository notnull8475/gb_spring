package ru.gb.springsecuritymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springsecuritymvc.model.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}
