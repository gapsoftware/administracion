package org.gemesys.administracion.shell.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static javax.persistence.TemporalType.DATE;

/**
 * Created by gperezv on 07-02-18.
 */

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

// attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "*Ingrese un e-mail válido")
    @NotEmpty(message = "*Ingrese un e-mail")
    private String email;

    @Column(name = "password")
    @Length(min = 7, message = "*Su contraseña debe tener 7 caracteres como mínimo")
    @NotEmpty(message = "*Ingrese una contraseña")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Ingrese nombre")
    private String name;

    @Column(name = "lastname1")
    @NotEmpty(message = "*Ingrese su primer apellido")
    private String lastName1;

    @Column(name = "lastname2")
    private String lastName2;


    @Column(name = "creationdate")
    private Date CreationDate;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "enterprise")
    private List<Enterprise> enterprise;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @OrderBy("name")
    private SortedSet<Role> roles;

// getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public SortedSet<Role> getRoles() {
        return roles;
    }

    public void setRoles(SortedSet<Role> roles) {
        this.roles = roles;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Enterprise> getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(List<Enterprise> enterprise) {
        this.enterprise = enterprise;
    }

}