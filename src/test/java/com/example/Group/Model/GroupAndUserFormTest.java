package com.example.Group.Model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.example.Group.Entity.User;

public class GroupAndUserFormTest {

    @Test
    public void testEmptyConstructor() {
        // Arrange
        GroupAndUserForm form = new GroupAndUserForm();

        // Assert
        assertNull(form.getUsers());
        assertNull(form.getUserNumber());
        assertNull(form.getGroupNumber());
        assertNull(form.getConfiguration());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        GroupAndUserForm form = new GroupAndUserForm();
        List<User> users = new ArrayList<>();
        users.add(new User());
        Integer userNumber = 5;
        Integer groupNumber = 2;
        String configuration = "testConfiguration";

        // Act
        form.setUsers(users);
        form.setUserNumber(userNumber);
        form.setGroupNumber(groupNumber);
        form.setConfiguration(configuration);

        // Assert
        assertEquals(users, form.getUsers());
        assertEquals(userNumber, form.getUserNumber());
        assertEquals(groupNumber, form.getGroupNumber());
        assertEquals(configuration, form.getConfiguration());
    }

    @Test
    public void testNegativeUserNumber() {
        // Arrange
        GroupAndUserForm form = new GroupAndUserForm();

        // Act
        form.setUserNumber(-5);

        // Assert
        assertEquals(0, Math.max(0, form.getUserNumber()));
    }

    @Test
    public void testNegativeGroupNumber() {
        // Arrange
        GroupAndUserForm form = new GroupAndUserForm();

        // Act
        form.setGroupNumber(-2);

        // Assert
        assertEquals(0, Math.max(0, form.getGroupNumber()));
    }

}
