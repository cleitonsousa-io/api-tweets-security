package fcss.dev.security.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String name;


    // GETTERS
    public Long getRoleId() {
        return roleId;
    }
    public String getName() {
        return name;
    }

    // SETTERS
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public enum Values{
        ADMIN(1L),
        BASIC(2L);
        final long roleId;
        Values(long roleId){
            this.roleId = roleId;
        }
        public long getRoleId() {
            return roleId;
        }
    }
}
