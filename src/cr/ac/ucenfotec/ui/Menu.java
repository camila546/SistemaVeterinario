package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.Exceptions.OpcionInvalidaException;
import cr.ac.ucenfotec.tl.Controller;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    // Menú Principal
    public static void MostrarMenu() throws Exception {
        byte opcion = -1;
        do {
            System.out.println("\n    Menú Veterinario:   ");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Veterinarios");
            System.out.println("3. Gestión de Mascotas");
            System.out.println("4. Gestión de Consultas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Byte.parseByte(scanner.nextLine());
                if (opcion < 0 || opcion > 4) {
                    throw new OpcionInvalidaException(
                            "La opción indicada no se encuentra entre las ofrecidas.\n");
                }
                Controller.procesarSeleccionPrincipal(opcion);
            } catch (NumberFormatException e) {
                System.out.println("El formato del dato ingresado no es válido.\n");
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 0);
    }

    // Submenú para Gestión de Clientes
    public static void menuClientes() throws Exception {
        byte opcion = -1;

        do {
            System.out.println("\n----- Gestión de Clientes -----");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Byte.parseByte(scanner.nextLine());
                if (opcion < 0 || opcion > 4) {
                    throw new OpcionInvalidaException(
                            "La opción indicada no se encuentra entre las ofrecidas.\n");
                }
                Controller.procesarMenuClientes(opcion);
            } catch (NumberFormatException e) {
                System.out.println("El formato del dato ingresado no es válido.\n");
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 0);
    }
    public static void menuMascotas() throws Exception {
        byte opcion = -1;
        do {
            System.out.println("\n----- Gestión de Mascotas -----");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Listar mascotas");
            System.out.println("3. Modificar mascota");
            System.out.println("4. Eliminar mascota");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Byte.parseByte(scanner.nextLine());
                if (opcion < 0 || opcion > 4) {
                    throw new OpcionInvalidaException(
                            "La opción indicada no se encuentra entre las ofrecidas.\n");
                }
                Controller.procesarMenuMascotas(opcion);
            } catch (NumberFormatException e) {
                System.out.println("El formato del dato ingresado no es válido.\n");
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 0);
    }
    public static void menuVeterinarios() throws Exception {
        byte opcion = -1;
        do {
            System.out.println("\n----- Gestión de Veterinarios -----");
            System.out.println("1. Registrar veterinario");
            System.out.println("2. Listar veterinarios");
            System.out.println("3. Modificar veterinario");
            System.out.println("4. Eliminar veterinario");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Byte.parseByte(scanner.nextLine());
                if (opcion < 0 || opcion > 4) {
                    throw new OpcionInvalidaException(
                            "La opción indicada no se encuentra entre las ofrecidas.\n");
                }
                Controller.procesarMenuVeterinarios(opcion);
            } catch (NumberFormatException e) {
                System.out.println("El formato del dato ingresado no es válido.\n");
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 0);
    }
    public static void menuConsultas() throws Exception {
        byte opcion = -1;
        do {
            System.out.println("\n----- Gestión de Consultas -----");
            System.out.println("1. Programar consulta");
            System.out.println("2. Listar consultas");
            System.out.println("3. Modificar consulta");
            System.out.println("4. Eliminar consulta");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Byte.parseByte(scanner.nextLine());
                if (opcion < 0 || opcion > 4) {
                    throw new OpcionInvalidaException(
                            "La opción indicada no se encuentra entre las ofrecidas.\n");
                }
                Controller.procesarMenuConsultas(opcion);
            } catch (NumberFormatException e) {
                System.out.println("El formato del dato ingresado no es válido.\n");
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 0);
    }

    public static String leerTexto() {
        return scanner.nextLine();
    }
}





