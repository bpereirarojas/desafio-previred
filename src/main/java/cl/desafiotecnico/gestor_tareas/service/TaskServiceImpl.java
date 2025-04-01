package cl.desafiotecnico.gestor_tareas.service;

import cl.desafiotecnico.gestor_tareas.dto.TaskRequest;
import cl.desafiotecnico.gestor_tareas.dto.TaskResponse;
import cl.desafiotecnico.gestor_tareas.dto.TasksResponse;
import cl.desafiotecnico.gestor_tareas.entity.Task;
import cl.desafiotecnico.gestor_tareas.entity.TaskStatus;
import cl.desafiotecnico.gestor_tareas.enums.TaskStatusEnum;
import cl.desafiotecnico.gestor_tareas.exception.ResourceNotFoundException;
import cl.desafiotecnico.gestor_tareas.repository.TaskRepository;
import cl.desafiotecnico.gestor_tareas.repository.TaskStatusRepository;
import cl.desafiotecnico.gestor_tareas.utils.DTOUtil;
import cl.desafiotecnico.gestor_tareas.utils.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityMapper mapper;

    @Override
    public TasksResponse getAllTasks(Integer userId) {
        var tasks = this.taskRepository.findByUserId(userId);
        return TasksResponse.builder()
                .data(tasks)
                .message("Tasks found successfully")
                .build();
    }

    @Override
    public TaskResponse getTaskById(Integer id) {

        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        return TaskResponse.builder()
                .data(task)
                .message("Task found successfully")
                .build();
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {

        var statusEnum = TaskStatusEnum.PENDIENTE;


        var taskStatus = TaskStatus.builder()
                .status(statusEnum)
                .build();

        var taskToCreate = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .user(taskRequest.getUser())
                .taskStatus(taskStatus)
                .build();

        taskStatus.setTask(taskToCreate);

        var savedTask = taskRepository.save(taskToCreate);

        return TaskResponse.builder()
                .data(savedTask)
                .message("Task created successfully")
                .build();
    }



    @Override
    public TaskResponse updateTask(Integer id, TaskRequest taskDetails) {

        var entity = this.taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));

        mapper.updateTaskFromDto(taskDetails, entity);

        var updatedTask = taskRepository.save(entity);

        return TaskResponse.builder()
                .data(updatedTask)
                .message("Task updated successfully")
                .build();

    }

    @Override
    public TaskResponse deleteTask(Integer id) {
        var existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(existingTask);

        return TaskResponse.builder()
                .data(null)
                .message("Task deleted successfully")
                .build();
    }
}
