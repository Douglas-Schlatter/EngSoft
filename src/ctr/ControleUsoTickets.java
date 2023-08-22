/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ctr;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DatabaseLink;

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
        resultSet = db.verificaMatricula();
        resultSet.next();
        String mat = resultSet.getString("matricula_vinculado");
        
        if(mat.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
        }
        
        public Boolean isTicket() throws SQLException {
        resultSet = db.verificaMatricula();
        resultSet.next();
        String mat = resultSet.getString("matricula_vinculado");
        
        if(mat.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
        
    }
}
