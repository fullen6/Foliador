package com.munaysoft.foliador.modelo;

import javafx.scene.control.Label;

public enum FuenteModelo  {
    COURIER("Courier","Courier"),
    COURIER_BOLD("Courier Bold","Courier-Bold"),
    COURIER_OBLIQUE("Courier Oblique","Courier-Oblique"),
    COURIER_BOLDOBLIQUE("Courier BoldOblique","Courier-BoldOblique"),
    HELVETICA("Helvetica","Helvetica"),
    HELVETICA_BOLD("Helvetica Bold","Helvetica-Bold"),
    HELVETICA_OBLIQUE("Helvetica Oblique","Helvetica-Oblique"),
    HELVETICA_BOLDOBLIQUE("Helvetica BoldOblique","Helvetica-BoldOblique"),
    SYMBOL("Symbol","Symbol"),
    TIMES_ROMAN("Times Roman","Times-Roman"),
    TIMES_BOLD("Times Bold","Times-Bold"),
    TIMES_ITALIC("Times Italic","Times-Italic"),
    TIMES_BOLDITALIC("Times BoldItalic","Times-BoldItalic"),
    ZAPFDINGBATS("ZapfDingbats","ZapfDingbats");

    private String fuente;
    private String codigo;

    FuenteModelo(String fuente, String codigo) {
        this.fuente = fuente;
        this.codigo = codigo;
    }

    public String getFuente() {
        return fuente;
    }


    public String getCodigo() {
        return codigo;
    }

}
