package med.voll.api.domain.consulta;

import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class CancelamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (dados.motivoCancelamento() == null) {
            throw new ValidacaoException("Não foi informado o motivo do cancelamento");
        }
        if (!dados.consulta().getData().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new ValidacaoException("É necessário 24 horas de antecedência para se cancelar uma consulta");
        }
        consultaRepository.deleteById(dados.consulta().getId());

    }
}
