package hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @Column(columnDefinition = "BIGINT", nullable = false)
    @Getter @Setter
    private long id;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String phone;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String password;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String firstName;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String lastName;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String phone, String password, String firstName, String lastName, UserRole role) {
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

}
