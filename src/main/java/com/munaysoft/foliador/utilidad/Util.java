package com.munaysoft.foliador.utilidad;

import com.itextpdf.kernel.geom.PageSize;
import javafx.scene.control.SpinnerValueFactory;
import static com.munaysoft.foliador.utilidad.ConstantesFoliador.*;

public class Util {
    public static SpinnerValueFactory<Integer> getValueFactory(int valorInicial){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.MAX_VALUE, valorInicial);
    }

    public static PageSize orientarPagina(int orientar){
        PageSize pageSize;
        switch (orientar){
            case PAGINA_HORIZONTAL:
                pageSize = PageSize.Default;
                break;
            case PAGINA_VERTICAL:
                pageSize = PageSize.A4.rotate();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + orientar);
        }
        return pageSize;
    }
}
