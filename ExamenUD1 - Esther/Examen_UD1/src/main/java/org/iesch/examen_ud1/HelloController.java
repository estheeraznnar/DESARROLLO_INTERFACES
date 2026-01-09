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

    // --- Atributos de Conexión y Estado ---
    Connection conn; // Objeto para gestionar la conexión a MySQL
    private Alert alert; // Ventana emergente para mensajes al usuario
    private ResultSet rs; // Almacena el resultado de las consultas SQL (navegable)
    private boolean nuevoRegistro = false; // Flag para saber si estamos creando un empleado o solo viendo
    private ValidationSupport v = new ValidationSupport(); // Soporte de validación de ControlsFX

    // --- Elementos de la Interfaz (Inyectados desde FXML) ---
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
        // 1. Configurar validadores para los campos obligatorios
        v.registerValidator(tNombre, Validator.createEmptyValidator("Nombre requerido"));
        v.registerValidator(dFecha,Validator.createEmptyValidator("La fecha de nacimiento es obligatoria"));
        v.registerValidator(comboDep,Validator.createEmptyValidator("Debes seleccionar un departamento"));

        // Desactivar visualmente los errores al inicio
        v.setErrorDecorationEnabled(false);

        // 2. Configuración de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String clave = "1234";

        try {
            conn = DriverManager.getConnection(url, user, clave);

            // Crear Statement que permite desplazarse hacia atrás y adelante (TYPE_SCROLL_INSENSITIVE)
            Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // 3. Cargar el ComboBox con los nombres de los departamentos
            String sql = "select departamento from departamentos";
            rs = stat.executeQuery(sql);
            while (rs.next()){
                comboDep.getItems().add(rs.getString(1));
            }

            // 4. Cargar todos los empleados y mostrar el primero si existe
            sql = "select * from empresa.empleados";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                rellenar();// Llena los campos de texto con los datos de la BD
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --- Métodos de Navegación ---

    @FXML
    public void avanzar(){
        nuevoRegistro = false;
        try {
            if (rs.next()) { // Mueve el cursor a la siguiente fila
                setEditable(false);
                rellenar();
            }else{
                retroceder();// Si llega al final, vuelve al último registro
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
            if (rs.previous()) { // Mueve el cursor a la fila anterior
                setEditable(false);
                rellenar();
            }else{
                avanzar(); // Si llega al inicio, vuelve al primer registro
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
            if (rs.first()) {// Salta directamente al primer registro
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
            if (rs.last()) { // Salta directamente al último registro
                setEditable(false);
                rellenar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --- Gestión de Datos ---

    @FXML
    public void nuevoRegistro(){
        nuevoRegistro = true; // Activamos el modo creación
        setEditable(true);  // Habilitamos los campos para escribir
        // Limpiamos los campos
        tNombre.clear();
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        comboDep.setValue(comboDep.getItems().get(0));
        tSalario.clear();
    }

    @FXML
    public void guardar(){
        v.setErrorDecorationEnabled(true);// Mostrar errores visuales si los hay

        if (isEmpty()){
            showAlert("Hay que rellenar todos los campos");
        } else if (nuevoRegistro) {
            try {
                // Preparar consulta de inserción
                String sql = "INSERT INTO empleados (Nombre,FechaNac,Sexo,Departamento,Salario) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1,tNombre.getText());
                stmt.setDate(2,Date.valueOf(dFecha.getValue()));// Convierte LocalDate a SQL Date

                // Mapeo de RadioButtons a valores enteros (0 para Masc, 1 para Fem)
                if (rbMasculino.isSelected()){
                    stmt.setInt(3,0);
                }else{
                    stmt.setInt(3,1);
                }

                // Mapeo de ComboBox a ID (índice + 1)
                stmt.setInt(4,comboDep.getItems().indexOf(comboDep.getValue())+1);

                // Validación numérica del salario
                Double salario;
                try {
                    salario = Double.parseDouble(tSalario.getText());
                    stmt.setDouble(5,salario);
                } catch (NumberFormatException e) {
                    showAlert("Debes poner un numero decimal o entero, sin simbolos");
                    return;
                }

                // Ejecutar en BD
                stmt.executeUpdate();
                showAlert("Guardado correctamente");
                // Refrescar el ResultSet para incluir el nuevo registro
                Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                String sqlSelect = "select * from empresa.empleados";
                rs = stat.executeQuery(sqlSelect);
                rs.last();// Ir al final para ver el registro recién creado
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
        // Abre una nueva ventana cargando un archivo FXML distinto (table-view.fxml)
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage2 = new Stage();
        stage2.setTitle("Tabla");
        stage2.setScene(scene);
        stage2.show();
    }

    // --- Métodos de Utilidad ---

    private void showAlert(String mensaje) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setHeaderText(mensaje); // Nota: headerText suele ser nulo, mensaje va en Content
        alert.show();
    }

    public boolean isEmpty(){
        // Verifica que los campos críticos no estén vacíos o sin marcar
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
        // Extrae los datos del ResultSet (fila actual) y los pone en los controles JavaFX
        try {
            tNombre.setText(rs.getString(2)); // Columna 2: Nombre
            dFecha.setValue(rs.getDate(3).toLocalDate());// Columna 3: Fecha

            // Sexo (Columna 4)
            if (rs.getInt(4) == 1) {
                rbFemenino.setSelected(true);
                rbMasculino.setSelected(false);
            } else {
                rbMasculino.setSelected(true);
                rbFemenino.setSelected(false);
            }
            // Departamento (Columna 5) - Ajusta índice del combo
            comboDep.setValue(comboDep.getItems().get(rs.getInt(5)-1));
            // Salario (Columna 6)
            tSalario.setText((rs.getInt(6)) + "€");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEditable(boolean editable){
        // Activa o desactiva la edición de los controles según el estado (ver vs crear)
        tNombre.setEditable(editable);
        dFecha.setDisable(!editable);
        rbMasculino.setDisable(!editable);
        rbFemenino.setDisable(!editable);
        comboDep.setDisable(!editable);
        tSalario.setEditable(editable);
    }
}