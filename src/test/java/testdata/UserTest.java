package testdata;

import java.util.Objects;

public class UserTest {
    private String email;
    private String password;
    private String name;

    public UserTest() {
    }

    public UserTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest testUser = (UserTest) o;
        return Objects.equals(email, testUser.email) &&
                Objects.equals(password, testUser.password) &&
                Objects.equals(name, testUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name);
    }

    @Override
    public String toString() {
        return String.format("UserTest{email='%s', password='%s', name='%s'}",
                email, password, name);
    }
}