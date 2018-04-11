package org.gemesys.administracion.shell.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by gperezv on 10-04-18.
 */

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "module_id")
    private int id;

    @Column(name = "module_name")
    private String name;

    @Column(name = "module_active")
    private int active;

    @Column(name = "module_order")
    private int order;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "module_menu",
            joinColumns = @JoinColumn(name = "module_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Set<Menu> menus;

//constructors

    public Module(){}

    public Module(String name, int active, int order){
        this.name=name;
        this.active=active;
        this.order=order;
    }

    public Module(String name, int active, int order, Set<Menu> menus){
        this.name=name;
        this.active=active;
        this.order=order;
        this.menus=menus;
    }

// getters and setters

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}

