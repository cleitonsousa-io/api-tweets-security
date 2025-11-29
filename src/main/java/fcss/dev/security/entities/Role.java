package fcss.dev.security.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Entity
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    @Getter
    public enum Values{
        ADMIN(1L),
        BASIC(2L);

        final long roleId;

        Values(long roleId){
            this.roleId = roleId;
        }
    }
}
