package giuseppeacquaviva.U5S7L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "creatore_id")
    private Utente creatore;

    @OneToMany(mappedBy = "evento")
    private Set<Prenotazione> prenotazioni;

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int postiDisponibili, Utente creatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.postiDisponibili = postiDisponibili;
        this.creatore = creatore;
    }
}
