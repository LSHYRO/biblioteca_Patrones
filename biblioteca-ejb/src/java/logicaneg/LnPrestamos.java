/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logicaneg;

import accesodatos.PrestamosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Prestamos;

/**
 *
 * @author lshyro
 */
@Stateless
@LocalBean
public class LnPrestamos {

    @EJB
    private PrestamosFacade prestamosFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void addPrestamo(Prestamos p){
        prestamosFacade.create(p);
    }
    
    public List<Prestamos> findPrestamos(){
        return prestamosFacade.findAll();
    }
}
