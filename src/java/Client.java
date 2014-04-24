
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Client"})
public class Client extends HttpServlet {

    @EJB
    FinalReceiverSync finalReceiverSync;

    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<br/>");
            out.println("<br/>");
            out.println("Messages received:");
            out.println("<br/>");
            out.println("<br/>");

            while (true) {
                TextMessage message = (TextMessage) finalReceiverSync.receiveMessage();
                if (message == null) {
                    break;
                } else {
                    out.println(message.getText());
                    out.println("<br/>");
                }
            }
            out.println("<br/>");
            out.println("<br/>");
        } catch (JMSException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<br/>");
            out.println("<br/>");
            out.println("Not implemented...");
            out.println("<br/>");
            out.println("<br/>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}