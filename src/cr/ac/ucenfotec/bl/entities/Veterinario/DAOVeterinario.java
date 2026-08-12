package cr.ac.ucenfotec.bl.entities.Veterinario;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOVeterinario {
    private static String query;
    private static String statement;

    // Métodos
    public static String insertarVeterinario(Veterinario veterinarioInsertar) throws Exception {
        statement = "INSERT INTO t_veterinarios (cedula, nombre, apellidos, telefono, correo, especialidad) VALUES ('" + veterinarioInsertar.getCedula() + "', '" + veterinarioInsertar.getNombre() + "', '" + veterinarioInsertar.getApellidos() + "', '" + veterinarioInsertar.getTelefono() + "', '" + veterinarioInsertar.getCorreo() + "', '" + veterinarioInsertar.getEspecialidad() + "');";
        Connector.getConnection().ejecutarStatement(statement);
        return "El veterinario se registró en la base de datos correctamente.";
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

}
