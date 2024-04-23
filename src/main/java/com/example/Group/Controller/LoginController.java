package com.example.Group.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.example.Group.Model.LoginForm;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {

    System.out.println("Email: " + email);
    System.out.println("Password: " + password);
        
        return "redirect:/accueil";
    }

    @GetMapping("/accueil")
    public String showHomePage() {
        return "home";
    }
}
