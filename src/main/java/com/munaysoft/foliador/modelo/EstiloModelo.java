package com.munaysoft.foliador.modelo;

public enum EstiloModelo {
    UNDEFINED( "UNDEFINED", -1),
    NORMAL("NORMAL" , 0),
    BOLD("BOLD" , 1),
    ITALIC( "ITALIC" , 2),
    BOLDITALIC("BOLDITALIC" , 3);

    private String nombre;
    private int codigo;

    EstiloModelo(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }
}
