package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Cliente.Cliente;
import cr.ac.ucenfotec.bl.entities.Cliente.DAOCliente;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorCliente {
    public static String agregarCliente(String nombre, String apellidos, String cedula, String telefono, String correo) throws Exception {
        Cliente nuevoCliente = new Cliente(nombre, apellidos, cedula, telefono, correo);
        return DAOCliente.insertarCliente(nuevoCliente);
    }

    public static void listarClientes(ArrayList<Cliente> listaDestino) throws Exception {
        ArrayList<Cliente> listaClientes = DAOCliente.listarClientes();
        if (listaClientes != null) {
            listaDestino.addAll(listaClientes);
        }
    }

    public static void listarClientesID(HashMap<Integer, Cliente> listaClientesID) throws Exception {
        listaClientesID.clear();
        listaClientesID.putAll(DAOCliente.listarClientesID());
    }

    public static String modificarCliente(int idCliente, String nombre, String apellidos, String cedula, String telefono, String correo) throws Exception {
        Cliente clienteNuevo = new Cliente(nombre, apellidos, cedula, telefono, correo);
        return DAOCliente.modificarCliente(idCliente, clienteNuevo);
    }

    public static String eliminarCliente(int idCliente) throws Exception {
        return DAOCliente.eliminarCliente(idCliente);
    }
}
