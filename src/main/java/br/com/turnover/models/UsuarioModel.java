package br.com.turnover.models;

import br.com.turnover.enums.TipoDeAcessoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha = "1234";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeAcessoEnum tipoDeAcessoEnum;

    @JsonBackReference
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private FuncionarioModel funcionario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_" + tipoDeAcessoEnum.name());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    // Getters and setters
}
