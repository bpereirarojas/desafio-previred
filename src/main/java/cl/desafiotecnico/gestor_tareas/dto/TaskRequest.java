package cl.desafiotecnico.gestor_tareas.dto;

import cl.desafiotecnico.gestor_tareas.entity.User;
import lombok.Data;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private User user;

}
