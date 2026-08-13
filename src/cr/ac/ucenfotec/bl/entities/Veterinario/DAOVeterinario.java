package cr.ac.ucenfotec.bl.entities.Veterinario;

import cr.ac.ucenfotec.bl.Exceptions.CedulaDuplicadaException;
import cr.ac.ucenfotec.bl.Exceptions.EntidadNoEncontradaException;
import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOVeterinario {
    private static String query;
    private static String statement;

    // Métodos
    public static String insertarVeterinario(Veterinario vet) throws CedulaDuplicadaException, Exception {
        if (buscarVeterinarioPorCedula(vet.getCedula()) != null) {
            throw new CedulaDuplicadaException("La cédula " + vet.getCedula() + " ya está registrada para otro veterinario.");
        }
        String sql = "INSERT INTO t_veterinarios (nombre, apellidos, cedula, telefono, correo, especialidad) VALUES ('"
                + vet.getNombre() + "', '"
                + vet.getApellidos() + "', '"
                + vet.getCedula() + "', '"
                + vet.getTelefono() + "', '"
                + vet.getCorreo() + "', '"
                + vet.getEspecialidad() + "');";

        Connector.getConnection().ejecutarStatement(sql);
        return "Veterinario registrado exitosamente.";
    }

    public static Veterinario buscarVeterinarioPorCedula(String cedula) throws Exception {
        String sql = "SELECT * FROM t_veterinarios WHERE cedula = '" + cedula + "';";
        ResultSet rs = Connector.getConnection().ejecutarQuery(sql);
        if (rs.next()) {
            return new Veterinario(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("cedula"), rs.getString("telefono"), rs.getString("correo"), rs.getString("especialidad"));
        }
        return null;
    }
    public static Veterinario obtenerVeterinarioObligatorio(String cedula) throws EntidadNoEncontradaException, Exception {
        Veterinario vet = buscarVeterinarioPorCedula(cedula);
        if (vet == null) {
            throw new EntidadNoEncontradaException("No se encontró ningún veterinario con la cédula: " + cedula);
        }
        return vet;
    }

    public static ArrayList<Veterinario> listarVeterinarios() throws Exception {
        ArrayList<Veterinario> listaVeterinarios = new ArrayList<>();
        query = "SELECT * FROM t_veterinarios;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return null;
        }
        do {
            Veterinario vetTemp = new Veterinario(resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getString("cedula"), resultado.getString("telefono"), resultado.getString("correo"), resultado.getString("especialidad"));
            listaVeterinarios.add(vetTemp);
        } while (resultado.next());
        return listaVeterinarios;
    }

    public static HashMap<Integer, Veterinario> listarVeterinariosID() throws Exception {
        HashMap<Integer, Veterinario> mapaVeterinarios = new HashMap<>();
        query = "SELECT * FROM t_veterinarios ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return mapaVeterinarios;
        }
        do {
            int idDB = resultado.getInt("id"); // <--- Aquí usamos 'id'
            Veterinario vetTemp = new Veterinario(resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getString("cedula"), resultado.getString("telefono"), resultado.getString("correo"), resultado.getString("especialidad"));
            mapaVeterinarios.put(idDB, vetTemp);
        } while (resultado.next());
        return mapaVeterinarios;
    }
    public static String modificarVeterinario(int idVetDB, Veterinario vetModificar) throws Exception {
        statement = "UPDATE t_veterinarios SET cedula = '" + vetModificar.getCedula()
                + "', nombre = '" + vetModificar.getNombre()
                + "', apellidos = '" + vetModificar.getApellidos()
                + "', telefono = '" + vetModificar.getTelefono()
                + "', correo = '" + vetModificar.getCorreo()
                + "', especialidad = '" + vetModificar.getEspecialidad()
                + "' WHERE id = " + idVetDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "El veterinario se modificó en la base de datos correctamente.";
    }

    public static String eliminarVeterinario(int idVetDB) throws Exception {
        statement = "DELETE FROM t_veterinarios WHERE id = " + idVetDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "El veterinario se eliminó de la base de datos correctamente (si existía).";
    }
    public static Veterinario buscarVeterinarioPorID(int idVeterinarioDB) throws Exception {
        String sql = "SELECT * FROM t_veterinarios WHERE id = " + idVeterinarioDB + ";";
        ResultSet rs = Connector.getConnection().ejecutarQuery(sql);
        if (rs.next()) {
            return new Veterinario(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("cedula"), rs.getString("telefono"),rs.getString("correo"),rs.getString("especialidad"));
        }
        return null;
    }

}
