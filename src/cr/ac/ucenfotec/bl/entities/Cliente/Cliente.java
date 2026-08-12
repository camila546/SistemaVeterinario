package cr.ac.ucenfotec.bl.entities.Cliente;

import cr.ac.ucenfotec.bl.entities.Mascota.Mascota;
import cr.ac.ucenfotec.bl.entities.Persona.Persona;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    //atributos
    private List<Mascota> listaMascotas; // Relación de Agregacion

    //constructor
    public Cliente(String nombre, String apellidos, String cedula, String telefono, String correo) {
        super(nombre, apellidos, cedula, telefono, correo);
        this.listaMascotas = new ArrayList<>();
    }

    //getters y setters
    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }
    public void setListaMascotas(List<Mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public boolean agregarMascota(Mascota nuevaMascota){
        if(nuevaMascota==null)return false;    //si e snula no se agrega
        if(listaMascotas.contains(nuevaMascota)){   //validar que esta mascota no exista
            return false;
        }
        listaMascotas.add(nuevaMascota);
        return true;
    }
    public boolean eliminarMascota(String idMascota){
        for(int i = 0; i<listaMascotas.size(); i++){  //recorre la lista
            if(listaMascotas.get(i).getIdMascota().equalsIgnoreCase(idMascota)){ //compara el id con el que se da
                listaMascotas.remove(i);
                return true;
            }
        }
        return false;
    }
    public Mascota obtenerMascotaPorID(String idMascota){
        for(Mascota m: listaMascotas){
            if(m.getIdMascota().equalsIgnoreCase(idMascota)){
                return m;
            }
        }
        return null;
    }
    public int calcularTotalMascotas(){
        return this.listaMascotas.size();
    }

    @Override
    public String obtenerFicha() {
        return "CLIENTE: " + nombre + " " + apellidos + " | Cédula: " + cedula + " | Teléfono: " + telefono;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " " + apellidos + " (Cédula: " + cedula + ")";
    }
}


