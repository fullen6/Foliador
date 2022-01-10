package com.munaysoft.foliador.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.munaysoft.foliador.event.VistaPreviaPdfEvent;
import com.munaysoft.foliador.modelo.EstiloModelo;
import com.munaysoft.foliador.modelo.FuenteModelo;
import com.munaysoft.foliador.utilidad.ConstantesFoliador;
import com.munaysoft.foliador.utilidad.FuenteCell;
import com.munaysoft.foliador.utilidad.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class InicioController extends RegistrarEventoController implements Initializable{

    @FXML
    private Spinner<Integer> spiInicio;
    @FXML
    private Spinner<Integer> spiFin;
    @FXML
    private Spinner<Integer> spiIntervalo;
    @FXML
    private ComboBox<FuenteModelo> comboFuente;
    @FXML
    private Spinner <Integer> spiTamanio;
    @FXML
    private RadioButton radioJustificacionIzquierda;
    @FXML
    private RadioButton radioJustificacionCentro;
    @FXML
    private RadioButton radioJustificacionDerecha;
    @FXML
    private RadioButton radioLineaUnoNumero;
    @FXML
    private RadioButton radioLineaUnoLiteral;
    @FXML
    private RadioButton radioLineaUnoNinguno;
    @FXML
    private RadioButton radioLineaDosNumero;
    @FXML
    private RadioButton radioLineaDosLiteral;
    @FXML
    private RadioButton radioLineaDosNinguno;
    @FXML
    private ToggleGroup justificacion;
    @FXML
    private ToggleGroup linea1;
    @FXML
    private ToggleGroup linea2;

    private EventBus eventBus;

    private FoliadorController foliadorModelo;

    public InicioController(EventBus eventBus,FoliadorController foliadorModelo) {
        super(eventBus);
        this.eventBus = eventBus;
        this.foliadorModelo = foliadorModelo;
    }

    @Subscribe
    public void actualizarPdf(VistaPreviaPdfEvent e){
        actualizarDatos();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spiInicio.setValueFactory(Util.getValueFactory(ConstantesFoliador.LIMITE_NUMERO_INICIO));
        spiIntervalo.setValueFactory(Util.getValueFactory(ConstantesFoliador.LIMITE_NUMERO_INTREVALO));
        spiFin.setValueFactory(Util.getValueFactory(ConstantesFoliador.LIMITE_NUMERO_FIN));
        comboFuente.getItems().addAll(FuenteModelo.values());
        comboFuente.getSelectionModel().select(FuenteModelo.TIMES_BOLD);
        comboFuente.setCellFactory(new Callback<ListView<FuenteModelo>, ListCell<FuenteModelo>>() {
            @Override
            public ListCell<FuenteModelo> call(ListView<FuenteModelo> fuenteModeloListView) {
                return new FuenteCell();
            }
        });

        spiTamanio.setValueFactory(Util.getValueFactory(ConstantesFoliador.FUENTE_TAMANIO));

        radioJustificacionIzquierda.setUserData(ConstantesFoliador.JUSTIFICACION_IZQUIERDA);
        radioJustificacionCentro.setUserData(ConstantesFoliador.JUSTIFICACION_CENTRO);
        radioJustificacionDerecha.setUserData(ConstantesFoliador.JUSTIFICACION_DERECHA);

        radioLineaUnoLiteral.setUserData(ConstantesFoliador.LINEA_LITERAL);
        radioLineaUnoNinguno.setUserData(ConstantesFoliador.LINEA_NINGUNO);
        radioLineaUnoNumero.setUserData(ConstantesFoliador.LINEA_NUMERO);

        radioLineaDosLiteral.setUserData(ConstantesFoliador.LINEA_LITERAL);
        radioLineaDosNinguno.setUserData(ConstantesFoliador.LINEA_NINGUNO);
        radioLineaDosNumero.setUserData(ConstantesFoliador.LINEA_NUMERO);
    }

    private void actualizarDatos(){
        foliadorModelo.foliadorModelo.setInicio(spiInicio.getValue());
        foliadorModelo.foliadorModelo.setFin(spiFin.getValue());
        foliadorModelo.foliadorModelo.setIntervalo(spiIntervalo.getValue());
        foliadorModelo.foliadorModelo.setFuente(comboFuente.getValue().getCodigo());
        foliadorModelo.foliadorModelo.setFuenteTamanio(spiTamanio.getValue());
        foliadorModelo.foliadorModelo.setJustificacion((int)justificacion.getSelectedToggle().getUserData());
        foliadorModelo.foliadorModelo.setLineaUno((int) linea1.getSelectedToggle().getUserData());
        foliadorModelo.foliadorModelo.setLineaDos((int) linea2.getSelectedToggle().getUserData());

    }


}
