package cl.desafiotecnico.gestor_tareas.config;

import cl.desafiotecnico.gestor_tareas.entity.Task;
import cl.desafiotecnico.gestor_tareas.entity.TaskStatus;
import cl.desafiotecnico.gestor_tareas.entity.User;
import cl.desafiotecnico.gestor_tareas.enums.TaskStatusEnum;
import cl.desafiotecnico.gestor_tareas.repository.TaskRepository;
import cl.desafiotecnico.gestor_tareas.repository.TaskStatusRepository;
import cl.desafiotecnico.gestor_tareas.repository.UserRepository;
import cl.desafiotecnico.gestor_tareas.utils.PasswordUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository,
                                            TaskStatusRepository taskStatusRepository,
                                            TaskRepository taskRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                List<User> users = Arrays.asList(
                        User.builder()
                                .name("Bryan")
                                .email("bryan@desafiotecnico.cl")
                                .password(PasswordUtil.encrypt("password123"))
                                .build(),
                        User.builder()
                                .name("Bob")
                                .email("bob@desafiotecnico.cl")
                                .password(PasswordUtil.encrypt("password321"))
                                .build(),
                        User.builder()
                                .name("Charlie")
                                .email("charlie@desafiotecnico.cl")
                                .password(PasswordUtil.encrypt("password123"))
                                .build()
                );
                userRepository.saveAll(users);
            }

            List<Task> tasks = null;

            List<TaskStatus> statusTasks = taskStatusRepository.findAll();
            List<User> users = userRepository.findAll();

            if (taskRepository.count() == 0) {
                
                TaskStatusEnum[] statuses = TaskStatusEnum.values();

                tasks = users.stream()
                        .<Task>flatMap(user ->
                                IntStream.rangeClosed(1, 10)
                                        .mapToObj(i -> {
                                                Task task = new Task();
                                                task.setTitle("Tarea " + i + " de " + user.getName());
                                                task.setDescription("Descripción de la tarea " + i);
                                                task.setUser(user);

                                                taskRepository.save(task);

                                                TaskStatus taskStatus = new TaskStatus();
                                                taskStatus.setTask(task);
                                                taskStatus.setStatus(TaskStatusEnum.values()[new Random().nextInt(TaskStatusEnum.values().length)]);
                                                taskStatusRepository.save(taskStatus);

                                                return task;
                                        })
                        )
                        .collect(Collectors.toList());
               
            }
        };
    }
}
