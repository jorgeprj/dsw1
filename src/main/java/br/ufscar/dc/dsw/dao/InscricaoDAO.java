package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Vaga;

public class InscricaoDAO extends GenericDAO {

    public void insert(Inscricao inscricao) {

        if (inscricao.getData().after(inscricao.getVaga().getDataLimite())){
            throw new RuntimeException("A data da inscrição deve ser anterior a data limite da vaga que esta sendo aplicada.");
        }

        String sql = "INSERT INTO Inscricao (data, cpf, curriculo, vaga_id) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setDate(1, inscricao.getData());
            statement.setString(2, inscricao.getCpf());
            statement.setString(3, inscricao.getCurriculo());
            statement.setLong(4, inscricao.getVaga().getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Inscricao> getAll() {

        List<Inscricao> listaInscricoes = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i INNER JOIN Vaga v ON i.vaga_id = v.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("i.id");
                Date data = resultSet.getDate("i.data");
                String cpf = resultSet.getString("i.cpf");
                String curriculo = resultSet.getString("i.curriculo");

                Long vagaId = resultSet.getLong("v.id");
                String nomeVaga = resultSet.getString("v.nome");
                String descricaoVaga = resultSet.getString("v.descricao");
                String cnpjVaga = resultSet.getString("v.cnpj");
                Date dataLimiteVaga = resultSet.getDate("v.data_limite");

                Vaga vaga = new Vaga(vagaId, nomeVaga, descricaoVaga, cnpjVaga, dataLimiteVaga);

                Inscricao inscricao = new Inscricao(id, data, cpf, curriculo, vaga);
                listaInscricoes.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaInscricoes;
    }

    public void delete(Inscricao inscricao) {
        String sql = "DELETE FROM Inscricao WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, inscricao.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Inscricao inscricao) {
        String sql = "UPDATE Inscricao SET data = ?, cpf = ?, curriculo = ?, vaga_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setDate(1, inscricao.getData());
            statement.setString(2, inscricao.getCpf());
            statement.setString(3, inscricao.getCurriculo());
            statement.setLong(4, inscricao.getVaga().getId());
            statement.setLong(5, inscricao.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Inscricao getById(Long id) {
        Inscricao inscricao = null;

        String sql = "SELECT * FROM Inscricao i INNER JOIN Vaga v ON i.vaga_id = v.id WHERE i.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Date data = resultSet.getDate("i.data");
                String cpf = resultSet.getString("i.cpf");
                String curriculo = resultSet.getString("i.curriculo");

                Long vagaId = resultSet.getLong("v.id");
                String nomeVaga = resultSet.getString("v.nome");
                String descricaoVaga = resultSet.getString("v.descricao");
                String cnpjVaga = resultSet.getString("v.cnpj");
                Date dataLimiteVaga = resultSet.getDate("v.data_limite");

                Vaga vaga = new Vaga(vagaId, nomeVaga, descricaoVaga, cnpjVaga, dataLimiteVaga);

                inscricao = new Inscricao(id, data, cpf, curriculo, vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return inscricao;
    }
}
