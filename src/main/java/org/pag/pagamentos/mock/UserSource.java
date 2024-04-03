package org.pag.pagamentos.mock;

import lombok.Getter;
import org.pag.pagamentos.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class UserSource {

    List<User> users = new ArrayList<>();

    public UserSource() throws Exception {

        User user = new User();
        user.setId(2L);
        user.setName("Ana");
        user.setEmail("ana@email.com");
        user.setSetor(Setor.LOCAL);
        user.addRole(Role.GESTOR_RH);
        user.addAllAuthority(Role.GESTOR_PREV);
        user.addPermission(Permisson.PROVIMENTO);
        users.add(user);

        User user2 = new User();
        user2.setId(3L);
        user2.setName("Lucas");
        user2.setEmail("lucas@email.com");
        user2.setSetor(Setor.GLOBAL);
        user2.addRole(Role.ADMIN);
        users.add(user2);

        User user3 = new User();
        user3.setId(4L);
        user3.setName("Pedro");
        user3.setEmail("pedro@email.com");
        user3.setSetor(Setor.LOCAL);
        users.add(user3);
    }

    public User getByName(String name) throws Exception {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new Exception("User not found."));
    }

    public User getById(Long id) throws Exception {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("User not found."));
    }
}
