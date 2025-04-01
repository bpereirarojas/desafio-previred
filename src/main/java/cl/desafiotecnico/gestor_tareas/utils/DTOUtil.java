package cl.desafiotecnico.gestor_tareas.utils;

import cl.desafiotecnico.gestor_tareas.dto.TaskRequest;
import cl.desafiotecnico.gestor_tareas.entity.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DTOUtil {

    /**
     * Convierte un DTO en una entidad usando la función convertidora proporcionada.
     *
     * @param source El objeto DTO de origen.
     * @param converter La función que transforma el DTO en la entidad.
     * @param <T> El tipo del DTO.
     * @param <K> El tipo de la entidad.
     * @return La entidad convertida.
     */
    public <T, K> K convertDtoToEntity(T source, Function<T, K> converter) {
        return converter.apply(source);
    }


}
