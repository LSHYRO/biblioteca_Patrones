/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admin;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import logicaneg.LnUsuarios;
import modelo.Usuario;

/**
 *
 * @author yeste
 */
@Named(value = "adUsuario")
@SessionScoped
public class AdUsuario implements Serializable {

    @EJB
    private LnUsuarios lnUsuarios;

    //Entity Classes
    private Usuario user;
    private Usuario usuarioEncontrado;
    
    //Other attributes
    private String pagina;
    private String mensaje;
    
    //Otro bean
    @Inject
    private AdPrestamos adp;
    
    public void pasarVariable() {
        adp.setUsuarioEncontrado(usuarioEncontrado);
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Usuario getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public void setUsuarioEncontrado(Usuario usuarioEncontrado) {
        this.usuarioEncontrado = usuarioEncontrado;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Usuario> getUsuarios() {
        return lnUsuarios.findUsuarios();
    }

    public boolean existUsuario(Usuario uu) {
        for (Usuario u : getUsuarios()) {
            if (u.getUsuario().equalsIgnoreCase(uu.getUsuario())) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarContraseña(Usuario uu) {
        for (Usuario u : getUsuarios()) {
            if (u.getUsuario().equalsIgnoreCase(uu.getUsuario()) && u.getContrasenia().equals(uu.getContrasenia())) {
                usuarioEncontrado = u;
                return true;
            }
        }
        return false;
    }

    public void validarUsuario(FacesContext context, UIComponent toValidate, Object valor) {
        String msj = "";
        Usuario uu = new Usuario();
        uu.setUsuario((String) valor);
        ((UIInput) toValidate).setValid(false);
        if (!existUsuario(uu)) {
            msj = "Este usuario no se encuentra registrado";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else {
            ((UIInput) toValidate).setValid(true);
        }

    }

    /*public void validarContraseña(FacesContext context, UIComponent toValidate, Object valor) {
        String msj = "";
        Usuario uu = new Usuario();
        uu.setUsuario(user.getUsuario());
        uu.setContrasenia((int) valor);
        ((UIInput) toValidate).setValid(false);
        if (!buscarContraseña(uu)) {
            msj = "Contraseña incorrecta";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else {
            ((UIInput) toValidate).setValid(true);
        }
    }*/

    public void iniciarSesion() {
        if (!buscarContraseña(user)) {
            System.out.println("No Encontro");
            pagina = "IniciarSesion.xhtml";
            mensaje = "Contraseña incorrecta";
        } else {
            mensaje = "";
            pagina = "Prestamos";
            pasarVariable();
            
        }
    }

    /**
     * Creates a new instance of AdUsuario
     */
    public AdUsuario() {
        user = new Usuario();
        usuarioEncontrado = new Usuario();
    }

}
