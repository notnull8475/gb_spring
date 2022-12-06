package ru.gb.springdata.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.springdata.model.AppRole;
import ru.gb.springdata.model.AppUser;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserPrincipal implements UserDetails {

    private final AppUser appUser;
//    private List<GrantedAuthority> grantedAuthorityList;

    public AppUserPrincipal(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (AppRole r : appUser.getAppRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }
        return authorities;
//        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));
    }


    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return appUser.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return appUser.isEnabled();
    }

    public AppUser getAppUser() {
        return this.appUser;
    }
}
