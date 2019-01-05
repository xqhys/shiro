package com.hys.login.manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginControl {

    @RequestMapping(value = {"login","casLogout"})
    public String login(HttpServletRequest request) throws Exception {

        String excaptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (excaptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(excaptionClassName)){
                request.setAttribute("errormessage","账号不存在");
            }else if (IncorrectCredentialsException.class.getName().equals(excaptionClassName)){
                request.setAttribute("errormessage","密码错误");
            }else {
                throw new Exception();
            }
        }

        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return "index";
        }

        return "login";
    }

    @RequiresPermissions(value = {"user:create"})
    @RequestMapping("user")
    public String user(){
        return "cs";
    }

    @RequestMapping("money")
    public String money(){
        return "cs";
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping("money2")
    public String money2(){
        return "cs";
    }

    @RequiresRoles(value = {"user"})
    @RequestMapping("money3")
    public String money3(){
        return "cs";
    }

    @RequestMapping("anonymous")
    public String anonymous(){
        return "cs";
    }


}
