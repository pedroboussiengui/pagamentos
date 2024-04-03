package org.pag.pagamentos.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private Setor setor;
    private Set<String> authorities = new HashSet<>();
    private Group group;

    public void addAllAuthority(Role role) {
        authorities.add(role.name());
        for (Permisson permisson : Permisson.values()) {
            if (permisson.getRoleAss().equals(role))
                authorities.add(permisson.getName());
        }
    }

    public void addRole(Role role) {
        authorities.add(role.name());
    }

    public void addPermission(Permisson permisson) throws Exception {
        if (authorities.contains(permisson.getRoleAss().name()))
            authorities.add(permisson.getName());
        else
            throw new Exception("User has no role associate to assign the permission");
    }

    public void removeRole(Role role) {
        authorities.remove(role.name());
        for (Permisson permisson : Permisson.values()) {
            if (permisson.getRoleAss().equals(role))
                authorities.remove(permisson.getName());
        }
    }

    public void removePermisson(Permisson permisson) {
        authorities.remove(permisson.getName());
    }
}
