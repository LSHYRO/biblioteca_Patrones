/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lshyro
 */
@Entity
@Table(name = "PRESTAMOS_LIBROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamosLibros.findAll", query = "SELECT p FROM PrestamosLibros p"),
    @NamedQuery(name = "PrestamosLibros.findByIdprestamoLibro", query = "SELECT p FROM PrestamosLibros p WHERE p.idprestamoLibro = :idprestamoLibro")})
public class PrestamosLibros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRESTAMO_LIBRO")
    private Integer idprestamoLibro;
    @JoinColumn(name = "IDLIBRO", referencedColumnName = "IDLIBRO")
    @ManyToOne
    private Libros idlibro;
    @JoinColumn(name = "IDPRESTAMO", referencedColumnName = "IDPRESTAMO")
    @ManyToOne
    private Prestamos idprestamo;

    public PrestamosLibros() {
    }

    public PrestamosLibros(Integer idprestamoLibro) {
        this.idprestamoLibro = idprestamoLibro;
    }

    public Integer getIdprestamoLibro() {
        return idprestamoLibro;
    }

    public void setIdprestamoLibro(Integer idprestamoLibro) {
        this.idprestamoLibro = idprestamoLibro;
    }

    public Libros getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(Libros idlibro) {
        this.idlibro = idlibro;
    }

    public Prestamos getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(Prestamos idprestamo) {
        this.idprestamo = idprestamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprestamoLibro != null ? idprestamoLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamosLibros)) {
            return false;
        }
        PrestamosLibros other = (PrestamosLibros) object;
        if ((this.idprestamoLibro == null && other.idprestamoLibro != null) || (this.idprestamoLibro != null && !this.idprestamoLibro.equals(other.idprestamoLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PrestamosLibros[ idprestamoLibro=" + idprestamoLibro + " ]";
    }
    
}
