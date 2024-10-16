package giuseppeacquaviva.U5S7L5.controllers;

import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.exceptions.BadRequestException;
import giuseppeacquaviva.U5S7L5.payloads.UtenteDTO;
import giuseppeacquaviva.U5S7L5.payloads.UtenteLoginDTO;
import giuseppeacquaviva.U5S7L5.services.AuthService;
import giuseppeacquaviva.U5S7L5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UtenteLoginDTO login(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        // Genera il token e restituisci il DTO con il token
        return new UtenteLoginDTO(authService.authUserAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteDTO saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Utente savedDipendente = utenteService.salvaUtente(body);
        return new UtenteDTO(savedDipendente.getUsername(), savedDipendente.getPassword(),
                savedDipendente.getRuolo());
    }

}
