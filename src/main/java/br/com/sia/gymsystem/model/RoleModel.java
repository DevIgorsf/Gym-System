package br.com.sia.gymsystem.model;

import br.com.sia.gymsystem.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RoleModel implements GrantedAuthority, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private RoleName roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return roleName.toString();
	}

	public void setNome(RoleName nome) {
		this.roleName = nome;
	}

	@Override
	public String getAuthority() {
		return roleName.toString();
	}
	
}
