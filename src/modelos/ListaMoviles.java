package modelos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaMoviles  extends AbstractListModel {
    private List<Movil> lista;

    public ListaMoviles(List<Movil> lista) {
        this.lista = new ArrayList<>(lista);
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        Movil m = lista.get(index);
        return m;
    }

    public Movil getMovil(int index){
        return lista.get(index);
    }


}
