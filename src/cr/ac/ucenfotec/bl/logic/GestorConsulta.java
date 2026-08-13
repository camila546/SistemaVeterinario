package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.Exceptions.EntidadNoEncontradaException;
import cr.ac.ucenfotec.bl.Exceptions.FechaInvalidaException;
import cr.ac.ucenfotec.bl.Exceptions.HorarioOcupadoException;
import cr.ac.ucenfotec.bl.entities.Consulta.DAOConsulta;
import cr.ac.ucenfotec.bl.entities.Consulta.Consulta;
import cr.ac.ucenfotec.bl.entities.Mascota.Mascota;
import cr.ac.ucenfotec.bl.entities.Veterinario.Veterinario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorConsulta {

    public static String agregarConsulta(String tipo, LocalDate fecha, LocalTime hora, double costo, String diagnostico, String estado, int idMascotaDB, int idVeterinarioDB)
            throws FechaInvalidaException, HorarioOcupadoException, EntidadNoEncontradaException, Exception {
        if (fecha.isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("No se puede programar una consulta para una fecha pasada.");
        }

        if (DAOConsulta.existeCitaEnHorario(idVeterinarioDB, idMascotaDB, fecha, hora)) {
            throw new HorarioOcupadoException("El veterinario o la mascota ya tienen una consulta asignada para esa fecha y hora.");
        }
        Consulta nuevaConsulta = new Consulta(tipo, fecha, hora, costo, null, null);
        nuevaConsulta.setDiagnostico(diagnostico);
        nuevaConsulta.setEstado(estado);
        return DAOConsulta.insertarConsulta(nuevaConsulta, idMascotaDB, idVeterinarioDB);
    }

    public static void listarConsultas(ArrayList<Consulta> listaDestino) throws Exception {
        ArrayList<Consulta> lista = DAOConsulta.listarConsultas();
        if (lista != null) {
            listaDestino.addAll(lista);
        }
    }

    public static void listarConsultasID(HashMap<Integer, Consulta> listaConsultasID) throws Exception {
        listaConsultasID.clear();
        listaConsultasID.putAll(DAOConsulta.listarConsultasID());
    }

    public static String modificarConsulta(int idConsultaDB, String tipo, LocalDate fecha, LocalTime hora, double costo, String diagnostico, String estado) throws Exception {
        Consulta consultaModificada = new Consulta(tipo, fecha, hora, costo, null, null);
        consultaModificada.setDiagnostico(diagnostico);
        consultaModificada.setEstado(estado);
        return DAOConsulta.modificarConsulta(idConsultaDB, consultaModificada);
    }

    public static String eliminarConsulta(int idConsultaDB) throws Exception {
        return DAOConsulta.eliminarConsulta(idConsultaDB);
    }
}
