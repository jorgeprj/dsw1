package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO {

    public void insert(Empresa empresa) {

        String sql = "INSERT INTO Empresa (id, nome, email, senha, papel, cnpj, cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, empresa.getId());
            statement.setString(2, empresa.getNome());
            statement.setString(3, empresa.getEmail());
            statement.setString(4, empresa.getSenha());
            statement.setString(5, empresa.getPapel());
            statement.setString(6, empresa.getCNPJ());
            statement.setString(7, empresa.getCidade());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Empresa> getAll() {

        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * FROM Empresa";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");

                Empresa empresa = new Empresa(id, nome, email, senha, cnpj, cidade);
                listaEmpresas.add(empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }

    public void delete(Empresa empresa) {
        String sql = "DELETE FROM Empresa WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, empresa.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET nome = ?, email = ?, senha = ?, papel = ?, cnpj = ?, cidade = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getEmail());
            statement.setString(3, empresa.getSenha());
            statement.setString(4, empresa.getPapel());
            statement.setString(5, empresa.getCNPJ());
            statement.setString(6, empresa.getCidade());
            statement.setLong(7, empresa.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empresa get(Long id) {
        Empresa empresa = null;

        String sql = "SELECT * FROM Empresa WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");

                empresa = new Empresa(id, nome, email, senha, cnpj, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
}