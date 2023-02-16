package todo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todo_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "taskList")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "categoryList")
    private List<Task> taskList = new ArrayList<>();
}
