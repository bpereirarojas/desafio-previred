package cl.desafiotecnico.gestor_tareas.dto;

import cl.desafiotecnico.gestor_tareas.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TasksResponse {

    private List<Task> data;
    private String message;

}
