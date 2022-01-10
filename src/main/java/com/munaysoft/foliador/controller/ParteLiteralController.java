package com.munaysoft.foliador.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.munaysoft.foliador.event.VistaPreviaPdfEvent;
import com.munaysoft.foliador.utilidad.ConstantesFoliador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class ParteLiteralController extends RegistrarEventoController implements Initializable {

    @FXML
    private TextField txtLiteralIzquierdoAdicional;

    @FXML
    private TextField txtLiteralDerechoAdicional;

    @FXML
    private RadioButton radioPrimeraMayuscula;

    @FXML
    private RadioButton radioTodoMinuscula;

    @FXML
    private RadioButton radioTodoMayuscula;

    @FXML
    private ToggleGroup mayusculas;

    @FXML
    private RadioButton radioMil;

    @FXML
    private RadioButton radioUnMil;

    @FXML
    private ToggleGroup expresion;

    private EventBus eventBus;

    private FoliadorController foliadorController;

    public ParteLiteralController(EventBus eventBus, FoliadorController foliadorController) {
        super(eventBus);
        this.eventBus = eventBus;
        this.foliadorController = foliadorController;
    }



    @Subscribe
    public void actualizarPdf(VistaPreviaPdfEvent e){
        actualizarDatos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtLiteralDerechoAdicional.setText(ConstantesFoliador.TEXTO_ADICIONAL);
        txtLiteralIzquierdoAdicional.setText(ConstantesFoliador.TEXTO_ADICIONAL);
        radioPrimeraMayuscula.setUserData(ConstantesFoliador.CONTROL_PRIMERA_MAYUSCULA);
        radioTodoMinuscula.setUserData(ConstantesFoliador.CONTROL_TODO_MINUSCULA);
        radioTodoMayuscula.setUserData(ConstantesFoliador.CONTROL_TODO_MAYUSCULA);
        radioMil.setUserData(ConstantesFoliador.LITERAL_MIL);
        radioUnMil.setUserData(ConstantesFoliador.LITERAL_UN_MIL);

    }

    private void actualizarDatos(){
        foliadorController.foliadorModelo.setTextoAdicionalLiteralIzquierda(txtLiteralIzquierdoAdicional.getText());
        foliadorController.foliadorModelo.setTextoAdicionalLiteralDerecha(txtLiteralDerechoAdicional.getText());
        foliadorController.foliadorModelo.setControlMayusculaMinuscula((Integer) mayusculas.getSelectedToggle().getUserData());
        foliadorController.foliadorModelo.setExpresionLiteral((Integer) expresion.getSelectedToggle().getUserData());
    }
}
