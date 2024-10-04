package ghc.undertow;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IncludeMeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("doGet: " + req.getRequestURI() + " " + getClass());
        includeStatusPage(resp.getOutputStream());
        resp.setStatus(403);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void includeStatusPage(ServletOutputStream streamer) throws IOException {
        streamer.write("<ul>".getBytes());
        streamer.write("<li>Had a slight weapons malfunction but, uh everything’s perfectly all right now. We’re fine. We’re all fine here now. Thank you. How are you?</li>".getBytes());
        streamer.write("<li>I find your lack of faith disturbing.</li>".getBytes());
        streamer.write("<li>These aren’t the droids you’re looking for.</li>".getBytes());
        streamer.write("</ul>".getBytes());
    }

}
