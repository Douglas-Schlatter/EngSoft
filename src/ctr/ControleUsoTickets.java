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
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import model.DatabaseLink;
import view.TelaGerenciarTickets;
import view.TelaTickets;
import view.TelaVisualizacao;

/**
 *
 * @author Douglas
 */
public class ControleUsoTickets {
    public String matricula = "";
    public String entrada = "";
    public String nome = "";
    DatabaseLink db;
    ResultSet resultSet;
    
    public ControleUsoTickets()
    {
    
    }
    public void iniciaControle(String imat,String ient) throws SQLException
    {
        matricula = imat;
        entrada = ient;
        db = new DatabaseLink(matricula,entrada,"c");
    }
    
    public Boolean isUsuario() throws SQLException {
        //Boolean mat = db.verificaMatricula(matricula);
        //resultSet.next();
        //String mat = resultSet.getString("matricula");
        System.out.println("isUsuario "+matricula);
        if(db.verificaMatricula(matricula)){
            return true;
        }
        else return false;
        
     }
       
    
    public void utilizarTicket(String ticket) throws SQLException {
       
        if(ticket.charAt(0) == '0'){ // é ticket
            if(!db.verificaTicket(matricula,ticket).isEmpty()) {
                if(db.deletarTicket(matricula, ticket) == 1){
                  JOptionPane.showMessageDialog(null, "Tíquete utilizado com sucesso.");
            }
        }
            else {
                JOptionPane.showMessageDialog(null, "Tíquete não existe no banco de dados");
            }
        }
        
        else{  // é pool
            if(!db.verificaPool(matricula,ticket).isEmpty()) {
                if(db.descontarPool(matricula, ticket) == 1){
                  JOptionPane.showMessageDialog(null, "Pool atualizada.");
            }
        }
            else {
                JOptionPane.showMessageDialog(null, "Este usuário não participa da pool informada.");
            }
        }
        
        
    }
    
    
    public void visualizaTicket(TelaGerenciarTickets tela){
        ArrayList<String> tickets = new ArrayList();
        
        try {
            tickets = db.pegarTickets();
        }catch (SQLException ex){
            Logger.getLogger(TelaTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TelaVisualizacao telinha  = (new TelaVisualizacao());
        tela.setVisible(false);
        telinha.setVisible(true);
        telinha.exibeLista(tickets);
    }
    
}
