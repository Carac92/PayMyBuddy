package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.ContactRepository;
import com.paymybuddy.app.service.implementation.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ContactServiceTest {
    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private Principal principal;
    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");
        user.setCredit(BigDecimal.valueOf(100.00));

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2");
        user2.setFirstName("test2");
        user2.setLastName("test2");
        user2.setCredit(BigDecimal.valueOf(100.00));

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setContactUser(user2);
        contact.setUser(user);
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contact);

        when(principal.getName()).thenReturn("test");
        when(userService.findByEmail(principal.getName())).thenReturn(user);
        when(userService.findByEmail("test2")).thenReturn(user2);
        when(contactRepository.findContactByUserIdAndContactUserId(user.getId(), user2.getId())).thenReturn(contact);
        when(contactRepository.findAllByUserId(user.getId())).thenReturn(contactList);
    }

    @Test
    void testAddContactForUser() throws Exception {
        // WHEN
        contactService.addContact(principal, "test2");
        verify(contactRepository, times(1)).save(any());
    }
    @Test
    void testGetContactsForUser() throws Exception {
        List<Contact> result = contactService.getAllContactsForConnectedUser(principal);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getUser().getLastName()).isEqualTo("test");
    }
    @Test
    void testGetContactByUserIdAndContactId() throws Exception {
        Contact result = contactService.getContactWithConnectedUserAndContactEmail(principal, "test2");
        assertThat(result.getUser().getLastName()).isEqualTo("test");
        assertThat(result.getContactUser().getLastName()).isEqualTo("test2");
        assertThat(result.getId()).isEqualTo(1L);
    }
    @Test
    void testRemoveContactForUser() throws Exception {
        contactService.removeContact(principal,1L);
        verify(contactRepository, times(1)).deleteById(1L);
    }
}
