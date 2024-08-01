package Model;



public class Participacion {
    private int id;
    private int afiliadoId;
    private int eventoId;
    private int disciplinaId;
    private int puesto;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAfiliadoId() { return afiliadoId; }
    public void setAfiliadoId(int afiliadoId) { this.afiliadoId = afiliadoId; }
    public int getEventoId() { return eventoId; }
    public void setEventoId(int eventoId) { this.eventoId = eventoId; }
    public int getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(int disciplinaId) { this.disciplinaId = disciplinaId; }
    public int getPuesto() { return puesto; }
    public void setPuesto(int puesto) { this.puesto = puesto; }
}
