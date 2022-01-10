module com.munaysoft.foliador {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires PDFViewerFX;
    requires com.google.common;
    requires kernel;
    requires layout;
    requires org.apache.commons.lang3;
    requires javax.print;
    requires java.desktop;
    requires fontawesomefx;

    opens com.munaysoft.foliador.main.vista to javafx.fxml;
    exports com.munaysoft.foliador.controller;
    opens com.munaysoft.foliador.controller to javafx.fxml;
    opens com.munaysoft.foliador.main to javafx.graphics;
}