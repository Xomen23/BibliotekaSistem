/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ApstraktniDomenskiObjekat;
import model.Pozajmica;
import repository.db.DBRepository;
import repository.db.DbConnectionFactory;

/**
 *
 * @author Petar
 */
public class DbRepositoryGeneric implements DBRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov != null){ // TODO
            upit = upit + uslov;  
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public int add(ApstraktniDomenskiObjekat param) throws Exception {
        
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" +param.vratiKoloneZaUbacivanje() + ") VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        //st.executeUpdate(upit);
        //st.close();
        
        
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = st.getGeneratedKeys();
        int generatedId = -1;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        rs.close();
        st.close();
        return generatedId;
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc();
        
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();        
        
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();   
        
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() { // TODO
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAllStavke(Pozajmica p) throws Exception{
        
        
        String upit = "DELETE FROM stavkaPozajmice WHERE stavkaPozajmice.idPozajmica=" + p.getIdPozajmica();

        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
        
    }
    
    
    
    
}
