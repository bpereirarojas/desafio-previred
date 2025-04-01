package cl.desafiotecnico.gestor_tareas.repository;

import cl.desafiotecnico.gestor_tareas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
