/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m03uf5prac;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author garri
 */
public class CalculadoraResponsiveVistaController implements Initializable {

    @FXML
    private Button btBin;
    @FXML
    private Button btHex;
    @FXML
    private Button btA;
    @FXML
    private Button btB;
    @FXML
    private Button btD;
    @FXML
    private Button btE;
    @FXML
    private Button btPi;
    @FXML
    private Button btRightP;
    @FXML
    private Button btRaiz;
    @FXML
    private Button btDec;
    @FXML
    private Button btC;
    @FXML
    private Button btF;
    @FXML
    private Button btLeftP;
    @FXML
    private Button btExp;
    @FXML
    private Button bt1;
    @FXML
    private Button bt4;
    @FXML
    private Button bt7;
    @FXML
    private Button bt8;
    @FXML
    private Button bt5;
    @FXML
    private Button bt0;
    @FXML
    private Button bt9;
    @FXML
    private Button bt6;
    @FXML
    private Button bt3;
    @FXML
    private Button btPunto;
    @FXML
    private Button btProducto;
    @FXML
    private Button btResta;
    @FXML
    private Button btSuma;
    @FXML
    private Button bt2;
    @FXML
    private Button btBorrar;
    @FXML
    private Button btDivision;
    @FXML
    private Button btEvaluar;
    @FXML
    private Button btClear;
    @FXML
    private Label lbResult;
    @FXML
    private Button btTeclado;
    @FXML
    private TextField tbExpressio;
    @FXML
    private TableView<Mensaje> tvMensajes;
    @FXML
    private Button btHelp;
    @FXML
    private TableView<Expresion> tvHistorial;
    @FXML
    private Button btAceptar;
    @FXML
    private TableColumn<Mensaje, String> ColME;
    @FXML
    private TableColumn<Mensaje, String> ColFecha;
     @FXML
    private TableColumn<Expresion, String> ColHistoric;
      @FXML
    private TableColumn<Expresion, String> ColResult;
    
