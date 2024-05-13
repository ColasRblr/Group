package com.example.Group.Controller;

import com.example.Group.GroupApplication;
import com.example.Group.Entity.Group;
import com.example.Group.Entity.User;
import com.example.Group.Entity.UserGroup;
import com.example.Group.Model.GroupAndUserForm;
import com.example.Group.Repository.GroupRepository;
import com.example.Group.Repository.UserGroupRepository;
import com.example.Group.Repository.UserRepository;
import com.example.Group.Service.CustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GroupApplication.class)
@Transactional
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void testCreateUsersAndGroupsWithLastMinConfiguration() throws Exception {
        GroupAndUserForm form = new GroupAndUserForm();
        form.setUserNumber(11);
        form.setGroupNumber(2);
        form.setConfiguration("LAST_MIN");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setEmail("user" + i + "@example.com");
            user.setPassword("password");
            users.add(user);
        }
        form.setUsers(users);

        String result = homeController.create_users_and_groups(form);
        assertEquals("redirect:/home", result);

        int[] expectedGroupSizes = new int[form.getGroupNumber()];
        int usersPerGroup = form.getUserNumber() / form.getGroupNumber();
        int remainingUsers = form.getUserNumber() % form.getGroupNumber();
        for (int i = 0; i < form.getGroupNumber(); i++) {
            expectedGroupSizes[i] = usersPerGroup + (i < remainingUsers ? 1 : 0);
        }

        ArgumentCaptor<Group> groupArgumentCaptor = ArgumentCaptor.forClass(Group.class);
        verify(groupRepository, times(form.getGroupNumber())).save(groupArgumentCaptor.capture());
        List<Group> capturedGroups = groupArgumentCaptor.getAllValues();
        assertEquals(expectedGroupSizes[0], capturedGroups.get(0).getNumberUsers());
        assertEquals(expectedGroupSizes[1], capturedGroups.get(1).getNumberUsers());
        assertTrue(capturedGroups.get(0).getNumberUsers() == capturedGroups.get(1).getNumberUsers() + 1);
    }

    @Test
    public void testCreateUsersAndGroupsWithLastMaxConfiguration() throws Exception {
        GroupAndUserForm form = new GroupAndUserForm();
        form.setUserNumber(11);
        form.setGroupNumber(2);
        form.setConfiguration("LAST_MAX");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setEmail("user" + i + "@example.com");
            user.setPassword("password");
            users.add(user);
        }
        form.setUsers(users);

        String result = homeController.create_users_and_groups(form);
        assertEquals("redirect:/home", result);

        int[] expectedGroupSizes = new int[form.getGroupNumber()];
        int usersPerGroup = form.getUserNumber() / form.getGroupNumber();
        int remainingUsers = form.getUserNumber() % form.getGroupNumber();
        for (int i = 0; i < form.getGroupNumber(); i++) {
            expectedGroupSizes[i] = usersPerGroup + (i == form.getGroupNumber() - remainingUsers ? 1 : 0);
        }

        ArgumentCaptor<Group> groupArgumentCaptor = ArgumentCaptor.forClass(Group.class);
        verify(groupRepository, times(form.getGroupNumber())).save(groupArgumentCaptor.capture());
        List<Group> capturedGroups = groupArgumentCaptor.getAllValues();
        assertEquals(expectedGroupSizes[0], capturedGroups.get(0).getNumberUsers());
        assertEquals(expectedGroupSizes[1], capturedGroups.get(1).getNumberUsers());
        assertTrue(capturedGroups.get(1).getNumberUsers() == capturedGroups.get(0).getNumberUsers() + 1);
    }

    @Test
    public void testShowHomePageWithoutUsersAndGroups() throws Exception {
        userRepository.deleteAll();
        groupRepository.deleteAll();

        Model model = new ExtendedModelMap();
        String viewName = homeController.showHomePage(model);

        assertEquals("home", viewName);
        assertFalse((Boolean) model.getAttribute("hasUsers"));
        assertFalse((Boolean) model.getAttribute("hasGroups"));
        assertNull(model.getAttribute("users"));
        assertNull(model.getAttribute("groupsWithUsers"));
        assertNull(model.getAttribute("groupsWithoutUsers"));
    }

}
