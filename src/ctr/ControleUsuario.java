/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ctr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DatabaseLink;
import view.TelaTickets;
import view.TelaVisualizacao;

/**
 *
 * @author henry
 */
public class ControleUsuario {
    
    public String matricula = "";
    DatabaseLink db = new DatabaseLink();
    ResultSet resultSet;
    
    public ControleUsuario(String imat){
        matricula = imat;
        
    }
    
    public void verificaMatricula(TelaTickets tl) throws SQLException{
        resultSet = db.verificaMatricula(matricula);
        
        if(!resultSet.next()){
            JOptionPane.showMessageDialog(null, "Matricula Inexistente");
        }
        else{
            this.visualizaTicket();
        }
    }
    
    public void visualizaTicket(){
        ArrayList<String> tickets = new ArrayList();
        
        try {
            tickets = db.conectaPostgres(matricula);
        }catch (SQLException ex){
            Logger.getLogger(TelaTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        TelaVisualizacao telinha  = (new TelaVisualizacao());
        telinha.setVisible(true);
        telinha.exibeLista(tickets);
    }
}
