package cr.ac.ucenfotec.bl.entities.Veterinario;

import cr.ac.ucenfotec.bl.entities.Persona.Persona;

import java.time.LocalTime;

public class Veterinario  extends Persona {
    private String especialidad;

    public Veterinario(String nombre, String apellidos, String cedula, String telefono, String correo, String especialidad) {
        super(nombre, apellidos, cedula, telefono, correo);
        this.especialidad = uppercaseEspecialidad(especialidad);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = uppercaseEspecialidad(especialidad);
    }

    public boolean validarDisponibilidad(LocalTime horaCita) {
        LocalTime inicioJornada = LocalTime.of(8, 0);
        LocalTime finJornada = LocalTime.of(18, 0);
        return (!horaCita.isBefore(inicioJornada)) && !horaCita.isAfter(finJornada);
    }

    private String uppercaseEspecialidad(String esp) {
        if (esp == null || esp.isEmpty()) return "General";
        return esp.substring(0, 1).toUpperCase() + esp.substring(1).toLowerCase();
    }

    @Override
    public String obtenerFicha() {
        return "VETERINARIO: Dr(a). " + nombre + " " + apellidos + " | Especialidad: " + especialidad + " | Cédula: " + cedula;
    }

    @Override
    public String toString() {
        return "Veterinario: " + nombre + " " + apellidos + " - Especialidad: " + especialidad;
    }
}
