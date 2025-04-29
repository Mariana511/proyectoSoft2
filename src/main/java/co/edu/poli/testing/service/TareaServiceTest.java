package co.edu.poli.testing.service;

import co.edu.poli.testing.model.Tarea;
import co.edu.poli.testing.repository.TareaRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TareaServiceTest{

    private TareaService tareaService;

    @BeforeEach
    void setUp() {
        tareaService = new TareaService(new TareaRepository());
    }

    @Test
    void crearTarea() {
        Tarea tarea = new Tarea();
        tarea.seTitulo("Nueva tarea");
        tarea.setDescripcion("Descripción de la tarea");
        tarea.setPrioridad("Alta");

        Tarea creada = tareaService.crearTarea(tarea);

        assertNotNull(creada.getId);
        assertEquals("Pendiente", creada.getEstado());
    }

    @Test
    void actualizarEstado() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Tarea a actualizar");
        tarea.setPrioridad("Media");
        tareaService.crearTarea(tarea);

        var actualizada = tareaService.actualizarEstado(tarea.getId(), "Completada");
        assertTrue(actualizada.isPresent());
        assertEquals("Completada", actualizada.get().getEstado());
    }
}