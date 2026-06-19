/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;
import model.TipClanstva;

/**
 *
 * @author Petar
 */
public class ModelTabeleTipClanstva extends AbstractTableModel {

    List<TipClanstva> lista;
    String[] kolone = {"id", "tip" , "cena", "max broj stavki"};

    
    public ModelTabeleTipClanstva(List<TipClanstva> lista) {
        this.lista = lista;
    }

    public List<TipClanstva> getLista() {
        return lista;
    }
    
    
    @Override
    public int getRowCount() {  
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        TipClanstva tc = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(tc.getIdTipClanstva());
            case 1:
                return tc.getTip();
            case 2:
                return String.valueOf(tc.getCena());
            case 3:
                return String.valueOf(tc.getMaksimalanBrojStavki());
            default:
                return "N/A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String tip, int maxBrojStavki) {
        
        
        List<TipClanstva> filteredList =  lista.stream()
                .filter(tc -> (tip == null || tip.isEmpty() || tc.getTip().toLowerCase().contains(tip.toLowerCase())))
                .filter(tc -> (maxBrojStavki <= 0 || tc.getMaksimalanBrojStavki() == maxBrojStavki))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();

    }
    
    
    
}
