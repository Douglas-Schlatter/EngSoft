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
    public String senha = "";
    DatabaseLink db = new DatabaseLink();
    ResultSet resultSet;
    ResultSet passResult;
    
    public ControleUsuario(String imat,String isen){
        matricula = imat;
        senha = isen;
        
    }
    
    public void login(TelaTickets telaTick) throws SQLException{
        resultSet = db.verificaMatricula(matricula);
        
        
        
        if(!resultSet.next()){
            JOptionPane.showMessageDialog(null, "Matricula Inexistente");
        }
        else{
            passResult = db.verificaSenha(matricula,senha);
            if(!passResult.next()){
            JOptionPane.showMessageDialog(null, "Senha Incorreta");
            }
            else
            {
             this.visualizaTicket();
             telaTick.setVisible(false);
            }
           
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
