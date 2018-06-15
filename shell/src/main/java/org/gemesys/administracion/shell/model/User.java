package org.gemesys.administracion.shell.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.DATE;

/**
 * Created by gperezv on 07-02-18.
 */

@Entity
@Table(name = "user")
public class User {

// attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email")
    @Email(message = "*Ingrese un e-mail válido")
    @NotEmpty(message = "*Ingrese un e-mail")
    private String email;

    @Column(name = "user_password")
    @Length(min = 7, message = "*Su contraseña debe tener 7 caracteres como mínimo")
    @NotEmpty(message = "*Ingrese una contraseña")
    private String password;

    @Column(name = "user_name")
    @NotEmpty(message = "*Ingrese nombre")
    private String name;

    @Column(name = "user_last_name1")
    @NotEmpty(message = "*Ingrese su primer apellido")
    private String lastName1;

    @Column(name = "user_last_name2")
    private String lastName2;


   /* @Column(name = "user_creation_date")
    @NotEmpty(message = "*Fecha de Creación")
    private Date CreationDate;


    @Column(name = "user_birth_date")
    @NotEmpty(message = "*Ingrese su fecha de nacimiento")
    private Date birthDate;

    @Column(name = "user_gender")
    @NotEmpty(message = "*Hombre o Mujer")
    private String gender;*/

    @Column(name = "user_active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}