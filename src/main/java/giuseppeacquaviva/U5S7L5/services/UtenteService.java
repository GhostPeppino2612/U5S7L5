package giuseppeacquaviva.U5S7L5.services;

import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.exceptions.NotFoundException;
import giuseppeacquaviva.U5S7L5.payloads.UtenteDTO;
import giuseppeacquaviva.U5S7L5.repositories.UtenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Utente> getUtenti(int page, int size, String sortBy) {
        return utenteRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    public Utente findByIdAndUpdate(Long id, UtenteDTO utenteDTO) {
        Utente foundUtente = utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));

        foundUtente.setUsername(utenteDTO.getUsername());
        foundUtente.setPassword(utenteDTO.getPassword());
        foundUtente.setRuolo(utenteDTO.getRuolo());
        if (utenteDTO.getPassword() != null && !utenteDTO.getPassword().isEmpty()) {
            foundUtente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        }
        return utenteRepository.save(foundUtente);
    }


    public Utente findById(long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    public void deleteUtente(long id) {
        Utente utente = this.findById(id);
        utenteRepository.delete(utente);
    }
}