    // BASE
    int base = 10;
    ObservableList<Mensaje> ListaMensajes;
    ObservableList<Expresion> ListaOperaciones;
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColME.setCellValueFactory(new PropertyValueFactory<>("texto"));
        ColFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColHistoric.setCellValueFactory(new PropertyValueFactory<>("expresion"));
        ColResult.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        ListaMensajes = FXCollections.observableArrayList();
        ListaOperaciones = FXCollections.observableArrayList();
    }    
    
      /*
    NOTAS
    - Ser?? necesario tomar en cuenta si los label de operaci??n y resultado est??n vac??os
    o no para ir concatenando la expresi??n que luego se enviar?? al m??todo avalua.
    
    - El s??mbolo '=' ser?? el que llame a avalua desde donde se evaluar?? primero que no hayan
    errores de syntax y de haber a trav??s de la excepci??n se lanzar?? el mensaje de error.
    Otra opci??n ser??a verificar la expresi??n antes de enviarla a avalua.
    
    - El s??mbolo 'C' pone los label en blanco.
    
    - El s??mbolo << atr??s elimina los car??cteres 1 a 1.
    
    - Los s??mbolos de BIN, HEX y DEC determinan que botones se habilitan
    y deshabilitan y que base se env??a a trav??s de avalua (usar booleans).
    
    - El s??mbolo de *flecha giratoria* representa el hist??rico de operaciones
    y al ser clicado env??a a otra vista donde habr?? un ListView con las operaciones.
    (Manejar la l??gica en su respectivo controlador).
    La idea es que el usuario al seleccionar la expresi??n del historial la pueda modificar.
    
    - El s??mbolo de *tri??ngulo de warning* representa el hist??rico de mensajes de error e info.
    Al ser clicado env??a a la vista de errores donde habr?? un listView only read.
    
    - Las dos vistas de hist??rico deben tener un bot??n de Volver a calculadora.
    Si se clica desde error volver?? a la calculadora como se haya dejado.
    Si se clica desde historial volver?? a la calculadora con la expresi??n seleccionada.
    
    - ??Se puede mover el cursor a lo largo de la expresi??n? Para modificar.
    
    - ??C??mo capturar las acciones del teclado?
    */
    
    /**
     * funci??n para capturar i guardar la informaci??n cuando se pica el bot??n
     * n??mero, se optiene el dato del nombre del texto del bot??n
     * @param e 
     */
    @FXML
    public void NumberClicked(ActionEvent e) {
        Button boton = (Button) e.getSource();
        String expresion = tbExpressio.getText();
        
        tbExpressio.setText(expresion.concat(boton.getText()));
    }

    /**
     * funci??n para capturar i guardar la informaci??n cuando de pica el bot??n
     * con un s??mbolo, se captura la informaci??n del nombre del bot??n, menos
     * la raiz que convertimos en la funci??n.
     * @param e 
     */
    @FXML
    public void SymbolClicked(ActionEvent e) {
        Button boton = (Button) e.getSource();
        String expresion = tbExpressio.getText();

        if (boton == btRaiz) {
            tbExpressio.setText(expresion.concat("^(1/2)"));
        }else if(boton == btHelp){
             DisplayMessage(2, "A --- * Multiplicar\n"
                        + "BACK_SPACE --- << Borrar\n"
                        + "SPACE --- C Clear\n"
                        + "Q --- ( Par??ntesis Izquierdo\n"
                        + "W --- ) Par??ntesis Derecho\n"
                        + "S --- / Dividir\n"
                        + "C --- ^ Potencia\n"
                        + "R --- ^(1/2) Ra??z Cuadrada\n"
                        + "P --- ?? Pi\n"
                        + "B --- Bin Modo Binario\n"
                        + "H --- Hex Modo Hexadecimal\n"
                        + "D --- Dec Modo Decimal");
        }else{
            tbExpressio.setText(expresion.concat(boton.getText()));
        }
    }

    /**
     * M??todo para deshabilitar botones excepto 0, 1 y s??mbolos
     */
    @FXML
    public void BinClicked() {
        base = 2;
        
        bt0.setDisable(false);
        bt1.setDisable(false);
        bt2.setDisable(true);
        bt3.setDisable(true);
        bt4.setDisable(true);
        bt5.setDisable(true);
        bt6.setDisable(true);
        bt7.setDisable(true);
        bt8.setDisable(true);
        bt9.setDisable(true);
        btA.setDisable(true);
        btB.setDisable(true);
        btC.setDisable(true);
        btD.setDisable(true);
        btE.setDisable(true);
        btF.setDisable(true);
        btPi.setDisable(true);
        btPunto.setDisable(true);
    }

    /**
     * M??todo para habilitar letras A B C D E F
     */
    @FXML
    public void HexClicked() {
        base = 16;
        
        bt0.setDisable(false);
        bt1.setDisable(false);
        bt2.setDisable(false);
        bt3.setDisable(false);
        bt4.setDisable(false);
        bt5.setDisable(false);
        bt6.setDisable(false);
        bt7.setDisable(false);
        bt8.setDisable(false);
        bt9.setDisable(false);
        btA.setDisable(false);
        btB.setDisable(false);
        btC.setDisable(false);
        btD.setDisable(false);
        btE.setDisable(false);
        btF.setDisable(false);
        btPi.setDisable(true);
        btPunto.setDisable(true);
    }

    /**
     * M??todo para cambiar a modo decimal default
     */
    @FXML
    public void DecClicked() {
        base = 10;
        
        bt0.setDisable(false);
        bt1.setDisable(false);
        bt2.setDisable(false);
        bt3.setDisable(false);
        bt4.setDisable(false);
        bt5.setDisable(false);
        bt6.setDisable(false);
        bt7.setDisable(false);
        bt8.setDisable(false);
        bt9.setDisable(false);
        btA.setDisable(true);
        btB.setDisable(true);
        btC.setDisable(true);
        btD.setDisable(true);
        btE.setDisable(true);
        btF.setDisable(true);
        btPi.setDisable(false);
        btPunto.setDisable(false);
    }


    /**
     * M??todo para mostrar mensajes de Error o Info en PopUp window
     * @param type tipo de alerta, entra un 1 para errores i 2 para informaci??n
     * @param text texto a mostrar
     */
    public void DisplayMessage(int type, String text) {
        switch (type) {
            case 1:
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(null);
                error.setTitle("Error");
                error.setContentText(text);
                error.showAndWait();
                break;
            case 2:
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setHeaderText(null);
                info.setTitle("Info");
                info.setContentText(text);
                info.show();
        }
    }
    
    /***
     * Metodo que l??mpia de informaci??n la ventana de expresi??n i resultado
     */
    @FXML
    public void ClearClicked () {
         tbExpressio.setStyle("-fx-text-fill: #c9c1c1"); 
        tbExpressio.setText("");
        lbResult.setText("0.0");
    }
    
    /**
     * Borrar un caracter hacia atr??s.
     */
    @FXML
    public void EraseClicked () {
        String expression = tbExpressio.getText();
        if (expression.length() > 1) {
            tbExpressio.setText(expression.substring(0,expression.length()-1));
        } else {
            tbExpressio.setText("");
        }
    }
    
    /**
     * Env??a la expresi??n entrada para calcular el resultado
     * capturamos excepciones que se produzcan y mostramos mensaje
     * @throws CalculadoraException 
     */
    @FXML
    public void EvaluateClicked () {
        tbExpressio.setEditable(false);
        String expression = tbExpressio.getText();
        double resultado = 0;
        if (expression.length() > 0) {
            try {
                resultado = Calculadora.avalua(expression, base);
                String resultadoString = String.valueOf(resultado);
                lbResult.setText(resultadoString);
                tbExpressio.setStyle("-fx-text-fill: green");      
                Expresion exp = new Expresion(expression, resultadoString);
                ListaOperaciones.add(exp);
                tvHistorial.setItems(ListaOperaciones);
            } catch (DivisioPerZeroException dpzEx) {
                tbExpressio.setStyle("-fx-text-fill: red");  
                String msgError = "Error en la posici??n " + dpzEx.getIndice() + " - " + dpzEx.getCausa();
                DisplayMessage(1,msgError);                     
                // D??a y hora actual
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaActual = dtf.format(LocalDateTime.now());
                Mensaje mensaje = new Mensaje(msgError, fechaActual);
                ListaMensajes.add(mensaje);
                tvMensajes.setItems(ListaMensajes);
            } catch (CalculadoraException calcEx) {
                tbExpressio.setStyle("-fx-text-fill: red"); 
                String msgError = "Error en la posici??n " + calcEx.getIndice() + " - " + calcEx.getCausa();
                DisplayMessage(1,msgError);  
                // D??a y hora actual
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaActual = dtf.format(LocalDateTime.now());
                Mensaje mensaje = new Mensaje(msgError, fechaActual);
                ListaMensajes.add(mensaje);
                tvMensajes.setItems(ListaMensajes);
            }          
        }
    }

   
    /**
     * M??todo de mapeo de teclas. 
     * Habilita el teclado accionado por el bot??n Key.
     * @param event
     * @throws CalculadoraException 
     */
    @FXML
    private void KeyPressed(KeyEvent event) throws CalculadoraException {
         String expresion = tbExpressio.getText();
         
        switch (event.getCode()) {
            case NUMPAD0:
            case DIGIT0:
                tbExpressio.setText(expresion.concat("0"));
                break;
            case NUMPAD1:
            case DIGIT1:
                tbExpressio.setText(expresion.concat("1"));
                break;
            case NUMPAD2:
            case DIGIT2:
                tbExpressio.setText(expresion.concat("2"));
                break;
            case NUMPAD3:
            case DIGIT3:
                tbExpressio.setText(expresion.concat("3"));
                break;
            case NUMPAD4:
            case DIGIT4:
                tbExpressio.setText(expresion.concat("4"));
                break;
            case NUMPAD5:
            case DIGIT5:
                tbExpressio.setText(expresion.concat("5"));
                break;
            case NUMPAD6:
            case DIGIT6:
                tbExpressio.setText(expresion.concat("6"));
                break;
            case NUMPAD7:
            case DIGIT7:
                tbExpressio.setText(expresion.concat("7"));
                break;
            case NUMPAD8:
            case DIGIT8:
                tbExpressio.setText(expresion.concat("8"));
                break;
            case NUMPAD9:
            case DIGIT9:
                tbExpressio.setText(expresion.concat("9"));
                break;  
            case ENTER: // = Calcular
                EvaluateClicked();
                break;
                //ASTERISK
            case A:
                 tbExpressio.setText(expresion.concat("*"));
                 break;
            case BACK_SPACE: //Atr??s
                EraseClicked();
                break;
            case SPACE: //Clear
                ClearClicked();
                break;
                //LEFT_PARENTHESIS
            case Q:
                 tbExpressio.setText(expresion.concat("("));
                 break;
            case MINUS:
                tbExpressio.setText(expresion.concat("-"));
                break;
            case PERIOD:
                 tbExpressio.setText(expresion.concat("."));
                 break;
            case PLUS:
                tbExpressio.setText(expresion.concat("+"));
                break;
                //RIGHT_PARENTHESIS
            case W:
                tbExpressio.setText(expresion.concat(")"));
                break;
                //SLASH '/'
            case S:
                tbExpressio.setText(expresion.concat("/"));
                break;
                //CIRCUMFLEX ^
            case C:
                tbExpressio.setText(expresion.concat("^"));
                break;
            case R: //Ra??z
                tbExpressio.setText(expresion.concat("^(1/2)"));
                break;
            case P: //Pi
                tbExpressio.setText(expresion.concat("??"));
                break;
            case B:
                BinClicked();
                DisplayMessage(2, "Modo Binario.");
                break;
            case H:
                HexClicked();
                DisplayMessage(2, "Modo Hexadecimal.");
                break;
            case D:
                DecClicked();
                DisplayMessage(2, "Modo Decimal Default.");
                break;               
        }
    }

    @FXML
    private void AceptarClicked(ActionEvent event) {
        if(tvHistorial.getSelectionModel().getSelectedItem() != null){
            Expresion exp = tvHistorial.getSelectionModel().getSelectedItem();          
            tbExpressio.setText(exp.expresion);
            tbExpressio.setEditable(true);
        }
    }
}
