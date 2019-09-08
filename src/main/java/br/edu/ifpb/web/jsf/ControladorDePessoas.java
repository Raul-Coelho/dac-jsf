package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.domain.Pessoas;
import br.edu.ifpb.infra.memory.PessoaEmJDBC;
import br.edu.ifpb.infra.memory.PessoasEmMemoria;
import java.io.Serializable;
import java.util.List;
//import javax.faces.bean.RequestScoped;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2019, 10:23:24
 */

@SessionScoped
@Named
public class ControladorDePessoas implements Serializable {

    private Dependente dependente = new Dependente();

    private Pessoa pessoa = new Pessoa();

    private Pessoas service = new PessoaEmJDBC();


    public String salvar() {
        this.service.nova(pessoa);
        this.pessoa = new Pessoa();
        return "list.xhtml?faces-redirect=true";
    }

    public String atualizar() {
        this.service.atualizar(pessoa);
        this.pessoa = new Pessoa();
        return null;
    }

    public String excluir(Pessoa pessoa) {
        this.service.excluir(pessoa);
        return null;
    }

    public String editar(Pessoa pessoa) {
        this.pessoa = pessoa;
        return "edit.xhtml?faces-redirect=true";
    }

    public String listar(){
        return "list.xhtml?faces-redirect=true";
    }

    public List<Pessoa> getTodasAsPessoas() {
        return this.service.todas();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }
}
