package com.samael.prueba.services;

import com.samael.prueba.models.Usuario;
import com.samael.prueba.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar un usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar por email
    public List<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Buscar por username
    public List<Usuario> buscarPorUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Verificar las credenciales de login
    public boolean verificarCredenciales(String email, String password) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(email);

        // Verifica si la lista no está vacía y si las contraseñas coinciden
        if (!usuarios.isEmpty()) {
            Usuario usuario = usuarios.get(0);  // Asumimos que solo habrá un usuario con ese email
            return usuario.getPassword().equals(password);  // Comparar la contraseña
        }
        return false;
    }

    public Usuario login2(String email, String password) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(email);
        if (!usuarios.isEmpty()) {
            Usuario usuario = usuarios.get(0);
            if (usuario.getPassword().equals(password)) {
                return usuario;  // Credenciales válidas
            }
        }
        throw new RuntimeException("Credenciales incorrectas");
    }

    public Usuario login(String email, String password) {
        if (verificarCredenciales(email, password)) {
            // Si las credenciales son correctas, devolver el usuario
            List<Usuario> usuarios = usuarioRepository.findByEmail(email);
            return usuarios.get(0);  // Asumimos que solo habrá un usuario con ese email
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }

    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar si el correo electrónico ya está registrado
        List<Usuario> usuariosPorEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (!usuariosPorEmail.isEmpty()) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        // Verificar si el nombre de usuario ya está registrado
        List<Usuario> usuariosPorUsername = usuarioRepository.findByUsername(usuario.getUsername());
        if (!usuariosPorUsername.isEmpty()) {
            throw new RuntimeException("El nombre de usuario ya está registrado.");
        }

        // Si no hay duplicados, guardamos el nuevo usuario
        return usuarioRepository.save(usuario);
    }
}
