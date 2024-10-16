package giuseppeacquaviva.U5S7L5.controllers;

import giuseppeacquaviva.U5S7L5.entities.Evento;
import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.payloads.EventoDTO;
import giuseppeacquaviva.U5S7L5.payloads.UtenteDTO;
import giuseppeacquaviva.U5S7L5.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("")
    public Evento creaEvento(@RequestBody EventoDTO eventoDTO, @RequestParam String username) {
        return eventoService.creaEvento(eventoDTO, username);
    }

    @PutMapping("/{id}")
    public Evento aggiornaEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO, @RequestParam String username) {
        return eventoService.aggiornaEvento(id, eventoDTO, username);
    }

    @DeleteMapping("/{id}")
    public void eliminaEvento(@PathVariable Long id, @RequestParam String username) {
        eventoService.eliminaEvento(id, username);
    }

    @GetMapping("")
    public List<Evento> trovaTuttiGliEventi() {
        return eventoService.trovaTuttiGliEventi();
    }
}