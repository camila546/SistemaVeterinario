import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        SistemaVeterinario clinica = new SistemaVeterinario();

        //registrar veterinario
        Veterinario vet = new Veterinario("Gerald Alvarado", "VET-100", "Felines");
        clinica.registrarVeterinario(vet);

        // registrar cliente
        Cliente cliente = new Cliente("Camila", "Sanchez", "7-0326-0583", "8744-4349", "cami@gmail.com");
        clinica.registrarCliente(cliente);

        //registrar mascotas
        Mascota m1 = new Mascota("M-01", "Luna", "Gato", "Persa", 7);
        Mascota m2 = new Mascota("M-02", "Bella", "Perro", "Bóxer", 9);

        cliente.agregarMascota(m1);
        cliente.agregarMascota(m2);

        // pruebas de Cliente
        System.out.println(cliente);
        cliente.mostrarCantidadMascotas();
        System.out.println(cliente.obtenerMascotaPorID("M-01"));

        // pruebas de Mascota
        m2.cumplirAnio();
        System.out.println(m2.getNombre() + " es adulto mayor: " + m2.esAdultoMayor());

        // pruebas de Veterinario
        System.out.println(vet.obtenerFichaProfesional());

        // agendar consultas
        clinica.agendarConsulta("M-01", "VET-100", LocalDate.now(), LocalTime.of(9, 0), "Regular", 20000);
        clinica.agendarConsulta("M-02", "VET-100", LocalDate.now(), LocalTime.of(10, 0), "Emergencia", 35000);

        // buscar objetos
        System.out.println(clinica.buscarCliente("7-0326-0583"));
        System.out.println(clinica.buscarVeterinario("VET-100"));
        System.out.println(clinica.buscarMascotaEnSistema("M-02"));

        // buscar consulta
        System.out.println(clinica.buscarConsulta("M-01", LocalDate.now(), LocalTime.of(9, 0)));

        // Simulación de atención médica
        clinica.simularAtencionMedica("M-01", "Otiflex", "2 gotas cada 12 horas", "Otitis leve detectada.");

        // Cancelar consulta
        clinica.cancelarConsultaGlobal("M-02", LocalDate.now(), LocalTime.of(10, 0));

        // Mostrar consultas del día
        clinica.mostrarConsultasDelDia(LocalDate.now());

    }
}