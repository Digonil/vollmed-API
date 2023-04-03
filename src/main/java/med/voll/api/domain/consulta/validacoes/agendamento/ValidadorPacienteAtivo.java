package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não poder agendada com paciente excluído");
        }
    }
}
