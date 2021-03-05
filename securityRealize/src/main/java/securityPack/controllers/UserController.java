package securityPack.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import securityPack.services.UserService;

@RestController
public class UserController {
    private UserService userService;

    @GetMapping("/admin")
    public String tryAdmin() { return "admin"; }

    @GetMapping("/user")
    public String tryUser() {
        return "user";
    }

    @GetMapping("/manager")
    public String tryManager() {
        return "manager";
    }

    @GetMapping("/all")
    public String tryAll() {
        return "all";
    }

    @GetMapping("/authenticated")
    public String tryAuth() {
        return "authenticated";
    }

    @GetMapping("/free")
    public String tryFree() {
        return "free";
    }

    @GetMapping("/um")
    public String tryUm() {
        return "um";
    }

    @GetMapping("/au")
    public String tryAu() {
        return "au";
    }

    @GetMapping("/authorityAdmin")
    public String tryAuthorityAdmin() {
        return "authorityAdmin";
    }

    @GetMapping("/authorityUser")
    public String tryAuthorityUser() {
        return "authorityUser";
    }
    @GetMapping("/authorityManager")
    public String tryAuthorityManager() {
        return "authorityManager";
    }

    @GetMapping("/authorityAll")
    public String tryAuthorityAll() {
        return "authorityAll";
    }

}
