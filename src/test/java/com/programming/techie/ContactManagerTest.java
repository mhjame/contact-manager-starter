package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.condition.EnabledOnOs;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;
    @BeforeAll
    public void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup() {
       contactManager = new ContactManager();
        System.out.println("Should print before each Tests");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public void tearDownALl() {
        System.out.println("Should Execute After All Tests");
    }
    @Test
    public void shouldCreateContact() {
        contactManager.addContact("John", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact-> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe")&&
                        contact.getPhoneNumber().equals("0123456789"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", "Doe", null);
        });
    }

    @Test
    @DisplayName("Should Create Contact Only on MACOS")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Should Run only on MAC")
    public void shouldCreateContactOnMAC() {
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact-> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe")&&
                        contact.getPhoneNumber().equals("0123456789"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should NOT Create Contact on WindowsOS")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
    public void shouldNotCreateOnlyOnWindows() {
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact-> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe")&&
                        contact.getPhoneNumber().equals("0123456789"))
                .findAny()
                .isPresent());
    }
}