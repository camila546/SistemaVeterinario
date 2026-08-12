package cr.ac.ucenfotec.bl.entities.Consulta;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOConsulta {
    private static String statement;
    private static String query;

    public static String insertarConsulta(Consulta consultaInsertar, int idMascotaDB, int idVeterinarioDB) throws Exception {
        statement = "INSERT INTO t_consultas (tipo, fecha, hora, costo, diagnostico, estado, idMascota, idVeterinario) VALUES ('"
                + consultaInsertar.getTipo() + "', '"
                + consultaInsertar.getFecha() + "', '"
                + consultaInsertar.getHora() + "', "
                + consultaInsertar.getCosto() + ", '"
                + consultaInsertar.getDiagnostico() + "', '"
                + consultaInsertar.getEstado() + "', "
                + idMascotaDB + ", "
                + idVeterinarioDB + ");";
        Connector.getConnection().ejecutarStatement(statement);
        return "La consulta se registró en la base de datos correctamente.";
    }

    public static ArrayList<Consulta> listarConsultas() throws Exception {
        ArrayList<Consulta> listaConsultas = new ArrayList<>();
        query = "SELECT * FROM t_consultas ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return null;
        }
        do {
            String tipo = resultado.getString("tipo");
            LocalDate fecha = LocalDate.parse(resultado.getString("fecha"));
            LocalTime hora = LocalTime.parse(resultado.getString("hora"));
            double costo = resultado.getDouble("costo");

            Consulta consultaTemp = new Consulta(tipo, fecha, hora, costo, null, null);
            consultaTemp.setDiagnostico(resultado.getString("diagnostico"));
            consultaTemp.setEstado(resultado.getString("estado"));

            listaConsultas.add(consultaTemp);
        } while (resultado.next());
        return listaConsultas;
    }

    public static HashMap<Integer, Consulta> listarConsultasID() throws Exception {
        HashMap<Integer, Consulta> mapaConsultas = new HashMap<>();
        query = "SELECT * FROM t_consultas ORDER BY id;";
        ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
        if (!resultado.next()) {
            return mapaConsultas;
        }
        do {
            int idDB = resultado.getInt("id");
            String tipo = resultado.getString("tipo");
            LocalDate fecha = LocalDate.parse(resultado.getString("fecha"));
            LocalTime hora = LocalTime.parse(resultado.getString("hora"));
            double costo = resultado.getDouble("costo");

            Consulta consultaTemp = new Consulta(tipo, fecha, hora, costo, null, null);
            consultaTemp.setDiagnostico(resultado.getString("diagnostico"));
            consultaTemp.setEstado(resultado.getString("estado"));

            mapaConsultas.put(idDB, consultaTemp);
        } while (resultado.next());
        return mapaConsultas;
    }

    public static String modificarConsulta(int idConsultaDB, Consulta consultaModificar) throws Exception {
        statement = "UPDATE t_consultas SET "
                + "tipo = '" + consultaModificar.getTipo() + "', "
                + "fecha = '" + consultaModificar.getFecha() + "', "
                + "hora = '" + consultaModificar.getHora() + "', "
                + "costo = " + consultaModificar.getCosto() + ", "
                + "diagnostico = '" + consultaModificar.getDiagnostico() + "', "
                + "estado = '" + consultaModificar.getEstado() + "' "
                + "WHERE id = " + idConsultaDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "La consulta se modificó en la base de datos correctamente.";
    }

    public static String eliminarConsulta(int idConsultaDB) throws Exception {
        statement = "DELETE FROM t_consultas WHERE id = " + idConsultaDB + ";";
        Connector.getConnection().ejecutarStatement(statement);
        return "La consulta se eliminó de la base de datos correctamente.";
    }
}