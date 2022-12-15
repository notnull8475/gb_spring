package ru.gb.springsecuritymvc.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.gb.springsecuritymvc.model.AppRole;
import ru.gb.springsecuritymvc.model.AppUser;
import ru.gb.springsecuritymvc.model.Product;
import ru.gb.springsecuritymvc.repository.AppRoleRepository;
import ru.gb.springsecuritymvc.repository.AppUserRepository;
import ru.gb.springsecuritymvc.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class DBFiller {

    private final ProductRepository repository;
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository roleRepository;

    public DBFiller(ProductRepository repository, AppUserRepository appUserRepository, AppRoleRepository roleRepository) {
        this.repository = repository;
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDBonStart() {
        repository.saveAll(generateProducts(20));
        List<AppRole> roles = fillRoles();
        roleRepository.saveAll(roles);
        appUserRepository.saveAll(fillUsers(roles));
    }


    private List<AppRole> fillRoles() {
        List<AppRole> roles = new ArrayList<>();

        AppRole userRole = new AppRole();
        userRole.setName("USER");
        roles.add(userRole);
        AppRole managerRole = new AppRole();
        managerRole.setName("MANAGER");
        roles.add(managerRole);
        AppRole adminRole = new AppRole();
        adminRole.setName("ADMIN");
        roles.add(adminRole);
        AppRole rootRole = new AppRole();
        rootRole.setName("ROOT");
        roles.add(rootRole);

        return roles;
    }

    private List<AppUser> fillUsers(List<AppRole> roles) {
        List<AppUser> users = new ArrayList<>();
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        AppUser userUser = new AppUser();
        userUser.setUsername("USER");
        userUser.setEnabled(true);
        userUser.setPassword(crypt.encode("user"));
        userUser.setAppRoles(Collections.singletonList(roles.stream().filter(r -> r.getName().equals("USER")).findFirst().get()));
        users.add(userUser);

        AppUser managerUser = new AppUser();
        managerUser.setUsername("MANAGER");
        managerUser.setEnabled(true);
        managerUser.setPassword(crypt.encode("manager"));
        managerUser.setAppRoles(Collections.singletonList(roles.stream().filter(r -> r.getName().equals("MANAGER")).findFirst().get()));
        users.add(managerUser);

        AppUser adminUser = new AppUser();
        adminUser.setUsername("ADMIN");
        adminUser.setEnabled(true);
        adminUser.setPassword(crypt.encode("admin"));
        adminUser.setAppRoles(Collections.singletonList(roles.stream().filter(r -> r.getName().equals("ADMIN")).findFirst().get()));
        users.add(adminUser);

        AppUser rootUser = new AppUser();
        rootUser.setUsername("ROOT");
        rootUser.setEnabled(true);
        rootUser.setPassword(crypt.encode("root"));
        rootUser.setAppRoles(Collections.singletonList(roles.stream().filter(r -> r.getName().equals("ROOT")).findFirst().get()));
        users.add(rootUser);

        return users;
    }


    private static List<Product> generateProducts(int numb) {
        Random r = new Random();
        String[] names = {"Твикс", "Марс", "Сникерс", "Баунти", "Плитки шоколадные", "Конфеты шоколадные", "Карамели", "Карамельные леденцы", "Мармелад", "Вафли", "Пряники", "Пряники к чаю", "Печенье Эсмеральда", "Печенье ювелирное", "Печенье овсяное", "Печенье утреннее", "Печенье Юбилейное", "Кексы", "Мини рулеты", "Рулеты", "Коржи бисквитные", "Сухари Киевские", "Сушки", "Орбит", "Дирол", "Холлс"};
        List<Product> products = new ArrayList<>();
        for (long i = 0; i < numb; i++) {
            Product p = new Product();
            p.setPrice(r.nextLong(1000));
            p.setTitle(names[r.nextInt(names.length)]);
            products.add(p);
        }
        return products;
    }

}
