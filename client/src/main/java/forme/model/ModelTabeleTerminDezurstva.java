/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.TerminDezurstva;

/**
 *
 * @author Petar
 */
public class ModelTabeleTerminDezurstva extends AbstractTableModel {

    List<TerminDezurstva> lista;
    String[] kolone = {"id", "smena" , "opis", "lokacija"};

    
    public ModelTabeleTerminDezurstva(List<TerminDezurstva> lista) {
        this.lista = lista;
    }

    public List<TerminDezurstva> getLista() {
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
        
        TerminDezurstva td = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(td.getIdTerminDezurstva());
            case 1:
                return td.getSmena();
            case 2:
                return td.getOpis();
            case 3:
                return td.getLokacija();
            default:
                return "N/A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String smena, String lokacija) {
        
        
        List<TerminDezurstva> filteredList =  lista.stream()
                .filter(td -> (smena == null || smena.isEmpty() || td.getSmena().toLowerCase().contains(smena.toLowerCase())))
                .filter(td -> (lokacija == null || lokacija.isEmpty() || td.getLokacija().toLowerCase().contains(lokacija.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();

    }
    
    
    
}
