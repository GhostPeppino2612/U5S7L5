package giuseppeacquaviva.U5S7L5.controllers;

import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.payloads.UtenteDTO;
import giuseppeacquaviva.U5S7L5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "20") int size,
                                      @RequestParam(defaultValue = "username") String sortBy) {
        return utenteService.getUtenti(page, size, sortBy);
    }

    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente utente) {
        return utente;
    }

    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente utente, @RequestBody UtenteDTO utenteDTO) {
        return utenteService.findByIdAndUpdate(utente.getId(), utenteDTO);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente utente) {
        utenteService.deleteUtente(utente.getId());
    }

    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable long utenteId) {
        return utenteService.findById(utenteId);
    }

    @PutMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Utente findByIdAndUpdate(@PathVariable long utenteId, @RequestBody UtenteDTO body) {
        return utenteService.findByIdAndUpdate(utenteId, body);
    }

    @DeleteMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void de(@PathVariable long utenteId) {
        utenteService.deleteUtente(utenteId);
    }
}
