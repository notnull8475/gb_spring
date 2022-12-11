package ru.gb.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailsService implements UserDetailsService {

    // FIXME: 08.12.2022 Инжектим сюда репозиторий UserRepository
    // FIXME: 08.12.2022 Инжектим сюда репозиторий RoleRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // FIXME: 08.12.2022 Загружаем юзера из БД (если его нет, бросает UsernameNotFoundException)
        // FIXME: 08.12.2022 Загружаем роли для юзера из БД
        // FIXME: 08.12.2022 Строим объект типа UserDetails

        return User.builder()
                .username(username)
                .password("")
                .authorities("")
                .build();
    }
}
