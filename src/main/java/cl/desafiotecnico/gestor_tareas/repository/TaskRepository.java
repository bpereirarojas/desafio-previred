package cl.desafiotecnico.gestor_tareas.repository;

import cl.desafiotecnico.gestor_tareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(Integer userId);




}
