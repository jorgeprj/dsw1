package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;


@WebServlet(urlPatterns = "/vagas/*")
public class VagaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private VagaDAO dao;

    @Override
    public void init() {
        dao = new VagaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Vaga> listaVagas = dao.getAll();
        request.setAttribute("listaVagas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vaga/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vaga/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Vaga vaga = dao.get(id);
        request.setAttribute("vaga", vaga);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vaga/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String cnpj = request.getParameter("cnpj");
        // Converte a String da data limite para o tipo Date
        Date dataLimite = Date.valueOf(request.getParameter("dataLimite"));

        Vaga vaga = new Vaga(nome, descricao, cnpj, dataLimite);
        dao.insert(vaga);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String cnpj = request.getParameter("cnpj");
        // Converte a String da data limite para o tipo Date
        Date dataLimite = Date.valueOf(request.getParameter("dataLimite"));

        Vaga vaga = new Vaga(id, nome, descricao, cnpj, dataLimite);
        dao.update(vaga);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Vaga vaga = new Vaga(id);
        dao.delete(vaga);
        response.sendRedirect("lista");
    }
}