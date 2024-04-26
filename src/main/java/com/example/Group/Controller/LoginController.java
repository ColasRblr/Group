package com.example.Group.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.Group.Model.LoginForm;
import com.example.Group.Service.MyCustomUserDetailsService;
import com.example.Group.Controller.HomeController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyCustomUserDetailsService userDetailsService;

    @Autowired
    private HomeController homeController;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        RedirectAttributes redirectAttributes) {

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded Password: " + encodedPassword);
        try {
            UserDetails user = userDetailsService.loadUserByUsername(email);
            if (passwordEncoder.matches(password, user.getPassword())) {
                model.addAttribute("userId", user.getUsername());
                return homeController.showHomePage(model);
            } else {
                model.addAttribute("message", "Identifiants incorrects. Veuillez réessayer.");
                return showLoginPage(model);
            }
        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Utilisateur introuvable.");
            return showLoginPage(model);
        } catch (RuntimeException e) {
            model.addAttribute("message", "Erreur lors de la connexion, veuillez réessayer");
            return showLoginPage(model);
        }
    }
}
