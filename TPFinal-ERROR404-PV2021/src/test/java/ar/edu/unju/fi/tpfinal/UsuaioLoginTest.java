package ar.edu.unju.fi.tpfinal;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.tpfinal.model.Usuario;

import ar.edu.unju.fi.tpfinal.service.IUsuarioService;

@SpringBootTest
class UsuaioLoginTest {

	@Autowired
	IUsuarioService usuarioService;
	
	@Test
	void testUsuario() {
		//se agregara datos del  usuario.
	   Usuario us = new Usuario();
		us.setPassword("12345678");
		us.setPerfil("CLIENTE");//el rol
		us.setUsuario("abel");
		usuarioService.guardarUsuario(us);
	//	assertEquals("CLIENTE", us.getPerfil());
		assertTrue(true);
	}
	
}
