package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.domain.Pessoas;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author raul on 06/09/19
 */
@Stateless
//@Remote(value = Pessoas.class)
public class PessoaEmJDBC implements Pessoas {

    @Resource(name = "java:app/jdbc/dacjsf")
    private DataSource dataSource;
    private Connection connection;

    @PostConstruct
    public void init() {
        try {
            this.connection = this.dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    private Dependente buscarDep(String uuid) throws SQLException {
        String sql = "SELECT * FROM dependente WHERE uuid = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, uuid);
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            return new Dependente(
                    resultSet.getString("uuid"),
                    resultSet.getString("nome"),
                    resultSet.getDate("dataNascimento").toLocalDate()
            );
        }
        return null;
    }

    private Pessoa montarPessoa(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(new CPF(resultSet.getString("cpf")));
        pessoa.setNome(resultSet.getString("nome"));
        pessoa.setDependente(buscarDep(resultSet.getString("id_dependente")));
        return pessoa;
    }


    @Override
    public void nova(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (cpf, nome, id_dependente) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getCpf().valor());
            ps.setString(2, pessoa.getNome());
            if(pessoa.getDependente() != null) {
                ps.setString(3, pessoa.getDependente().getUuid());
            }else{
                ps.setString(3, null);
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pessoa> todas() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                pessoas.add(montarPessoa(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    @Override
    public void excluir(Pessoa pessoa) {
        String sql = "DELETE FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getCpf().valor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, id_dependente = ? WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            if(pessoa.getDependente() != null){
                ps.setString(2, pessoa.getDependente().getUuid());
            }else{
                ps.setString(2,null);
            }
            ps.setString(3, pessoa.getCpf().valor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pessoa buscar(CPF cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf.valor());
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return montarPessoa(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pessoa> buscar(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf LIKE ?";
        List<Pessoa> pessoas = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+cpf+"%");
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                pessoas.add(montarPessoa(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    @Override
    public List<Dependente> todosOsDepentendes() {
        return null;
    }

    @Override
    public Dependente localizarDependenteComId(String uuid) {
        return null;
    }
}
