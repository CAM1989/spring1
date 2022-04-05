package ru.gb;

import ru.gb.persist.Product;
import ru.gb.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("ProductRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
//        wr.println("<p>ContextPath: "+ req.getContextPath() + "</p>");
//        wr.println("<p>ServletPath: "+ req.getServletPath() + "</p>");
//        wr.println("<p>PathInfo: "+ req.getPathInfo() + "</p>");
//        wr.println("<p>QueryString: "+ req.getQueryString() + "</p>");
//        wr.println("<p>param1: "+ req.getParameter("param1") + "</p>");
//        wr.println("<p>param2: "+ req.getParameter("param2") + "</p>");

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>ID</th>");
            wr.println("<th>Product</th>");
            wr.println("<th>Cost</th>");
            wr.println("</tr>");
            for (Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td><a href='" + req.getContextPath() + req.getServletPath() + "/" + product.getId() + "'>" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getName() + "</td>");
                wr.println("<td>" + product.getCost() + "</td>");
                wr.println("</tr>");
            }
            wr.println("</table>");
        } else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if (matcher.matches()) {
                Long id = Long.parseLong(matcher.group(1));
                Product product = this.productRepository.findById(id);
                if (product == null) {
                    wr.println("<p>Product not found</p>");
                    resp.setStatus(404);
                    return;
                }
                wr.println("<p>ID: " + product.getId() + "</p>");
                wr.println("<p>Product: " + product.getName() + "</p>");
                wr.println("<p>Cost: " + product.getCost() + "</p>");
                wr.println("<td><a href='" + req.getContextPath() + req.getServletPath() + "'> >Back< </a></td>");
                resp.setStatus(200);
            } else {
                wr.println("<p>Bad parameters</p>");
                resp.setStatus(400);
            }
        }
    }
}

