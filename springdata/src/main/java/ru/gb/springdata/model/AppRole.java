package ru.gb.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String name;

//    @ManyToMany
//    @JoinTable(name = "users_roles",
//    joinColumns = @JoinColumn(name = "role_id"),
//    inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private User user;
}
