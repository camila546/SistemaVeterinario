package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.Exceptions.CedulaDuplicadaException;
import cr.ac.ucenfotec.bl.Exceptions.EntidadNoEncontradaException;
import cr.ac.ucenfotec.bl.Exceptions.FechaInvalidaException;
import cr.ac.ucenfotec.bl.Exceptions.HorarioOcupadoException;
import cr.ac.ucenfotec.bl.entities.Cliente.Cliente;
import cr.ac.ucenfotec.bl.entities.Consulta.Consulta;
import cr.ac.ucenfotec.bl.entities.Mascota.Mascota;
import cr.ac.ucenfotec.bl.entities.Veterinario.Veterinario;
import cr.ac.ucenfotec.bl.logic.*;
import cr.ac.ucenfotec.ui.Menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
    private static void agregarCliente() {
        try {
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

        } catch (CedulaDuplicadaException e) {
            System.out.println("\n[ERROR DE DUPLICADO]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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

    // Modificar Cliente
    public static void modificarCliente() {
        int idCliente = 0;
        while (true) {
            try {
                listarClientesConID();
                System.out.print("\nIngrese el ID de la base de datos del cliente a modificar: ");
                idCliente = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar los clientes: " + e.getMessage());
                return;
            }
        }
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
        try {
            System.out.println(GestorCliente.modificarCliente(idCliente, nombre, apellidos, cedula, telefono, correo));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
    }
    public static void eliminarCliente() {
        int idCliente = 0;
        while (true) {
            try {
                listarClientesConID();
                System.out.print("\nIngrese el ID de la base de datos del cliente a eliminar: ");
                idCliente = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar los clientes: " + e.getMessage());
                return;
            }
        }
        try {
            System.out.println(GestorCliente.eliminarCliente(idCliente));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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

    private static void agregarMascota() {
        System.out.println("\n----- Agregar Mascota -----");
        System.out.print("Ingrese el ID/Código de la mascota: ");
        String idMascota = Menu.leerTexto();

        System.out.print("Ingrese el nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese la especie (ej. Perro, Gato): ");
        String especie = Menu.leerTexto();

        System.out.print("Ingrese la raza: ");
        String raza = Menu.leerTexto();
        int edad = 0;
        while (true) {
            try {
                System.out.print("Ingrese la edad: ");
                edad = Integer.parseInt(Menu.leerTexto());
                if (edad < 0) {
                    System.out.println("La edad no puede ser un número negativo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número válido.");
            }
        }
        int idClienteDB = 0;
        while (true) {
            try {
                listarClientesConID();
                System.out.print("Ingrese el ID del cliente dueño de la mascota: ");
                idClienteDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar los clientes: " + e.getMessage());
                return;
            }
        }
        try {
            System.out.println(GestorMascota.agregarMascota(idMascota, nombre, especie, raza, edad, idClienteDB));
        } catch (EntidadNoEncontradaException e) {
            System.out.println("\n[ERROR DE VALIDACIÓN]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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

    public static void modificarMascota() {
        int idMascotaDB = 0;
        while (true) {
            try {
                listarMascotasConID();
                System.out.print("\nIngrese el ID de la base de datos de la mascota a modificar: ");
                idMascotaDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar las mascotas: " + e.getMessage());
                return;
            }
        }
        System.out.print("Ingrese el nuevo ID/Código de la mascota: ");
        String idMascota = Menu.leerTexto();

        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = Menu.leerTexto();

        System.out.print("Ingrese la nueva especie: ");
        String especie = Menu.leerTexto();

        System.out.print("Ingrese la nueva raza: ");
        String raza = Menu.leerTexto();
        int edad = 0;
        while (true) {
            try {
                System.out.print("Ingrese la nueva edad: ");
                edad = Integer.parseInt(Menu.leerTexto());
                if (edad < 0) {
                    System.out.println("La edad no puede ser un número negativo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número válido.");
            }
        }
        try {
            System.out.println(GestorMascota.modificarMascota(idMascotaDB, idMascota, nombre, especie, raza, edad));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
    }
    public static void eliminarMascota() {
        int idMascotaDB = 0;
        while (true) {
            try {
                listarMascotasConID();
                System.out.print("\nIngrese el ID de la base de datos de la mascota a eliminar: ");
                idMascotaDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar las mascotas: " + e.getMessage());
                return;
            }
        }
        try {
            System.out.println(GestorMascota.eliminarMascota(idMascotaDB));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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

    private static void agregarVeterinario() {
        try {
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

        } catch (CedulaDuplicadaException e) {
            System.out.println("\n[ERROR DE DUPLICADO]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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

    public static void modificarVeterinario() {
        int idVetDB = 0;
        while (true) {
            try {
                listarVeterinariosConID();
                System.out.print("\nIngrese el ID de la base de datos del veterinario a modificar: ");
                idVetDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar los veterinarios: " + e.getMessage());
                return;
            }
        }
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
        try {
            System.out.println(GestorVeterinario.modificarVeterinario(idVetDB, nombre, apellidos, cedula, telefono, correo, especialidad));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
    }
    public static void eliminarVeterinario() {
        int idVetDB = 0;
        while (true) {
            try {
                listarVeterinariosConID();
                System.out.print("\nIngrese el ID de la base de datos del veterinario a eliminar: ");
                idVetDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar los veterinarios: " + e.getMessage());
                return;
            }
        }
        try {
            System.out.println(GestorVeterinario.eliminarVeterinario(idVetDB));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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
    private static void agregarConsulta() {
        System.out.println("\n----- Programar Consulta -----");
        System.out.print("Ingrese el tipo de consulta (ej. Control, Emergencia, Accidente): ");
        String tipo = Menu.leerTexto();
        LocalDate fecha = null;
        while (true) {
            try {
                System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                fecha = LocalDate.parse(Menu.leerTexto());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Debe utilizar YYYY-MM-DD.");
            }
        }
        LocalTime hora = null;
        while (true) {
            try {
                System.out.print("Ingrese la hora (HH:MM): ");
                hora = LocalTime.parse(Menu.leerTexto());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Debe utilizar HH:MM.");
            }
        }
        double costo = 0;
        while (true) {
            try {
                System.out.print("Ingrese el costo estimado: ");
                costo = Double.parseDouble(Menu.leerTexto());
                if (costo < 0) {
                    System.out.println("El costo no puede ser un monto negativo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un monto numérico válido.");
            }
        }
        int idMascota = 0;
        while (true) {
            try {
                listarMascotasConID();
                System.out.print("Ingrese el ID de la base de datos de la mascota: ");
                idMascota = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar mascotas: " + e.getMessage());
                return;
            }
        }
        int idVeterinario = 0;
        while (true) {
            try {
                listarVeterinariosConID();
                System.out.print("Ingrese el ID de la base de datos del veterinario: ");
                idVeterinario = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar veterinarios: " + e.getMessage());
                return;
            }
        }
        try {
            System.out.println(GestorConsulta.agregarConsulta(tipo, fecha, hora, costo, "Por definir", "Programada", idMascota, idVeterinario));
        } catch (EntidadNoEncontradaException e) {
            System.out.println("\n[ERROR DE VALIDACIÓN]: " + e.getMessage());
        } catch (FechaInvalidaException e) {
            System.out.println("\n[ERROR DE FECHA]: " + e.getMessage());
        } catch (HorarioOcupadoException e) {
            System.out.println("\n[ERROR DE AGENDA]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
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

    public static void modificarConsulta() {
        int idConsultaDB = 0;
        while (true) {
            try {
                listarConsultasConID();
                System.out.print("\nIngrese el ID de la base de datos de la consulta a modificar: ");
                idConsultaDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar las consultas: " + e.getMessage());
                return;
            }
        }
        System.out.print("Ingrese el nuevo tipo: ");
        String tipo = Menu.leerTexto();
        LocalDate fecha = null;
        while (true) {
            try {
                System.out.print("Ingrese la nueva fecha (YYYY-MM-DD): ");
                fecha = LocalDate.parse(Menu.leerTexto());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Debe utilizar YYYY-MM-DD.");
            }
        }
        LocalTime hora = null;
        while (true) {
            try {
                System.out.print("Ingrese la nueva hora (HH:MM): ");
                hora = LocalTime.parse(Menu.leerTexto());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Debe utilizar HH:MM.");
            }
        }
        double costo = 0;
        while (true) {
            try {
                System.out.print("Ingrese el nuevo costo: ");
                costo = Double.parseDouble(Menu.leerTexto());
                if (costo < 0) {
                    System.out.println("El costo no puede ser un monto negativo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un monto numérico válido.");
            }
        }
        System.out.print("Ingrese el diagnóstico: ");
        String diagnostico = Menu.leerTexto();

        System.out.print("Ingrese el estado (Programada / En progreso / Completado / Cancelada): ");
        String estado = Menu.leerTexto();
        try {
            System.out.println(GestorConsulta.modificarConsulta(idConsultaDB, tipo, fecha, hora, costo, diagnostico, estado));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
    }
    public static void eliminarConsulta() {
        int idConsultaDB = 0;
        while (true) {
            try {
                listarConsultasConID();
                System.out.print("\nIngrese el ID de la base de datos de la consulta a eliminar: ");
                idConsultaDB = Integer.parseInt(Menu.leerTexto());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un número entero válido.");
            } catch (Exception e) {
                System.out.println("Error al listar las consultas: " + e.getMessage());
                return;
            }
        }
        try {
            System.out.println(GestorConsulta.eliminarConsulta(idConsultaDB));
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO]: " + e.getMessage());
        }
    }
}