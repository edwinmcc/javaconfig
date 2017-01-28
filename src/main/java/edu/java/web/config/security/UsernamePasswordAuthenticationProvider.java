package edu.java.web.config.security;

import edu.java.web.beans.Batch;
import edu.java.web.beans.Role;
import edu.java.web.beans.User;
import edu.java.web.service.UserService;
import edu.java.web.status.OperationResult;
import edu.java.web.status.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 18/01/17.
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    public UsernamePasswordAuthenticationProvider() {

    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        UsernamePasswordAuthenticationToken userToken=null;
        System.out.println("UserNamePasswordAuthenticationProvider 1 :: userName "+username+" password : "+password);
        OperationResult result=userService.getUser(username);
        User user=null;
        if(result.getStatus()== OperationStatus.SUCCESS) {
            user=(User)result.getResultObject();
        }
        if(result.getStatus()==OperationStatus.NOT_FOUND) {
            throw new UsernameNotFoundException("UserName not present");
        }

        System.out.println("UserNamePasswordAuthenticationProvider 2 :: userName "+user.getUsername()+" password : "+user.getPassword());
        if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {

            List<SimpleGrantedAuthority> sgaList=new ArrayList<SimpleGrantedAuthority>();

            for(Role role : user.getRolesList()) {
                SimpleGrantedAuthority sga=new SimpleGrantedAuthority(role.getAuthority());
                sgaList.add(sga);
            }

            List<Batch> batches=user.getBatches();

            System.out.println("Batch count :: "+batches.size());

            for(Batch batch : batches) {

                System.out.println("Batch Name : "+batch.getBatchName());

            }

            userToken=new UsernamePasswordAuthenticationToken(username,password,sgaList);
        }
        return userToken;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserService(UserService userService) {
        this.userService=userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
