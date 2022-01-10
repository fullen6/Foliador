package com.munaysoft.foliador.modelo;
import static com.munaysoft.foliador.utilidad.ConstantesFoliador.*;
public enum PaginaOrientarModelo {

    HORIZONTAL("Horizontal",PAGINA_HORIZONTAL),
    VERTICAL("Vertical",PAGINA_VERTICAL);
    private String nombre;
    private int codigo;

    PaginaOrientarModelo(String nombre, int codigo) {
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
