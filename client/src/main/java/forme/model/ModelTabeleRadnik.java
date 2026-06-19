/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Radnik;

/**
 *
 * @author Petar
 */
public class ModelTabeleRadnik extends AbstractTableModel {

    List<Radnik> lista;
    String[] kolone = {"id", "ime" , "prezime", "jmbg", "brojTelefona" , "korisnickoIme"};

    
    public ModelTabeleRadnik(List<Radnik> lista) {
        this.lista = lista;
    }

    public List<Radnik> getLista() {
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
        
        Radnik r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(r.getIdRadnik());
            case 1:
                return r.getIme();
            case 2:
                return r.getPrezime();
            case 3:
                return r.getJmbg();
            case 4:
                return r.getBrojTelefona();
            case 5:
                return r.getKorisnickoIme();
            default:
                return "N/A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String ime, String prezime, String korisnickoIme) {
        
        List<Radnik> filteredList =  lista.stream()
            .filter(r -> (ime == null || ime.isEmpty() || r.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(r -> (prezime == null || prezime.isEmpty() || r.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(r -> (korisnickoIme == null || korisnickoIme.isEmpty() || r.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())))
            .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
                
    }
    
    
    
}
