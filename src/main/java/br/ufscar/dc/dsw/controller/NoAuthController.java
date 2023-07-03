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
import br.ufscar.dc.dsw.domain.Usuario;

@WebServlet(urlPatterns = "/noAuth/*")
public class NoAuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EmpresaDAO dao;

    @Override
    public void init() {
        dao = new EmpresaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/listaEmpresasCidade":
                listaEmpresasCidade(request, response);
                break;
            default:
                listaEmpresas(request, response);
                break;
        }
    }

    private void listaEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaEmpresas = dao.getAll();
        request.setAttribute("listaEmpresas", listaEmpresas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/noAuthView/consulta-empresas.jsp");
        dispatcher.forward(request, response);
    }

    private void listaEmpresasCidade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String cidade = request.getParameter("cidade");

        
        List<Usuario> listaEmpresasCidade = dao.getByCidade(cidade);

        
        request.setAttribute("listaEmpresasCidade", listaEmpresasCidade);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/noAuthView/listaEmpresasCidade.jsp");
        dispatcher.forward(request, response);
    }
}
