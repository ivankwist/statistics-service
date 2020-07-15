package com.pd2undav.statisticsservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EscuchaController {

    private static final Logger log = LoggerFactory.getLogger(EscuchaController.class);
    private final EscuchaRepository escuchaRepository;

    public EscuchaController(EscuchaRepository escuchaRepository) {
        this.escuchaRepository = escuchaRepository;
    }

    @RequestMapping("/cancion/escuchas")
    public ResponseEntity<Integer> getCancionEscuchas(@RequestParam(value = "cancionID") String cancionID) {
        int escuchas = escuchaRepository.findCancionEscuchaCount(cancionID);
        log.info("Escuchas for Cancion({}): {}", cancionID, escuchas);

        return new ResponseEntity<Integer>(escuchas, HttpStatus.OK);
    }

    @RequestMapping("/ambito/escuchas")
    public ResponseEntity<Integer> getAmbitoEscuchas(@RequestParam(value = "ambitoID") String ambitoID) {
        int escuchas = escuchaRepository.findAmbitoEscuchaCount(ambitoID);
        log.info("Escuchas for Ambito({}): {}", ambitoID, escuchas);

        return new ResponseEntity<Integer>(escuchas, HttpStatus.OK);
    }

    @RequestMapping("/escuchas/cancion")
    public ResponseEntity<String> getMostListenedCancion(@RequestParam(value="fechaStart") String fechaStart,
                                                          @RequestParam(value="fechaEnd") String fechaEnd) {
        String cancion = null;

        try {
            Date fechaStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStart);
            Date fechaEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEnd);
            cancion = escuchaRepository.findMostListenedCancionBetween(fechaStartDate, fechaEndDate);

            log.info("Most listened Cancion: {}", cancion);
        } catch (ParseException e) {
            log.error("Parsing error", e);
        }

        return new ResponseEntity<String>(cancion, HttpStatus.OK);
    }

    @RequestMapping("/escuchas/album")
    public ResponseEntity<String> getMostListenedAlbum(@RequestParam(value="fechaStart") String fechaStart,
                                                       @RequestParam(value="fechaEnd") String fechaEnd) {
        String album = null;

        try {
            Date fechaStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStart);
            Date fechaEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEnd);
            album = escuchaRepository.findMostListenedAmbitoBetween(fechaStartDate, fechaEndDate, "album");

            log.info("Most listened Album: {}", album);
        } catch (ParseException e) {
            log.error("Parsing Error", e);
        }

        return new ResponseEntity<String>(album, HttpStatus.OK);
    }

    @RequestMapping("/escuchas/radio")
    public ResponseEntity<String> getMostListenedPlaylist(@RequestParam(value="fechaStart") String fechaStart,
                                                          @RequestParam(value="fechaEnd") String fechaEnd) {
        String radio = null;

        try {
            Date fechaStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStart);
            Date fechaEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEnd);
            radio = escuchaRepository.findMostListenedAmbitoBetween(fechaStartDate, fechaEndDate, "radio");

            log.info("Most listened Radio: {}", radio);
        } catch (ParseException e) {
            log.error("Parsing error", e);
        }

        return new ResponseEntity<String>(radio, HttpStatus.OK);
    }

    @RequestMapping("escuchas/user/artistas")
    public ResponseEntity<List<String>> getUserArtists(@RequestParam(value="userID") String userID){
        List<String> artists = escuchaRepository.findUserArtists(userID);

        return new ResponseEntity<List<String>>(artists, HttpStatus.OK);
    }

    @RequestMapping("escuchas/user/artista/albums")
    public ResponseEntity<List<String>> getUserArtistAlbums(@RequestParam(value="userID") String userID,
                                                            @RequestParam(value="artistaID") String artistaID){
        List<String> albums = escuchaRepository.findUserArtistAlbums(userID, artistaID);

        return new ResponseEntity<List<String>>(albums, HttpStatus.OK);
    }

}
