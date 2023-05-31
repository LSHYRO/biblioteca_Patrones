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
@Table(name = "ESTUDIANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByNocontrol", query = "SELECT e FROM Estudiante e WHERE e.nocontrol = :nocontrol"),
    @NamedQuery(name = "Estudiante.findByApellidosEst", query = "SELECT e FROM Estudiante e WHERE e.apellidosEst = :apellidosEst"),
    @NamedQuery(name = "Estudiante.findByNombreEst", query = "SELECT e FROM Estudiante e WHERE e.nombreEst = :nombreEst"),
    @NamedQuery(name = "Estudiante.findByGrado", query = "SELECT e FROM Estudiante e WHERE e.grado = :grado"),
    @NamedQuery(name = "Estudiante.findByGrupo", query = "SELECT e FROM Estudiante e WHERE e.grupo = :grupo"),
    @NamedQuery(name = "Estudiante.findByContacto", query = "SELECT e FROM Estudiante e WHERE e.contacto = :contacto")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NOCONTROL")
    private Integer nocontrol;
    @Size(max = 50)
    @Column(name = "APELLIDOS_EST")
    private String apellidosEst;
    @Size(max = 30)
    @Column(name = "NOMBRE_EST")
    private String nombreEst;
    @Column(name = "GRADO")
    private Integer grado;
    @Column(name = "GRUPO")
    private Character grupo;
    @Column(name = "CONTACTO")
    private Integer contacto;
    @OneToMany(mappedBy = "nocontrol")
    private List<Prestamos> prestamosList;

    public Estudiante() {
    }

    public Estudiante(Integer nocontrol) {
        this.nocontrol = nocontrol;
    }

    public Integer getNocontrol() {
        return nocontrol;
    }

    public void setNocontrol(Integer nocontrol) {
        this.nocontrol = nocontrol;
    }

    public String getApellidosEst() {
        return apellidosEst;
    }

    public void setApellidosEst(String apellidosEst) {
        this.apellidosEst = apellidosEst;
    }

    public String getNombreEst() {
        return nombreEst;
    }

    public void setNombreEst(String nombreEst) {
        this.nombreEst = nombreEst;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    public Integer getContacto() {
        return contacto;
    }

    public void setContacto(Integer contacto) {
        this.contacto = contacto;
    }

    @XmlTransient
    public List<Prestamos> getPrestamosList() {
        return prestamosList;
    }

    public void setPrestamosList(List<Prestamos> prestamosList) {
        this.prestamosList = prestamosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nocontrol != null ? nocontrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.nocontrol == null && other.nocontrol != null) || (this.nocontrol != null && !this.nocontrol.equals(other.nocontrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Estudiante[ nocontrol=" + nocontrol + " ]";
    }
    
}
