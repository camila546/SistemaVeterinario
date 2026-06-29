import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaVeterinario {
    private List<Cliente> listaClientes;
    private List<Veterinario> listaVeterinarios;
    private List<Consulta> listaConsultas;

    public SistemaVeterinario() {
        this.listaClientes = new ArrayList<>();
        this.listaVeterinarios = new ArrayList<>();
        this.listaConsultas = new ArrayList<>();
    }
    //metodos
    public boolean registrarCliente(Cliente nuevoCliente){
        if(nuevoCliente==null) return false;
        for(Cliente c : listaClientes){
            if(c.getCedula().equals((nuevoCliente.getCedula()))) return false; //duplicado
        }
        listaClientes.add(nuevoCliente);
        return true;
    }
    public boolean registrarVeterinario(Veterinario nuevoVet){
        if(nuevoVet==null) return false;
        for(Veterinario v : listaVeterinarios){
            if(v.getCedula().equals(nuevoVet.getCedula())) return false;
        }
        listaVeterinarios.add(nuevoVet);
        return true;
    }
    public Mascota buscarMascotaEnSistema(String idMascota){
        for(Cliente c : listaClientes){
            Mascota m = c.obtenerMascotaPorID(idMascota);
            if(m != null){
                return m;
            }
        }
        return null;
    }
    public Veterinario buscarVeterinario(String cedula){
        for(Veterinario v : listaVeterinarios){
            if(v.getCedula().equals(cedula)) return v;
        }
        return null;
    }
    public boolean agendarConsulta(String idMascota, String cedulaVet, LocalDate fecha, LocalTime hora, String tipo, double costo){
        Mascota paciente = buscarMascotaEnSistema(idMascota);
        Veterinario medico = buscarVeterinario(cedulaVet);

        if(paciente == null || medico == null){
            System.out.println("Cita Rechazada: Paciente o Médico no existen en el sistema.");
            return false;
        }
        //Validar disponibilidadd del vvet
        if(!medico.validarDisponibilidad(hora)) {
            System.out.println("Cita para " + paciente.getNombre() + ": RECHAZADA (Fuera de horario laboral)");
            return false;
        }
        //validar si el vet tiene cita a esa hora y dia
        for(Consulta c : listaConsultas){
            if (c.getMedico().getCedula().equals(cedulaVet) &&
                    c.getFecha().equals(fecha) &&
                    c.getHora().equals(hora) &&
                    !c.getEstado().equalsIgnoreCase("Cancelada")) {
                System.out.println("Cita para " + paciente.getNombre()
                        + ": RECHAZADA (Choque de horario con el veterinario "
                        + medico.getNombre() + ")");
                return false;
            }
        }
        Consulta nuevaConsulta=new Consulta(tipo, fecha, hora, costo, paciente, medico);
        listaConsultas.add(nuevaConsulta);
        System.out.println("Cita para " + paciente.getNombre() + ": AGENDADA CON EXITO");
        return true;
    }
    public boolean cancelarConsultaGlobal(String idMascota, LocalDate fecha, LocalTime hora) {
        for (Consulta c : listaConsultas) {
            if (c.getPaciente().getIdMascota().equalsIgnoreCase(idMascota) &&
                    c.getFecha().equals(fecha) &&
                    c.getHora().equals(hora)) {
                c.cancelar(); // Llama al metodo
                return true;
            }
        }
        return false;
    }

    public void simularAtencionMedica(String idMascota, String medicamento, String dosis, String diagnostico) {
        for (Consulta c : listaConsultas) {
            if (c.getPaciente().getIdMascota().equalsIgnoreCase(idMascota) && c.getEstado().equalsIgnoreCase("Programada")) {
                c.iniciarConsulta();
                c.agregarMedicamento(medicamento, dosis);
                c.finalizarConsulta(diagnostico);
                return;
            }
        }
        System.out.println("No se encontró ninguna consulta programada para la mascota: " + idMascota);
    }
    public List<Consulta> obtenerConsultasDelDia(LocalDate fecha) {
        List<Consulta> delDia = new ArrayList<>();
        for (Consulta c : listaConsultas) {
            if (c.getFecha().equals(fecha)) {
                delDia.add(c);
            }
        }
        return delDia;
    }
    public void mostrarConsultasDelDia(LocalDate fecha){
        for(Consulta c : obtenerConsultasDelDia(fecha)){
            System.out.println(c);
        }
    }
    public Cliente buscarCliente(String cedula){
        for(Cliente c : listaClientes){
            if(c.getCedula().equals(cedula)){
                return c;
            }
        }
        return null;
    }
    public Consulta buscarConsulta(String idMascota, LocalDate fecha, LocalTime hora){
        for(Consulta c : listaConsultas){
            if(c.getPaciente().getIdMascota().equalsIgnoreCase(idMascota)
                    && c.getFecha().equals(fecha)
                    && c.getHora().equals(hora)){
                return c;
            }
        }
        return null;
    }

    // Getters de control general
    public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public List<Veterinario> getListaVeterinarios() {
        return listaVeterinarios;
    }
}
