package ghc.undertow;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ForwardToServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("doGet: " + req.getRequestURI() + " " + getClass());
        final ServletOutputStream streamer = resp.getOutputStream();
        forwardStatusPage("NOBODY EXPECTS Hostile Takeover", streamer);
        resp.setStatus(208);
        resp.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void forwardStatusPage(String title, ServletOutputStream streamer) throws IOException {
        streamer.write("<!DOCTYPE HTML>".getBytes());
        streamer.write("<html lang=\"en\">".getBytes());
        streamer.write("<head>".getBytes());
        streamer.write("<META charset=\"UTF-8\">".getBytes());
        streamer.write("<META name=\"viewport\"".getBytes());
        streamer.write("content=\"width=device-width, initial-scale=1.0\">".getBytes());
        streamer.write(("<title>" + title + "</title>").getBytes());
        streamer.write("</head>".getBytes());
        streamer.write("<body>".getBytes());

        streamer.write("Example of a Hostile Takeover<br>".getBytes());
        streamer.write(
                "For example, Company A is looking to pursue an acquisition strategy and expand into a new geographical market.<br>"
                        .getBytes());

        streamer.write("Company A approaches Company B with a bid offer to purchase Company B.<br>".getBytes());
        streamer.write(
                "The board of directors of Company B concludes that this would not be in the best interest of shareholders in Company B and rejects the bid offer.<br>"
                        .getBytes());
        streamer.write(
                "Despite seeing the bid offer denied, Company A continues to push for an attempted acquisition of Company B.<br>"
                        .getBytes());

        streamer.write("</body>".getBytes());
        streamer.write("</html>".getBytes());
    }

}
