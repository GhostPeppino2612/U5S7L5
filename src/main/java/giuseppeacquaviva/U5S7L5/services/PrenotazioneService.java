package giuseppeacquaviva.U5S7L5.services;

import giuseppeacquaviva.U5S7L5.entities.Evento;
import giuseppeacquaviva.U5S7L5.entities.Prenotazione;
import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.exceptions.NotFoundException;
import giuseppeacquaviva.U5S7L5.repositories.EventoRepository;
import giuseppeacquaviva.U5S7L5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public Prenotazione prenotaEvento(Long eventoId, String username) {
        Utente utente = utenteService.findByUsername(username);
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        if (evento.getPostiDisponibili() <= 0) {
            throw new RuntimeException("Posti esauriti");
        }

        if (prenotazioneRepository.existsByUtenteAndEvento(utente, evento)) {
            throw new RuntimeException("Hai già prenotato questo evento");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataPrenotazione(LocalDate.now());
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> trovaPrenotazioniPerEvento(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));
        return prenotazioneRepository.findByEvento(evento);
    }
}