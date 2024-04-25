package com.example.Group.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.Group.Model.GroupAndUserForm;
import java.util.List;
import com.example.Group.Entity.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/accueil")
    public String showHomePage(Model model) {
        model.addAttribute("groupAndUserForm", new GroupAndUserForm());
        return "home";
    }

    @PostMapping("/create_users_and_groups")
    public String create_users_and_groups(@ModelAttribute("groupAndUserForm") GroupAndUserForm form) {

    int userNumber = form.getUserNumber();
    int groupNumber = form.getGroupNumber();
    String configuration = form.getConfiguration();
    List<User> users = form.getUsers();

    System.out.println("userNumber : " + userNumber);
    System.out.println("groupNumber : " + groupNumber);
    System.out.println("configuration : " + configuration);
        for (User user : users) {
            System.out.println("Nom : " + user.getFirstname());
            System.out.println("Prénom : " + user.getLastname());
            System.out.println("Email : " + user.getEmail());
            System.out.println("Mot de passe : " + user.getPassword());
            // Effectuez ici le traitement approprié avec les données des utilisateurs
        }
    return "redirect:/accueil";
    }

}