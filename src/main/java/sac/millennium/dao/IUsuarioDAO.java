package sac.millennium.dao;

import sac.millennium.model.Usuario;
import sac.millennium.util.IGenericCRUD;

public interface IUsuarioDAO extends IGenericCRUD<Usuario, String> {

	Usuario iniciarSesion(Usuario obj);
}
