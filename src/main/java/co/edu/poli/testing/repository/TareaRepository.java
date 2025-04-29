package co.edu.poli.testing.repository;

import co.edu.poli.testing.model.Tarea;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TareaRepository {
    private final Map<Long, Tarea> baseDatos = new HashMap<>();
    private long contadorId = 1;

    public Tarea guardar(Tarea tarea) {
        tarea.setId(contadorId++);
        baseDatos.put(tarea.getId(), tarea);
        return tarea;
    }

    public List<Tarea> listar() {
        return new ArrayList<>(baseDatos.values());
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return Optional.ofNullable(baseDatos.get(id));
    }

    public void eliminar(Long id) {
        baseDatos.remove(id);
    }
}