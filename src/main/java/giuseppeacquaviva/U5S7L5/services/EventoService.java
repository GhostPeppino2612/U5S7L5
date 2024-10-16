package giuseppeacquaviva.U5S7L5.services;

import giuseppeacquaviva.U5S7L5.entities.Evento;
import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.payloads.EventoDTO;
import giuseppeacquaviva.U5S7L5.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public Evento creaEvento(EventoDTO eventoDTO, String username) {
        Utente organizzatore = utenteService.findByUsername(username);
        Evento evento = new Evento();
        evento.setTitolo(eventoDTO.getTitolo());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setData(eventoDTO.getData());
        evento.setLuogo(eventoDTO.getLuogo());
        evento.setPostiDisponibili(eventoDTO.getPostiDisponibili());
        evento.setCreatore(organizzatore);
        return eventoRepository.save(evento);
    }

    public List<Evento> trovaEventiCreati(String username) {
        Utente organizzatore = utenteService.findByUsername(username);
        return eventoRepository.findByCreatore(organizzatore);
    }

    public Evento aggiornaEvento(Long id, EventoDTO eventoDTO, String username) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
        if (!evento.getCreatore().getUsername().equals(username)) {
            throw new RuntimeException("Non sei autorizzato a modificare questo evento");
        }
        evento.setTitolo(eventoDTO.getTitolo());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setData(eventoDTO.getData());
        evento.setLuogo(eventoDTO.getLuogo());
        evento.setPostiDisponibili(eventoDTO.getPostiDisponibili());
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(Long id, String username) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
        if (!evento.getCreatore().getUsername().equals(username)) {
            throw new RuntimeException("Non sei autorizzato a eliminare questo evento");
        }
        eventoRepository.delete(evento);
    }

    public List<Evento> trovaTuttiGliEventi() {
        return eventoRepository.findAll();
    }
}