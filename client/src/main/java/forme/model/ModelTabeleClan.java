/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Clan;

/**
 *
 * @author Petar
 */
public class ModelTabeleClan extends AbstractTableModel {

    List<Clan> lista;
    String[] kolone = {"id", "ime" , "prezime", "email", "brojTelefona", "tipClanstva"};

    
    public ModelTabeleClan(List<Clan> lista) {
        this.lista = lista;
    }

    public List<Clan> getLista() {
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
        
        Clan c = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(c.getIdClan());
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getEmail();
            case 4:
                return c.getBrojTelefona();
            case 5:
                if(c.getTipClanstva() != null){
                    return c.getTipClanstva().getTip();
                }
                return null;
            default:
                return "N/A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String ime, String prezime, String email) {
        
        
        List<Clan> filteredList =  lista.stream()
                .filter(c -> (ime == null || ime.isEmpty() || c.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(c -> (prezime == null || prezime.isEmpty() || c.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(c -> (email == null || email.isEmpty() || c.getEmail().toLowerCase().contains(email.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();

    }
    
    
    
}
