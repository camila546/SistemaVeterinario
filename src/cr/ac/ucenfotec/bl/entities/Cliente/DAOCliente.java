package cr.ac.ucenfotec.bl.entities.Cliente;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DAOCliente {

    private static String statement;
    private static String query;

    public static String insertarCliente(Cliente clienteInsertar) throws Exception {
        statement = "INSERT INTO t_clientes (nombre, apellidos, cedula, telefono, correo) VALUES ('"
                + clienteInsertar.getNombre() + "', '"
                + clienteInsertar.getApellidos() + "', '"
                + clienteInsertar.getCedula() + "', '"
                + clienteInsertar.getTelefono() + "', '"
                + clienteInsertar.getCorreo() + "');";
        Connector.getConnection().ejecutarStatement(statement);
        return "El cliente se registró en la base de datos correctamente.";
    }

    public static ArrayList<Cliente> listarClientes() throws Exception {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        query = "SELECT * FROM t_clientes ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return null;
        }
        do {
            Cliente clienteTemp = new Cliente(resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getString("cedula"), resultado.getString("telefono"), resultado.getString("correo"));
            listaClientes.add(clienteTemp);
        } while (resultado.next());
        return listaClientes;
    }
    public static LinkedHashMap<Integer, Cliente> listarClientesID() throws Exception {
        LinkedHashMap<Integer, Cliente> mapaClientes = new LinkedHashMap<>();
        query = "SELECT * FROM t_clientes ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return mapaClientes;
        }
        do {
            int id = resultado.getInt("id");
            Cliente clienteTemp = new Cliente(resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getString("cedula"), resultado.getString("telefono"), resultado.getString("correo"));
            mapaClientes.put(id, clienteTemp);
        } while (resultado.next());
        return mapaClientes;
    }

    public static String modificarCliente(int idClienteDB, Cliente clienteModificar) throws Exception {
        statement = "UPDATE t_clientes SET "
                + "nombre = '" + clienteModificar.getNombre() + "', "
                + "apellidos = '" + clienteModificar.getApellidos() + "', "
                + "cedula = '" + clienteModificar.getCedula() + "', "
                + "telefono = '" + clienteModificar.getTelefono() + "', "
                + "correo = '" + clienteModificar.getCorreo() + "' "
                + "WHERE id = " + idClienteDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "El cliente se modificó en la base de datos correctamente.";
    }

    public static String eliminarCliente(int idClienteDB) throws Exception {
        statement = "DELETE FROM t_clientes WHERE id = " + idClienteDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "El cliente se eliminó de la base de datos correctamente (si existía).";
    }
}