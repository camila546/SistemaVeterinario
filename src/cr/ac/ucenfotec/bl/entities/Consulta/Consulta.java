package cr.ac.ucenfotec.bl.entities.Consulta;

import cr.ac.ucenfotec.bl.entities.Mascota.Mascota;
import cr.ac.ucenfotec.bl.entities.Veterinario.Veterinario;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    private int idConsulta;
    private String tipo;
    private LocalDate fecha;
    private LocalTime hora;
    private double costo;
    private String diagnostico;
    private String estado;
    //relacion asociacion
    private Mascota paciente;
    private Veterinario medico;

    //METDOOS
    //constructor
    public Consulta(String tipo, LocalDate fecha, LocalTime hora,
                    double costo, Mascota paciente, Veterinario medico) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.diagnostico = "Sin revisar. Cita inicial programada.";
        this.estado = "Programada";
        this.paciente = paciente;
        this.medico = medico;
    }
    //getters y setters
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Mascota getPaciente() {
        return paciente;
    }
    public void setPaciente(Mascota paciente) {
        this.paciente = paciente;
    }

    public Veterinario getMedico() {
        return medico;
    }
    public void setMedico(Veterinario medico) {
        this.medico = medico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
//inicia la consulta (cambia "En progreso)
    public void iniciarConsulta(){
        //inicia solo si esta programada
        if(this.estado.equals("Programada")){
            this.estado= "En progreso";
            this.diagnostico= "Paciente en mesa de examen medico";
        }
    }
    public void finalizarConsulta(String diagnosticoFinal){
        //solo se finaliza si esta en progreso
        if(this.estado.equals("En progreso")){
            this.diagnostico=diagnosticoFinal;
            this.estado="Completado";
        }
    }
    public void cancelar(){ // Cancela la consulta (si aún no está completada)
        // Solo cancela si no está completada
        if(!this.estado.equalsIgnoreCase("Completado")){
            this.estado="Cancelada";
        }
    }
    public boolean esUrgente(){
        return this.tipo.equalsIgnoreCase("Emergencia") || this.tipo.equalsIgnoreCase("Accidente");
    }
    public double calcularCostoTotal(double porcentajeImpuesto){
        return this.costo +(this.costo *(porcentajeImpuesto/100));
    }
    public void agregarMedicamento(String medicamento, String dosis){
        if (this.estado.equals("En progreso")) {
            this.diagnostico += "\n[Tratamiento] Prescrito: " + medicamento + " - Dosis: " + dosis;
        }
    }
    @Override
    public String toString() {
        String nombrePaciente = (paciente != null) ? paciente.getNombre() : "Registrado en BD";
        String nombreMedico = (medico != null) ? medico.getNombre() : "Registrado en BD";

        return "Consulta | Tipo: " + tipo + " | Fecha: " + fecha + " " + hora +
                " | Costo: ₡" + costo + " | Estado: " + estado +
                " | Diagnóstico: " + diagnostico +
                " | Paciente: " + nombrePaciente +
                " | Médico: " + nombreMedico;
    }
}
