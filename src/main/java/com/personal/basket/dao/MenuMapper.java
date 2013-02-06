package com.personal.basket.dao;

import java.util.List;
import com.personal.basket.model.MenuModelDTO;


public interface MenuMapper {

	List<MenuModelDTO> getMenus(String idTipoMenu);

	
} 