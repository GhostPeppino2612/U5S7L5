package giuseppeacquaviva.U5S7L5.security;

import giuseppeacquaviva.U5S7L5.entities.Utente;
import giuseppeacquaviva.U5S7L5.exceptions.UnauthorizedException;
import giuseppeacquaviva.U5S7L5.repositories.UtenteRepository;
import giuseppeacquaviva.U5S7L5.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTools jwTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Per favore inserisci il token correttamente");
        String token = authHeader.substring(7);
        jwTools.verifyToken(token);
        String username = jwTools.extractSubject(token);
        Utente utente = utenteService.findByUsername(username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(utente, null,
                utente.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}