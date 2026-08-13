package cr.ac.ucenfotec.bl.entities.Cliente;

import cr.ac.ucenfotec.bl.Exceptions.CedulaDuplicadaException;
import cr.ac.ucenfotec.bl.Exceptions.EntidadNoEncontradaException;
import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DAOCliente {

    private static String statement;
    private static String query;

    public static String insertarCliente(Cliente cliente) throws Exception {
        if (buscarClientePorCedula(cliente.getCedula()) != null) {
            throw new CedulaDuplicadaException("La cédula " + cliente.getCedula() + " ya está registrada en el sistema.");
        }
        String sql = "INSERT INTO t_clientes (nombre, apellidos, cedula, telefono, correo) VALUES ('"
                + cliente.getNombre() + "', '"
                + cliente.getApellidos() + "', '"
                + cliente.getCedula() + "', '"
                + cliente.getTelefono() + "', '"
                + cliente.getCorreo() + "');";
        Connector.getConnection().ejecutarStatement(sql);
        return "Cliente registrado exitosamente";
    }

    public static Cliente buscarClientePorCedula(String cedula) throws Exception {
        String sql = "SELECT * FROM t_clientes WHERE cedula = '" + cedula + "';";
        ResultSet rs = Connector.getConnection().ejecutarQuery(sql);
        if (rs.next()) {
            return new Cliente(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("cedula"), rs.getString("telefono"), rs.getString("correo"));
        }
        return null;
    }
    public static Cliente buscarClientePorID(int idClienteDB) throws Exception {
        String sql = "SELECT * FROM t_clientes WHERE id = " + idClienteDB + ";";
        ResultSet rs = Connector.getConnection().ejecutarQuery(sql);
        if (rs.next()) {
            return new Cliente(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("cedula"), rs.getString("telefono"), rs.getString("correo"));
        }
        return null;
    }

    public static Cliente obtenerClienteObligatorio(String cedula) throws Exception {
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente == null) {
            throw new EntidadNoEncontradaException("No existe ningún cliente registrado con la cédula: " + cedula);
        }
        return cliente;
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
