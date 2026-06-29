import java.util.Objects;

public class Mascota {
    //atributos
    private String idMascota;
    private String nombre;
    private String especie;
    private String raza; //a veces la edad y raza influye en varias recetas
    private int edad;

    //constructor
    public Mascota(String idMascota, String nombre, String especie, String raza, int edad) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
    }
    //getters y setters
    public String getIdMascota() {
        return idMascota;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }


    public void cumplirAnio(){ //aumentar edad
        this.edad++;
    }
    // V¿verifica si la mascota es adulta mayor según especie
    public boolean esAdultoMayor(){
        // perros/gatos con 8+ años ocupan dosis/cuidados especiales
        if (this.especie.equalsIgnoreCase("Perro") || this.especie.equalsIgnoreCase("Gato")) {
            return this.edad >= 8;
        }
        return this.edad >= 5; // Para otras especies menores
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj){ //verifica si los obejetos son los mismos
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // Verifica que el objeto no sea nulo
            return false;
        }
        Mascota otra = (Mascota) obj;
        return idMascota.equalsIgnoreCase(otra.idMascota); // Compara el iid de ambas mascotas.
    }
    @Override
    public String toString() {
        return "Mascota:" +
                "idMascota: " + idMascota + '\'' +
                ", nombre: " + nombre + '\'' +
                ", especie: " + especie + '\'' +
                ", raza: " + raza + '\'' +
                ", edad: " + edad;
    }
}
