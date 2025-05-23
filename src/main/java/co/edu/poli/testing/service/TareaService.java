package co.edu.poli.testing.service;

import co.edu.poli.testing.model.Tarea;
import co.edu.poli.testing.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea crearTarea(Tarea tarea) {
        tarea.setEstado("Pendiente");
        return tareaRepository.guardar(tarea);
    }

    public List<Tarea> obtenerTareas() {
        return tareaRepository.listar();
    }

    public Optional<Tarea> actualizarEstado(Long id, String nuevoEstado) {
        Optional<Tarea> tareaOpt = tareaRepository.buscarPorId(id);
        tareaOpt.ifPresent(t -> t.setEstado(nuevoEstado));
        return tareaOpt;
    }

    public void eliminarTarea(Long id) {
        tareaRepository.eliminar(id);
    }
}