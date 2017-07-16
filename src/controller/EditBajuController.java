/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import konekasi.koneksi;
import model.classBaju;
import model.navigasi;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author herudi
 */
public class EditBajuController implements Initializable {
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
    private TextField txtTanggalConvert;
    @FXML
    private DatePicker txtTanggalBaju;
    @FXML
    private HBox hboxBaju;
    @FXML
    private Button simpan,close;
    DatePicker datePicker = new DatePicker();
    classBaju thisBaju = new classBaju();
    navigasi nav = new navigasi();
    Stage stage;
    koneksi k;
    public static Integer no;
    public static String kode;
    public static String nama;
    public static String vendor;
    public static String harga;
    public static String tanggal;
    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        thisBaju.setBajuNo(no);
        lblKodeBaju.setText(kode);
        txtNamaBaju.setText(nama);
        txtVendorBaju.setText(vendor);
        txtHargaBaju.setText(harga.replace("Rp", "").replace(".", "").replace(",00", ""));
        txtTanggalConvert.setText(tanggal);
    }    

    @FXML
    private void btnSimpan(ActionEvent event) throws IOException {
        thisBaju.setBajuNo(no);
        thisBaju.setKodeBaju(lblKodeBaju.getText());
        thisBaju.setNamaBaju(txtNamaBaju.getText());
        thisBaju.setVendorBaju(txtVendorBaju.getText());
        thisBaju.setHargaBaju(txtHargaBaju.getText());
        thisBaju.setTanggalBaju(txtTanggalConvert.getText());
        thisBaju.setStatusBaju("A");
        thisBaju.sp_dataBajuInput(thisBaju);
        Dialogs.create()
                    .owner(stage)
                    .title("Information")
                    .masthead("Informasi Data Baju")
                    .message("Data Telah Diupdate Ke Database")
                    .showInformation();
    }
    
     
    @FXML
    private void aksiTanggal(ActionEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String a = sdf.format(Date.valueOf(txtTanggalBaju.getValue()));
        txtTanggalConvert.setText(a);
    }
    
   
   
    @FXML
    private void aksiHargaBaju(ActionEvent event){
        
    }
    
    @FXML
    private void closeRefresh(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage st = (Stage) node.getScene().getWindow();
        st.setScene(createScene(loadMainPane()));
        st.show();
    }
    
}
