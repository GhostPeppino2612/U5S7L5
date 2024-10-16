package giuseppeacquaviva.U5S7L5.payloads;

import giuseppeacquaviva.U5S7L5.entities.Ruolo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UtenteDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Size(min = 5, max = 255)
    private String password;

    @NotNull
    private Ruolo ruolo;


}
