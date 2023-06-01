/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Prestamos;

/**
 *
 * @author yeste
 */
@Stateless
public class PrestamosFacade extends AbstractFacade<Prestamos> {

    @PersistenceContext(unitName = "biblioteca-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamosFacade() {
        super(Prestamos.class);
    }
    
}
