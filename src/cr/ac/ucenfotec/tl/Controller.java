package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.Cliente.Cliente;
import cr.ac.ucenfotec.bl.entities.Consulta.Consulta;
import cr.ac.ucenfotec.bl.entities.Mascota.Mascota;
import cr.ac.ucenfotec.bl.entities.Veterinario.Veterinario;
import cr.ac.ucenfotec.bl.logic.*;
import cr.ac.ucenfotec.ui.Menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    // Procesar la selección del menú principal
    public static void procesarSeleccionPrincipal(byte opcion) throws Exception {
        switch (opcion) {
            case 1:
                Menu.menuClientes();
                break;
            case 2:
                Menu.menuVeterinarios();
                break;
            case 3:
                Menu.menuMascotas();
                break;
            case 4:
                Menu.menuConsultas();
                break;
            case 0:
                System.out.println("\n¡Gracias por utilizar el Sistema Veterinario!");
                break;
            default:
                System.out.println("La selección realizada no es válida.");
                break;
        }
    }

    public static void procesarMenuClientes(byte opcion) throws Exception {
        switch (opcion) {
            case 1:
                agregarCliente();
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                modificarCliente();
                break;
            case 4:
                eliminarCliente();
                break;
            case 0:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("La selección realizada no es válida.");
                break;
        }
    }
    //Registrar Cliente
    private static void agregarCliente() throws Exception {
        System.out.println("\n----- Agregar Cliente -----");
        System.out.print("Ingrese el nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese los apellidos: ");
        String apellidos = Menu.leerTexto();

        System.out.print("Ingrese la cédula: ");
        String cedula = Menu.leerTexto();

        System.out.print("Ingrese el teléfono: ");
        String telefono = Menu.leerTexto();

        System.out.print("Ingrese el correo electrónico: ");
        String correo = Menu.leerTexto();
        System.out.println(GestorCliente.agregarCliente(nombre, apellidos, cedula, telefono, correo));
    }
    // Listar Clientes
    public static void listarClientes() throws Exception {
        ArrayList<Cliente> lista = new ArrayList<>();
        GestorCliente.listarClientes(lista);
        System.out.println("\n----- Lista de Clientes -----");
        if (lista.isEmpty()) {
            System.out.println("No hay clientes registrados en el sistema.");
        } else {
            for (Cliente c : lista) {
                System.out.println(c.obtenerFicha());
            }
        }
    }

    private static void listarClientesConID() throws Exception {
        HashMap<Integer, Cliente> listaID = new HashMap<>();
        GestorCliente.listarClientesID(listaID);
        for (HashMap.Entry<Integer, Cliente> pareja : listaID.entrySet()) {
            System.out.println("ID: " + pareja.getKey() + " | " + pareja.getValue().obtenerFicha());
        }
    }
    //Modificar Cliente
    public static void modificarCliente() throws Exception {
        listarClientesConID();
        System.out.print("\nIngrese el ID de la base de datos del cliente a modificar: ");
        int idCliente = Integer.parseInt(Menu.leerTexto());

        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese los nuevos apellidos: ");
        String apellidos = Menu.leerTexto();

        System.out.print("Ingrese la nueva cédula: ");
        String cedula = Menu.leerTexto();

        System.out.print("Ingrese el nuevo teléfono: ");
        String telefono = Menu.leerTexto();

        System.out.print("Ingrese el nuevo correo: ");
        String correo = Menu.leerTexto();
        System.out.println(GestorCliente.modificarCliente(idCliente, nombre, apellidos, cedula, telefono, correo));
    }
    // Eliminar Cliente
    public static void eliminarCliente() throws Exception {
        listarClientesConID();
        System.out.print("\nIngrese el ID de la base de datos del cliente a eliminar: ");
        int idCliente = Integer.parseInt(Menu.leerTexto());
        System.out.println(GestorCliente.eliminarCliente(idCliente));
    }
    public static void procesarMenuMascotas(byte opcion) throws Exception {
        switch (opcion) {
            case 1:
                agregarMascota();
                break;
            case 2:
                listarMascotas();
                break;
            case 3:
                modificarMascota();
                break;
            case 4:
                eliminarMascota();
                break;
            case 0:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("La selección realizada no es válida.");
                break;
        }
    }
    private static void agregarMascota() throws Exception {
        System.out.println("\n----- Agregar Mascota -----");
        System.out.print("Ingrese el ID/Código de la mascota: ");
        String idMascota = Menu.leerTexto();

        System.out.print("Ingrese el nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese la especie (ej. Perro, Gato): ");
        String especie = Menu.leerTexto();

        System.out.print("Ingrese la raza: ");
        String raza = Menu.leerTexto();

        System.out.print("Ingrese la edad: ");
        int edad = Integer.parseInt(Menu.leerTexto());

        listarClientesConID();
        System.out.print("Ingrese el ID del cliente dueño de la mascota: ");
        int idClienteDB = Integer.parseInt(Menu.leerTexto());
        System.out.println(GestorMascota.agregarMascota(idMascota, nombre, especie, raza, edad, idClienteDB));
    }

    public static void listarMascotas() throws Exception {
        ArrayList<Mascota> lista = new ArrayList<>();
        GestorMascota.listarMascotas(lista);
        System.out.println("\n----- Lista de Mascotas -----");
        if (lista.isEmpty()) {
            System.out.println("No hay mascotas registradas en el sistema.");
        } else {
            for (Mascota m : lista) {
                System.out.println(m + (m.esAdultoMayor() ? " [Adulto Mayor]" : ""));
            }
        }
    }
    private static void listarMascotasConID() throws Exception {
        HashMap<Integer, Mascota> listaID = new HashMap<>();
        GestorMascota.listarMascotasID(listaID);
        for (HashMap.Entry<Integer, Mascota> pareja : listaID.entrySet()) {
            System.out.println("ID: " + pareja.getKey() + " | " + pareja.getValue());
        }
    }
    public static void modificarMascota() throws Exception {
        listarMascotasConID();
        System.out.print("\nIngrese el ID de la base de datos de la mascota a modificar: ");
        int idMascotaDB = Integer.parseInt(Menu.leerTexto());

        System.out.print("Ingrese el nuevo ID/Código de la mascota: ");
        String idMascota = Menu.leerTexto();

        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese la nueva especie: ");
        String especie = Menu.leerTexto();

        System.out.print("Ingrese la nueva raza: ");
        String raza = Menu.leerTexto();

        System.out.print("Ingrese la nueva edad: ");
        int edad = Integer.parseInt(Menu.leerTexto());

        System.out.println(GestorMascota.modificarMascota(idMascotaDB, idMascota, nombre, especie, raza, edad));
    }
    public static void eliminarMascota() throws Exception {
        listarMascotasConID();
        System.out.print("\nIngrese el ID de la base de datos de la mascota a eliminar: ");
        int idMascotaDB = Integer.parseInt(Menu.leerTexto());
        System.out.println(GestorMascota.eliminarMascota(idMascotaDB));
    }
    public static void procesarMenuVeterinarios(byte opcion) throws Exception {
        switch (opcion) {
            case 1:
                agregarVeterinario();
                break;
            case 2:
                listarVeterinarios();
                break;
            case 3:
                modificarVeterinario();
                break;
            case 4:
                eliminarVeterinario();
                break;
            case 0:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("La selección realizada no es válida.");
                break;
        }
    }

    private static void agregarVeterinario() throws Exception {
        System.out.println("\n----- Agregar Veterinario -----");
        System.out.print("Ingrese el nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese los apellidos: ");
        String apellidos = Menu.leerTexto();

        System.out.print("Ingrese la cédula: ");
        String cedula = Menu.leerTexto();

        System.out.print("Ingrese el teléfono: ");
        String telefono = Menu.leerTexto();

        System.out.print("Ingrese el correo electrónico: ");
        String correo = Menu.leerTexto();

        System.out.print("Ingrese la especialidad: ");
        String especialidad = Menu.leerTexto();
        System.out.println(GestorVeterinario.agregarVeterinario(nombre, apellidos, cedula, telefono, correo, especialidad));
    }
    public static void listarVeterinarios() throws Exception {
        ArrayList<Veterinario> lista = new ArrayList<>();
        GestorVeterinario.listarVeterinarios(lista);
        System.out.println("\n----- Lista de Veterinarios -----");
        if (lista.isEmpty()) {
            System.out.println("No hay veterinarios registrados en el sistema.");
        } else {
            for (Veterinario v : lista) {
                System.out.println(v.obtenerFicha());
            }
        }
    }

    private static void listarVeterinariosConID() throws Exception {
        HashMap<Integer, Veterinario> listaID = new HashMap<>();
        GestorVeterinario.listarVeterinariosID(listaID);
        for (HashMap.Entry<Integer, Veterinario> pareja : listaID.entrySet()) {
            System.out.println("ID: " + pareja.getKey() + " | " + pareja.getValue().obtenerFicha());
        }
    }

    public static void modificarVeterinario() throws Exception {
        listarVeterinariosConID();
        System.out.print("\nIngrese el ID de la base de datos del veterinario a modificar: ");
        int idVetDB = Integer.parseInt(Menu.leerTexto());

        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese los nuevos apellidos: ");
        String apellidos = Menu.leerTexto();

        System.out.print("Ingrese la nueva cédula: ");
        String cedula = Menu.leerTexto();

        System.out.print("Ingrese el nuevo teléfono: ");
        String telefono = Menu.leerTexto();

        System.out.print("Ingrese el nuevo correo: ");
        String correo = Menu.leerTexto();

        System.out.print("Ingrese la nueva especialidad: ");
        String especialidad = Menu.leerTexto();
        System.out.println(GestorVeterinario.modificarVeterinario(idVetDB, nombre, apellidos, cedula, telefono, correo, especialidad));
    }

    public static void eliminarVeterinario() throws Exception {
        listarVeterinariosConID();
        System.out.print("\nIngrese el ID de la base de datos del veterinario a eliminar: ");
        int idVetDB = Integer.parseInt(Menu.leerTexto());
        System.out.println(GestorVeterinario.eliminarVeterinario(idVetDB));
    }
    public static void procesarMenuConsultas(byte opcion) throws Exception {
        switch (opcion) {
            case 1:
                agregarConsulta();
                break;
            case 2:
                listarConsultas();
                break;
            case 3:
                modificarConsulta();
                break;
            case 4:
                eliminarConsulta();
                break;
            case 0:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("La selección realizada no es válida.");
                break;
        }
    }

    private static void agregarConsulta() throws Exception {
        System.out.println("\n----- Programar Consulta -----");
        System.out.print("Ingrese el tipo de consulta (ej. Control, Emergencia, Accidente): ");
        String tipo = Menu.leerTexto();

        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(Menu.leerTexto());

        System.out.print("Ingrese la hora (HH:MM): ");
        LocalTime hora = LocalTime.parse(Menu.leerTexto());

        System.out.print("Ingrese el costo estimado: ");
        double costo = Double.parseDouble(Menu.leerTexto());

        listarMascotasConID();
        System.out.print("Ingrese el ID de la base de datos de la mascota: ");
        int idMascota = Integer.parseInt(Menu.leerTexto());

        listarVeterinariosConID();
        System.out.print("Ingrese el ID de la base de datos del veterinario: ");
        int idVeterinario = Integer.parseInt(Menu.leerTexto());
        System.out.println(GestorConsulta.agregarConsulta(tipo, fecha, hora, costo, idMascota, idVeterinario));
    }

    public static void listarConsultas() throws Exception {
        ArrayList<Consulta> lista = new ArrayList<>();
        GestorConsulta.listarConsultas(lista);
        System.out.println("\n----- Lista de Consultas -----");
        if (lista.isEmpty()) {
            System.out.println("No hay consultas registradas en el sistema.");
        } else {
            for (Consulta c : lista) {
                System.out.println(c);
            }
        }
    }

    private static void listarConsultasConID() throws Exception {
        HashMap<Integer, Consulta> listaID = new HashMap<>();
        GestorConsulta.listarConsultasID(listaID);
        for (HashMap.Entry<Integer, Consulta> pareja : listaID.entrySet()) {
            System.out.println("ID: " + pareja.getKey() + " | " + pareja.getValue());
        }
    }

    public static void modificarConsulta() throws Exception {
        listarConsultasConID();
        System.out.print("\nIngrese el ID de la base de datos de la consulta a modificar: ");
        int idConsultaDB = Integer.parseInt(Menu.leerTexto());

        System.out.print("Ingrese el nuevo tipo: ");
        String tipo = Menu.leerTexto();

        System.out.print("Ingrese la nueva fecha (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(Menu.leerTexto());

        System.out.print("Ingrese la nueva hora (HH:MM): ");
        LocalTime hora = LocalTime.parse(Menu.leerTexto());

        System.out.print("Ingrese el nuevo costo: ");
        double costo = Double.parseDouble(Menu.leerTexto());

        System.out.print("Ingrese el diagnóstico: ");
        String diagnostico = Menu.leerTexto();

        System.out.print("Ingrese el estado (Programada / En progreso / Completado / Cancelada): ");
        String estado = Menu.leerTexto();
        System.out.println(GestorConsulta.modificarConsulta(idConsultaDB, tipo, fecha, hora, costo, diagnostico, estado));
    }
    public static void eliminarConsulta() throws Exception {
        listarConsultasConID();
        System.out.print("\nIngrese el ID de la base de datos de la consulta a eliminar: ");
        int idConsultaDB = Integer.parseInt(Menu.leerTexto());
        System.out.println(GestorConsulta.eliminarConsulta(idConsultaDB));
    }

}



