package pl.spring_security_sfa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @GetMapping("/sing-up")
    public String singup(Model model){
        model.addAttribute("user",new User());
        return  "sing-up";
    }
    @PostMapping("/register")
    public String register(User user){
        userService.addUser(user);

        return "sing-up";
    }
    @GetMapping("/for-user")
    public String forUser() {
        return "hello-user";
    }
}
