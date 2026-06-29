import java.time.LocalTime;

public class Veterinario {
    private String nombre;
    private String cedula;
    private String especialidad;

    //constructor
    public Veterinario(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = uppercaseEspecialidad(especialidad);
    }
    //getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    //verificar si el vet esta disponible
    public boolean validarDisponibilidad(LocalTime horaCita){
        //ejemplo de horario de 8am a 6pm
        LocalTime inicioJornada=LocalTime.of(8,0);
        LocalTime finJornada=LocalTime.of(18,0);
        return (!horaCita.isBefore(inicioJornada)) && !horaCita.isAfter(finJornada);
    }
    public String obtenerFichaProfesional(){
        return "VET-DOC: " + nombre + " | Cédula: " + cedula + " | Especialidad: " + especialidad;
    }
    private String uppercaseEspecialidad(String esp) {
        //si esta vacia se asigna "Generaal"
        if(esp == null || esp.isEmpty()) return "General";
        return esp.substring(0, 1).toUpperCase() + esp.substring(1).toLowerCase();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veterinario v = (Veterinario) obj;
        return cedula.equals(v.cedula);
    }

    @Override
    public String toString() {
        return "Veterinario: " +
                "nombre: " + nombre + '\'' +
                ", cedula: " + cedula + '\'' +
                ", especialidad: " + especialidad + '\'';
    }
}
