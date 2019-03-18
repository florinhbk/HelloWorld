import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class FirstTestNGTest {

    UserManager usermanager;

    @BeforeMethod
    public void customLocalSetupMethod(Method testMethod){
        String desc = testMethod.getAnnotation(Test.class).description();

        System.out.println("Starting test: " + testMethod.getName() +
                "with description: " + desc);
        usermanager = new UserManager();
    }

    @Test(description = "Verify that addUser method returns true when successful")
    public void successfulAddUserReturnsTrue() {
        // Arrange

        // Act
        boolean result = usermanager.addUser("mike@email.com");

        // Assert
        Assert.assertTrue(result);
    }

    @Test(description = "Verify that getUser method retrieves the correct existing user")
    public void getUserReturnsExistingSavedUser() {
        usermanager.addUser("Micheal@yahoo.com");
        String result = usermanager.getUser("Micheal@yahoo.com");
        Assert.assertEquals(result, "1Micheal@yahoo.com");
    }

    @Test(description = "Verify that getUser method retrieves the correct existing user")
    public void getNonExistingUserReturnsNull() {
      //  usermanager.addUser("Micheal@yahoo.com");
        String result = usermanager.getUser("Micheal@yahoo.com");
        Assert.assertNull(result);
    }

    @DataProvider
    protected Object[][] invalidEmailProvider(){
        return new Object[][]{
                {""},
                {"johnemail.com"},
                {"johnemail.com"},
                {"john@emailcom"}
        };
    }

    @Test(dataProvider = "invalidEmailProvider", expectedExceptions = IllegalArgumentException.class)
    public void emptyUserThrowsExceptions(String invalidEmail){

        boolean result = usermanager.addUser(invalidEmail);
        Assert.assertTrue(result);

    }
}