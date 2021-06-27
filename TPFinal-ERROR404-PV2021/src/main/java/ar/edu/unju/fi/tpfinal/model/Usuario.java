package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.stereotype.Component;

@Entity
@Table(name="usuarios")
@Component
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "TIPO")
	private String perfil;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	






	public Usuario(Long id, String usuario, String password, String perfil) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.perfil = perfil;
	}









	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", password=" + password + ", perfil=" + perfil + "]";
	}
	
	
}