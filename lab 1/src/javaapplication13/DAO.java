/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication13;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author eng. mahmoud hassan
 */
public class DAO {
    private boolean conFlag;
    private Connection con;
    private ResultSet rs;
    public DAO (){
        System.out.println("connection "+ connect());
    }
    
    public boolean connect(){
        
        conFlag=false;
    try {   
            
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact","mahmoud1","12345");
            if(con != null){
                conFlag=true;
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
    }
    
    public List<ContactOfPerson> getContacts(){
        String query = "select * from contact order by name";
        List<ContactOfPerson> contacts = new ArrayList<>();
        try {
            if (conFlag){
            Statement stmt = con.createStatement();   
            ResultSet rs = stmt.executeQuery(query);
            
                while (rs.next()) {
                    ContactOfPerson person = createContactOfPerson(rs);
                    contacts.add(person);
                }
                rs.close();
                stmt.close();
            }
        }catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }
    
    public ContactOfPerson createContactOfPerson(ResultSet rs){
        ContactOfPerson p =new ContactOfPerson();
        try {
            p.setId(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setNick_name(rs.getString(3));
            p.setAddress(rs.getString(4));
            p.setHome_phone(rs.getString(5));
            p.setWork_phone(rs.getString(6));
            p.setCell_phone(rs.getString(7));
            p.setEmail(rs.getString(8));
            p.setBirthday(rs.getDate(9));
            p.setWeb_site(rs.getString(10));
            p.setProfession(rs.getString(11));
        } catch (SQLException ex) {
            Logger.getLogger(ContactOfPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
   
    public void closeConnection () {
        try {
            con.close();
            conFlag=false;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean insertContactPerson(ContactOfPerson person){
        boolean resultFlag = false;
        
        try {
            if(conFlag){
                
                PreparedStatement stmt = con.prepareStatement("insert into contact (name,nick_name,address,home_phone,work_phone,cell_phone,email,birthday,web_site,profession) values(?,?,?,?,?,?,?,?,?,?)");
                
                stmt.setString(1,person.getName());
                stmt.setString(2, person.getNick_name());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getHome_phone());
                stmt.setString(5, person.getWork_phone());
                stmt.setString(6, person.getCell_phone());
                stmt.setString(7, person.getEmail());
                java.sql.Date sqlDate = new java.sql.Date(person.getBirthday().getTime());
                stmt.setDate(8, sqlDate);
                stmt.setString(9, person.getWeb_site());
                stmt.setString(10, person.getProfession());
                
            
                
                if(stmt.executeUpdate() != 0){
                    resultFlag = true;
                }
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         return resultFlag;    
        
    }
    
    public List<ContactOfPerson> getContactOfPerson (String name){
        List<ContactOfPerson> listOfPersons = new ArrayList<>();
        try {
            if(conFlag){
                PreparedStatement stmt = con.prepareStatement("select * from contact where name = ?");
                stmt.setString(1, name);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    ContactOfPerson person = createContactOfPerson(rs);
                    listOfPersons.add(person);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfPersons;
    }
    
    public boolean editContactOfPersonById(ContactOfPerson person){
        try {
            if(conFlag){
                PreparedStatement stmt = con.prepareStatement("update contact set name=?,nick_name=?,address=?,home_phone=?,work_phone=?,cell_phone=?,email=?,birthday=?,web_site=?,profession=? where id = ?");
                stmt.setInt(11, person.getId());
                stmt.setString(1, person.getName());
                stmt.setString(2, person.getNick_name());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getHome_phone());
                stmt.setString(5, person.getWork_phone());
                stmt.setString(6, person.getCell_phone());
                stmt.setString(7, person.getEmail());
                java.sql.Date sqlDate = new java.sql.Date(person.getBirthday().getTime());   
                stmt.setDate(8, sqlDate);
                stmt.setString(9, person.getWeb_site());
                stmt.setString(10, person.getProfession());
                
                if(stmt.executeUpdate() !=0){
                    return true;
                }
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return false;
    }
}

