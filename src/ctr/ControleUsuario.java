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
import view.TelaGerente;
import view.TelaLogin;
import view.TelaTickets;
import view.TelaVinculado;
import view.TelaVisualizacao;

/**
 *
 * @author henry
 */
public class ControleUsuario {
    
    public String matricula = "";
    public String senha = "";
    DatabaseLink db;
    ResultSet resultSet;
    ResultSet passResult;
    
    public ControleUsuario(){

    }
    public void iniciaControle(String imat,String isen)
    {
        matricula = imat;
        senha = isen;
        db = new DatabaseLink(matricula,senha);
    }
    
    public void login(TelaLogin tela) throws SQLException{
        resultSet = db.verificaMatricula();
        
        
        
        if(!resultSet.next()){
            JOptionPane.showMessageDialog(null, "Matricula Inexistente");
        }
        else{
            passResult = db.verificaSenha();
            if(!passResult.next()){
            JOptionPane.showMessageDialog(null, "Senha Incorreta");
            }
            else
            {
                //System.out.println(db.verificaUsuario());
                if (db.verificaUsuario().equals( "v")) {
                TelaVinculado telinha  = (new TelaVinculado());
                telinha.setVisible(true);
                } else {
                TelaGerente telinha  = (new TelaGerente());
                telinha.setVisible(true);
                }
             //this.visualizaTicket();

             tela.setVisible(false);
             
            }
           
        }
    }
    
    public void visualizaTicket(){
        ArrayList<String> tickets = new ArrayList();
        
        try {
            tickets = db.pegarTickets();
        }catch (SQLException ex){
            Logger.getLogger(TelaTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        TelaVisualizacao telinha  = (new TelaVisualizacao());
        telinha.setVisible(true);
        telinha.exibeLista(tickets);
    }
}
