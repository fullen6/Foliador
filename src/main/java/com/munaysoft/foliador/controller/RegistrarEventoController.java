package com.munaysoft.foliador.controller;

import com.google.common.eventbus.EventBus;

public abstract class RegistrarEventoController {


    public RegistrarEventoController(EventBus eventBus) {
        eventBus.register(this);
    }
}
