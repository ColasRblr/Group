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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.Group.Service.CustomUser;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyCustomUserDetailsService userDetailsService;

    // @Autowired
    // private HomeController homeController;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/signin")
    public String login(@RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        System.out.println("email : " + email);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String hashedPassword = passwordEncoder.encode(email);
            System.out.println("password : " + hashedPassword);

            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                CustomUser customUser = (CustomUser) userDetails;
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUser, null,
                        customUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("L'authentification a réussi pour l'utilisateur : " + customUser.getFirstname() + " "
                        + customUser.getLastname());
                System.out.println("L'authentification a elle réussi ? : " + authentication.isAuthenticated());
                System.out.println("Le rôle de l'utilisateur est : " + customUser.getAuthorities());
                // return homeController.showHomePage(model);

                HttpSession session = request.getSession();
                session.setAttribute("connecté", "Julie");

                System.out.println("L'id de la session est le suivant : " + session.getId());

                return "redirect:/home";
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
