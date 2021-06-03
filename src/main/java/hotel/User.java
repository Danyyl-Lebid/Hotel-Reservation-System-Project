package hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class User {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private UserRole role;

    public User(String phone, String password, String firstName, String lastName, UserRole role) {
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

}
