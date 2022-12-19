package ru.gb.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdata.model.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}
