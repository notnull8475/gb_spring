package ru.gb.springsecuritymvc.service;

import javax.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.springsecuritymvc.model.AppRole;
import ru.gb.springsecuritymvc.model.AppUser;
import ru.gb.springsecuritymvc.repository.AppUserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Optional<AppUser> findByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthority(user.getAppRoles()));


//        AppUser appUser = appUserRepository.findByUsername(username);
//        if (appUser == null) throw new UsernameNotFoundException(username);
//        List<String> roleNames = this.appRoleService.getRoleNames(appUser.getId());


//
//        List<GrantedAuthority> grantList = new ArrayList<>();
//        if (roleNames!=null){
//            for (String r:roleNames) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(r);
//                grantList.add(authority);
//            }
//        }

//        return new User(appUser.getUsername(), //
//                appUser.getPassword(), grantList);
//        return new AppUserPrincipal(appUser);
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<AppRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
