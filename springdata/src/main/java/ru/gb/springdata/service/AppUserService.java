package ru.gb.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.springdata.model.AppUser;
import ru.gb.springdata.repository.AppUserRepository;
import ru.gb.springdata.security.AppUserPrincipal;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) throw new UsernameNotFoundException(username);
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
        return new AppUserPrincipal(appUser);
    }
}
