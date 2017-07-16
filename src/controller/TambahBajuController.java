/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import konekasi.*;
import model.classBaju;
import model.navigasi;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author herudi
 */
public class TambahBajuController implements Initializable {
    @FXML
    private Label lblKodeBaju;
    @FXML
    private TextField txtNamaBaju;
    @FXML
    private TextField txtVendorBaju;
    @FXML
    private TextField txtHargaBaju;
    @FXML
    private AnchorPane paneTambah;
    @FXML
    private StackPane stackTambah;
    @FXML
    private TextField txtTanggalConvert;
    @FXML
    private DatePicker txtTanggalBaju = new DatePicker();
    @FXML
    private HBox hboxBaju;
    @FXML
    private Button simpan,close;
    DatePicker datePicker = new DatePicker();
    classBaju thisBaju = new classBaju();
    navigasi nav = new navigasi();
    Stage stage;
    koneksi k;
    /**
     * Initializes the controller class.
     */
    
    private Pane loadMainPane() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(nav.getDaftarBaju()));
        daftarBajuController daftarBaju = loader.getController();
        navigasi.setStageController(daftarBaju);
        return mainPane;
        
    }
    
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(getClass().getResource("/css/baju.css").toExternalForm());        
        return scene;
    }
    
    private void clearTambah(){
        txtNamaBaju.clear();
        txtVendorBaju.clear();
        txtHargaBaju.clear();
        txtTanggalConvert.clear();
        txtTanggalBaju.getEditor().clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bajuNoMax();
        autoNumberKodeBaju();
        if(lblKodeBaju.getText().isEmpty()){
            lblKodeBaju.setText("KB001");
        }
    }    

    @FXML
    private void btnSimpan(ActionEvent event) {
        thisBaju.setBajuNo(thisBaju.getBajuNo());
        thisBaju.setKodeBaju(lblKodeBaju.getText());
        thisBaju.setNamaBaju(txtNamaBaju.getText());
        thisBaju.setVendorBaju(txtVendorBaju.getText());
        thisBaju.setHargaBaju(txtHargaBaju.getText());
        thisBaju.setTanggalBaju(txtTanggalConvert.getText());
        thisBaju.setStatusBaju("A");
        thisBaju.sp_dataBajuInput(thisBaju);
        clearTambah();
        bajuNoMax();
        autoNumberKodeBaju();
        Dialogs.create()
                    .owner(stage)
                    .title("Information")
                    .masthead("Informasi Data Baju")
                    .message("Data Telah Tersimpan Ke Database")
                    .showInformation();
    }
    
    @FXML
    private void aksiTanggal(ActionEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String a = sdf.format(Date.valueOf(txtTanggalBaju.getValue()));
        txtTanggalConvert.setText(a);
    }
    
    private void autoNumberKodeBaju() {
        k = new koneksi();
        CallableStatement cstmt;
        try {
            cstmt = koneksi.connect().prepareCall("{call sp_dataBajuOutput(?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.setString(2, "");
            cstmt.setString(3, "T");
            cstmt.execute();
            try (ResultSet rs = cstmt.getResultSet()) {
                while(rs.next()){
                    String kodeBaju = rs.getString("kodeBaju").substring(2);
                    String AN = "" + (Integer.parseInt(kodeBaju) + 1);
                    String Nol = "";
                    if(AN.length()==1)
                    {Nol = "00";}
                    else if(AN.length()==2)
                    {Nol = "0";}
                    else if(AN.length()==3)
                    {Nol = "";}
                    lblKodeBaju.setText("KB"+Nol + AN);
                    
                }
            }
        }catch(SQLException e){
            
        }
            
    }
   
    @FXML
    private void aksiHargaBaju(KeyEvent event){
        harusAngka();
    }
    
    @FXML
    private void closeRefresh(ActionEvent event) throws IOException {
        daftarBajuController.tambah = "close";
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(createScene(loadMainPane()));
        stage.show();
    }
    
    public void bajuNoMax() {
        CallableStatement cstmt;
        try{
            cstmt = koneksi.connect().prepareCall("{call sp_dataBajuOutput(?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.setString(2, "");
            cstmt.setString(3, "T");
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while(rs.next()){
                thisBaju.setBajuNo(rs.getInt(1)+1);
            }
        }catch(SQLException ex){
             Logger.getLogger(koneksi.class.getName()).log(
                Level.SEVERE, null, ex);
        }
    }
    
    private void harusAngka(){
        txtHargaBaju.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (txtHargaBaju.getText().matches("[0-9]*")){
                    System.out.println("OK");
                }else{
                    Dialogs.create()
                    .owner(stage)
                    .title("Information")
                    .masthead("Informasi Data Baju")
                    .message("Harga Harus Di isi Dengan Angka")
                    .showInformation();
                    txtHargaBaju.setText("");
                    txtHargaBaju.requestFocus();
                }
            }
        });
        
    }
    
}
