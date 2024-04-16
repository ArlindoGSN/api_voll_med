package com.voll.med.api.domain.usuario;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutencicacaoService implements UserDetailsService {


    private final UsuarioRepository repository;

    public AutencicacaoService(UsuarioRepository repository1) {
        this.repository = repository1;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
