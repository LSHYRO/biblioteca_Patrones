/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logicaneg;

import accesodatos.LibrosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Libros;

/**
 *
 * @author lshyro
 */
@Stateless
@LocalBean
public class LnLibros {

    @EJB
    private LibrosFacade librosFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Libros> findLibros() {
        return librosFacade.findAll();
    }

    public boolean agregarLibro(Libros libro) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!seEncuentra(libro)) {
            librosFacade.create(libro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado correctamente", "El libro se ha agregado a la base correctamente"));
            return true;
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El libro ya se encuentra registrado", "El libro que ha intentado registrar ya se encuentra en los registros"));
            return false;
        }
    }

    public Libros findlibro(int id) {
        return librosFacade.find(id);
    }

    public boolean seEncuentra(Libros libro) {
        boolean seEncuentra = false;
        for (Libros a : librosFacade.findAll()) {
            if (a.getTitulo().equalsIgnoreCase(libro.getTitulo())
                    && a.getAutor().equalsIgnoreCase(libro.getAutor())
                    && a.getIdedicion().getIdedicion().equals(libro.getIdedicion().getIdedicion())) {
                seEncuentra = true;
            }
        }
        System.out.println(seEncuentra + "Estado final");
        return seEncuentra;
    }

    public void eliminarLibro(int i) {
        librosFacade.remove(librosFacade.find(i));
    }

    public void modificarLibro(Libros libro) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (seEncuentra(libro)) {
            librosFacade.edit(libro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El libro se ha modificado correctamente", "El libro se ha modificado"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El libro no se ha encontrado", "El libro que ha intentado modificar no se encuentra en los registros"));
        }
    }

    public boolean libroExistente(Libros libro) {
        for (Libros a : librosFacade.findAll()) {
            if (a.getIdlibro().equals(libro.getIdlibro())
                    && a.getTitulo().equalsIgnoreCase(libro.getTitulo())
                    && a.getAutor().equalsIgnoreCase(libro.getAutor())
                    && a.getIdedicion().getIdedicion().equals(libro.getIdedicion().getIdedicion())) {
                return true;
            }
        }
        return false;
    }
}
