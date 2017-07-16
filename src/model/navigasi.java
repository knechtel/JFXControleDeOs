/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.daftarBajuController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author herudi
 */
public class navigasi {
    private String tambahBaju = "/view/tambahBaju.fxml";
    private String daftarBaju = "/view/daftarBaju.fxml";
    private String editBaju = "/view/editBaju.fxml";
    private String textEditor = "/view/textEditor.fxml";
    private static daftarBajuController daftarBajuController;
    
    public String getTextEditor() {
        return textEditor;
    }
    public void setTextEditor(String textEditor) {
        this.textEditor = textEditor;
    }
    public String getTambahBaju() {
        return tambahBaju;
    }
    public void setTambahBaju(String tambahBaju) {
        this.tambahBaju = tambahBaju;
    }
    public String getDaftarBaju() {
        return daftarBaju;
    }
    public void setDaftarBaju(String daftarBaju) {
        this.daftarBaju = daftarBaju;
    }
    public String getEditBaju() {
        return editBaju;
    }
    public void setEditBaju(String editBaju) {
        this.editBaju = editBaju;
    }
    public static void setStageController (daftarBajuController daftarBajuController ) {
        navigasi.daftarBajuController = daftarBajuController;
    }
    public static void loadStageTambahBaju (String fxml) throws IOException{
        daftarBajuController.setStageTambahBaju((Node) FXMLLoader
                                .load(navigasi.class.getResource(fxml)));
    }
    public static void loadStageDaftarListBaju (String fxml) throws IOException{
        daftarBajuController.setStageDaftarListBaju((Node) FXMLLoader
                                .load(navigasi.class.getResource(fxml)));
    }
    
}
