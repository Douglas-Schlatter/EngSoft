/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Main.Back;


import ctr.ControleUsoTickets;
import ctr.ControleUsuario;
import model.DatabaseLink;
import view.TelaLogin;
import view.TelaTickets;


/**
 *
 * @author henry
 */
public class Main {

    public static ControleUsuario controleU = new ControleUsuario(); 
    public static ControleUsoTickets controleT = new ControleUsoTickets();
    public static void main(String[] args) {
        
        
        
        
        new TelaLogin().setVisible(true);
    }
}
