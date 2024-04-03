package org.pag.pagamentos.model;

import lombok.Getter;

@Getter
public enum Permisson {
    PESSOAFISICA("permission:pessoafisica", Role.CADASTRANTE),
    DEMISSAO("permission:demissao", Role.GESTOR_RH),
    PROVIMENTO("permission:provimento", Role.GESTOR_RH),
    FALECIMENTO("permission:falecimento", Role.GESTOR_RH),
    SETOR("permission:setor", Role.ANALISTA_RH),
    CARGO("permission:cargo", Role.ANALISTA_RH),
    FUNCAO("permission:funcao", Role.ANALISTA_RH),
    DEPENDENTE("permission:dependentes", Role.GESTOR_PREV);

    private final String name;
    private final Role roleAss;

    Permisson(String name, Role role) {
        this.name = name;
        this.roleAss = role;
    }
}
