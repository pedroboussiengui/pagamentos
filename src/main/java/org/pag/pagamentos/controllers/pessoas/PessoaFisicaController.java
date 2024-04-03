package org.pag.pagamentos.controllers.pessoas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoas/pessoasfisicas")
public class PessoaFisicaController {

    @PostMapping
    public String cadastrar() {
        return "Cadastrando pessoa...";
    }
}
