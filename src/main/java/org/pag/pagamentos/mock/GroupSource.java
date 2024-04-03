package org.pag.pagamentos.mock;

import lombok.Getter;
import org.pag.pagamentos.model.Group;
import org.pag.pagamentos.model.Permisson;
import org.pag.pagamentos.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class GroupSource {

    List<Group> groups = new ArrayList<>();

    public GroupSource() throws Exception {
        Group group = new Group();
        group.setId(1L);
        group.setTitle("Grupos dos picapau");
        group.addRole(Role.GESTOR_RH);
        group.addPermission(Permisson.PROVIMENTO);
        group.addPermission(Permisson.DEMISSAO);
        groups.add(group);
    }

    public Group getGroupById(Long id) throws Exception {
        return groups.stream()
                .filter(group -> group.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Group not found"));
    }
}
