/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;

/**
 *
 * @author Petar
 */
public class ModelTabeleKnjiga extends AbstractTableModel {

    List<Knjiga> lista;
    String[] kolone = {"id", "naziv" , "autor", "isbn", "zanr"};

    
    public ModelTabeleKnjiga(List<Knjiga> lista) {
        this.lista = lista;
    }

    public List<Knjiga> getLista() {
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
        
        Knjiga k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(k.getIdKnjiga());
            case 1:
                return k.getNaziv();
            case 2:
                return k.getAutor();
            case 3:
                return k.getIsbn();
            case 4:
                return k.getZanr();
            default:
                return "N/A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String naziv, String autor, String zanr) {
        
        
        List<Knjiga> filteredList =  lista.stream()
                .filter(k -> (naziv == null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(k -> (autor == null || autor.isEmpty() || k.getAutor().toLowerCase().contains(autor.toLowerCase())))
                .filter(k -> (zanr == null || zanr.isEmpty() || k.getZanr().toLowerCase().contains(zanr.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();

    }
    
    
    
}
