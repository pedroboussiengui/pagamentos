package org.pag.pagamentos.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Group {
    private Long id;
    private String title;
    private Set<String> authorities = new HashSet<>();

    public void addRole(Role role) {
        authorities.add(role.name());
    }

    public void addPermission(Permisson permisson) throws Exception {
        if (authorities.contains(permisson.getRoleAss().name()))
            authorities.add(permisson.getName());
        else
            throw new Exception("Group has no role associate to assign the permission");
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
