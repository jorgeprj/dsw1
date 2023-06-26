package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Inscricao;

public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {

        String sql = "INSERT INTO Vaga (id, nome, descricao, cnpj, data_limite) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getId());
            statement.setString(2, vaga.getNome());
            statement.setString(3, vaga.getDescricao());
            statement.setString(4, vaga.getCNPJ());
            statement.setDate(5, vaga.getDataLimite());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {

        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * FROM Vaga";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cnpj = resultSet.getString("cnpj");
                Date dataLimite = resultSet.getDate("data_limite");

                Vaga vaga = new Vaga(id, nome, descricao, cnpj, dataLimite);
                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET nome = ?, descricao = ?, cnpj = ?, data_limite = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, vaga.getNome());
            statement.setString(2, vaga.getDescricao());
            statement.setString(3, vaga.getCNPJ());
            statement.setDate(4, vaga.getDataLimite());
            statement.setLong(5, vaga.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga getById(Long id) {
        Vaga vaga = null;

        String sql = "SELECT * FROM Vaga WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cnpj = resultSet.getString("cnpj");
                Date dataLimite = resultSet.getDate("data_limite");

                vaga = new Vaga(id, nome, descricao, cnpj, dataLimite);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }

    public List<Inscricao> getInscricoesByVaga(Vaga vaga) {
        List<Inscricao> inscricoes = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Date data = resultSet.getDate("data");
                String cpf = resultSet.getString("cpf");
                String curriculo = resultSet.getString("curriculo");

                Inscricao inscricao = new Inscricao(id,data,cpf,curriculo,vaga);
                inscricoes.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inscricoes;
    }
}