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
import Main.Main;
import java.util.Random;

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
                JOptionPane.showMessageDialog(null, "Tíquete não existe");
            }
        }
        
        else{  // é pool
            if(!db.verificaPool(matricula,ticket).isEmpty()) {
                if(db.descontarPool(matricula, ticket) == 1){
                  JOptionPane.showMessageDialog(null, "Pool Utilizada.");
            }
        }
            else {
                JOptionPane.showMessageDialog(null, "Este usuário não participa da pool informada ou essa pool ja esta esgotada");
            }
        }
        
        
    }
    //1 cod = 50 usos -> unico
    //50 cod = 1 usos ->mult
    public void ComprarTicket(String tipo,int quantidade) throws SQLException
    {
        System.out.println(Main.controleU.matricula);
        matricula = Main.controleU.matricula;
        if(tipo == "Único")
        {
            for(int i = 0;  i<quantidade; i++)
            {
                Random rnd = new Random();
                int number = rnd.nextInt(999999);


               String tick = "0"+ String.format("%07d", number);
               Main.controleU.db.inserirTicket(matricula,tick, 1);
            }
        
        }
        else
        {
                Random rnd = new Random();
                int number = rnd.nextInt(999999);


               String tick = "0"+ String.format("%07d", number);
               Main.controleU.db.inserirTicket(matricula,tick, quantidade);
        }
        
        //Main.controleU.db.inserirTicket();
        //System.out.println(restult);
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
