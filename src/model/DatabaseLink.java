
package model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class DatabaseLink {



    public DatabaseLink()
    {

    }
    
    public ResultSet verificaMatricula(String matricula) throws SQLException{
        String sqluser = "postgres";
        String password = "1804";
        String url = "jdbc:postgresql://localhost:5432/portal";
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT * FROM Vinculado WHERE matricula_vinculado = '" + matricula + "'";                              
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
        return resultSet;
        
    }

    public ArrayList<String> conectaPostgres(String matricula) throws SQLException{
        String sqluser = "postgres";
        String password = "1804";
        String url = "jdbc:postgresql://localhost:5432/portal";
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> tickets = new ArrayList();

        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT id_tiquete FROM Vinculado JOIN Tiquetes ON (Vinculado.matricula_vinculado = Tiquetes.id_vinculado) WHERE matricula_vinculado = '" + matricula + "'";                              
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
        try{
            
        // Criar a declaração SQL

            // Executar a consulta SQL
        
        while (resultSet.next()) {
                // Extrair dados do resultado
                String nome = resultSet.getString("id_tiquete");

                // Fazer algo com os dados obtidos
               tickets.add("Tiquete: " +nome);
        }
        //System.out.println("OI");
        if(conn != null){
            //System.out.println("OI");
        }
        conn.close();
        }catch(Exception e){
            //System.out.println(e.getMessage());

            JOptionPane.showMessageDialog(null, "Banco de dados não conectado");
        }
        
        return tickets;


    }

}
