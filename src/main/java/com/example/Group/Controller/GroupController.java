package com.example.Group.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Group.Entity.Group;
import com.example.Group.Entity.UserGroup;
import com.example.Group.Repository.GroupRepository;
import com.example.Group.Repository.UserGroupRepository;
import com.example.Group.Service.CustomUser;

@Controller
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @GetMapping("/groupe/{id}")
    public String showGroupPage(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        Group group = groupRepository.findById(id).orElse(null);

        if (group != null) {
            boolean userInGroup = userGroupRepository.existsByIdGroupAndIdUser(id, customUser.getId());
            model.addAttribute("group", group);
            model.addAttribute("userInGroup", userInGroup);
            return "group";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/createGroup/{groupId}")
    public String create_groupe(@PathVariable Long groupId, Model model) {
        Group group = groupRepository.findById(groupId).orElse(null);

        group.setInvitationLink("invitation/" + groupId);
        groupRepository.save(group);

        return "redirect:/groupe/" + groupId;
    }

    @PostMapping("/invitation/{groupId}")
    public String accept_invitation_group(@PathVariable Long groupId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        UserGroup userGroup = new UserGroup();
        userGroup.setIdGroup(groupId);
        userGroup.setIdUser(customUser.getId());
        userGroupRepository.save(userGroup);

        return "redirect:/groupe/" + groupId;
    }

}
