package com.munaysoft.foliador.event;

import com.munaysoft.foliador.modelo.FoliadorModelo;

public abstract class FoliadorEvento {

    private FoliadorModelo foliadorModelo;
    public FoliadorEvento(FoliadorModelo foliadorModelo) {
        this.foliadorModelo = foliadorModelo;
    }

    public FoliadorModelo getFoliadorModelo() {
        return foliadorModelo;
    }
}
