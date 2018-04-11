package org.gemesys.administracion.shell.dto;

import lombok.Data;
import org.gemesys.administracion.shell.model.Menu;

import java.util.List;
import java.util.Set;

/**
 * Created by gperezv on 10-04-18.
 */

public class ModuleDTO {

    private int id;
    private String nombre;
    private int orden;
    private int activo;
    private Set<Menu> menus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
