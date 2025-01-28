package com.samael.prueba.services;

import com.samael.prueba.models.Mensaje;
import com.samael.prueba.repositories.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class MensajeService {


    @Autowired
    private MensajeRepository mensajeRepository;


    // Guardar un mensaje
    public Mensaje guardarMensaje(Mensaje mensaje) {
        if (mensaje.getImageUrl() == null) {
            mensaje.setImageUrl(""); // Asignar un valor por defecto si es necesario
        }
        return mensajeRepository.save(mensaje);
    }


    // Obtener todos los mensajes
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeRepository.findAll();
    }


    // Obtener mensajes de un usuario específico
    public List<Mensaje> obtenerMensajesPorUsuario(String username) {
        return mensajeRepository.findByUsername(username);
    }
}

