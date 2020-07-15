package com.pd2undav.statisticsservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EscuchaRepository extends CrudRepository<Escucha, Long> {

    @Query(value = "SELECT cancion FROM escucha " +
                   "WHERE fecha BETWEEN ?1 AND ?2 " +
                   "GROUP BY cancion ORDER BY COUNT(*) DESC LIMIT 1;", nativeQuery = true)
    String findMostListenedCancionBetween(Date fechaStart, Date fechaEnd);

    @Query(value = "SELECT ambito_id FROM escucha " +
            "WHERE fecha BETWEEN ?1 AND ?2 " +
            "AND ambito_tipo = ?3 " +
            "GROUP BY ambito_id ORDER BY COUNT(*) DESC LIMIT 1;", nativeQuery = true)
    String findMostListenedAmbitoBetween(Date fechaStart, Date fechaEnd, String ambito_tipo);

    @Query(value = "SELECT COUNT(*) FROM escucha " +
            "WHERE ambito_id = ?1 " +
            "GROUP BY ambito_id;", nativeQuery = true)
    int findAmbitoEscuchaCount(String ambito_id);

    @Query(value = "SELECT COUNT(*) FROM escucha " +
            "WHERE cancion = ?1 " +
            "GROUP BY cancion;", nativeQuery = true)
    int findCancionEscuchaCount(String cancion);

    @Query(value = "SELECT DISTINCT artista FROM escucha " +
            "WHERE usuario = ?1 and ambito_tipo='album';", nativeQuery = true)
    List<String> findUserArtists(String userID);

    @Query(value = "SELECT DISTINCT ambito_id FROM escucha " +
            "WHERE usuario = ?1 and artista = ?2 and ambito_tipo='album';", nativeQuery = true)
    List<String> findUserArtistAlbums(String userID, String artistaID);


}
