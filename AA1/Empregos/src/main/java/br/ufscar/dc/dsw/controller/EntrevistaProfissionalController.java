package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.utils.Erro;
import br.ufscar.dc.dsw.dao.EntrevistaDAO;
import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Usuario;



@WebServlet(urlPatterns = "/entrevistas-profissional/*")

public class EntrevistaProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EntrevistaDAO daoEntrevista;
    private EmpresaDAO daoEmpresa;

    @Override
    public void init() {
        daoEntrevista = new EntrevistaDAO();
        daoEmpresa = new EmpresaDAO();
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

        request.setAttribute("profissionalSelecionado", request.getParameter("crm"));
        request.setAttribute("dataSelecionada", request.getParameter("data"));
        request.setAttribute("horaSelecionada", request.getParameter("hora"));

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().equals("PROFISSIONAL")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Profissionais têm acesso a essa página.");
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
                    apresentaFormCadastroEntrevista(request, response);
                    break;
                case "/insercao":
                    insereEntrevista(request, response);
                    break;
                case "/remocao":
                    removeEntrevista(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoEntrevista(request, response);
                    break;
                case "/atualizacao":
                    atualizaEntrevista(request, response);
                    break;
                default:
                    listaEntrevista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormCadastroEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaEmpresas = daoEmpresa.getAll();
        request.setAttribute("listaEmpresas", listaEmpresas);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/profissional/entrevistas-profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Profissional profissional = (Profissional) request.getSession().getAttribute("usuarioLogado");
        String cpfProfissional = profissional.getCpf();

        String cnpjEmpresa = request.getParameter("cnpj");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String dataHora = data + " " + hora + ":00";

        Entrevista entrevista = new Entrevista(cpfProfissional, cnpjEmpresa, dataHora);

        boolean disponivel = daoEntrevista.verificaDisponibilidade(cpfProfissional, cnpjEmpresa, dataHora);

        if (disponivel) {
            daoEntrevista.insert(entrevista);
            response.sendRedirect("lista");
        } else {
            request.setAttribute("disponibilidade", false);
            apresentaFormCadastroEntrevista(request, response);
        }
    }

    private void listaEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Profissional profissional = (Profissional) usuarioLogado;
        String cpfProfissional = profissional.getCpf();

        List<Entrevista> listaEntrevistas = daoEntrevista.getEntrevistasByCpfProfissional(cpfProfissional);
        request.setAttribute("listaEntrevistas", listaEntrevistas);

        List<String> listaNomes = daoEntrevista.getNomeEmpresa(cpfProfissional);
        request.setAttribute("listaNomes", listaNomes);

        List<String> listaData = daoEntrevista.getDatasFormatadas(listaEntrevistas);
        request.setAttribute("listaData", listaData);

        List<String> listaHora = daoEntrevista.getHorasFormatadas(listaEntrevistas);
        request.setAttribute("listaHora", listaHora);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/profissional/entrevistas-profissional/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void removeEntrevista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Entrevista entrevista = new Entrevista(id);
        daoEntrevista.delete(entrevista);
        response.sendRedirect("lista");
    }

    private void apresentaFormEdicaoEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaEmpresas = daoEmpresa.getAll();
        request.setAttribute("listaEmpresas", listaEmpresas);

        Long id = Long.parseLong(request.getParameter("id"));
        Entrevista entrevista = daoEntrevista.get(id);
        request.setAttribute("entrevista", entrevista);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/profissional/entrevistas-profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizaEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Profissional profissional = (Profissional) usuarioLogado;
        String cpfProfissional = profissional.getCpf();

        String cnpjEmpresa = request.getParameter("cnpj");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String dataHora = data + " " + hora + ":00";

        Entrevista entrevista = new Entrevista(id, cpfProfissional, cnpjEmpresa, dataHora);

        boolean disponivel = daoEntrevista.verificaDisponibilidade(cpfProfissional, cnpjEmpresa, dataHora);

        if (disponivel) {
            daoEntrevista.update(entrevista);
            response.sendRedirect("lista");
        } else {
            request.setAttribute("disponibilidade", false);
            apresentaFormEdicaoEntrevista(request, response);
        }
    }
}