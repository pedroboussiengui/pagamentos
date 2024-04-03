package org.pag.pagamentos.controllers.eventos;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/eventos/demissao")
@PreAuthorize("hasRole('ADMIN') or hasAuthority('permission:demissao')")
public class DemissaoController {

    @PostMapping
    public String realizarDemissao() {
        return "Realizando demissao...";
    }
}