package control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Caixas;
import modelo.CaixasBuilder;

@WebServlet(name = "ControleCaixas", urlPatterns = {"/ControleCaixas"})
public class ControleCaixas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String opc = request.getParameter("Confirma");

            if ("Cadastrar".equals(opc)) {
                
                int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
                
                Caixas caixa = new CaixasBuilder()
                        .comDescricao(request.getParameter("txtDescricao"))
                        .comQuantidade(quantidade)
                        .comResponsavel(request.getParameter("txtResponsavel"))
                        .constroi();

                String url_api = "http://localhost:8080/APICaixas/webresources/generic/caixa/cadastrar";

                try {

                    Gson g = new Gson();
                    sendPost(url_api, g.toJson(caixa), "PUT");

                    request.setAttribute("Mensagem", "Cadastrado com sucesso!");
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            } else if ("Listar".equals(opc)) {

                String url_api = "http://localhost:8080/APICaixas/webresources/generic/caixa/consultartodos";

                try {

                    String st = sendGet(url_api, "GET");
                    Gson g = new Gson();

                    Type ListCaixasType = new TypeToken<ArrayList<Caixas>>() {
                    }.getType();
                    ArrayList<Caixas> caixas = g.fromJson(st, ListCaixasType);

                    request.setAttribute("listaCaixas", caixas);

                    RequestDispatcher rd = request.getRequestDispatcher("listacaixas.jsp");
                    rd.forward(request, response);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            } else if ("Listar Id".equals(opc)) {

                int Id = Integer.parseInt(request.getParameter("txtId"));
                String url_api = "http://localhost:8080/APICaixas/webresources/generic/caixa/get/" + Id;

                try {
                    String st = sendGet(url_api, "GET");
                    Caixas caixa = new Gson().fromJson(st, Caixas.class);

                    request.setAttribute("listaId", caixa);

                    RequestDispatcher rd = request.getRequestDispatcher("listaId.jsp");
                    rd.forward(request, response);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            } else if ("Deletar".equals(opc)) {

                int Id = Integer.parseInt(request.getParameter("txtId"));
                String url_api = "http://localhost:8080/APICaixas/webresources/generic/caixa/deletar/" + Id;

                try {
                    Gson g = new Gson();
                    sendDelete(url_api, "DELETE");
                    
                    request.setAttribute("Mensagem", "Deletado com sucesso com sucesso!");
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            } else if ("Alterar".equals(opc)) {

                int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
                int id = Integer.parseInt(request.getParameter("txtId"));
                
                Caixas caixa = new CaixasBuilder()
                        .comDescricao(request.getParameter("txtDescricao"))
                        .comQuantidade(quantidade)
                        .comResponsavel(request.getParameter("txtResponsavel"))
                        .comId(id)
                        .constroi();
                

                String url_api = "http://localhost:8080/APICaixas/webresources/generic/caixa/alterar";

                try {

                    Gson g = new Gson();
                    sendPost(url_api, g.toJson(caixa), "PUT");

                    request.setAttribute("Mensagem", "Alterado com sucesso!");
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        } catch (Exception e) {
            request.setAttribute("erro", e);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    public static String sendGet(String url, String method) throws Exception {
        URL objurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objurl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer result = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            result.append(inputLine);
        }
        return result.toString();
    }

    /**
     *
     * @param url
     * @param urlParams
     * @param method
     */
    public static void sendPost(String url, String urlParams, String method) throws Exception {

        URL objurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objurl.openConnection();

        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        DataOutputStream dout = new DataOutputStream(con.getOutputStream());
        dout.writeBytes(urlParams);
        dout.flush();
        dout.close();

        int responseCod = con.getResponseCode();
        System.out.println("Resposta...: " + responseCod);
    }
    
        public static void sendDelete(String url, String method) throws Exception {

        URL objurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objurl.openConnection();

        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        DataOutputStream dout = new DataOutputStream(con.getOutputStream());
        dout.flush();
        dout.close();

        int responseCod = con.getResponseCode();
        System.out.println("Resposta...: " + responseCod);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
