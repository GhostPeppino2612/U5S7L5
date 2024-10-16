package giuseppeacquaviva.U5S7L5.services;

import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.exceptions.UnauthorizedException;
import giuseppeacquaviva.U5S7L5.payloads.UtenteDTO;
import giuseppeacquaviva.U5S7L5.security.JWTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTools jwTools;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authUserAndGenerateToken(UtenteDTO body) {
        Utente utente = utenteService.findByUsername(body.getUsername());

        if (passwordEncoder.matches(body.getPassword(), utente.getPassword())) {
            return jwTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette");
        }
    }
}
