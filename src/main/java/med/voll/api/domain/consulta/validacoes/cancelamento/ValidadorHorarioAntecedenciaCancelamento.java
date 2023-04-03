package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoDeConsulta {

    public ConsultaRepository repository;

    @Override
    public void cancelar(DadosCancelamentoConsulta dados) {

        var consulta = repository.getReferenceById(dados.consulta().getId());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();


        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("É necessário 24 horas de antecedência para se cancelar uma consulta");
        }
    }
}
