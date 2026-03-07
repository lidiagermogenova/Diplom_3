package testdata;

public class TestUserFactory {

    public static UserTest validUser() {
        long timestamp = System.currentTimeMillis();
        String email = "user" + timestamp + "@test.ru";
        return new UserTest(email, "123456", "User");
    }

    public static UserTest userWithShortPassword() {
        long timestamp = System.currentTimeMillis();
        String email = "user" + timestamp + "@test.ru";
        return new UserTest(email, "12345", "User");
    }
}