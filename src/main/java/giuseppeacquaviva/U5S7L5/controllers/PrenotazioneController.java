package giuseppeacquaviva.U5S7L5.controllers;

import giuseppeacquaviva.U5S7L5.entities.Prenotazione;
import giuseppeacquaviva.U5S7L5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/prenota/{eventoId}")
    public Prenotazione prenotaEvento(@PathVariable Long eventoId, @RequestParam String username) {
        return prenotazioneService.prenotaEvento(eventoId, username);
    }

    @GetMapping("/evento/{eventoId}")
    public List<Prenotazione> prenotazioniPerEvento(@PathVariable Long eventoId) {
        return prenotazioneService.trovaPrenotazioniPerEvento(eventoId);
    }
}