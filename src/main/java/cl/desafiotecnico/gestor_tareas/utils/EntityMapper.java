package cl.desafiotecnico.gestor_tareas.utils;

import cl.desafiotecnico.gestor_tareas.dto.TaskRequest;
import cl.desafiotecnico.gestor_tareas.entity.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromDto(TaskRequest request, @MappingTarget Task entity);
}
