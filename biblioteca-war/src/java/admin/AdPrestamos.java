/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admin;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import logicaneg.LnEstudiante;
import logicaneg.LnLibros;
import logicaneg.LnPrestamos;
import logicaneg.LnPrestamosLibros;
import logicaneg.LnUsuarios;
import modelo.Estudiante;
import modelo.Libros;
import modelo.Prestamos;
import modelo.PrestamosLibros;
import modelo.Usuario;

/**
 *
 * @author yeste
 */
@Named(value = "adPrestamos")
@SessionScoped
public class AdPrestamos implements Serializable {

    @EJB
    private LnUsuarios lnUsuarios;

    @EJB
    private LnLibros lnLibros;

    @EJB
    private LnEstudiante lnEstudiante;

    @EJB
    private LnPrestamosLibros lnPrestamosLibros;

    @EJB
    private LnPrestamos lnPrestamos;

    //EntityClasses from DataBase
    private Prestamos prestamo;
    private PrestamosLibros preslib;
    private Libros libro;
    private Usuario usuarioEncontrado;
    private Estudiante estudiante;

    //Other attributes
    private List<PrestamosLibros> carrito;
    private String mensaje;
    private String msj;
    private int IdEliminar;
    private String pagina;
    private Date fechainicial;

    public Prestamos getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamos prestamo) {
        this.prestamo = prestamo;
    }

    public PrestamosLibros getPreslib() {
        return preslib;
    }

    public void setPreslib(PrestamosLibros preslib) {
        this.preslib = preslib;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Usuario getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public void setUsuarioEncontrado(Usuario usuarioEncontrado) {
        this.usuarioEncontrado = usuarioEncontrado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public int getIdEliminar() {
        return IdEliminar;
    }

    public void setIdEliminar(int IdEliminar) {
        this.IdEliminar = IdEliminar;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public List<Libros> getLibros() {
        List<Libros> librosFound = new ArrayList();
        for (Libros lib : lnLibros.findLibros()) {
            if (lib.getCantidad() > 0) {
                librosFound.add(lib);
            }
        }
        return librosFound;
    }

    public void añadirPrestamoLibro() {
        crearPrestamo();
        System.out.println("Usuario: " + usuarioEncontrado.getUsuario() + "Password: " + usuarioEncontrado.getContrasenia());
        System.out.println("Prestamo: " + prestamo.getFechaInicio() + " " + prestamo.getFechaFinal() + " " + prestamo.getIdprestamo() + " " + prestamo.getIdusuario() + " " + prestamo.getNocontrol() + " " + prestamo.getTotalLibro());

    }

    public void removerPrestamo() {

    }

    public List<PrestamosLibros> getCarrito() {
        return carrito;
    }

    public Libros buscarLibro() {
        Libros found = null;
        for (Libros l : getLibros()) {
            if (libro.getIdlibro() == l.getIdlibro()) {
                found = l;
            }
        }
        return found;
    }

    public boolean existLibroCarrito() {
        if (!carrito.isEmpty()) {
            for (PrestamosLibros pl : carrito) {
                if (pl.getIdlibro().getIdlibro() == libro.getIdlibro()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existEstudiante(Estudiante e) {
        for (Estudiante est : lnEstudiante.findEstudiantes()) {
            if (e.getNocontrol() == est.getNocontrol()) {
                return true;
            }
        }
        return false;
    }

    public boolean existEstudiantePrestamo(Estudiante e) {
        if (existEstudiante(e)) {
            for (PrestamosLibros pl : lnPrestamosLibros.findPrestamosLibros()) {
                if (e.getNocontrol() == pl.getIdprestamo().getNocontrol().getNocontrol()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void changePage() {
        if (!carrito.isEmpty()) {
            pagina = "comprobacionPrestamo";
            mensaje = "";
        } else {
            pagina = "Prestamos";
            msj = "Favor de agregar minimo un libro a la lista";
        }
    }

    public void añadirLista() {
        msj = "";
        if (buscarLibro() == null) {
            mensaje = "!Error al buscar el libro!";
        } else if (carrito.size() > 2) {
            mensaje = "El limite es de 3 prestamos por estudiante*";
        } else if (existLibroCarrito()) {
            mensaje = "Ya añadio ese libro*";
        } else {
            preslib.setIdlibro(buscarLibro());
            carrito.add(preslib);
            preslib = new PrestamosLibros();
            mensaje = "Se agrego el producto";
            msj = "";
        }
    }

    public void eliminarLista() {
        if (carrito.size() > 0) {
            for (int i = 0; i < carrito.size(); i++) {
                if (carrito.get(i).getIdlibro().getIdlibro() == IdEliminar) {
                    carrito.remove(i);
                    msj = "Se elimino un producto de la lista";
                    mensaje = "";
                }
            }
        } else {
            msj = "No hay ningun libro en la lista";
            mensaje = "";
        }
    }

    public void crearPrestamo() {
        System.out.println("Prestamo: " + prestamo.getFechaInicio() + " " + prestamo.getFechaFinal() + " " + prestamo.getIdprestamo() + " " + prestamo.getIdusuario() + " " + prestamo.getNocontrol() + " " + prestamo.getTotalLibro());
        Date fecha = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        prestamo.setFechaInicio(fecha);
        System.out.println("Prestamo: " + prestamo.getFechaInicio() + " " + prestamo.getFechaFinal() + " " + prestamo.getIdprestamo() + " " + prestamo.getIdusuario() + " " + prestamo.getNocontrol() + " " + prestamo.getTotalLibro());
        prestamo.setTotalLibro(carrito.size());
        System.out.println("Prestamo: " + prestamo.getFechaInicio() + " " + prestamo.getFechaFinal() + " " + prestamo.getIdprestamo() + " " + prestamo.getIdusuario() + " " + prestamo.getNocontrol() + " " + prestamo.getTotalLibro());
        prestamo.setNocontrol(estudiante);
        System.out.println("Prestamo: " + prestamo.getFechaInicio() + " " + prestamo.getFechaFinal() + " " + prestamo.getIdprestamo() + " " + prestamo.getIdusuario() + " " + prestamo.getNocontrol() + " " + prestamo.getTotalLibro());
        prestamo.setIdusuario(usuarioEncontrado);
        System.out.println("Prestamo: " + prestamo.getFechaInicio() + " " + prestamo.getFechaFinal() + " " + prestamo.getIdprestamo() + " " + prestamo.getIdusuario() + " " + prestamo.getNocontrol() + " " + prestamo.getTotalLibro());

    }
    
    public void validar_fecha_inicial(FacesContext context, UIComponent toValidate, Object valor) {
        String msj = "";
        ((UIInput) toValidate).setValid(false);
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaingresada = ((Date) valor).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (!fechaingresada.isEqual(fechaActual)) {
            msj = "La fecha debe de ser la fecha de hoy";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else {
            ((UIInput) toValidate).setValid(true);
        }

    }

    public void validar_fecha(FacesContext context, UIComponent toValidate, Object valor) {
        String msj = "";
        ((UIInput) toValidate).setValid(false);
        LocalDate fechaActual = LocalDate.now().plusDays(1);
        LocalDate fechalimite = LocalDate.now().plusDays(7);
        LocalDate fechaingresada = ((Date) valor).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (fechaingresada.isBefore(fechaActual)) {
            msj = "La fecha debe de ser posterior a la fecha de hoy";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else if (fechaingresada.isAfter(fechalimite)) {
            msj = "La fecha no debe pasar de 7 dias depues de la fecha de entrega";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else {
            ((UIInput) toValidate).setValid(true);
        }

    }

    public void validar_estudiante(FacesContext context, UIComponent toValidate, Object valor) {
        String msj = "";
        Estudiante e = new Estudiante();
        e.setNocontrol((int) valor);
        ((UIInput) toValidate).setValid(false);
        if (!existEstudiante(e)) {
            msj = "El estudiante no existe";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else if (existEstudiantePrestamo(e)) {
            msj = "El estudiante ya tiene un prestamo registrado \nPara un nuevo prestamo hacer la devolución del pendiente";
            //Forma de crear mensajes
            FacesMessage message = new FacesMessage(msj);
            context.addMessage(toValidate.getClientId(context), message);
        } else {
            ((UIInput) toValidate).setValid(true);
        }

    }

    public void clear() {
        prestamo = new Prestamos();
        preslib = new PrestamosLibros();
        estudiante = new Estudiante();
        libro = new Libros();
        carrito = new ArrayList<PrestamosLibros>();
    }

    /**
     * Creates a new instance of AdPrestamos
     */
    public AdPrestamos() {
        prestamo = new Prestamos();
        preslib = new PrestamosLibros();
        usuarioEncontrado = new Usuario();
        estudiante = new Estudiante();
        libro = new Libros();
        carrito = new ArrayList<PrestamosLibros>();
        fechainicial = new Date();
    }

}
