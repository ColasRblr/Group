package com.example.Group.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.Group.Model.GroupAndUserForm;
import com.example.Group.Repository.UserRepository;
import com.example.Group.Repository.GroupRepository;
import com.example.Group.Repository.UserGroupRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import com.example.Group.Entity.User;
import com.example.Group.Entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Group.Service.CustomUser;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/home")
    public String showHomePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        System.out.println(customUser.getId());
        boolean hasUsers = userRepository.count() > 0;
        boolean hasGroups = groupRepository.count() > 0;

        model.addAttribute("hasUsers", hasUsers);
        model.addAttribute("hasGroups", hasGroups);
        model.addAttribute("userId", customUser.getId());
        model.addAttribute("firstname", customUser.getFirstname());
        model.addAttribute("lastname", customUser.getLastname());
        model.addAttribute("isAdmin", customUser.getIsAdmin());

        if (hasUsers && hasGroups) {
            List<User> users = userRepository.findAll();
            List<Group> groups = groupRepository.findAll();

            List<Long> groupIdsWithUsers = userGroupRepository.findGroupIdsWithUsers();
            List<Group> groupsWithUsers = new ArrayList<>();
            List<Group> groupsWithoutUsers = new ArrayList<>();

            for (Group group : groups) {
                if (groupIdsWithUsers.contains(group.getId())) {
                    groupsWithUsers.add(group);
                } else {
                    groupsWithoutUsers.add(group);
                }
            }
            model.addAttribute("users", users);
            model.addAttribute("groupsWithUsers", groupsWithUsers);
            model.addAttribute("groupsWithoutUsers", groupsWithoutUsers);
            return "home_with_users_and_groups";
        } else {
            model.addAttribute("groupAndUserForm", new GroupAndUserForm());
            return "home";
        }
    }

    @PostMapping("/create_users_and_groups")
    public String create_users_and_groups(@ModelAttribute("groupAndUserForm") GroupAndUserForm form) {
        int userNumber = form.getUserNumber();
        int groupNumber = form.getGroupNumber();
        String configuration = form.getConfiguration();
        List<User> users = form.getUsers();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for (User user : users) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }

        for (int i = 0; i < groupNumber; i++) {
            Group group = new Group();
            group.setNumberUsers(configuration.equals("LAST_MIN")
                    ? ((i != groupNumber - 1) ? ((userNumber + 1) / groupNumber) : (userNumber / groupNumber))
                    : (configuration.equals("LAST_MAX")
                            ? ((i != groupNumber - 1) ? (userNumber / groupNumber) : ((userNumber / groupNumber) + 1))
                            : (userNumber / groupNumber)));
            group.setIsCreated(false);
            group.setInvitationLink("");
            groupRepository.save(group);
        }
        return "redirect:/home";
    }

}