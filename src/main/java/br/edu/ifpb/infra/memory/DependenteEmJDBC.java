package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependentes;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author raul on 08/09/19
 */
public class DependenteEmJDBC implements Serializable {

    private Dependente dependente = new Dependente();
    private List<Dependente> todosOsDependentes;

    @Inject
    private Dependentes service;



    public String salvar(){
        service.salvar(dependente);
        dependente = new Dependente();
        atualizarLista();
        return "";
    }

    private void atualizarLista() {
        this.todosOsDependentes = service.todosOsDepentendes();
    }

    public String atualizar(){
        service.atualizar(dependente);
        atualizarLista();
        return "";
    }

    public String editar(Dependente dependente){
        this.dependente = dependente;
        return "";

    }

    public String excluir(Dependente dependente){
        service.excluir(dependente);
        atualizarLista();
        return "";
    }

    public List<Dependente> getTodosOsDependentes() {
        return todosOsDependentes;
    }

    public void setTodosOsDependentes(List<Dependente> todosOsDependentes) {
        this.todosOsDependentes = todosOsDependentes;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }
}
