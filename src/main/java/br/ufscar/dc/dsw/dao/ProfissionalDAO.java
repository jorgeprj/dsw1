package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public class ProfissionalDAO extends UsuarioDAO {


    public void insert(Profissional profissional) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            long idUsuario = usuarioDAO.insert(profissional, "PROFISSIONAL");

            
            String profissionalSql = "INSERT INTO Profissional (id, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";
            Connection conn = this.getConnection();
            PreparedStatement profissionalStatement = conn.prepareStatement(profissionalSql);

            profissionalStatement.setLong(1, idUsuario);
            profissionalStatement.setString(2, profissional.getCpf());
            profissionalStatement.setString(3, profissional.getTelefone());
            profissionalStatement.setString(4, profissional.getSexo());
            profissionalStatement.setString(5, profissional.getDataNascimento());
            profissionalStatement.executeUpdate();

            profissionalStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT" + 
        " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Profissional.cpf, Profissional.telefone, Profissional.sexo, Profissional.data_nascimento" +
        " FROM Usuario" +
        " JOIN Profissional ON Usuario.id = Profissional.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");
                Profissional profissional = new Profissional(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
                listaUsuarios.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }


    public void delete(Profissional profissional) {

        String profissionalSql = "DELETE FROM Profissional WHERE id = ?";
        String usuarioSql = "DELETE FROM Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();

            PreparedStatement profissionalStatement = conn.prepareStatement(profissionalSql);
            profissionalStatement.setLong(1, profissional.getId());
            profissionalStatement.executeUpdate();

            PreparedStatement usuarioStatement = conn.prepareStatement(usuarioSql);
            usuarioStatement.setLong(1, profissional.getId());
            usuarioStatement.executeUpdate();

            profissionalStatement.close();
            usuarioStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional) {

        String sql = "UPDATE Profissional AS profissional " +
                     "INNER JOIN Usuario AS usuario ON profissional.id = usuario.id " + 
                     "SET usuario.email = ?, " +
                     "usuario.senha = ?, " +
                     "profissional.cpf = ?, " +
                     "usuario.nome = ?, " +
                     "profissional.telefone = ?, " +
                     "profissional.sexo = ?, " +
                     "profissional.data_nascimento = ? " +
                     "WHERE profissional.id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getTelefone());
            statement.setString(6, profissional.getSexo());
            statement.setString(7, profissional.getDataNascimento());
            statement.setLong(8, profissional.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long id) {
        Profissional profissional = null;

        String sql = "SELECT " +
        " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Profissional.cpf, Profissional.telefone, Profissional.sexo, Profissional.data_nascimento" +
        " FROM Usuario" +
        " JOIN Profissional ON Usuario.id = Profissional.id" +
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
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");
                profissional = new Profissional(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public Profissional getByCPF(String cpf) {
        Profissional profissional = null;

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Profissional.cpf, Profissional.telefone, " +
                " Profissional.sexo, Profissional.data_nascimento FROM Usuario" +
                " JOIN Profissional ON Usuario.id = Profissional.id" +
                " WHERE Profissional.cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");

                profissional = new Profissional(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}
