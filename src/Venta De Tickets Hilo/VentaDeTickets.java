/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentaDeTickets;

/**
 *
 * @author Carlos
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentaDeTickets implements Runnable {

    private ContadorDeTickets contador;
    private String nombre;

    public VentaDeTickets(ContadorDeTickets contador, String nombre) {
        this.contador = contador;
        this.nombre = nombre;
    }

    public static void main(String[] args) {
        // Crea una nueva instancia de la clase ContadorDeTickets con un límite de tickets disponibles
        ContadorDeTickets contador = new ContadorDeTickets(20);

        // Crea un arreglo de hilos para simular la venta de tickets por parte de varios vendedores
        Thread[] vendedores = new Thread[5];
        for (int i = 0; i < vendedores.length; i++) {
            vendedores[i] = new Thread(new VentaDeTickets(contador, "Vendedor " + (i + 1)));
            vendedores[i].start();
        }

        // Espera a que todos los hilos terminen
        for (Thread vendedor : vendedores) {
            try {
                vendedor.join();
            } catch (InterruptedException e) {
                System.out.println("Error en: " + e.getMessage());
            }
        }

        // Imprime el número total de tickets vendidos
        System.out.println("Total de tickets vendidos: " + contador.obtenerTotalVendidos());

        // Crea un archivo para almacenar el registro de las ventas de tickets
        File ventas = new File("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\EjerciciosHiloIA\\src\\VentaDeTickets\\ventas.txt");
        try {
            // Verifica si el archivo ya existe y, de ser así, lo borra
            if (ventas.exists()) {
                ventas.delete();
            }

            // Crea el archivo y escribe el número total de tickets vendidos
            ventas.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ventas));
            writer.write("Total de tickets vendidos: " + contador.obtenerTotalVendidos());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentaDeTickets.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (contador.venderTicket()) {
                System.out.println(nombre + " vendió un ticket");
            } else {
                break;
            }
        }
    }
}
