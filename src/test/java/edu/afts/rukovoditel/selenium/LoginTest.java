package edu.afts.rukovoditel.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.afts.rukovoditel.testframework.service.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends RukovoditelTestBase {

    private LoginPage fixture;

    @BeforeEach
    public void setup() {
        super.setup();
        fixture = new LoginPage(driver, wait);
    }

    @Test
    void testLoginUser() {
        fixture.loginUser();
        assertTrue(fixture.isLoggedIn());
    }

    @Test
    void testLoginInvalidCredentials() {
        fixture.loginUser("random", "random");
        assertFalse(fixture.isLoggedIn());
    }

    @Test
    void testLogoutUser() {
        // GIVEN
        fixture.loginUser();
        assertTrue(fixture.isLoggedIn());

        // WHEN
        fixture.logoutUser();

        // THEN
        assertFalse(fixture.isLoggedIn());
    }
}
