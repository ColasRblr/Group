package com.example.Group.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Group.Entity.Group;
import com.example.Group.Repository.GroupRepository;

@Controller
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groupe/{id}")
    public String showGroupPage(@PathVariable Long id, Model model) {
        Group group = groupRepository.findById(id).orElse(null);

        if (group != null) {
            model.addAttribute("group", group);
            return "group";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/createGroup/{groupId}")
    public String create_groupe(@PathVariable Long groupId, Model model) {
        Group group = groupRepository.findById(groupId).orElse(null);

        group.setinvitationLink("invitation/" + groupId);
        groupRepository.save(group);

        return "redirect:/group/" + groupId;
    }

}
