/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import animasi.FadeInRightTransition;
import animasi.FadeOutLeftTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.classBaju;
import model.navigasi;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author herudi
 */
public class daftarBajuController implements Initializable{
    @FXML 
    private TableView<classBaju> tableBaju;
    @FXML 
    private TableColumn<classBaju, String> colKode,colNama,
            colVendor,colTanggal,colHarga;
    @FXML
    private TableColumn<classBaju, Integer> colNo;
    @FXML 
    private AnchorPane editBaju;
    @FXML 
    private StackPane tambahBaju,daftarListMenu;
    @FXML 
    private StackPane loadStage;
    @FXML 
    private StackPane menuBaju;
    @FXML 
    private TextField txtCariBaju;
    @FXML 
    private Label lblAdmin;
    @FXML 
    private ListView<String> listViewBaju;
    @FXML
    private ObservableList<classBaju> listBaju = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<String> listView = FXCollections.observableArrayList("Menu Baju",""
            + "Text Editor");
    navigasi nav = new navigasi();
    classBaju thisBaju = new classBaju();
    Stage stage;
    public static String tambah = "";
    public static String comboBaju;
    
    public void setStageTambahBaju (Node node) {
        loadStage.getChildren().setAll(node);
    }
    public void setStageDaftarListBaju (Node node) {
        daftarListMenu.getChildren().setAll(node);
    }
    
    public void aksesLoading(){
        thisBaju.setAksesLoading(tambah);
        if(thisBaju.getAksesLoading().equals("close")){
            tampilkan();
        }else{
            tampilkan();
            loading();
        }
    }
    
    private void tampilkan() {
        listBaju = thisBaju.sp_dataBajuOutput(thisBaju);
        tableBaju.setItems(listBaju);
        thisBaju.setStatusBaju("T");
    }
    
    private void columnBaju(){
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBaju"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaBaju"));
        colVendor.setCellValueFactory(new PropertyValueFactory<>("vendorBaju"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBaju"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalBaju"));
        colNo.setCellValueFactory(new PropertyValueFactory<>("bajuNo"));
        listViewBaju.setItems(listView);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tambahBaju.toBack();
        tambahBaju.setOpacity(0);
        editBaju.toBack();
        editBaju.setOpacity(0);
        columnBaju();
        menuBaju.setEffect(null);
        lblAdmin.setText(comboBaju);
        tampilkan();
        aksesLoading();
    }
    
    @FXML 
    private void btnTambah(ActionEvent event) throws IOException{
        menuBaju.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(tambahBaju).play();
        navigasi.loadStageTambahBaju(nav.getTambahBaju());
    }
    
    @FXML 
    private void btnEdit(ActionEvent event) throws IOException{
        classBaju klik = tableBaju.getSelectionModel().getSelectedItems().get(0);
        menuBaju.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(tambahBaju).play();
        controller.EditBajuController.no = klik.getBajuNo();
        controller.EditBajuController.kode = klik.getKodeBaju();
        controller.EditBajuController.nama = klik.getNamaBaju();
        controller.EditBajuController.vendor = klik.getVendorBaju();
        controller.EditBajuController.harga = klik.getHargaBaju();
        controller.EditBajuController.tanggal = klik.getTanggalBaju();
        navigasi.loadStageTambahBaju(nav.getEditBaju());
    }
    
    @FXML 
    private void btnHapus(ActionEvent event){
        classBaju klik = tableBaju.getSelectionModel().getSelectedItems().get(0);
        thisBaju.setBajuNo(klik.getBajuNo());
        thisBaju.setKodeBaju(thisBaju.getKodeBaju());
        thisBaju.setNamaBaju(thisBaju.getNamaBaju());
        thisBaju.setVendorBaju(thisBaju.getVendorBaju());
        thisBaju.setHargaBaju(thisBaju.getTanggalBaju());
        thisBaju.setTanggalBaju(thisBaju.getTanggalBaju());
        thisBaju.setStatusBaju("D");
        thisBaju.sp_dataBajuInput(thisBaju);
        Dialogs.create()
                    .owner(stage)
                    .title("Information")
                    .masthead("Informasi Data Baju")
                    .message("Data Telah Dihapus Dari Database")
                    .showInformation();
        tampilkan();
    }
    
    @FXML 
    private void btnBatal(ActionEvent event){
        txtCariBaju.setText("");
        tampilkan();
    }
    
    @FXML 
    private void btnClose(ActionEvent event) {
       menuBaju.setEffect(null);
       new FadeOutLeftTransition(tambahBaju).play();
       tampilkan();       
    }
    
    @FXML 
    private void klikBaju(MouseEvent event) throws IOException{
        if(event.getClickCount()==2){
           new FadeInRightTransition(tambahBaju).play(); 
           menuBaju.setEffect(new GaussianBlur(10));
           classBaju klik = tableBaju.getSelectionModel().getSelectedItem();
           controller.EditBajuController.no = klik.getBajuNo();
           controller.EditBajuController.kode = klik.getKodeBaju();
           controller.EditBajuController.nama = klik.getNamaBaju();
           controller.EditBajuController.vendor = klik.getVendorBaju();
           controller.EditBajuController.harga = klik.getHargaBaju();
           controller.EditBajuController.tanggal = klik.getTanggalBaju();
           navigasi.loadStageTambahBaju(nav.getEditBaju());

        }
    }
    
    @FXML 
    private void klikListDaftar(MouseEvent event){
        switch(listViewBaju.getSelectionModel().getSelectedIndex()){
            case 0:{
                    menuBaju.toFront();
                    menuBaju.setOpacity(100);
            }break;
            case 1:{
                    daftarListMenu.toFront();
                    daftarListMenu.setOpacity(100);
            }break;
        }    
        
        listViewBaju.setOnKeyTyped((KeyEvent event1) -> {
            switch(listViewBaju.getSelectionModel().getSelectedIndex()){
                case 0:{
                        menuBaju.toFront();
                        menuBaju.setOpacity(100);
                }break;
                case 1:{
                        daftarListMenu.toFront();
                        daftarListMenu.setOpacity(100);
                }break;
            }
        });
    }
    
    @FXML 
    private void cariBaju(KeyEvent event){
        txtCariBaju.setOnKeyReleased((KeyEvent event1) -> {
            thisBaju.setCariBaju(txtCariBaju.getText());
            thisBaju.setStatusBaju("C");
            listBaju = thisBaju.sp_dataBajuOutput(thisBaju);
            tableBaju.setItems(listBaju);
        });
    }
    
    private void loading (){
    Service<Void> service;
        service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call()
                            throws InterruptedException {
                        updateMessage("Loading Data . . .");
                        updateProgress(0, 100);
                        for (int i = 0; i < 100; i++) {
                            Thread.sleep(70);
                            updateProgress(i + 1, 100);
                            updateMessage("Loading " + (i + 1) + "%");
                        }
                        updateMessage("Found all.");
                        return null;
                    }
                };
            }
        };
        Dialogs.create()
        .owner(stage)
        .title("Please Wait")
        .masthead("Searching From Database")
        .showWorkerProgress(service);
        service.start();
    }
}
