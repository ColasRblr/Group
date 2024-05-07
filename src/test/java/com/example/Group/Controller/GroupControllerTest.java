import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ui.Model;

import com.example.Group.Controller.GroupController;
import com.example.Group.Entity.Group;
import com.example.Group.Repository.GroupRepository;
import com.example.Group.Repository.UserGroupRepository;
import com.example.Group.Service.CustomUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private Model model;

    @InjectMocks
    private GroupController controller;

    @Test
    void testShowGroupPage_GroupExists() {
        
        Authentication authentication = mock(Authentication.class);
        CustomUser customUser = mock(CustomUser.class);
        when(authentication.getPrincipal()).thenReturn(customUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        
        Group group = new Group(); 
        when(groupRepository.findById(any())).thenReturn(Optional.of(group));
        when(userGroupRepository.existsByIdGroupAndIdUser(any(), any())).thenReturn(true);

    
        String view = controller.showGroupPage(1L, model);
        verify(model).addAttribute("group", group);
        verify(model).addAttribute("userInGroup", true);
        assertEquals("group", view);
    }

    @Test
    void testShowGroupPage_GroupDoesNotExist() {
        Authentication authentication = mock(Authentication.class);
        CustomUser customUser = mock(CustomUser.class);
        when(authentication.getPrincipal()).thenReturn(customUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        
        when(groupRepository.findById(any())).thenReturn(Optional.empty());

        
        String view = controller.showGroupPage(1L, model);

        
        assertEquals("redirect:/error", view);
    }
}
