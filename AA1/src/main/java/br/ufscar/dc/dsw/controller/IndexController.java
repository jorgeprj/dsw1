package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet(name = "Index", urlPatterns = {"/index.jsp", "/logout.jsp"})
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EmpresaDAO empresaDao = new EmpresaDAO();
        
        List<String> listaCidades = empresaDao.inicializaCidades();
        request.setAttribute("listaCidades", listaCidades);

        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            if (email == null || email.isEmpty()) {
                erros.add("E-mail não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }

            if (!erros.isExisteErros()) {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.getbyEmail(email);
                if (usuario != null) {
                    if (usuario.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("usuarioLogado", usuario);

                        if (usuario.getPapel().equals("PROFISSIONAL")) {
                            response.sendRedirect("profissional/");
                        } else if (usuario.getPapel().equals("EMPRESA")) {
                            response.sendRedirect("empresa/");
                        } else if(usuario.getPapel().equals("ADMIN")){
							response.sendRedirect("admin/");
						}
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Usuário não encontrado!");
                }
            }
        }

        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
