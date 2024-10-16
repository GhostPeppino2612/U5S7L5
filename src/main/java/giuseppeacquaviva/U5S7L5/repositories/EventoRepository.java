package giuseppeacquaviva.U5S7L5.repositories;

import giuseppeacquaviva.U5S7L5.entities.Evento;
import giuseppeacquaviva.U5S7L5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCreatore(Utente creatore);

}
