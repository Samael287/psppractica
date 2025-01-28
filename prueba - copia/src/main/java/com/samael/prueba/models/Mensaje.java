package com.samael.prueba.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "mensajes") // Indica el nombre de la colección(tabla) en MongoDB, (como @Entity en hibernate)
public class Mensaje {


    @Id
    private String id; // Identificador único del mensaje
    private String autor; // Nombre del usuario o autor
    private String username; // Identificador único del usuario
    private String contenido; // Texto del mensaje
    private String color; // Color del texto
    private String imageUrl; // Nuevo campo
    private LocalDateTime fechaEnvio; // Fecha y hora del mensaje


    // Constructor vacío
    public Mensaje() {}


    // Constructor completo
    public Mensaje(String autor, String username, String contenido, String color,  String imageUrl, LocalDateTime fechaEnvio) {
        this.autor = autor;
        this.username = username;
        this.contenido = contenido;
        this.color = color;
        this.imageUrl = imageUrl;
        this.fechaEnvio = fechaEnvio;
    }


    // Getters y Setters
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getAutor() {
        return autor;
    }


    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getContenido() {
        return contenido;
    }


    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }


    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}