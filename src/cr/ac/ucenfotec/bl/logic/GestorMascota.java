package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.Exceptions.EntidadNoEncontradaException;
import cr.ac.ucenfotec.bl.entities.Mascota.DAOMascota;
import cr.ac.ucenfotec.bl.entities.Mascota.Mascota;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorMascota {

    public static String agregarMascota(String idMascota, String nombre, String especie, String raza, int edad, int idClienteDB)
            throws EntidadNoEncontradaException, Exception {
        Mascota nuevaMascota = new Mascota(idMascota, nombre, especie, raza, edad);
        return DAOMascota.insertarMascota(nuevaMascota, idClienteDB);
    }

    public static void listarMascotas(ArrayList<Mascota> listaDestino) throws Exception {
        ArrayList<Mascota> listaMascotas = DAOMascota.listarMascotas();
        if (listaMascotas != null) {
            listaDestino.addAll(listaMascotas);
        }
    }

    public static void listarMascotasID(HashMap<Integer, Mascota> listaMascotasID) throws Exception {
        listaMascotasID.clear();
        listaMascotasID.putAll(DAOMascota.listarMascotasID());
    }

    public static String modificarMascota(int idMascotaDB, String idMascota, String nombre, String especie, String raza, int edad) throws Exception {
        Mascota mascotaModificada = new Mascota(idMascota, nombre, especie, raza, edad);
        return DAOMascota.modificarMascota(idMascotaDB, mascotaModificada);
    }

    public static String eliminarMascota(int idMascotaDB) throws Exception {
        return DAOMascota.eliminarMascota(idMascotaDB);
    }
}