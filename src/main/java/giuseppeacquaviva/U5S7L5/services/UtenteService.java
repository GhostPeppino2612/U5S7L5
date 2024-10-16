package giuseppeacquaviva.U5S7L5.services;

import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.exceptions.NotFoundException;
import giuseppeacquaviva.U5S7L5.payloads.UtenteDTO;
import giuseppeacquaviva.U5S7L5.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Utente salvaUtente(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setUsername(utenteDTO.getUsername());
        utente.setUsername(utenteDTO.getUsername());
        utente.setPassword(utenteDTO.getPassword());
        utente.setRuolo(utenteDTO.getRuolo());

        utente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));

        return utenteRepository.save(utente);
    }

    public Utente findById(long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}
