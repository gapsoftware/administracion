package org.gemesys.administracion.shell.service;

import lombok.Builder;
import org.gemesys.administracion.shell.dto.MenuDTO;
import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gperezv on 10-04-18.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(int id) {
        return menuRepository.findOne(id);
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        menuRepository.save(menu);

    }

    @Override
    public void delete(int id) {
        menuRepository.delete(id);
    }

    @Override
    @Builder
    public Menu buildMenu(MenuDTO menuDTO) {
        return new Menu(menuDTO.getNombre(), menuDTO.getActivo(),menuDTO.getOrden());
    }
}
