package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamento implements ValidadorCancelamentoDeConsulta {

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (dados.motivoCancelamento() == null) {
            throw new ValidacaoException("Não foi informado o motivo do cancelamento");
        }
    }
}
