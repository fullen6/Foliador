package com.munaysoft.foliador.controller;


import com.dansoftware.pdfdisplayer.PDFDisplayer;
import com.dansoftware.pdfdisplayer.PdfJSVersion;
import com.munaysoft.foliador.event.VistaPreviaPdfEvent;
import com.munaysoft.foliador.impresion.Imprimir;
import com.munaysoft.foliador.main.FoliadorApplication;
import com.munaysoft.foliador.modelo.FoliadorModelo;
import com.munaysoft.foliador.pdf.CrearPdf;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;


import com.google.common.eventbus.EventBus;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.print.PrintException;

public class FoliadorController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollInicio;

    @FXML
    private ScrollPane scrollNumero;

    @FXML
    private ScrollPane scrollLiteral;

    @FXML
    private AnchorPane anchorImprimir;

    @FXML
    private Button imprimir;

    @FXML
    private Button vistaPrevia;


    @FXML private TitledPane titlePaneInicio;
    @FXML private TitledPane titlePaneNumero;
    @FXML private TitledPane titlePaneLiteral;
    @FXML private TitledPane titlePaneImpresion;

    private Path path;


    private EventBus foliadorControllerEvent = new EventBus();
    public FoliadorModelo foliadorModelo = new FoliadorModelo();
    private InicioController inicioController = new InicioController(foliadorControllerEvent, this);
    private ParteNumericaController parteNumericaController = new ParteNumericaController(foliadorControllerEvent, this) ;
    private ParteLiteralController parteLiteralController = new ParteLiteralController(foliadorControllerEvent, this);
    private ImpresionController impresionController = new ImpresionController(foliadorControllerEvent,this);


    private PDFDisplayer displayer = new PDFDisplayer(PdfJSVersion._2_2_228);

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        AnchorPane vBoxIncio  = null;
        AnchorPane vBoxNumero = null;
        AnchorPane vBoxLiteral = null;
        AnchorPane vBoxImprimir = null;
        try {
             vBoxIncio = getController("vista/inicio-view.fxml",inicioController).load();
             vBoxNumero = getController("vista/parte-numerica-view.fxml",parteNumericaController).load();
             vBoxLiteral = getController("vista/parte-literal-view.fxml",parteLiteralController).load();
             vBoxImprimir = getController("vista/impresion-view.fxml",impresionController).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollInicio.setContent(vBoxIncio);
        scrollNumero.setContent(vBoxNumero);
        scrollLiteral.setContent(vBoxLiteral);
        anchorImprimir.getChildren().addAll(vBoxImprimir);

        displayer.setSecondaryToolbarToggleVisibility(false);
        gridPane.getChildren().addAll(displayer.toNode());
        GridPane.setConstraints(displayer.toNode(),1,0);

        crearVistaPrevia(false);

        foliadorControllerEvent.register(this);

       agregarIcono(titlePaneInicio,Color.rgb(255,255,255,.8),FontAwesomeIcon.COGS,"17px");
       agregarIcono(titlePaneNumero,Color.rgb(255,255,255,.8),FontAwesomeIcon.CALCULATOR,"17px");
       agregarIcono(titlePaneLiteral,Color.rgb(255,255,255,.8),FontAwesomeIcon.FILE_TEXT,"17px");
       agregarIcono(titlePaneImpresion,Color.rgb(255,255,255,.8),FontAwesomeIcon.FILE_ARCHIVE_ALT,"17px");

        agregarIcono(imprimir,Color.rgb(255,255,255,.8),FontAwesomeIcon.PRINT,"17px");
        agregarIcono(vistaPrevia,Color.rgb(255,255,255,.8),FontAwesomeIcon.FILE_PDF_ALT,"17px");


    }
    private void agregarIcono(Labeled titledPane, Color color, FontAwesomeIcon fontAwesomeIcon, String iconSize){
        Text icon = GlyphsDude.createIcon(fontAwesomeIcon,iconSize);
        icon.setFill(color);
        titledPane.setGraphic(icon);
        titledPane.setContentDisplay(ContentDisplay.LEFT);
    }


    @FXML
    public void onActionCambio(ActionEvent event){
        foliadorControllerEvent.post(new VistaPreviaPdfEvent(null));
        crearVistaPrevia(true);

    }

    @FXML
    public void onActionImprimir(ActionEvent event){
        Node node = (Node) event.getSource();
        imprimirPdf(node);
    }

    private FXMLLoader getController(String fxmlRuta, RegistrarEventoController registrarEventoController){
        FXMLLoader loader = new FXMLLoader(FoliadorApplication.class.getResource(fxmlRuta));
        loader.setController(registrarEventoController);
        return loader;
    }

    private void crearVistaPrevia(boolean rePreview){
        try {
            if(rePreview)
                Files.deleteIfExists(path);

            path = Files.createTempFile("foliador_",".pdf");

            CrearPdf.manipulatePdf(path.toString(), foliadorModelo);
            displayer.loadPDF(new File(path.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void imprimirPdf(Node node){
        Imprimir imprimir = new Imprimir();

        try {
            imprimir.start(node,path);
        } catch (PrintException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}