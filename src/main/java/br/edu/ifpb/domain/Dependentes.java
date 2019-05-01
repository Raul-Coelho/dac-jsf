package br.edu.ifpb.domain;

import java.util.List;

public interface Dependentes {

    void salvar(Dependente dependente);

    void excluir(Dependente dependente);

    void atualizar(Dependente dependente);

    List<Dependente> todosOsDepentendes();

    Dependente buscarDependenteUUID(String uuid);

}
