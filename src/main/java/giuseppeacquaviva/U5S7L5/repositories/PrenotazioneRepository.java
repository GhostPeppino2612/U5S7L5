package giuseppeacquaviva.U5S7L5.repositories;

import giuseppeacquaviva.U5S7L5.entities.Evento;
import giuseppeacquaviva.U5S7L5.entities.Prenotazione;
import giuseppeacquaviva.U5S7L5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByEvento(Evento evento);

    boolean existsByUtenteAndEvento(Utente utente, Evento evento);
}
