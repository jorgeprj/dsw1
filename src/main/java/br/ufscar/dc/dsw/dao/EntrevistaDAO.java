package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.domain.Entrevista;

public class EntrevistaDAO extends GenericDAO {

    public void insert(Entrevista entrevista) {

        String sql = "INSERT INTO Entrevista (cpf_profissional, cnpj_empresa, data_hora) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, entrevista.getCpfProfissional());
            statement.setString(2, entrevista.getCnpjEmpresa());
            statement.setString(3, entrevista.getDataHora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Entrevista> getAll() {

        List<Entrevista> listaEntrevistas = new ArrayList<>();

        String sql = "SELECT * FROM Entrevista ORDER BY data_hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfProfissional = resultSet.getString("cpf_profissional");
                String cnpjEmpresa = resultSet.getString("cnpj_empresa");
                String dataHora = resultSet.getString("data_hora");
                Entrevista entrevista = new Entrevista(id, cpfProfissional, cnpjEmpresa, dataHora);
                listaEntrevistas.add(entrevista);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEntrevistas;
    }

    public List<Entrevista> getAllbyCnpj(String cnpj) {

        List<Entrevista> listaEntrevistas = new ArrayList<>();

        String sql = "SELECT * FROM Entrevista WHERE cnpj_empresa = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfProfissional = resultSet.getString("cpf_profissional");
                String cnpjEmpresa = resultSet.getString("cnpj_empresa");
                String dataHora = resultSet.getString("data_hora");
                Entrevista entrevista = new Entrevista(id, cpfProfissional, cnpjEmpresa, dataHora);
                listaEntrevistas.add(entrevista);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEntrevistas;
    }

    public List<Entrevista> getEntrevistasByCpfProfissional(String cpfProfissional) {
        List<Entrevista> listaEntrevistas = new ArrayList<>();

        String sql = "SELECT * FROM Entrevista WHERE cpf_profissional = ? ORDER BY data_hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfProfissional);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cnpjEmpresa = resultSet.getString("cnpj_empresa");
                String dataHora = resultSet.getString("data_hora");
                Entrevista entrevista = new Entrevista(id, cpfProfissional, cnpjEmpresa, dataHora);
                listaEntrevistas.add(entrevista);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEntrevistas;
    }

    public List<Entrevista> getEntrevistasByCnpjEmpresa(String cnpjEmpresa) {
        List<Entrevista> listaEntrevistas = new ArrayList<>();

        String sql = "SELECT * FROM Entrevista WHERE cnpj_empresa = ? ORDER BY data_hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpjEmpresa);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfProfissional = resultSet.getString("cpf_profissional");
                String dataHora = resultSet.getString("data_hora");
                Entrevista entrevista = new Entrevista(id, cpfProfissional, cnpjEmpresa, dataHora);
                listaEntrevistas.add(entrevista);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEntrevistas;
    }

    public List<String> getNomeEmpresa(String cpfProfissional) {
        List<String> resp = new ArrayList<>();
        List<Entrevista> lista = getEntrevistasByCpfProfissional(cpfProfissional);
        for (Entrevista con : lista) {
            EmpresaDAO daoEmpresa = new EmpresaDAO();

            resp.add(daoEmpresa.getByCnpj(con.getCnpjEmpresa()).getNome());
        }
        return resp;
    }

    public List<String> getNomeProfissional(String cnpjEmpresa) {
        List<String> resp = new ArrayList<>();
        List<Entrevista> lista = getEntrevistasByCnpjEmpresa(cnpjEmpresa);
        for (Entrevista con : lista) {
            
            ProfissionalDAO daoProfissional = new ProfissionalDAO();

            resp.add(daoProfissional.getByCPF(con.getCpfProfissional()).getNome());
        }
        return resp;
    }

    public void delete(Entrevista entrevista) {
        try {
            String sql = "DELETE FROM Entrevista WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, entrevista.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Entrevista get(Long id) {
        Entrevista entrevista = null;

        try {
            String sql = "SELECT * FROM Entrevista WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cpfProfissional = resultSet.getString("cpf_profissional");
                String cnpjEmpresa = resultSet.getString("cnpj_empresa");
                String dataHora = resultSet.getString("data_hora");

                entrevista = new Entrevista(id, cpfProfissional, cnpjEmpresa, dataHora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entrevista;
    }

    public void update(Entrevista entrevista) {
        try {
            String sql = "UPDATE Entrevista SET cpf_profissional = ?, cnpj_empresa = ?, data_hora = ? WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, entrevista.getCpfProfissional());
            statement.setString(2, entrevista.getCnpjEmpresa());
            statement.setString(3, entrevista.getDataHora());
            statement.setLong(4, entrevista.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaDisponibilidade(String cpfProfissional, String cnpjEmpresa, String dataHora) {
        boolean disponivel = true;

        List<Entrevista> entrevistasProfissional = getEntrevistasByCpfProfissional(cpfProfissional);
        for (Entrevista entrevista : entrevistasProfissional) {
            if (entrevista.getDataHora().equals(dataHora)) {
                disponivel = false;
                break;
            }
        }

        if (disponivel) {
            List<Entrevista> entrevistasEmpresa = getAllbyCnpj(cnpjEmpresa);
            for (Entrevista entrevista : entrevistasEmpresa) {
                if (entrevista.getDataHora().equals(dataHora)) {
                    disponivel = false;
                    break;
                }
            }
        }

        return disponivel;
    }

    public List<String> getDatasFormatadas(List<Entrevista> listaEntrevistas) {
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");

        
        List<String> listaData = new ArrayList<>();

        for (Entrevista entrevista : listaEntrevistas) {
            try {
                Date dataHora = formatoOriginal.parse(entrevista.getDataHora());
                String dataFormatada = formatoDesejado.format(dataHora);
                listaData.add(dataFormatada);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }

        return listaData;
    }

    public List<String> getHorasFormatadas(List<Entrevista> listaEntrevistas) {
        List<String> listaHora = new ArrayList<>();
        DateTimeFormatter formatoHoraOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatoHoraDesejado = DateTimeFormatter.ofPattern("HH:mm");

        for (Entrevista entrevista : listaEntrevistas) {
            try {
                LocalDateTime dataHora = LocalDateTime.parse(entrevista.getDataHora(), formatoHoraOriginal);
                String horaFormatada = dataHora.format(formatoHoraDesejado);
                listaHora.add(horaFormatada);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        }

        return listaHora;
    }
}
