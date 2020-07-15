package com.pd2undav.statisticsservice;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Escucha {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String cancion;
    private String ambito_id;
    private String ambito_tipo;
    private String artista;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    protected Escucha() {}

    public Escucha(String usuario, String cancion, String ambito_id, String ambito_tipo, String artista) {
        this.usuario = usuario;
        this.cancion = cancion;
        this.ambito_id = ambito_id;
        this.ambito_tipo = ambito_tipo;
        this.artista = artista;
        this.fecha = new Date();
    }

    @Override
    public String toString() {
        return String.format("Escucha[id=%d, usuario=%s, cancion=%s, ambito=%s, artista=%s, fecha=%s]", id, usuario, cancion, ambito_id, artista, fecha.toString());
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCancion() {
        return cancion;
    }

    public String getAmbito_id() {
        return ambito_id;
    }

    public String getAmbito_tipo() { return ambito_tipo; }

    public String getArtista() { return artista; }

    public Date getFecha() {
        return fecha;
    }
}
