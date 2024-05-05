package com.example.Group.Controller;

import com.example.Group.GroupApplication;
import com.example.Group.Entity.Group;
import com.example.Group.Entity.User;
import com.example.Group.Model.GroupAndUserForm;
import com.example.Group.Repository.GroupRepository;
import com.example.Group.Repository.UserGroupRepository;
import com.example.Group.Repository.UserRepository;
import com.example.Group.Service.CustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GroupApplication.class)
@Transactional // Assure que chaque test est exécuté dans une transaction et annulé à la fin du
               // test
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    // @Test
    // @WithMockUser
    // public void testHomePageAccess() throws Exception {
    // mockMvc.perform(get("/home"))
    // .andExpect(status().isOk())
    // .andExpect(view().name("home"));
    // }
    // @Test
    // public void testHomePageAccess() throws Exception {
    // mockMvc.perform(get("/home"))
    // .andExpect(status().isOk())
    // .andExpect(view().name("home"))
    // .andExpect(model().attributeExists("hasUsers"))
    // .andExpect(model().attributeExists("hasGroups"))
    // .andExpect(model().attributeExists("userId"))
    // .andExpect(model().attributeExists("firstname"))
    // .andExpect(model().attributeExists("lastname"))
    // .andExpect(model().attributeExists("isAdmin"));
    // }

    // @Test
    // public void testCreateUsersAndGroups() throws Exception {
    // List<User> users = new ArrayList<>();
    // User user1 = new User();
    // user1.setEmail("user1@outlook.fr");
    // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // String hashedPassword = passwordEncoder.encode("password1");
    // user1.setPassword(hashedPassword);
    // users.add(user1);

    // List<User> existingUsers = userRepository.findAll();

    // // Stocker le nombre d'utilisateurs avant d'appeler la méthode
    // int initialUserCount = existingUsers.size();

    // // Appeler la méthode
    // GroupAndUserForm form = new GroupAndUserForm();
    // form.setUsers(users);
    // form.setUserNumber(1);
    // form.setGroupNumber(1);
    // form.setConfiguration("LAST_MIN");
    // mockMvc.perform(post("/create_users_and_groups")
    // .flashAttr("groupAndUserForm", form))
    // .andExpect(status().is3xxRedirection())
    // .andExpect(redirectedUrl("/home"));

    // // Vérifier que la méthode a ajouté un nouvel utilisateur
    // List<User> updatedUsers = userRepository.findAll();
    // assertEquals(initialUserCount + 1, updatedUsers.size());

    // // Vérifier que la méthode a ajouté un nouveau groupe
    // List<Group> updatedGroups = groupRepository.findAll();
    // assertEquals(1, updatedGroups.size());
    // }
}
