package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet(urlPatterns = "/profissionais/*")
public class AdminProfissionalController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().equals("ADMIN")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [ADMIN] tem acesso a essa página.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");

            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastroProfissional(request, response);
                    break;
                case "/insercao":
                    insereProfissional(request, response);
                    break;
                case "/remocao":
                    removeProfissional(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoProfissional(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    listaProfissional(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaProfissionais = dao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissionais/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Profissional profissional = dao.get(id);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissionais/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");
        
        Profissional profissional = new Profissional(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
        dao.update(profissional);
        response.sendRedirect("lista");
    }

    private void apresentaFormCadastroProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissionais/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");
        
        Profissional profissional = new Profissional(nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
        dao.insert(profissional);
        response.sendRedirect("lista");
    }

    private void removeProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Profissional profissional = new Profissional(id);
        dao.delete(profissional);
        response.sendRedirect("lista");
    }
}