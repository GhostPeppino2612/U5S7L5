package giuseppeacquaviva.U5S7L5.payloads;
import java.time.LocalDate;
import java.util.List;

public record ErrorPayloadList(
        String message,
        LocalDate time,
        List<String> errorList
) {
}