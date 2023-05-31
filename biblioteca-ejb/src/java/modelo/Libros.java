/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lshyro
 */
@Entity
@Table(name = "LIBROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libros.findAll", query = "SELECT l FROM Libros l"),
    @NamedQuery(name = "Libros.findByIdlibro", query = "SELECT l FROM Libros l WHERE l.idlibro = :idlibro"),
    @NamedQuery(name = "Libros.findByTitulo", query = "SELECT l FROM Libros l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Libros.findByAutor", query = "SELECT l FROM Libros l WHERE l.autor = :autor"),
    @NamedQuery(name = "Libros.findByEditorial", query = "SELECT l FROM Libros l WHERE l.editorial = :editorial"),
    @NamedQuery(name = "Libros.findByFechaPublicacion", query = "SELECT l FROM Libros l WHERE l.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Libros.findByCantidad", query = "SELECT l FROM Libros l WHERE l.cantidad = :cantidad")})
public class Libros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLIBRO")
    private Integer idlibro;
    @Size(max = 50)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 60)
    @Column(name = "AUTOR")
    private String autor;
    @Size(max = 40)
    @Column(name = "EDITORIAL")
    private String editorial;
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @JoinColumn(name = "IDEDICION", referencedColumnName = "IDEDICION")
    @ManyToOne
    private Edicion idedicion;
    @OneToMany(mappedBy = "idlibro")
    private List<PrestamosLibros> prestamosLibrosList;

    public Libros() {
    }

    public Libros(Integer idlibro) {
        this.idlibro = idlibro;
    }

    public Integer getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(Integer idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Edicion getIdedicion() {
        return idedicion;
    }

    public void setIdedicion(Edicion idedicion) {
        this.idedicion = idedicion;
    }

    @XmlTransient
    public List<PrestamosLibros> getPrestamosLibrosList() {
        return prestamosLibrosList;
    }

    public void setPrestamosLibrosList(List<PrestamosLibros> prestamosLibrosList) {
        this.prestamosLibrosList = prestamosLibrosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlibro != null ? idlibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libros)) {
            return false;
        }
        Libros other = (Libros) object;
        if ((this.idlibro == null && other.idlibro != null) || (this.idlibro != null && !this.idlibro.equals(other.idlibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Libros[ idlibro=" + idlibro + " ]";
    }
    
}
