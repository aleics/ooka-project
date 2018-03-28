import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ooka.usermngmt.domain.AccountType;
import org.ooka.usermngmt.domain.User;
import org.ooka.usermngmt.services.JwtService;

import java.util.UUID;

public class JwtServiceTest {

    JwtService jwtService;
    private static User user;


    public JwtServiceTest() {
        this.jwtService = new JwtService();
    }

    @BeforeClass
    public static void generateUser() {
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("test@example.com");
        user.setUserName("Test");
        user.setPassword("1234");
        user.setAccountType(AccountType.NORMAL);
    }

    @Test
    public void generateJwt() {
        String token = jwtService.createUserJwt(user);
        Assert.assertNotNull(token);
    }

    @Test
    public void validateJwt() {
        String token = jwtService.createUserJwt(user);
        Assert.assertNotNull(token);

        Assert.assertTrue(jwtService.validate(token));

        String invalidToken = "invalidToken";

        Assert.assertFalse(jwtService.validate(invalidToken));
    }
}
