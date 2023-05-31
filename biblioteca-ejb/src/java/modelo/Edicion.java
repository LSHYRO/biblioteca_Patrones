/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lshyro
 */
@Entity
@Table(name = "EDICION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edicion.findAll", query = "SELECT e FROM Edicion e"),
    @NamedQuery(name = "Edicion.findByIdedicion", query = "SELECT e FROM Edicion e WHERE e.idedicion = :idedicion"),
    @NamedQuery(name = "Edicion.findByNumeroEdicion", query = "SELECT e FROM Edicion e WHERE e.numeroEdicion = :numeroEdicion")})
public class Edicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEDICION")
    private Integer idedicion;
    @Size(max = 20)
    @Column(name = "NUMERO_EDICION")
    private String numeroEdicion;
    @OneToMany(mappedBy = "idedicion")
    private List<Libros> librosList;

    public Edicion() {
    }

    public Edicion(Integer idedicion) {
        this.idedicion = idedicion;
    }

    public Integer getIdedicion() {
        return idedicion;
    }

    public void setIdedicion(Integer idedicion) {
        this.idedicion = idedicion;
    }

    public String getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(String numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @XmlTransient
    public List<Libros> getLibrosList() {
        return librosList;
    }

    public void setLibrosList(List<Libros> librosList) {
        this.librosList = librosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idedicion != null ? idedicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edicion)) {
            return false;
        }
        Edicion other = (Edicion) object;
        if ((this.idedicion == null && other.idedicion != null) || (this.idedicion != null && !this.idedicion.equals(other.idedicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Edicion[ idedicion=" + idedicion + " ]";
    }
    
}
