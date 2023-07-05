package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet(urlPatterns = "/empresas/*")
public class AdminEmpresaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EmpresaDAO dao;

    @Override
    public void init() {
        dao = new EmpresaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    apresentaFormCadastroEmpresa(request, response);
                    break;
                case "/insercao":
                    insereEmpresa(request, response);
                    break;
                case "/remocao":
                    removeEmpresa(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoEmpresa(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    listaEmpresa(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaEmpresas = dao.getAll();
        request.setAttribute("listaEmpresas", listaEmpresas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/empresas/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Empresa empresa = dao.get(id);
        request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/empresas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cnpj = request.getParameter("cnpj");
        String cidade = request.getParameter("cidade");

        Empresa empresa = new Empresa(id, nome, email, senha, papel, cnpj, cidade);
        dao.update(empresa);
        response.sendRedirect("lista");
    }

    private void apresentaFormCadastroEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/empresas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cnpj = request.getParameter("cnpj");
        String cidade = request.getParameter("cidade");

        Empresa empresa = new Empresa(nome, email, senha, papel, cnpj, cidade);
        dao.insert(empresa);
        response.sendRedirect("lista");
    }

    private void removeEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Empresa empresa = new Empresa(id);
        dao.delete(empresa);
        response.sendRedirect("lista");
    }
}