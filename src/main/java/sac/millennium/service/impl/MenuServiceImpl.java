package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.IMenuDAO;
import sac.millennium.model.Menu;
import sac.millennium.model.Perfil;
import sac.millennium.service.IMenuService;

public class MenuServiceImpl implements IMenuService {

	IMenuDAO dao;

	public MenuServiceImpl(IMenuDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Menu> findMenuByPerfil(Perfil perfil) {
		return dao.findMenuByPerfil(perfil);
	}

	@Override
	public List<Menu> listaHermanos(Menu menu) {
		return dao.listaHermanos(menu);
	}

	@Override
	public List<Menu> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(Menu obj) {
		String id = generarId();
		String contenedor = obj.getContenedor();
		obj.setId(id);
		obj.setContenedor(obj.getContenedor().equals("0") ? id : contenedor);
		// obj.setFormularioAsociado(obj.getFormularioAsociado() == null ? "#" :
		// obj.getFormularioAsociado());
		obj.setOrdenAparicion(generarOrden(obj));
		return dao.create(obj);
	}

	@Override
	public int update(Menu obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public Menu findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		return dao.generarId();
	}

	@Override
	public List<Menu> listaContenedoresHermanos() {
		return dao.listaContenedoresHermanos();
	}

	@Override
	public List<Menu> listaContenedoresHermanos(Menu menu) {
		return dao.listaContenedoresHermanos(menu);
	}

	@Override
	public List<Menu> listaItemsHermanos(Menu menu) {
		return dao.listaItemsHermanos(menu);
	}

	private int generarOrden(Menu menu) {
		int orden = 0;
		List<Menu> padres = listaContenedoresHermanos();
		List<Menu> hijos = listaHermanos(menu);
		if (menu.getContenedor() == menu.getId()) {
			if (menu.getOrdenAparicion() == 0) {
				orden = padres.isEmpty() ? 10 : padres.get(0).getOrdenAparicion() - 1;
			} else if (menu.getOrdenAparicion() == 1) {
				orden = padres.isEmpty() ? 10 : padres.get(padres.size() - 1).getOrdenAparicion() + 10;
			} else {
				orden = menu.getOrdenAparicion() + 1;
			}
		} else {
			if (menu.getOrdenAparicion() == 0) {
				orden = hijos.isEmpty() ? 10 : hijos.get(0).getOrdenAparicion() - 1;
			} else if (menu.getOrdenAparicion() == 1) {
				orden = hijos.isEmpty() ? 10 : hijos.get(hijos.size() - 1).getOrdenAparicion() + 10;
			} else {
				orden = menu.getOrdenAparicion() + 1;
			}
		}
		return orden;
	}

}
