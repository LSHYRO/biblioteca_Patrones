/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.PrestamosLibros;

/**
 *
 * @author lshyro
 */
@Stateless
public class PrestamosLibrosFacade extends AbstractFacade<PrestamosLibros> {

    @PersistenceContext(unitName = "biblioteca-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamosLibrosFacade() {
        super(PrestamosLibros.class);
    }
    
}
