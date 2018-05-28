package sac.millennium.service;

import sac.millennium.model.Usuario;
import sac.millennium.util.IGenericCRUD;

public interface IUsuarioService extends IGenericCRUD<Usuario, String> {

	Usuario iniciarSesion(Usuario obj);
}
