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

@WebServlet("/afiliados")
public class AfiliadoServlet extends HttpServlet {
    private MongoDBConnection dbConnection;

    @Override
    public void init() throws ServletException {
        dbConnection = new MongoDBConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");

        if (nombre != null && direccion != null && telefono != null) {
            MongoDatabase database = dbConnection.getDatabase("club_deportivo");
            MongoCollection<Document> collection = database.getCollection("afiliados");

            Document document = new Document("nombre", nombre)
                    .append("direccion", direccion)
                    .append("telefono", telefono);

            try {
                collection.insertOne(document);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Afiliado agregado exitosamente");
            } catch (Exception e) {
                e.printStackTrace(); // Imprime el error en los logs del servidor
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al agregar afiliado: " + e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Datos incompletos");
        }
    }}
