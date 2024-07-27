package br.com.turnover.repositories;

import br.com.turnover.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {

    boolean existsByUsuarioCpf(String cpf);

    //
    Optional<FuncionarioModel> findByUsuarioCpf(String cpf);

    List<FuncionarioModel> findAllByDepartamentoId(UUID departamentoId);

    List<FuncionarioModel> findByDepartamentoIsNull();

    List<FuncionarioModel> findAllByCargoId(UUID cargoId);

//    @Query("SELECT f " +
//            "FROM FuncionarioModel f " +
//            "LEFT JOIN f.cargo c " +
//            "LEFT JOIN f.departamento d " +
//            "LEFT JOIN f.usuario u ")
//    List<FuncionarioModel> findAllWithDetails();

//    void deleteByCpf(String cpf);
}
