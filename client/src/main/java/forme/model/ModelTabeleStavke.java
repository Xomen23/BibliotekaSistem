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
import model.StavkaPozajmice;

/**
 *
 * @author Petar
 */
public class ModelTabeleStavke extends AbstractTableModel {

    List<StavkaPozajmice> lista;
    String[] kolone = {"rb" , "datumVracanja", "vraceno", "kazna", "knjiga", "autor"};

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    
    public ModelTabeleStavke(List<StavkaPozajmice> lista) {
        this.lista = lista;
    }

    public List<StavkaPozajmice> getLista() {
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
        
        StavkaPozajmice sp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(sp.getRb());
            case 1:
                if(sp.getDatumVracanja() != null){
                    return dateFormat.format(sp.getDatumVracanja());
                }
                return "/";
            case 2:
                if(sp.isVraceno()){
                    return "DA";
                }else{
                    return "NE";
                }
            case 3:
                return String.valueOf(sp.getKazna());
            case 4:
                if(sp.getKnjiga() != null){
                    return sp.getKnjiga().getNaziv();
                }
                return null;
            case 5:
                if(sp.getKnjiga() != null){
                    return sp.getKnjiga().getAutor();
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
    
    
    
}
