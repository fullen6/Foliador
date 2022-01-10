package com.munaysoft.foliador.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.munaysoft.foliador.event.VistaPreviaPdfEvent;
import com.munaysoft.foliador.modelo.PaginaOrientarModelo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;
import static com.munaysoft.foliador.utilidad.ConstantesFoliador.*;

public class ImpresionController extends RegistrarEventoController implements Initializable {

    private EventBus eventBus;

    private FoliadorController foliadorController;

    @FXML private RadioButton radioUno;
    @FXML private RadioButton radioDos;
    @FXML private RadioButton radioTres;
    @FXML private RadioButton radioCuatro;
    @FXML private RadioButton radioCinco;
    @FXML private RadioButton radioSeis;
    @FXML private ToggleGroup posicionPagina;
    @FXML private ComboBox<PaginaOrientarModelo> comboPaginaOrientar;

    public ImpresionController(EventBus eventBus, FoliadorController foliadorController) {
        super(eventBus);
        this.eventBus = eventBus;
        this.foliadorController = foliadorController;
    }

    @Subscribe
    public void actualizarPdf(VistaPreviaPdfEvent e){
        actualizarDatos();
    }

    private void actualizarDatos() {
        foliadorController.foliadorModelo.setPosicionPagina((int) posicionPagina.getSelectedToggle().getUserData());
        foliadorController.foliadorModelo.setOrientarPagina(comboPaginaOrientar.getValue().getCodigo());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioUno.setUserData(ALINEAR_TOP_IZQUIERDA);
        radioDos.setUserData(ALINEAR_TOP_CENTRO);
        radioTres.setUserData(ALINEAR_TOP_DERECHA);
        radioCuatro.setUserData(ALINEAR_BOTTON_IZQUIERDA);
        radioCinco.setUserData(ALINEAR_BOTTON_CENTRO);
        radioSeis.setUserData(ALINEAR_BOTTON_DERECHA);
        comboPaginaOrientar.getItems().addAll(PaginaOrientarModelo.values());
        comboPaginaOrientar.getSelectionModel().select(PaginaOrientarModelo.HORIZONTAL);

    }
}
