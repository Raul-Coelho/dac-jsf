package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependentes;

import java.util.ArrayList;
import java.util.List;

public class DependenteEmMemoria implements Dependentes {

    private final List<Dependente> dependentes = new ArrayList<>();

    @Override
    public void salvar(Dependente dependente) {
        dependentes.add(dependente);
    }

    @Override
    public void excluir(Dependente dependente) {
        dependentes.remove(dependente);
    }

    @Override
    public void atualizar(Dependente dependente) {
        dependentes.remove(dependente);
        dependentes.add(dependente);
    }

    @Override
    public List<Dependente> todosOsDepentendes() {
        return dependentes;
    }

    @Override
    public Dependente buscarDependenteUUID(String uuid) {
        for (Dependente d:dependentes) {
            if(d.getUuid().equals(uuid)){
                return d;
            }
        }
        return null;
    }

}
