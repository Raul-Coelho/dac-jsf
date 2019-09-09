package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

public interface Dependentes  extends Serializable {

    void salvar(Dependente dependente);

    void excluir(Dependente dependente);

    void atualizar(Dependente dependente);

    List<Dependente> todosOsDepentendes();

    Dependente buscarDependenteUUID(String uuid);

}
