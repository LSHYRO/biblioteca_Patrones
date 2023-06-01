/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logicaneg;

import accesodatos.TipoUsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.TipoUsuario;

/**
 *
 * @author yeste
 */
@Stateless
@LocalBean
public class LnTipoUsuario {

    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;

    public List<TipoUsuario> findTiposUsuarios(){
        return tipoUsuarioFacade.findAll();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
