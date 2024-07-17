package br.com.turnover.services;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.repositories.CargoRepository;
import br.com.turnover.repositories.DepartamentoRepository;
import br.com.turnover.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final DepartamentoRepository departamentoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Transactional
    public FuncionarioModel saveFuncionario(FuncionarioRecordDto funcionarioRecordDto) {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome(funcionarioRecordDto.nome());
        funcionario.setPublisher(cargoRepository.findById(funcionarioRecordDto.publisherId()).get());
        funcionario.setAuthors(departamentoRepository.findAllById(funcionarioRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }
}
