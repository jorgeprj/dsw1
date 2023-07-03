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
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;

@WebServlet(urlPatterns = "/entrevistas-empresa/*")

public class EntrevistaEmpresaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EntrevistaDAO daoEntrevista;

    @Override
    public void init() {
        daoEntrevista = new EntrevistaDAO();
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
        } else if (!usuario.getPapel().equals("EMPRESA")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [EMPRESA] tem acesso a essa página.");
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
                case "/remocao":
                    removeEntrevista(request, response);
                    break;
                default:
                    listaEntrevista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Empresa empresa = (Empresa) usuarioLogado;
        String cnpjEmpresa = empresa.getCnpj();

        List<Entrevista> listaEntrevistas = daoEntrevista.getEntrevistasByCnpjEmpresa(cnpjEmpresa);
        request.setAttribute("listaEntrevistas", listaEntrevistas);

        List<String> listaNomes = daoEntrevista.getNomeProfissional(cnpjEmpresa);
        request.setAttribute("listaNomes", listaNomes);


        List<String> listaData = daoEntrevista.getDatasFormatadas(listaEntrevistas);
        request.setAttribute("listaData", listaData);

        List<String> listaHora = daoEntrevista.getHorasFormatadas(listaEntrevistas);
        request.setAttribute("listaHora", listaHora);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/empresa/entrevistas-empresa/lista.jsp");
        dispatcher.forward(request, response);

    }

    private void removeEntrevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        System.out.println(id);

        Entrevista entrevista = new Entrevista(id);
        daoEntrevista.delete(entrevista);
        response.sendRedirect("lista");
    }
}
