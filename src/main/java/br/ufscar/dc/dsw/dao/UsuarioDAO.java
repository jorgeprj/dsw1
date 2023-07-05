package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public long insert(Usuario usuario) {
        return insert(usuario, "ADMIN");
    }

    public long insert(Usuario usuario, String papel) {
        long userID = 0;
        String sql = "INSERT INTO Usuario (nome, email, senha, papel) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, papel);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                userID = rs.getLong(1);
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userID;
    }

    public Usuario getbyEmail(String email) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                

                if (papel.equals("PROFISSIONAL")) {
                    String profissionalSql = "SELECT * FROM Profissional WHERE id = ?";
                    PreparedStatement profissionalStatement = conn.prepareStatement(profissionalSql);
                    profissionalStatement.setLong(1, id);
                    ResultSet profissionalResultSet = profissionalStatement.executeQuery();

                    if (profissionalResultSet.next()) {
                        String cpf = profissionalResultSet.getString("cpf");
                        String telefone = profissionalResultSet.getString("telefone");
                        String sexo = profissionalResultSet.getString("sexo");
                        String dataNascimento = profissionalResultSet.getString("data_nascimento");

                        usuario = new Profissional(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
                    }

                    profissionalStatement.close();
                    profissionalResultSet.close();
                } else if (papel.equals("EMPRESA")) {
                    String empresaSql = "SELECT * FROM Empresa WHERE id = ?";
                    PreparedStatement empresaStatement = conn.prepareStatement(empresaSql);
                    empresaStatement.setLong(1, id);
                    ResultSet empresaResultSet = empresaStatement.executeQuery();

                    if (empresaResultSet.next()) {
                        String cnpj = empresaResultSet.getString("cnpj");
                        String cidade = empresaResultSet.getString("cidade");

                        usuario = new Empresa(id, nome, email, senha, papel, cnpj, cidade);
                    }

                    empresaStatement.close();
                    empresaResultSet.close();
                } else {
                    usuario = new Usuario(id, nome, email, senha, papel);
                }
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    public void update(Usuario usuario) {
        try {
            String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setLong(4, usuario.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Usuario usuario) {
        try {
            String sql = "DELETE FROM Usuario WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get(Long id) {
        Usuario usuario = null;

        try {
            String sql = "SELECT * FROM Usuario WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                usuario = new Usuario(id, nome, email, senha, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            // String sql = "SELECT * FROM Usuario";
            String sql = "SELECT * FROM Usuario";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                Usuario usuario = new Usuario(id, nome, email, senha, papel);
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

}