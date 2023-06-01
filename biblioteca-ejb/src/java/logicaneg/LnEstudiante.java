/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logicaneg;

import accesodatos.EstudianteFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Estudiante;

/**
 *
 * @author lshyro
 */
@Stateless
@LocalBean
public class LnEstudiante {

    @EJB
    private EstudianteFacade estudianteFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Estudiante> findEstudiantes(){
        return estudianteFacade.findAll();
    }
}
