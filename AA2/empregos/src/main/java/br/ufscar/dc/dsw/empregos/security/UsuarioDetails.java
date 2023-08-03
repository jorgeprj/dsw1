package br.ufscar.dc.dsw.empregos.security;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.empregos.domain.Usuario;
 
@SuppressWarnings("serial")
public class UsuarioDetails implements UserDetails {
 
    private Usuario usuario;
     
    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getPapel());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getSenha() {
        return usuario.getSenha();
    }
 
    @Override
    public String getNome() {
        return usuario.getNome();
    }
 
    @Override
    public String getEmail(){
        return usuario.getEmail();
    }

    @Override
    public String getPapel(){
        return usuario.getPapel();
    }

	public Usuario getUsuario() {
		return usuario;
	}
    
}