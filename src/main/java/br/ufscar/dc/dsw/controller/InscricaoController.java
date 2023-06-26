package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Vaga;

@WebServlet(urlPatterns = "/inscricoes/*")
public class InscricaoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private InscricaoDAO dao;
    private VagaDAO vagaDAO;

    @Override
    public void init() {
        dao = new InscricaoDAO();
        vagaDAO = new VagaDAO();
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
        List<Inscricao> listaInscricoes = dao.getAll();
        request.setAttribute("listaInscricoes", listaInscricoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/inscricao/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Vaga> listaVagas = vagaDAO.getAll();
        request.setAttribute("listaVagas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/inscricao/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Date data = Date.valueOf(request.getParameter("data"));
        String cpf = request.getParameter("cpf");
        String curriculo = request.getParameter("curriculo");
        Long vagaId = Long.parseLong(request.getParameter("vaga"));

        Vaga vaga = vagaDAO.get(vagaId);
        Inscricao inscricao = new Inscricao(data, cpf, curriculo, vaga);
        dao.insert(inscricao);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Inscricao inscricao = new Inscricao(id);
        dao.delete(inscricao);
        response.sendRedirect("lista");
    }
}