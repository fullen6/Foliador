package com.munaysoft.foliador.utilidad;

import com.munaysoft.foliador.modelo.FuenteModelo;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;


public class FuenteCell extends ListCell<FuenteModelo> {
    @Override
    protected void updateItem(FuenteModelo fuenteModelo, boolean empty) {
        super.updateItem(fuenteModelo, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            setText(fuenteModelo.getFuente());
            setFont(Font.font(fuenteModelo.getFuente()));
        }

    }


}
