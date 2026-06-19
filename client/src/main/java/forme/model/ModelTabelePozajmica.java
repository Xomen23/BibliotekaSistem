/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Pozajmica;

/**
 *
 * @author Petar
 */
public class ModelTabelePozajmica extends AbstractTableModel {

    List<Pozajmica> lista;
    String[] kolone = {"id", "datum" , "rokVracanja", "status", "nacinPreuzimanja", "brojStavki", "ukupnaKazna", "clan", "tipClanstva","radnik"};

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
    
    public ModelTabelePozajmica(List<Pozajmica> lista) {
        this.lista = lista;
    }

    public List<Pozajmica> getLista() {
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
        
        Pozajmica p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(p.getIdPozajmica());
            case 1:
                if(p.getDatumPozajmice() != null){
                    return dateFormat.format(p.getDatumPozajmice());
                }
                return null;
            case 2:
                if(p.getRokVracanja() != null){
                    return dateFormat.format(p.getRokVracanja());
                }
                return null;
            case 3:
                return p.getStatus();
            case 4:
                return p.getNacinPreuzimanja();
            case 5:
                return String.valueOf(p.getBrojStavki());
            case 6:
                return String.valueOf(p.getUkupnaKazna());
            case 7:
                return p.getClan().toString();
            case 8:
                return p.getClan().getTipClanstva().getTip();
            case 9:
                return p.getRadnik().toString(); 
            default:
                return "N/A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String clan, String radnik) {
        
        
        List<Pozajmica> filteredList =  lista.stream()
                //.filter(p -> (status == null || status.isEmpty() || p.getStatus().toLowerCase().contains(status.toLowerCase())))
                .filter(p -> (clan == null || clan.isEmpty() || p.getClan().toString().toLowerCase().contains(clan.toLowerCase())))
                .filter(p -> (radnik == null || radnik.isEmpty() || p.getRadnik().toString().toLowerCase().contains(radnik.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();

    }
    
    
    
}
