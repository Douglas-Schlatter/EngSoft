
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
        String codRefeicao = "";
        

    public DatabaseLink(String imat,String ientrada,String t) throws SQLException
    {
        matricula = imat;
        if(t =="l")
        {
            //Nesse caso a entrada eh para login sendo equivalente a senha
                senha = ientrada;
                codRefeicao = "";
        }
        else
        {
            //Nesse caso a entrada eh para uso de ticket sendo equivalente a codRefeicao
            senha = "";
            codRefeicao = ientrada;
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        statement = conn.createStatement();
    }
  
    public ResultSet verificaMatricula() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT * FROM Registros WHERE matricula = '" + matricula + "'";                              
        resultSet = statement.executeQuery(sql);
        
        return resultSet;
        
    }
    
    
    public Boolean verificaMatricula(String matricula) throws SQLException{

        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT * FROM Registros WHERE matricula = '"+matricula+"'";                              
        resultSet = statement.executeQuery(sql);
        
        if(!resultSet.next()) {
            
            return false;
        }  
        else{
            String mat = resultSet.getString("matricula");
            System.out.println("return do sql "+ mat);
            return true;
        }
     
    }
    

    
        public ResultSet verificaSenha() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        //conn = DriverManager.getConnection(url, sqluser, password);
        //String sql = (String) "SELECT * FROM Registros WHERE senha = '" + senha + "'";      
        String sql = (String) "SELECT * FROM Registros WHERE matricula = '"+ matricula + "' and senha = '" + senha + "'";
        //statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
        return resultSet;
        
    }
        
        public String getNomeDB() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        //conn = DriverManager.getConnection(url, sqluser, password);
        //String sql = (String) "SELECT * FROM Registros WHERE senha = '" + senha + "'";      
        String sql = (String) "SELECT nome FROM Registros WHERE matricula = '"+ matricula + "' and senha = '" + senha + "'";
        //statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        
     
                
         //System.out.println(resultSet.toString());
         resultSet.next();
         //System.out.println(resultSet.getString("nome"));
        return resultSet.getString("nome");
        
    }
        
    public String verificaUsuario() throws SQLException{

        
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        //conn = DriverManager.getConnection(url, sqluser, password);
        //String sql = (String) "SELECT * FROM Registros WHERE senha = '" + senha + "'";      
        String sql = (String) "SELECT tipo FROM Registros WHERE matricula = '"+ matricula + "' and senha = '" + senha + "'";
        //statement = conn.createStatement();
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

        //conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT id_tiquete FROM Vinculado JOIN Tiquetes ON (Vinculado.matricula_vinculado = Tiquetes.id_vinculado) WHERE matricula_vinculado = '" + matricula + "'";                              
        //statement = conn.createStatement();
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
    
    public String verificaTicket(String matricula, String ticket) throws SQLException{
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        //conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT * FROM Tiquetes NATURAL JOIN Vinculado WHERE id_tiquete = '"+ticket+"' and matricula_vinculado = '"+matricula+"'";
        
        //statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        String tick = "";
        if(!resultSet.next()) {
            return tick;
        }
        else {
            tick = resultSet.getString("id_tiquete");
            return tick;
        }
    }
    
    public String verificaPool(String matricula, String pool) throws SQLException{
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }

        //conn = DriverManager.getConnection(url, sqluser, password);
        String sql = (String) "SELECT * FROM relation_vinculado_pool NATURAL JOIN Pool WHERE id_pool = '"+pool+"' AND matricula_vinculado = '"+matricula+"'";
        
        //statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        String res = "";
        if(!resultSet.next()) {
            return res;
        }
        else {
            res = resultSet.getString("id_pool");
            return res;
        }
    }
    
    public int descontarPool(String matricula, String pool) throws SQLException{
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }
        String sql = (String) "SELECT * FROM relation_vinculado_pool NATURAL JOIN Pool WHERE id_pool = '"+pool+"' AND matricula_vinculado = '"+matricula+"'";
        
        resultSet = statement.executeQuery(sql);
        resultSet.next();
        int usos = resultSet.getInt("uses");
        usos = usos - 1;
        
        int result = 0;
        
        if(usos == 0){
            sql = (String) "DELETE FROM Pool WHERE id_pool = '"+pool+"'";
            result = statement.executeUpdate(sql);
        }
        else {
            sql = (String) "UPDATE Pool SET uses = '"+usos+"' WHERE id_pool = '"+pool+"'";
            result = statement.executeUpdate(sql);
        }
       
        return result;
    }
    
    public int deletarTicket(String matricula, String ticket) throws SQLException{
        try{
             Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            e.getMessage();
        }
        String sql = (String) "SELECT * FROM Tiquetes NATURAL JOIN Vinculado WHERE id_tiquete = '"+ticket+"' and matricula_vinculado = '"+matricula+"'";
        resultSet = statement.executeQuery(sql);
        resultSet.next();
        int usos = resultSet.getInt("usos_restantes");
        usos = usos - 1;
        
        int result = 0;
        
        if(usos == 0){
            sql = (String) "DELETE FROM Tiquetes WHERE id_tiquete = '"+ticket+"' and matricula_vinculado = '"+matricula+"'";
            result = statement.executeUpdate(sql);
        }
        else {
            sql = (String) "UPDATE Tiquetes SET usos_restantes = '"+usos+"' WHERE id_tiquete = '"+ticket+"'";
            result = statement.executeUpdate(sql);
        }
       
        return result;
    }

}
