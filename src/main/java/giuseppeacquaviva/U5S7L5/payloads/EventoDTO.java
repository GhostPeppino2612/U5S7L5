package giuseppeacquaviva.U5S7L5.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String titolo;

    @NotNull
    @Size(min = 5, max = 255)
    private String descrizione;

    @NotNull
    private LocalDate data;

    @NotNull
    @Size(min = 2, max = 100)
    private String luogo;

    @NotNull
    private int postiDisponibili;
}
