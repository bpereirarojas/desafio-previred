package cl.desafiotecnico.gestor_tareas.controller;

import cl.desafiotecnico.gestor_tareas.annotation.CurrentUser;
import cl.desafiotecnico.gestor_tareas.dto.TaskRequest;
import cl.desafiotecnico.gestor_tareas.dto.TaskResponse;
import cl.desafiotecnico.gestor_tareas.dto.TasksResponse;
import cl.desafiotecnico.gestor_tareas.entity.User;
import cl.desafiotecnico.gestor_tareas.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Tasks", description = "CRUD de Tareas en la aplicación")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Obtener todas las tareas del usuario autenticado",
            description = "Retorna todas las tareas asociadas al usuario logueado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tareas obtenidas exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @GetMapping
    public ResponseEntity<TasksResponse> getAllTasks(
            @Parameter(description = "Usuario autenticado obtenido del contexto de seguridad")
            @CurrentUser User user) {

        TasksResponse tasksResponse = taskService.getAllTasks(user.getId());
        return ResponseEntity.ok(tasksResponse);
    }

    @Operation(summary = "Obtener una tarea por ID",
            description = "Retorna una tarea específica, si existe, en base a su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @Parameter(description = "ID de la tarea a obtener", example = "1")
            @PathVariable("id") Integer taskId) {

        var taskResponse = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Crear una nueva tarea",
            description = "Crea una nueva tarea utilizando los datos enviados en el cuerpo de la solicitud.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la creación de la tarea")
    })
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Parameter(description = "Información necesaria para crear la tarea")
            @RequestBody TaskRequest taskRequest) {

        var taskResponse = taskService.createTask(taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Actualizar una tarea",
            description = "Modifica los datos de una tarea existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la actualización de la tarea")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @Parameter(description = "ID de la tarea a actualizar", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Información con los nuevos datos de la tarea")
            @RequestBody TaskRequest task) {

        var updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Eliminar una tarea",
            description = "Elimina una tarea en base a su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(
            @Parameter(description = "ID de la tarea a eliminar", example = "1")
            @PathVariable Integer id) {

        var taskDeleted = taskService.deleteTask(id);
        return ResponseEntity.ok(taskDeleted);
    }
}
