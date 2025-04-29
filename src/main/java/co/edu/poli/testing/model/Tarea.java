package co.edu.poli.testing.model;

import lombok.Data;

@Data
public class Tarea {
    private Long id;
    private String titulo;
    private String descripcion;
    private String prioridad; // Alta, Media, Baja
    private String estado; // Pendiente, En progreso, Completada
    
}