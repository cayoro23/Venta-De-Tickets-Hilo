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
public class ContadorDeTickets {

    private int disponibles;
    private int totalVendidos;

    public ContadorDeTickets(int disponibles) {
        this.disponibles = disponibles;
        this.totalVendidos = 0;
    }

    public synchronized boolean venderTicket() {
        if (disponibles > 0) {
            disponibles--;
            totalVendidos++;
            return true;
        }
        return false;
    }

    public int obtenerTotalVendidos() {
        return totalVendidos;
    }
}
