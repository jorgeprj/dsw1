package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {

        String sql = "INSERT INTO Profissional (id, nome, email, senha, papel, cpf, telefone, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getId());
            statement.setString(2, profissional.getNome());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getSenha());
            statement.setString(5, profissional.getPapel());
            statement.setString(6, profissional.getCpf());
            statement.setString(7, profissional.getTelefone());
            statement.setDate(8, profissional.getDataNascimento());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * FROM Profissional";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                Profissional profissional = new Profissional(id, nome, email, senha, cpf, telefone, dataNascimento);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissional WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET nome = ?, email = ?, senha = ?, papel = ?, cpf = ?, telefone = ?, data_nascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setString(4, profissional.getPapel());
            statement.setString(5, profissional.getCpf());
            statement.setString(6, profissional.getTelefone());
            statement.setDate(7, new java.sql.Date(profissional.getDataNascimento().getTime()));
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

        String sql = "SELECT * FROM Profissional WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                profissional = new Profissional(id, nome, email, senha, cpf, telefone, dataNascimento);
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

        String sql = "SELECT * FROM Profissional WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                profissional = new Profissional(id, nome, email, senha, cpf, telefone, dataNascimento);
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
