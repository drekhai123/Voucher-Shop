/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBHelper;

/**
 *
 * @author HP
 */
public class UserDAO {
    public UserDTO login(String username, String password){
        String sql = "SELECT username, email, roleID FROM Users WHERE username = ? AND password = ?";
        try{
            Connection con = DBHelper.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                UserDTO dto = new UserDTO();
                dto.setUsername(rs.getString("username"));
                dto.setEmail(rs.getString("email"));
                dto.setRoleID(rs.getInt("roleID"));
                return dto;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public boolean insert(UserDTO user){
        String sql = "INSERT INTO Users( userID, username, email, password) "               
                + " VALUES (?, ?, ?, ?)";    
        try {
            
            Connection conn = DBHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setInt(1, user.getUserID());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                return true;
            }
        }
        catch (SQLException ex) {
            System.out.println("Insert user error!" + ex.getMessage());
        } 
        return false;
    }
    public Integer maxId() {
        Integer maxId = null;
        String sql = "SELECT MAX(userID) AS max_id FROM Users";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (SQLException ex) {
            System.out.println("New ID User error!" + ex.getMessage());
        }

        return maxId;
    }
    public boolean doesUsernameExist(String username) {
    String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
    try (Connection con = DBHelper.getConnection();
         PreparedStatement stm = con.prepareStatement(sql)) {
        stm.setString(1, username);
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error checking username existence: " + ex.getMessage());
    }
    return false; // Return false in case of an error or if username doesn't exist
}

    
}
