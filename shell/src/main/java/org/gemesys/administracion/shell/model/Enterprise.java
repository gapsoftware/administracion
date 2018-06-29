package org.gemesys.administracion.shell.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gperezv on 29-06-18.
 */

@Entity
@Table(name="enterprise")
public class Enterprise {

// attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enterprise_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "legalname")
    private String legalName;

    @Column(name = "adress")
    private String adressName;

    @Column(name = "taxesid")
    private String impuestosId;

    @Column(name = "modulekey")
    private String modulekey;

    @Column(name = "owner")
    private String owner;

    @Column(name = "accountant")
    private String accountant;

    @Column(name = "attorney")
    private String attorney;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "activationdate")
    private Date activationDate;

    @Column(name = "desactivaciondate")
    private Date desactivationDate;

    @Column(name = "active")
    private int active;

// constructors

    public Enterprise() {}

// getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getAdressName() {
        return adressName;
    }

    public void setAdressName(String adressName) {
        this.adressName = adressName;
    }

    public String getImpuestosId() {
        return impuestosId;
    }

    public void setImpuestosId(String impuestosId) {
        this.impuestosId = impuestosId;
    }

    public String getModulekey() {
        return modulekey;
    }

    public void setModulekey(String modulekey) {
        this.modulekey = modulekey;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountant() {
        return accountant;
    }

    public void setAccountant(String accountant) {
        this.accountant = accountant;
    }

    public String getAttorney() {
        return attorney;
    }

    public void setAttorney(String attorney) {
        this.attorney = attorney;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getDesactivationDate() {
        return desactivationDate;
    }

    public void setDesactivationDate(Date desactivationDate) {
        this.desactivationDate = desactivationDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
