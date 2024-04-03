package org.pag.pagamentos.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth/groups")
public class GroupController {

    public void create() {}

    // e melhor que as roles e permissions do grupo nao vazem para user, pois ao excluir um grupo eu nao preciso
    // excluir as authorities de cada usuario do grupo
    public void delete() {}
}
