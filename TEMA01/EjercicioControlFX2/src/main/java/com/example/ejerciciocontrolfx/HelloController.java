package com.example.ejerciciocontrolfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SearchableComboBox;

import java.sql.*;

public class HelloController {

    private Connection con = null;
    private ResultSet rs = null;

    @FXML
    private TableView<Capitales> capitales;

    @FXML
    private TableColumn<Capitales, String> provincia;

    @FXML
    private TableColumn<Capitales, String> poblacion;

    @FXML
    private TableColumn<Capitales, String> autonomia;

    @FXML
    private SearchableComboBox<String> combo;

    @FXML
    private RangeSlider slider;

    @FXML
    protected void initialize(){
        String url = "jdbc:mysql://localhost:3306/capitales";
        String user = "root";
        String clave = "1234";
        provincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
        poblacion.setCellValueFactory(new PropertyValueFactory<>("Población"));
        autonomia.setCellValueFactory(new PropertyValueFactory<>("Autonomía"));

        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from capitales";
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                Capitales c = new Capitales(rs.getString("Provincia"),rs.getString("Población"),rs.getString("Autonomía"));
                capitales.getItems().add(c);
            }
            rs= stat.executeQuery("Select distinct(Autonomía) From capitales.capitales");
            while (rs.next()){
                combo.getItems().add(rs.getString("Autonomía"));
            }
            int min = 0;
            int max = 0;
            rs = stat.executeQuery("Select max(Población), min(Población)  from capitales.capitales");
            while (rs.next()){
                min = rs.getInt("min(Población)");
                max = rs.getInt("max(Población)");
            }

            slider.setMax(max);
            slider.setMin(min);
            slider.setMajorTickUnit(1000000);
            slider.setHighValue(max);
            slider.setLowValue(min);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void setCombo(){
        capitales.getItems().clear();
        try {
            String sql = "select * from capitales.capitales where autonomía = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, combo.getValue());
            rs= ps.executeQuery();
            while (rs.next()) {
                provincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
                autonomia.setCellValueFactory(new PropertyValueFactory<>("Autonomía"));
                poblacion.setCellValueFactory(new PropertyValueFactory<>("Población"));
                Capitales c = new Capitales(rs.getString("Provincia"),rs.getString("Autonomía"),rs.getString("Población"));
                capitales.getItems().add(c);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setSlider(){
        capitales.getItems().clear();
        try {
            String sql = "select * from capitales.capitales where Población >= " + slider.getLowValue() + " AND Población<= " + slider.getHighValue();
            PreparedStatement ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()) {
                provincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
                autonomia.setCellValueFactory(new PropertyValueFactory<>("Autonomía"));
                poblacion.setCellValueFactory(new PropertyValueFactory<>("Población"));
                Capitales c = new Capitales(rs.getString("Provincia"),rs.getString("Autonomía"),rs.getString("Población"));
                capitales.getItems().add(c);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}