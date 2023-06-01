/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logicaneg;

import accesodatos.PrestamosLibrosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.PrestamosLibros;

/**
 *
 * @author yeste
 */
@Stateless
@LocalBean
public class LnPrestamosLibros {

    @EJB
    private PrestamosLibrosFacade prestamosLibrosFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void addPrestamoLibro(PrestamosLibros pl){
        prestamosLibrosFacade.create(pl);
    }
    
    public List<PrestamosLibros> findPrestamosLibros(){
        return prestamosLibrosFacade.findAll();
    }
}
