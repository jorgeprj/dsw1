package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;

public class EmpresaDAO extends UsuarioDAO {

    public void insert(Empresa empresa) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            long idUsuario = usuarioDAO.insert(empresa, "EMPRESA");

            
            String empresaSql = "INSERT INTO Empresa (id, cnpj, cidade) VALUES (?, ?, ?)";
            Connection conn = this.getConnection();
            PreparedStatement empresaStatement = conn.prepareStatement(empresaSql);

            
            empresaStatement.setLong(1, idUsuario);
            empresaStatement.setString(2, empresa.getCnpj());
            empresaStatement.setString(3, empresa.getCidade());
            empresaStatement.executeUpdate();

            empresaStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT" +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Empresa.cnpj, Empresa.cidade"
                +
                " FROM Usuario" +
                " JOIN Empresa ON Usuario.id = Empresa.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                Empresa empresa = new Empresa(id, nome, email, senha, papel, cnpj, cidade);
                
                listaUsuarios.add(empresa); 
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(Empresa empresa) {

        String empresaSql = "DELETE FROM Empresa WHERE id = ?";
        String usuarioSql = "DELETE FROM Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();

            
            PreparedStatement empresaStatement = conn.prepareStatement(empresaSql);
            empresaStatement.setLong(1, empresa.getId());
            empresaStatement.executeUpdate();

            
            PreparedStatement usuarioStatement = conn.prepareStatement(usuarioSql);
            usuarioStatement.setLong(1, empresa.getId());
            usuarioStatement.executeUpdate();

            empresaStatement.close();
            usuarioStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa AS empresa " +
                "INNER JOIN Usuario AS usuario ON empresa.id = usuario.id " +
                "SET usuario.email = ?, " +
                "usuario.senha = ?, " +
                "empresa.cnpj = ?, " +
                "usuario.nome = ?, " +
                "empresa.cidade = ? " +
                "WHERE empresa.id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getEmail());
            statement.setString(2, empresa.getSenha());
            statement.setString(3, empresa.getCnpj());
            statement.setString(4, empresa.getNome());
            statement.setString(5, empresa.getCidade());
            statement.setLong(6, empresa.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empresa get(Long id) {
        Empresa empresa = null;

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Empresa.cnpj, Empresa.cidade"
                +
                " FROM Usuario" +
                " JOIN Empresa ON Usuario.id = Empresa.id" +
                " WHERE Usuario.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                empresa = new Empresa(id, nome, email, senha, papel, cnpj, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }

    public Empresa getByCnpj(String cnpj) {
        Empresa empresa = null;

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Empresa.cidade" +
                " FROM Usuario" +
                " JOIN Empresa ON Usuario.id = Empresa.id" +
                " WHERE Empresa.cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                empresa = new Empresa(id, nome, email, senha, papel, cnpj, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }

    public List<Usuario> getByCidade(String cidade) {
        List<Usuario> listaEmpresasCidade = new ArrayList<>();

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Empresa.cnpj, Empresa.cidade"
                +
                " FROM Usuario" +
                " JOIN Empresa ON Usuario.id = Empresa.id" +
                " WHERE Empresa.cidade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Empresa empresa = new Empresa(id, nome, email, senha, papel, cnpj, cidade);
                listaEmpresasCidade.add(empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEmpresasCidade;
    }

    public List<String> inicializaCidades() {
        List<String> listaCidades = new ArrayList<>();

        listaCidades.add("Sao Carlos");
        listaCidades.add("Sao Paulo");
        listaCidades.add("Ribeirao Preto");
        listaCidades.add("Campinas");
        listaCidades.add("Rio de Janeiro");

        return listaCidades;
    }
}
