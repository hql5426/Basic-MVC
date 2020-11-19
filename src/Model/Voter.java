/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hayde
 */
@Entity
@Table(name = "VOTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voter.findAll", query = "SELECT v FROM Voter v")
    , @NamedQuery(name = "Voter.findById", query = "SELECT v FROM Voter v WHERE v.id = :id")
    , @NamedQuery(name = "Voter.findByFirstname", query = "SELECT v FROM Voter v WHERE v.firstname = :firstname")
    , @NamedQuery(name = "Voter.findByLastname", query = "SELECT v FROM Voter v WHERE v.lastname = :lastname")
    , @NamedQuery(name = "Voter.findByPoliticalparty", query = "SELECT v FROM Voter v WHERE v.politicalparty = :politicalparty")
    , @NamedQuery(name = "Voter.findByHasvoted", query = "SELECT v FROM Voter v WHERE v.hasvoted = :hasvoted")
    , @NamedQuery(name = "Voter.readPartyandHasVoted", query = "SELECT v FROM Voter v WHERE v.politicalparty = :politicalparty and v.hasvoted = :hasvoted")
     , @NamedQuery(name = "Voter.readfNameID", query = "SELECT v FROM Voter v WHERE v.firstname = :firstname and v.id = :id")
})
public class Voter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "POLITICALPARTY")
    private String politicalparty;
    @Basic(optional = false)
    @Column(name = "HASVOTED")
    private Boolean hasvoted;

    public Voter() {
    }

    public Voter(Integer id) {
        this.id = id;
    }

    public Voter(Integer id, Boolean hasvoted) {
        this.id = id;
        this.hasvoted = hasvoted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPoliticalparty() {
        return politicalparty;
    }

    public void setPoliticalparty(String politicalparty) {
        this.politicalparty = politicalparty;
    }

    public Boolean getHasvoted() {
        return hasvoted;
    }

    public void setHasvoted(Boolean hasvoted) {
        this.hasvoted = hasvoted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voter)) {
            return false;
        }
        Voter other = (Voter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Voter[ id=" + id + " ]";
    }
    
}
