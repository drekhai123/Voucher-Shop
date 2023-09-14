/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voucher;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBHelper;

/**
 *
 * @author HP
 */
public class VoucherDAO {
     public List<VoucherDTO> list(){
        List<VoucherDTO> list = new ArrayList<>();
        String sql = "SELECT voucherID, title ,description, price, image FROM Voucher";
        try{
            Connection con = DBHelper.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                list.add(new VoucherDTO(rs.getInt("voucherID"), 
                                     rs.getString("title"), 
                                     rs.getString("description"), 
                                     rs.getBigDecimal("price"),
                                     rs.getString("image")
                ));
            }
        }   catch (SQLException ex) {
                System.out.println("Query Voucher error!" + ex.getMessage());
            }
        return list;
    }
    public List<VoucherDTO> listByCategoryName(String categoryName) {
    List<VoucherDTO> voucherList = new ArrayList<>();
    String sql ="SELECT v.voucherID, v.title, v.description, v.price, v.categoryID " +
                 "FROM Voucher v " +
                 "INNER JOIN Category c ON v.categoryID = c.categoryID " +
                 "WHERE c.categoryName = ?" +
                "ORDER BY v.voucherID"; // Assuming you want to order by voucherID

    try {
        Connection con = DBHelper.getConnection();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, categoryName); // Set the category name parameter

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            voucherList.add(new VoucherDTO(
                rs.getInt("voucherID"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getBigDecimal("price"),
                rs.getInt("categoryID"),    
                rs.getString("image")
                
            ));
        }
    } catch (SQLException ex) {
        System.out.println("Query Voucher error!" + ex.getMessage());
    }
    return voucherList;
    }
     public List<VoucherDTO> listByCategoryID(int categoryID) {
    List<VoucherDTO> voucherList = new ArrayList<>();
    String sql = "SELECT voucherID, title, description, price, categoryID, image " +
                 "FROM Voucher " +
                 "WHERE categoryID = ?"; // Assuming you want to order by voucherID

    try {
        Connection con = DBHelper.getConnection();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, categoryID); // Set the category name parameter

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            voucherList.add(new VoucherDTO(
                rs.getInt("voucherID"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getBigDecimal("price"),   
                rs.getInt("categoryID"),
                rs.getString("image")
            ));
        }
    } catch (SQLException ex) {
        System.out.println("Query Voucher error!" + ex.getMessage());
    }
    return voucherList;
    }
    
    
    public List<VoucherDTO> searchVoucherByTitle(String keyword) {
    List<VoucherDTO> list = new ArrayList<>();
    String sql = "SELECT voucherID, title, description, price, image FROM Voucher WHERE title LIKE ?";
    
    try {
        Connection conn = DBHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (keyword != null && !keyword.trim().isEmpty()) {
            ps.setString(1, "%" + keyword + "%");
        }
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new VoucherDTO(
                    rs.getInt("voucherID"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getString("image")
            ));
        }
            }catch (SQLException ex) {
                System.out.println("Query Voucher error!" + ex.getMessage());
            }
        return list;
    }
    public VoucherDTO load(Integer id){

        String sql = "SELECT voucherID, title, description, price, image FROM Voucher where voucherID = ?";
    
        try {
            
            Connection conn = DBHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new VoucherDTO(rs.getInt("voucherID"), 
                                     rs.getString("title"), 
                                     rs.getString("description"), 
                                     rs.getBigDecimal("price"),
                                     rs.getString("image")
                );             
            }
	}
        catch (SQLException ex) {
            System.out.println("Query voucher error!" + ex.getMessage());
        } 
        return null;
    }
    public Integer maxId() {
        Integer maxId = null;
        String sql = "SELECT MAX(voucherID) AS max_id FROM Voucher";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (SQLException ex) {
            System.out.println("New ID Voucher error!" + ex.getMessage());
        }

        return maxId;
    }
     public boolean insert(VoucherDTO voucher){
        String sql = "INSERT INTO Voucher( voucherID, title, description, price, image) "               
                + " VALUES (?, ?, ?, ?, ?)";    
        try {
            
            Connection conn = DBHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setLong(1, voucher.getId());
            ps.setString(2, voucher.getTitle());
            ps.setString(3, voucher.getDescription());
            ps.setBigDecimal(4, voucher.getPrice());
            ps.setString(5, voucher.getImage());

            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                return true;
            }
        }
        catch (SQLException ex) {
            System.out.println("Insert Voucher error!" + ex.getMessage());
        } 
        return false;
    }
    public boolean update(VoucherDTO voucher){
        String sql = " UPDATE Voucher SET title = ? ,description = ?, price = ?, image = ? WHERE voucherID = ?";
        int id = voucher.getId();
        String name = voucher.getTitle();
        String des = voucher.getDescription();
        BigDecimal price = voucher.getPrice();
        String image = voucher.getImage();
        try{
            Connection con = DBHelper.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, des);
            stm.setBigDecimal(3, price);
            stm.setString(4, image);
            stm.setInt(5, id);
            if(stm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Update Voucher error!" + ex.getMessage());
        }
        return false;
    }
     public boolean delete(int id){
        String sql = "DELETE FROM Voucher WHERE voucherID = ?";
        try{
            Connection con = DBHelper.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            if(stm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Delete Book error!" + ex.getMessage());
        }
        return false;
    }
}
