package edu.java.web.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.java.web.beans.Role;
import edu.java.web.service.UserService;
import edu.java.web.status.OperationResult;
import edu.java.web.status.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * Created by edwin on 19/01/17.
 */

public class UserServiceDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService=null;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //LogUtil.debug("UserServiceDetailsServiceImpl :: logUserByUsername :: "+userName);
        // TODO Auto-generated method stub
        //User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        System.out.println("UserServiceDetailsServiceImpl :: 1 "+userName);
        Collection<? extends GrantedAuthority> userRoles=getAuthorityListForUser(userName);

        User user=new User(userName,userName,true,true,true,true,userRoles);
        System.out.println("UserDetailsServiceImpl :: 3 "+user.getAuthorities());
        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorityListForUser(String userName) {

        List<SimpleGrantedAuthority> sgaList=new ArrayList<SimpleGrantedAuthority>();
        OperationResult result=userService.getUser(userName);
        edu.java.web.beans.User user=null;
        if(result.getStatus() == OperationStatus.SUCCESS) {
            user=(edu.java.web.beans.User)result.getResultObject();
        }

        List<Role> rolesList=user.getRolesList();
        for(Role role : rolesList) {
            SimpleGrantedAuthority sga=new SimpleGrantedAuthority(role.getAuthority());
            sgaList.add(sga);
        }
        System.out.println("UserDetailsService :: roles List :  2 "+rolesList+" userName : "+userName);
        return sgaList;
    }
}