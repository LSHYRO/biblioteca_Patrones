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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yeste
 */
@Entity
@Table(name = "PRESTAMOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamos.findAll", query = "SELECT p FROM Prestamos p"),
    @NamedQuery(name = "Prestamos.findByIdprestamo", query = "SELECT p FROM Prestamos p WHERE p.idprestamo = :idprestamo"),
    @NamedQuery(name = "Prestamos.findByFechaInicio", query = "SELECT p FROM Prestamos p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Prestamos.findByFechaFinal", query = "SELECT p FROM Prestamos p WHERE p.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Prestamos.findByTotalLibro", query = "SELECT p FROM Prestamos p WHERE p.totalLibro = :totalLibro")})
public class Prestamos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRESTAMO")
    private Integer idprestamo;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "TOTAL_LIBRO")
    private Integer totalLibro;
    @JoinColumn(name = "NOCONTROL", referencedColumnName = "NOCONTROL")
    @ManyToOne
    private Estudiante nocontrol;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;
    @OneToMany(mappedBy = "idprestamo")
    private List<PrestamosLibros> prestamosLibrosList;

    public Prestamos() {
    }

    public Prestamos(Integer idprestamo) {
        this.idprestamo = idprestamo;
    }

    public Integer getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(Integer idprestamo) {
        this.idprestamo = idprestamo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getTotalLibro() {
        return totalLibro;
    }

    public void setTotalLibro(Integer totalLibro) {
        this.totalLibro = totalLibro;
    }

    public Estudiante getNocontrol() {
        return nocontrol;
    }

    public void setNocontrol(Estudiante nocontrol) {
        this.nocontrol = nocontrol;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
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
        hash += (idprestamo != null ? idprestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamos)) {
            return false;
        }
        Prestamos other = (Prestamos) object;
        if ((this.idprestamo == null && other.idprestamo != null) || (this.idprestamo != null && !this.idprestamo.equals(other.idprestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Prestamos[ idprestamo=" + idprestamo + " ]";
    }
    
}
