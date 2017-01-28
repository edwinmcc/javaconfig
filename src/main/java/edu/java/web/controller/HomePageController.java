package edu.java.web.controller;

import edu.java.web.beans.Batch;
import edu.java.web.beans.User;
import edu.java.web.service.BatchService;
import edu.java.web.service.UserService;
import edu.java.web.status.OperationResult;
import edu.java.web.status.OperationStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by edwin on 17/01/17.
 */

@Controller
public class HomePageController {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    @Autowired
    @Qualifier(value = "batchService")
    private BatchService batchService;

    @RequestMapping(value = { "/", "/loginpage" }, method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        return new ModelAndView("loginpage");
    }

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView showUserHomePage() {
        ModelAndView mav=new ModelAndView("/user/home");
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        User user=null;
        if(principal instanceof UserDetails) {
            UserDetails userDetails=(UserDetails)principal;
            username=userDetails.getUsername();
        }
        else {
            username=(String)principal;
        }
        OperationResult result=userService.getUser(username);
        if(result.getStatus()== OperationStatus.SUCCESS) {
            user=(User)result.getResultObject();
            List<Batch> batches=user.getBatches();
            mav.addObject("batches",batches);
            mav.addObject("status","success");
        }
        else {
            mav.addObject("status","failed");
        }
        return mav;
    }

    @RequestMapping(value="/user/home/getbatchdetails/{batchId}", method = RequestMethod.GET)
    public ModelAndView getBatchDetails(@PathVariable("batchId") Long batchId) {
        ModelAndView mav=null;
        mav=new ModelAndView("/user/batchdetails");

        OperationResult result=batchService.getBatch(batchId);
        if(result.getStatus()==OperationStatus.SUCCESS) {
            mav.addObject("status","success");
            Batch batch=(Batch)result.getResultObject();

            mav.addObject("batchName",batch.getBatchName());
            mav.addObject("batchSize",batch.getBatchSize());
            mav.addObject("entries",batch.getBatchEntries());

        }
        else {
            mav.addObject("status","failed");
        }
        return mav;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        return new ModelAndView("/admin/home");
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        CookieClearingLogoutHandler cookieClearingLogoutHandler=new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler=new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request,response,null);
        securityContextLogoutHandler.logout(request,response,null);
        try {
            response.sendRedirect("/javaconfig/loginpage");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
