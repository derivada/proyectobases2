package aplicacion;
import java.util.Calendar;

/*
 * Proyecto para Bases de Datos II - Mercado de Valores
 * repo: https://github.com/derivada/proyectobases2
 * libreria GUI: Swing
 * libreria postgres: postgresql.jar
 * 
 * estructura en paquetes: 
 * Aplicación - Lógica principal, clases principales y de entidades definidas
 * Database - Accesos a la base de datos
 * GUI - Interfaz gráfica
 *
 * Recordad comentad en vuestro código cualquier cosa que no sea obvia :) 
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(" --- Proyecto Bases de Datos II ---");
        FachadaAplicacion fa = new FachadaAplicacion();
        fa.inicializarGUI();
    }

}
