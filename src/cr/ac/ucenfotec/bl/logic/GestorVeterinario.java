package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.Exceptions.CedulaDuplicadaException;
import cr.ac.ucenfotec.bl.Exceptions.EntidadNoEncontradaException;
import cr.ac.ucenfotec.bl.entities.Veterinario.DAOVeterinario;
import cr.ac.ucenfotec.bl.entities.Veterinario.Veterinario;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorVeterinario {

    public static void listarVeterinarios(ArrayList<Veterinario> listaDestino) throws Exception {
        ArrayList<Veterinario> listaVets = DAOVeterinario.listarVeterinarios();
        if (listaVets != null) {
            listaDestino.addAll(listaVets);
        }
    }

    public static void listarVeterinariosID(HashMap<Integer, Veterinario> listaVeterinariosID) throws Exception {
        listaVeterinariosID.clear();
        listaVeterinariosID.putAll(DAOVeterinario.listarVeterinariosID());
    }

    public static String modificarVeterinario(int idVetDB, String nombre, String apellidos, String cedula, String telefono, String correo, String especialidad) throws Exception {
        Veterinario vetNuevo = new Veterinario(nombre, apellidos, cedula, telefono, correo, especialidad);
        return DAOVeterinario.modificarVeterinario(idVetDB, vetNuevo);
    }

    public static String eliminarVeterinario(int idVetDB) throws Exception {
        return DAOVeterinario.eliminarVeterinario(idVetDB);
    }
    public static String agregarVeterinario(String nombre, String apellidos, String cedula, String telefono, String correo, String especialidad) throws CedulaDuplicadaException, Exception {
        Veterinario nuevoVet = new Veterinario(nombre, apellidos, cedula, telefono, correo, especialidad);
        return DAOVeterinario.insertarVeterinario(nuevoVet);
    }

    public static Veterinario buscarVeterinarioPorCedula(String cedula) throws EntidadNoEncontradaException, Exception {
        return DAOVeterinario.obtenerVeterinarioObligatorio(cedula);
    }

}
