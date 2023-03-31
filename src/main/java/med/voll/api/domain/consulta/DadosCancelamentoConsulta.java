package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        Consulta consulta,
        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
