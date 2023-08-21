
package model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class DatabaseLink {

        String sqluser = "postgres";
        String password = "1707";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String matricula = "";
        String senha = "";
        

    public DatabaseLink(String imat,String isen)
    {
        matricula = imat;
        senha = isen;
    }
    
    public ResultSet verificaMatricula() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT * FROM Registros WHERE matricula = '" + matricula + "'";                              
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
        return resultSet;
        
    }
    
        public ResultSet verificaSenha() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        //String sql = (String) "SELECT * FROM Registros WHERE senha = '" + senha + "'";      
        String sql = (String) "SELECT * FROM Registros WHERE matricula = '"+ matricula + "' and senha = '" + senha + "'";
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
        return resultSet;
        
    }
        
    public String verificaUsuario() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        //String sql = (String) "SELECT * FROM Registros WHERE senha = '" + senha + "'";      
        String sql = (String) "SELECT tipo FROM Registros WHERE matricula = '"+ matricula + "' and senha = '" + senha + "'";
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
     
                
         //System.out.println(resultSet.toString());
         resultSet.next();
        return resultSet.getString("tipo");
        
    }
    

    public ArrayList<String> pegarTickets() throws SQLException{
        
        //TODO MAKE ONE FUNCTION ONLY THAT LOGINS AND IT IS STATIC IN THE CLASS
        /*
        String sqluser = "postgres";
        String password = "1707";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
*/
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
