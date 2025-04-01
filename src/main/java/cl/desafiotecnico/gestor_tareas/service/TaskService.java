package cl.desafiotecnico.gestor_tareas.service;

import cl.desafiotecnico.gestor_tareas.dto.TaskRequest;
import cl.desafiotecnico.gestor_tareas.dto.TaskResponse;
import cl.desafiotecnico.gestor_tareas.dto.TasksResponse;
import cl.desafiotecnico.gestor_tareas.entity.Task;
import cl.desafiotecnico.gestor_tareas.entity.User;

import java.util.List;

public interface TaskService {

    TasksResponse getAllTasks(Integer userId);

    TaskResponse getTaskById(Integer id);

    TaskResponse createTask(TaskRequest taskRequest);


    TaskResponse updateTask(Integer id, TaskRequest taskDetails);

    TaskResponse deleteTask(Integer id);

}
