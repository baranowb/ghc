package ghc.undertow;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet IMPL that dispatch in different way, based on URL
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("doGet: " + req.getRequestURI() + " " + getClass());
        final String uriString = req.getRequestURI();
        final ServletOutputStream streamer = resp.getOutputStream();
        final ServletContext context = req.getServletContext();
        if (uriString.contains("forwarder")) {
            startDispatcherStatusPage("FORWARD", streamer);
            //Absolute path in context of application
            final RequestDispatcher dispatcher = context.getRequestDispatcher("/forwardTarget");
            //FORWARD
            dispatcher.forward(req, resp);
            endDispatcherStatusPage(streamer);
            resp.setStatus(201);
            resp.flushBuffer();
        } else if (uriString.contains("includer")) {
            startDispatcherStatusPage("INCLUDE", streamer);
            //Absolute path in context of application
            final RequestDispatcher dispatcher = context.getRequestDispatcher("/included");
            //INCLUDE
            dispatcher.include(req, resp);
            endDispatcherStatusPage(streamer);
            resp.setStatus(202);
            resp.flushBuffer();
        } else {
            resp.setStatus(500);
            resp.flushBuffer();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void startDispatcherStatusPage(String title, ServletOutputStream streamer) throws IOException {
        streamer.write("<!DOCTYPE HTML>".getBytes());
        streamer.write("<html lang=\"en\">".getBytes());
        streamer.write("<head>".getBytes());
        streamer.write("<META charset=\"UTF-8\">".getBytes());
        streamer.write("<META name=\"viewport\"".getBytes());
        streamer.write("content=\"width=device-width, initial-scale=1.0\">".getBytes());
        streamer.write(("<title>" + title + "</title>").getBytes());
        streamer.write("</head>".getBytes());
        streamer.write("<body>".getBytes());
    }

    private void endDispatcherStatusPage(ServletOutputStream streamer) throws IOException {
        streamer.write("</body>".getBytes());
        streamer.write("</html>".getBytes());
    }
}
