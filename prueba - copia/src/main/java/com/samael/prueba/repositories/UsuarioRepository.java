package com.samael.prueba.repositories;

import com.samael.prueba.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findByEmail(String email);
    List<Usuario> findByUsername(String username);
}
