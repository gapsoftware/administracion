package org.gemesys.administracion.shell.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by gperezv on 10-04-18.
 */

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private int active;

    @Column(name = "sortorder")
    private int sortorder;

    @Column(name = "url")
    private String url;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "menu_role",
            joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

// constructors

    public Menu() {
    }

    public Menu(String name, int active, int sortorder, String url) {
        this.name = name;
        this.active = active;
        this.sortorder = sortorder;
        this.url = url;
    }

//getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

