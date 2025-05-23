package co.edu.poli.testing.service;

import co.edu.poli.testing.model.Tarea;
import co.edu.poli.testing.repository.TareaRepository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class TareaServiceTest {
    private TareaService tareaService;

    @BeforeEach
    void setUp() {
        // Suponemos que TareaRepository tiene un constructor vacío y funciona para pruebas
        tareaService = new TareaService(new TareaRepository());
    }

    @Test
    void crearTarea() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Nueva tarea");
        tarea.setDescripcion("Descripción de la tarea");
        tarea.setPrioridad("Alta");

        Tarea creada = tareaService.crearTarea(tarea);

        assertNotNull(creada.getId(), "La tarea creada debe tener un ID asignado");
        assertEquals("Sin hacer", creada.getEstado(), "El estado inicial debe ser 'Sin hacer'");
        assertEquals("Nueva tarea", creada.getTitulo());
        assertEquals("Alta", creada.getPrioridad());
    }

    @Test
    void actualizarEstado() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Tarea a actualizar");
        tarea.setDescripcion("Descripción");
        tarea.setPrioridad("Media");

        Tarea creada = tareaService.crearTarea(tarea);

        Optional<Tarea> actualizada = tareaService.actualizarEstado(creada.getId(), "Finalizada");

        assertTrue(actualizada.isPresent(), "La tarea debe estar presente para actualizar");
        assertEquals("Finalizada", actualizada.get().getEstado(), "El estado debe actualizarse a 'Finalizada'");
    }

    @Test
    void actualizarEstado_TareaNoExiste() {
        Optional<Tarea> actualizada = tareaService.actualizarEstado(999L, "Completada");
        assertFalse(actualizada.isPresent(), "No debe actualizarse estado si la tarea no existe");
    }

    @Test
    void eliminarTarea() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Tarea para eliminar");
        tarea.setDescripcion("Descripción");
        tarea.setPrioridad("Baja");

        Tarea creada = tareaService.crearTarea(tarea);

        tareaService.eliminarTarea(creada.getId());

        // Intentamos obtener la tarea para verificar que fue eliminada
        Optional<Tarea> buscada = tareaService.obtenerTareas()
                .stream()
                .filter(t -> t.getId().equals(creada.getId()))
                .findFirst();

        assertFalse(buscada.isPresent(), "La tarea debe ser eliminada y no estar presente");
    }

}
