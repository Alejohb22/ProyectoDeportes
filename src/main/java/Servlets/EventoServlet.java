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

@WebServlet("/eventos")
public class EventoServlet extends HttpServlet {
    private MongoDBConnection dbConnection;

    @Override
    public void init() throws ServletException {
        dbConnection = new MongoDBConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String lugar = request.getParameter("lugar");

        if (nombre != null && fecha != null && lugar != null) {
            MongoDatabase database = dbConnection.getDatabase("club_deportivo");
            MongoCollection<Document> collection = database.getCollection("eventos");

            Document document = new Document("nombre", nombre)
                    .append("fecha", fecha)
                    .append("lugar", lugar);

            try {
                collection.insertOne(document);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Evento agregado exitosamente");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al agregar evento: " + e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Datos incompletos");
        }
    }
}
