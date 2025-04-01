package cl.desafiotecnico.gestor_tareas.dto;

import cl.desafiotecnico.gestor_tareas.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class TaskResponse {

    private Task data;
    private String message;

}
