/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import konekasi.koneksi;

/**
 *
 * @author herudi
 */
public class classBaju implements Serializable {
    private String kodeBaju,namaBaju,vendorBaju,tanggalBaju,cariBaju,
                   strTanggalBaju,autoKode,hargaBaju,menuCrud,cmbLogin,
                   statusBaju;
    private int bajuNo,no;
    koneksi k;
    Stage stage;
    
    public classBaju(){
        k = new koneksi();
    }
    
    public classBaju (int bajuNo, String kodeBaju, String namaBaju, String vendorBaju,
                      String hargaBaju, String tanggalBaju, String statusBaju, String cariBaju,
                      String strTanggalBaju, String autoKode, String menuCrud,
                      String cmbLogin, int no){
        
        this.bajuNo = bajuNo;
        this.kodeBaju = kodeBaju;
        this.namaBaju = namaBaju;
        this.vendorBaju = vendorBaju;
        this.hargaBaju = hargaBaju;
        this.tanggalBaju = tanggalBaju;
        this.statusBaju = statusBaju;
        this.cariBaju = cariBaju;
        this.strTanggalBaju = strTanggalBaju;
        this.autoKode = autoKode;
        this.menuCrud = menuCrud;
        this.cmbLogin = cmbLogin;
        this.no = no;
    }
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public int getBajuNo() {
        return bajuNo;
    }
    public void setBajuNo(int bajuNo) {
        this.bajuNo = bajuNo;
    }
    public String getKodeBaju() {
        return kodeBaju;
    }
    public void setKodeBaju(String kodeBaju) {
        this.kodeBaju = kodeBaju;
    }
    public String getNamaBaju() {
        return namaBaju;
    }
    public void setNamaBaju(String namaBaju) {
        this.namaBaju = namaBaju;
    }
    public String getVendorBaju() {
        return vendorBaju;
    }
    public void setVendorBaju(String vendorBaju) {
        this.vendorBaju = vendorBaju;
    }
    public String getHargaBaju() {
        return hargaBaju;
    }
    public void setHargaBaju(String hargaBaju) {
        
        this.hargaBaju = hargaBaju;
    }
    public String getTanggalBaju() {
        return tanggalBaju;
    }
    public void setTanggalBaju(String tanggalBaju) {
        this.tanggalBaju = tanggalBaju;
    }
    public String getStatusBaju() {
        return statusBaju;
    }
    public void setStatusBaju(String statusBaju) {
        this.statusBaju = statusBaju;
    }
    public String getCariBaju() {
        return cariBaju;
    }
    public void setCariBaju(String cariBaju) {
        this.cariBaju = cariBaju;
    }
    public String getStrTanggalBaju() {
        return strTanggalBaju;
    }
    public void setStrTanggalBaju(String strTanggalBaju) {
        this.strTanggalBaju = strTanggalBaju;
    }
    public String getAutoKode() {
        return autoKode;
    }
    public void setAutoKode(String autoKode) {
        this.autoKode = autoKode;
    }
    public String getMenuCrud() {
        return menuCrud;
    }
    public void setMenuCrud(String menuCrud) {
        this.menuCrud = menuCrud;
    }
    public String getAksesLoading() {
        return cmbLogin;
    }
    public void setAksesLoading(String cmbLogin) {
        this.cmbLogin = cmbLogin;
    }
    
    public ObservableList<classBaju> sp_dataBajuOutput(classBaju sp) {
        CallableStatement cstmt;
        try {
            ObservableList<classBaju> listBaju = FXCollections.observableArrayList();
            cstmt = koneksi.connect().prepareCall("{call sp_dataBajuOutput(?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.setString(2, sp.getCariBaju());
            cstmt.setString(3, sp.getStatusBaju());
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while(rs.next()){
                    classBaju baju = new classBaju();
                    int a = rs.getInt(5);
                    NumberFormat nt = NumberFormat.getCurrencyInstance();
                    String rupiah = nt.format(a);
                    baju.setBajuNo(rs.getInt(1));
                    baju.setKodeBaju(rs.getString(2));
                    baju.setNamaBaju(rs.getString(3));
                    baju.setVendorBaju(rs.getString(4));
                    baju.setHargaBaju(rupiah);
                    baju.setTanggalBaju(rs.getString(6));
                    listBaju.add(baju);
            }
            return listBaju;
        }catch (SQLException e) {
            return null;
        }finally{
            
        }  
    }
   
    public void sp_dataBajuInput(classBaju sp){
        CallableStatement cstmt;
        try {
            cstmt = koneksi.connect().prepareCall("{call sp_dataBajuInput(?,?,?,?,?,?,?)}");
            cstmt.setInt(1, sp.getBajuNo());
            cstmt.setString(2, sp.getKodeBaju());
            cstmt.setString(3, sp.getNamaBaju());
            cstmt.setString(4, sp.getVendorBaju());
            cstmt.setString(5, sp.getHargaBaju());
            cstmt.setString(6, sp.getTanggalBaju());
            cstmt.setString(7, sp.getStatusBaju());
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(
                Level.SEVERE, null, ex);
        }
    }  
    
}
