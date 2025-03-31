/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import edu.ijse.mvc.db.DBConnection;
import java.sql.Connection;
import edu.ijse.mvc.dto.CustomerDto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ivanj
 */
public class CustomerModel {
    public String saveCustomer(CustomerDto customerDto) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customerDto.getCustId());
        statement.setString(2, customerDto.getCustTitle());
        statement.setString(3, customerDto.getCustName());
        statement.setString(4, customerDto.getDob());
        statement.setDouble(5, customerDto.getSalary());
        statement.setString(6, customerDto.getCustAddress());
        statement.setString(7, customerDto.getCity());
        statement.setString(8, customerDto.getProvince());
        statement.setString(9, customerDto.getPostalCode());
        
        return statement.executeUpdate() > 0 ? "Successfully Saved" : "Fail";
    }
    
    public String updateCustomer(CustomerDto customerDto) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Customer SET CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ? WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, customerDto.getCustTitle());
        statement.setString(2, customerDto.getCustName());
        statement.setString(3, customerDto.getDob());
        statement.setDouble(4, customerDto.getSalary());
        statement.setString(5, customerDto.getCustAddress());
        statement.setString(6, customerDto.getCity());
        statement.setString(7, customerDto.getProvince());
        statement.setString(8, customerDto.getPostalCode());
        statement.setString(9, customerDto.getCustId());
        
        return statement.executeUpdate() > 0 ? "Successfully Updated" : "Fail";
    }
    
    public String deleteCustomer(String custId) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, custId);
          
        return statement.executeUpdate() > 0 ? "Successfully Deleted" : "Fail";
    }
    
    public CustomerDto searchItem(String custId) throws ClassNotFoundException, SQLException{
          Connection connection = DBConnection.getInstance().getConnection();
          String sql = "SELECT * FROM Customer WHERE CustID = ?";
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1, custId);
          
          ResultSet rst = statement.executeQuery();
          
          if(rst.next()){
              CustomerDto dto = new CustomerDto(rst.getString("CustID"), rst.getString("CustTitle"), rst.getString("CustName"), rst.getString("DOB"), rst.getDouble("salary"), rst.getString("CustAddress"), rst.getString("City"), rst.getString("Province"), rst.getString("PostalCode"));
              return dto;
          }
          return null;
      }
      
      public ArrayList<CustomerDto> getAll() throws ClassNotFoundException, SQLException{
          Connection connection = DBConnection.getInstance().getConnection();
          String sql = "SELECT * FROM Customer";
          PreparedStatement statement = connection.prepareStatement(sql);
          
          ResultSet rst = statement.executeQuery();
          ArrayList<CustomerDto> custDtos = new ArrayList<>();
          
          while (rst.next()) {            
              CustomerDto dto = new CustomerDto(rst.getString("CustID"), rst.getString("CustTitle"), rst.getString("CustName"), rst.getString("DOB"), rst.getDouble("salary"), rst.getString("CustAddress"), rst.getString("City"), rst.getString("Province"), rst.getString("PostalCode"));
              custDtos.add(dto);
          }
          return custDtos;
      }
}
