package Servlets;


import db.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/participaciones")
public class ParticipacionServlet extends HttpServlet {
    private MongoDBConnection dbConnection;

    @Override
    public void init() throws ServletException {
        dbConnection = new MongoDBConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String afiliadoId = request.getParameter("afiliadoId");
        String eventoId = request.getParameter("eventoId");
        String puesto = request.getParameter("puesto");

        if (afiliadoId != null && eventoId != null && puesto != null) {
            MongoDatabase database = dbConnection.getDatabase("club_deportivo");
            MongoCollection<Document> collection = database.getCollection("participaciones");

            Document document = new Document("afiliadoId", afiliadoId)
                    .append("eventoId", eventoId)
                    .append("puesto", puesto);

            try {
                collection.insertOne(document);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Participación registrada exitosamente");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al registrar participación: " + e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Datos incompletos");
        }
    }
}
