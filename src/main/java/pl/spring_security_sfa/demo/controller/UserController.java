package pl.spring_security_sfa.demo.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.spring_security_sfa.demo.model.Token;
import pl.spring_security_sfa.demo.model.User;
import pl.spring_security_sfa.demo.repo.TokenRepo;
import pl.spring_security_sfa.demo.repo.UserRepo;
import pl.spring_security_sfa.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    private UserService userService;
    private UserRepo userRepo;
    private TokenRepo tokenRepo;

    public UserController(UserService userService, UserRepo userRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }
    @GetMapping("/email-acceptance")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "email-acceptance";
    }
    @GetMapping("/sing-up")
    public String singup(Model model) {
        model.addAttribute("user", new User());
        return "sing-up";
    }
        @PostMapping("/register")
        public String register(User user) {
            userService.addUser(user);
            return "register-successful";
        }
    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        User user = byValue.getUser();
        user.setEnabled(true);
        userRepo.save(user);
        return "email-acceptance";
    }
}