package todo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@Table(name = "todo_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "user_zone")
    private LocalDateTime userZone;
}
