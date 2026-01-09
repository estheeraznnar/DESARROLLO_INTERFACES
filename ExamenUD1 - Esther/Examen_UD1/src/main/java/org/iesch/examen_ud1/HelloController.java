package org.iesch.examen_ud1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class HelloController {

    Connection conn;

    private Alert alert;

    private ResultSet rs;

    private boolean nuevoRegistro = false;

    private ValidationSupport v = new ValidationSupport();


    @FXML
    private TextField tNombre;

    @FXML
    private DatePicker dFecha;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private ComboBox<String> comboDep;

    @FXML
    private TextField tSalario;

    @FXML
    public void initialize() {
        v.registerValidator(tNombre, Validator.createEmptyValidator("Nombre requerido"));
        v.registerValidator(dFecha,Validator.createEmptyValidator("La fecha de nacimiento es obligatoria"));
        v.registerValidator(comboDep,Validator.createEmptyValidator("Debes seleccionar un departamento"));

        v.setErrorDecorationEnabled(false);
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String clave = "1234";

        try {
            conn = DriverManager.getConnection(url, user, clave);
            Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select departamento from empresa.departamentos";
            rs = stat.executeQuery(sql);
            while (rs.next()){
                comboDep.getItems().add(rs.getString(1));
            }
            sql = "select * from empresa.empleados";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                rellenar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void avanzar(){
        nuevoRegistro = false;
        try {
            if (rs.next()) {
                setEditable(false);
                rellenar();
            }else{
                retroceder();
                showAlert("No hay mas Registros");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void retroceder(){
        nuevoRegistro = false;
        try {
            if (rs.previous()) {
                setEditable(false);
                rellenar();
            }else{
                avanzar();
                showAlert("No hay mas registros");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void primero(){
        nuevoRegistro = false;
        try {
            if (rs.first()) {
                setEditable(false);
                rellenar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ultimo(){
        nuevoRegistro = false;
        try {
            if (rs.last()) {
                setEditable(false);
                rellenar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void nuevoRegistro(){
        nuevoRegistro = true;
        setEditable(true);
        tNombre.clear();
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        comboDep.setValue(comboDep.getItems().get(0));
        tSalario.clear();
    }

    @FXML
    public void guardar(){
        v.setErrorDecorationEnabled(true);
        if (isEmpty()){
            showAlert("Hay que rellenar todos los campos");
        } else if (nuevoRegistro) {
            try {
                String sql = "INSERT INTO empleados (Nombre,FechaNac,Sexo,Departamento,Salario) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,tNombre.getText());
                stmt.setDate(2,Date.valueOf(dFecha.getValue()));
                if (rbMasculino.isSelected()){
                    stmt.setInt(3,0);
                }else{
                    stmt.setInt(3,1);
                }
                stmt.setInt(4,comboDep.getItems().indexOf(comboDep.getValue())+1);
                Double salario;
                try {
                    salario = Double.parseDouble(tSalario.getText());
                    stmt.setDouble(5,salario);
                } catch (NumberFormatException e) {
                    showAlert("Debes poner un numero decimal o entero, sin simbolos");
                    return;
                }
                stmt.executeUpdate();
                showAlert("Guardado correctamente");
                Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                String sqlSelect = "select * from empresa.empleados";
                rs = stat.executeQuery(sqlSelect);
                rs.last();
                setEditable(false);
                nuevoRegistro=false;
                rellenar();
                v.setErrorDecorationEnabled(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            showAlert("Para guardar a un empleado debes crear un registro nuevo");
        }
    }

    @FXML
    public void tabla() throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage2 = new Stage();
        stage2.setTitle("Tabla");
        stage2.setScene(scene);
        stage2.show();
    }

    private void showAlert(String mensaje) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setHeaderText(mensaje);
        alert.show();
    }

    public boolean isEmpty(){
        if (tNombre.getText().isBlank()){
            return true;
        } else if (tSalario.getText().isBlank()) {
            return true;
        } else if (!rbFemenino.isSelected() && !rbMasculino.isSelected()) {
            return true;
        }
        return false;
    }

    public void rellenar(){
        try {
            tNombre.setText(rs.getString(2));
            dFecha.setValue(rs.getDate(3).toLocalDate());
            if (rs.getInt(4) == 1) {
                rbFemenino.setSelected(true);
                rbMasculino.setSelected(false);
            } else {
                rbMasculino.setSelected(true);
                rbFemenino.setSelected(false);
            }
            comboDep.setValue(comboDep.getItems().get(rs.getInt(5)-1));
            tSalario.setText((rs.getInt(6)) + "€");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEditable(boolean editable){
        tNombre.setEditable(editable);
        dFecha.setDisable(!editable);
        rbMasculino.setDisable(!editable);
        rbFemenino.setDisable(!editable);
        comboDep.setDisable(!editable);
        tSalario.setEditable(editable);
    }
}