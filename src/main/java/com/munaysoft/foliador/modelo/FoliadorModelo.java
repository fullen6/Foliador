package com.munaysoft.foliador.modelo;

import static  com.munaysoft.foliador.utilidad.ConstantesFoliador.*;

public class FoliadorModelo {

    private int inicio = LIMITE_NUMERO_INICIO;
    private int fin = LIMITE_NUMERO_FIN;
    private int intervalo = LIMITE_NUMERO_INTREVALO;
    private String fuente = FuenteModelo.TIMES_BOLD.getCodigo();
    private int fuenteTamanio = FUENTE_TAMANIO;
    private int justificacion = JUSTIFICACION_CENTRO;
    private int lineaUno = LINEA_NUMERO;
    private int lineaDos = LINEA_LITERAL;
    private int tipoNumero = NUMERO_ARABICO;
    private int rellenoIzquierda = NUMERO_RELLENO_IZQUIERDA;
    private String textoAdicionalDerecha = TEXTO_ADICIONAL;
    private String textoAdicionalIzquierda = TEXTO_ADICIONAL;
    private String textoAdicionalLiteralIzquierda = TEXTO_ADICIONAL;
    private String textoAdicionalLiteralDerecha = TEXTO_ADICIONAL;
    private int controlMayusculaMinuscula = CONTROL_PRIMERA_MAYUSCULA;
    private int expresionLiteral = LITERAL_MIL;
    private int cantidadMaximadigitos = NUMERO_CANTIDAD_MAXIMA;
    private int posicionPagina = ALINEAR_TOP_DERECHA;
    private int orientarPagina = PAGINA_HORIZONTAL;

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public int getFuenteTamanio() {
        return fuenteTamanio;
    }

    public void setFuenteTamanio(int fuenteTamanio) {
        this.fuenteTamanio = fuenteTamanio;
    }

    public int getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(int justificacion) {
        this.justificacion = justificacion;
    }

    public int getLineaUno() {
        return lineaUno;
    }

    public void setLineaUno(int lineaUno) {
        this.lineaUno = lineaUno;
    }

    public int getLineaDos() {
        return lineaDos;
    }

    public void setLineaDos(int lineaDos) {
        this.lineaDos = lineaDos;
    }

    public int getTipoNumero() {
        return tipoNumero;
    }

    public void setTipoNumero(int tipoNumero) {
        this.tipoNumero = tipoNumero;
    }

    public int getRellenoIzquierda() {
        return rellenoIzquierda;
    }

    public void setRellenoIzquierda(int rellenoIzquierda) {
        this.rellenoIzquierda = rellenoIzquierda;
    }

    public String getTextoAdicionalDerecha() {
        return textoAdicionalDerecha;
    }

    public void setTextoAdicionalDerecha(String textoAdicionalDerecha) {
        this.textoAdicionalDerecha = textoAdicionalDerecha;
    }

    public String getTextoAdicionalIzquierda() {
        return textoAdicionalIzquierda;
    }

    public void setTextoAdicionalIzquierda(String textoAdicionalIzquierda) {
        this.textoAdicionalIzquierda = textoAdicionalIzquierda;
    }

    public String getTextoAdicionalLiteralIzquierda() {
        return textoAdicionalLiteralIzquierda;
    }

    public void setTextoAdicionalLiteralIzquierda(String textoAdicionalLiteralIzquierda) {
        this.textoAdicionalLiteralIzquierda = textoAdicionalLiteralIzquierda;
    }

    public String getTextoAdicionalLiteralDerecha() {
        return textoAdicionalLiteralDerecha;
    }

    public void setTextoAdicionalLiteralDerecha(String textoAdicionalLiteralDerecha) {
        this.textoAdicionalLiteralDerecha = textoAdicionalLiteralDerecha;
    }

    public int getControlMayusculaMinuscula() {
        return controlMayusculaMinuscula;
    }

    public void setControlMayusculaMinuscula(int controlMayusculaMinuscula) {
        this.controlMayusculaMinuscula = controlMayusculaMinuscula;
    }

    public int getExpresionLiteral() {
        return expresionLiteral;
    }

    public void setExpresionLiteral(int expresionLiteral) {
        this.expresionLiteral = expresionLiteral;
    }

    public int getCantidadMaximadigitos() {
        return cantidadMaximadigitos;
    }

    public void setCantidadMaximadigitos(int cantidadMaximadigitos) {
        this.cantidadMaximadigitos = cantidadMaximadigitos;
    }

    public int getPosicionPagina() {
        return posicionPagina;
    }

    public void setPosicionPagina(int posicionPagina) {
        this.posicionPagina = posicionPagina;
    }

    public int getOrientarPagina() {
        return orientarPagina;
    }

    public void setOrientarPagina(int orientarPagina) {
        this.orientarPagina = orientarPagina;
    }

    @Override
    public String toString() {
        return "FoliadorModelo{" +
                "inicio=" + inicio +
                ", fin=" + fin +
                ", intervalo=" + intervalo +
                ", fuente='" + fuente + '\'' +
                ", tamanio=" + fuenteTamanio +
                ", justificacion=" + justificacion +
                ", lineaUno=" + lineaUno +
                ", lineaDos=" + lineaDos +
                ", tipoNumero=" + tipoNumero +
                ", rellenoIzquierda='" + rellenoIzquierda + '\'' +
                ", textoAdicionalDerecha='" + textoAdicionalDerecha + '\'' +
                ", textoAdicionalIzquierda='" + textoAdicionalIzquierda + '\'' +
                ", textoAdicionalLiteralIzquierda='" + textoAdicionalLiteralIzquierda + '\'' +
                ", textoAdicionalLiteralDerecha='" + textoAdicionalLiteralDerecha + '\'' +
                ", controlMayusculaMinuscula=" + controlMayusculaMinuscula +
                ", expresionLiteral=" + expresionLiteral +
                ", cantidadMaximadigitos=" + cantidadMaximadigitos +
                ", posicionPagina=" + posicionPagina +
                '}';
    }
}
