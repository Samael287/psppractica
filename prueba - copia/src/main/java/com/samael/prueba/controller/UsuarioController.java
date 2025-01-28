package com.samael.prueba.controller;

import com.samael.prueba.models.Usuario;
import com.samael.prueba.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para registrar un usuario
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        System.out.println("Recibiendo solicitud para registrar el usuario: " + usuario.getEmail());

        List<Usuario> usuarios = usuarioService.buscarPorEmail(usuario.getEmail());

        // Validación para verificar si el email ya está registrado
        if (!usuarios.isEmpty()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }

        // Si el correo no está registrado, registrar el nuevo usuario
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        System.out.println("Nuevo usuario registrado: " + nuevoUsuario.getEmail());

        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {
            // Verificar las credenciales y devolver el usuario autenticado
            Usuario usuario = usuarioService.login2(email, password);
            return ResponseEntity.ok(usuario);  // Devuelve el usuario autenticado
        } catch (RuntimeException e) {
            // Si las credenciales son incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Usuario>> obtenerPorEmail(@PathVariable String email) {
        List<Usuario> usuarios = usuarioService.buscarPorEmail(email);
        return usuarios.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(usuarios);
    }

    // Obtener un usuario por username
    @GetMapping("/username/{username}")
    public ResponseEntity<List<Usuario>> obtenerPorUsername(@PathVariable String username) {
        List<Usuario> usuarios = usuarioService.buscarPorUsuario(username);
        return usuarios.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(usuarios);
    }

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestParam("texto") String texto,
                                           @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {
        if (imagen != null) {
            String ruta = guardarImagen(imagen); // Implementa lógica para guardar
            return ResponseEntity.ok(Map.of("texto", texto, "imagen", ruta));
        }
        return ResponseEntity.ok(Map.of("texto", texto));
    }

    private String guardarImagen(MultipartFile file) throws IOException {
        // Guarda la imagen y devuelve la ruta o URL
        String nombreArchivo = file.getOriginalFilename();
        Path ruta = Paths.get("uploads/" + nombreArchivo);
        Files.write(ruta, file.getBytes());
        return "/uploads/" + nombreArchivo;
    }
}
