package cr.ac.ucenfotec.bl.entities.Mascota;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOMascota {
    private static String statement;
    private static String query;

    public static String insertarMascota(Mascota mascotaInsertar, int idClienteDB) throws Exception {
        statement = "INSERT INTO t_mascotas (id_cliente, id_mascota, nombre, especie, raza, edad) VALUES ("
                + idClienteDB + ", '"
                + mascotaInsertar.getIdMascota() + "', '"
                + mascotaInsertar.getNombre() + "', '"
                + mascotaInsertar.getEspecie() + "', '"
                + mascotaInsertar.getRaza() + "', "
                + mascotaInsertar.getEdad() + ");";
        Connector.getConnection().ejecutarStatement(statement);
        return "La mascota se registró en la base de datos correctamente.";
    }

    public static ArrayList<Mascota> listarMascotas() throws Exception {
        ArrayList<Mascota> listaMascotas = new ArrayList<>();
        query = "SELECT * FROM t_mascotas ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return null;
        }
        do {
            Mascota mascotaTemp = new Mascota(resultado.getString("id_mascota"), resultado.getString("nombre"), resultado.getString("especie"), resultado.getString("raza"), resultado.getInt("edad"));
            listaMascotas.add(mascotaTemp);
        } while (resultado.next());
        return listaMascotas;
    }

    public static HashMap<Integer, Mascota> listarMascotasID() throws Exception {
        HashMap<Integer, Mascota> mapaMascotas = new HashMap<>();
        query = "SELECT * FROM t_mascotas ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return mapaMascotas;
        }
        do {
            int idDB = resultado.getInt("id");
            Mascota mascotaTemp = new Mascota(resultado.getString("id_mascota"), resultado.getString("nombre"), resultado.getString("especie"), resultado.getString("raza"), resultado.getInt("edad"));
            mapaMascotas.put(idDB, mascotaTemp);
        } while (resultado.next());
        return mapaMascotas;
    }

    public static String modificarMascota(int idMascotaDB, Mascota mascotaModificar) throws Exception {
        statement = "UPDATE t_mascotas SET "
                + "id_mascota = '" + mascotaModificar.getIdMascota() + "', "
                + "nombre = '" + mascotaModificar.getNombre() + "', "
                + "especie = '" + mascotaModificar.getEspecie() + "', "
                + "raza = '" + mascotaModificar.getRaza() + "', "
                + "edad = " + mascotaModificar.getEdad() + " "
                + "WHERE id = " + idMascotaDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "La mascota se modificó en la base de datos correctamente.";
    }

    public static String eliminarMascota(int idMascotaDB) throws Exception {
        statement = "DELETE FROM t_mascotas WHERE id = " + idMascotaDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "La mascota se eliminó de la base de datos correctamente (si existía).";
    }
}