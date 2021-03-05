package securityPack.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import securityPack.entities.Authority;
import securityPack.entities.Role;
import securityPack.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public Collection<Authority> getAu(Collection<Role> roles){
        Collection<Authority> authorityCollection = new ArrayList<>();
        for (Role r: roles) {
            authorityCollection.addAll(r.getAuthorities());
        }
    return authorityCollection;
    }

    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }

}
