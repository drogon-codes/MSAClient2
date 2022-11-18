/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author c computer
 */
@Entity
@Table(name = "garments")
@NamedQueries({
    @NamedQuery(name = "Garments.findAll", query = "SELECT g FROM Garments g"),
    @NamedQuery(name = "Garments.findByGarmentid", query = "SELECT g FROM Garments g WHERE g.garmentid = :garmentid"),
    @NamedQuery(name = "Garments.findByPrice", query = "SELECT g FROM Garments g WHERE g.price = :price"),
    @NamedQuery(name = "Garments.findByCatId", query = "SELECT g FROM Garments g WHERE g.catid.catid = :catid"),
    @NamedQuery(name = "Garments.findByStock", query = "SELECT g FROM Garments g WHERE g.stock = :stock")})
public class Garments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "garmentid")
    private Integer garmentid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @JoinColumn(name = "catid", referencedColumnName = "catid")
    @ManyToOne(optional = false)
    private Category catid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "garmentId")
    private Collection<TblOrder> tblOrderCollection;

    public Garments() {
    }

    public Garments(Integer garmentid) {
        this.garmentid = garmentid;
    }

    public Garments(Integer garmentid, int price, int stock) {
        this.garmentid = garmentid;
        this.price = price;
        this.stock = stock;
    }

    public Integer getGarmentid() {
        return garmentid;
    }

    public void setGarmentid(Integer garmentid) {
        this.garmentid = garmentid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCatid() {
        return catid;
    }

    public void setCatid(Category catid) {
        this.catid = catid;
    }

    @JsonbTransient
    public Collection<TblOrder> getTblOrderCollection() {
        return tblOrderCollection;
    }

    public void setTblOrderCollection(Collection<TblOrder> tblOrderCollection) {
        this.tblOrderCollection = tblOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (garmentid != null ? garmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garments)) {
            return false;
        }
        Garments other = (Garments) object;
        if ((this.garmentid == null && other.garmentid != null) || (this.garmentid != null && !this.garmentid.equals(other.garmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Garments[ garmentid=" + garmentid + " ]";
    }
    
}
