package com.munaysoft.foliador.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.munaysoft.foliador.event.VistaPreviaPdfEvent;
import com.munaysoft.foliador.utilidad.ConstantesFoliador;
import com.munaysoft.foliador.utilidad.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class ParteNumericaController extends RegistrarEventoController implements Initializable {

    @FXML
    private RadioButton radioArabico;
    @FXML
    private RadioButton radioRomano;
    @FXML
    private Spinner<Integer> spiRelleno;
    @FXML
    private TextField txtRellenoIzquierda;
    @FXML
    private TextField txtAdicionalIzquierda;
    @FXML
    private TextField txtAdicionalDerecha;
    @FXML
    private ToggleGroup tipoNumero;

    private EventBus eventBus;

    private FoliadorController foliadorController;

    public ParteNumericaController(EventBus eventBus, FoliadorController foliadorController) {
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
        spiRelleno.setValueFactory(Util.getValueFactory(ConstantesFoliador.LIMITE_NUMERO_INICIO));
        /*spiRelleno.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                eventBus.post(new VistaPreviaPdfEvent(null));
            }
        });*/

        txtRellenoIzquierda.setText(Integer.toString(ConstantesFoliador.NUMERO_RELLENO_IZQUIERDA));
        txtAdicionalIzquierda.setText(ConstantesFoliador.TEXTO_ADICIONAL);
        txtAdicionalDerecha.setText(ConstantesFoliador.TEXTO_ADICIONAL);
        radioArabico.setUserData(ConstantesFoliador.NUMERO_ARABICO);
        radioRomano.setUserData(ConstantesFoliador.NUMERO_ROMANO);
    }


    public void actualizarDatos(){
        foliadorController.foliadorModelo.setTipoNumero((int)tipoNumero.getSelectedToggle().getUserData());
        foliadorController.foliadorModelo.setCantidadMaximadigitos(spiRelleno.getValue());
        foliadorController.foliadorModelo.setRellenoIzquierda(Integer.parseInt(txtRellenoIzquierda.getText()));
        foliadorController.foliadorModelo.setTextoAdicionalDerecha(txtAdicionalDerecha.getText());
        foliadorController.foliadorModelo.setTextoAdicionalIzquierda(txtAdicionalIzquierda.getText());
    }
}
