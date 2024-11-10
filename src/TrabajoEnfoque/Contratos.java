package TrabajoEnfoque;

public class Contratos {

    private String idexpediente;
    private String objeto;
    private String nombre;
    private double precio;
    private String tipoContrato;

    public Contratos (String idexpediente, String objeto, String nombre, double precio, String tipoContrato) {
        this.idexpediente = idexpediente;
        this.objeto = objeto;
        this.nombre = nombre;
        this.tipoContrato = tipoContrato;
        this.precio = precio;
    }

    public String getIdexpediente() {
        return idexpediente;
    }

    public void setIdexpediente(String idexpediente) {
        this.idexpediente = idexpediente;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}
