import java.util.ArrayList;
import java.util.List;

public class Cliente {
    //atributos
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String correo;
    private List<Mascota> listaMascotas; // Relación de Agregacion

    //constructor
    public Cliente(String nombre, String apellidos, String cedula,
                   String telefono, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.listaMascotas = new ArrayList<>(); // Inicializar lista vacia
    }
    //getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }
    public void setListaMascotas(List<Mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public boolean agregarMascota(Mascota nuevaMascota){
        if(nuevaMascota==null)return false;    //si e snula no se agrega
        if(listaMascotas.contains(nuevaMascota)){   //validar que esta mascota no exista
            return false;
        }
        listaMascotas.add(nuevaMascota);
        return true;
    }
    public boolean eliminarMascota(String idMascota){
        for(int i = 0; i<listaMascotas.size(); i++){  //recorre la lista
            if(listaMascotas.get(i).getIdMascota().equalsIgnoreCase(idMascota)){ //compara el id con el que se da
                listaMascotas.remove(i);
                return true;
            }
        }
        return false;
    }
    public Mascota obtenerMascotaPorID(String idMascota){
        for(Mascota m: listaMascotas){
            if(m.getIdMascota().equalsIgnoreCase(idMascota)){
                return m;
            }
        }
        return null;
    }
    public int calcularTotalMascotas(){
        return this.listaMascotas.size();
    }
    public void mostrarCantidadMascotas(){
        System.out.println("Cantidad de mascotas: " + calcularTotalMascotas());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente c = (Cliente) obj;
        return cedula.equals(c.cedula);
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "nombre: " + nombre + '\'' +
                ", apellidos: " + apellidos + '\'' +
                ", cedula: " + cedula + '\'' +
                ", telefono: " + telefono + '\'' +
                ", correo: " + correo + '\'';
    }
}


