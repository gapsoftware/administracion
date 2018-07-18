package org.gemesys.administracion.shell.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.SortedSet;

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

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private int active;

    @Column(name = "sortorder")
    private int sortOrder;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "module_menu",
            joinColumns = @JoinColumn(name = "module_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    @OrderBy("menu_id")
    private SortedSet<Menu> menus;

//constructors

    public Module(){}

    public Module(String name, int active, int order){
        this.name=name;
        this.active=active;
        this.sortOrder=order;
    }

    public Module(String name, int active, int order, SortedSet<Menu> menus){
        this.name=name;
        this.active=active;
        this.sortOrder=order;
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

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortorder) {
        this.sortOrder = sortorder;
    }

    public SortedSet<Menu> getMenus() {
        return menus;
    }

    public void setMenus(SortedSet<Menu> menus) {
        this.menus = menus;
    }
}

