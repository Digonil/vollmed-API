package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
        Consulta consulta,
        @NotNull
        MotivoCancelamento cancelamento
) {
}
