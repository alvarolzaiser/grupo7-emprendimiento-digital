# Justificación Técnica de la Estructuras de Datos - Clínica

Este documento fundamenta la selección de cada estructura de Java aplicada en el proyecto o "sistema", teniendo en cuenta el rendimiento y la idoneidad de las estructuras elegidas en base a los requerimientos y necesidades del "sistema".

---

## 1. Padrón Único de Pacientes: HashSet
- **Responsable:** Hernández  
- *(a completar por Hernández, hay que explicar por qué HashSet y la importancia de equals/hashCode por DNI en este "sistema")*

---

## 2. Cola de Turnos Regulares: Queue (ArrayDeque)
- **Responsable:** Hinojosa  
- *(a completar por Hinojosa, hay que explicar el uso del principio FIFO y la elección de ArrayDeque versus LinkedList)*

---

## 3. Guardia de Urgencias Médicas: PriorityQueue
- **Responsable:** Ledesma
- **Implementación:** `java.util.PriorityQueue<Urgencia>` con `Comparator` por nivel de gravedad (5 a 1).
- **Justificación técnica y de negocio:**
  En una guardia médica el orden cronológico de llegada debe romperse si un paciente llega con riesgo de vida. Una cola tradicional (FIFO) haría que un paciente en código rojo (grave) espere detrás de consultas cotidianas.
  - Se eligió PriorityQueue, que utiliza un Heap para organizar los pacientes según su gravedad. El Heap es una estructura que organiza a los pacientes según su gravedad, manteniendo siempre al más grave en la primera posición para poder acceder a él rápidamente.
  - Las operaciones de inserción (offer) y extracción (poll) tienen un costo de O(log n), mientras que consultar al paciente más grave (peek) cuesta O(1). El "O(log n)" significa que el tiempo de ejecución aumenta a medida que aumentan los elementos a procesar y "O(1)" significa que el tiempo de ejecución es el mismo "1" independientemente de cuantos elementos haya o si eso crece.
  - A diferencia de ordenar toda la lista en cada ingreso con Collections.sort(), el Heap mantiene automáticamente al paciente más grave en primer lugar, haciendo el proceso más eficiente.

---

## 4. Módulo de Reversión y Auditoría: Stack (ArrayDeque)
- **Responsable:** Ledesma
- **Implementación:** `java.util.ArrayDeque<Accion>` utilizada bajo el contrato de Pila (Stack).
- **Justificación técnica y de negocio:**
  El módulo de "Deshacer" exige revertir estrictamente la última acción administrativa registrada (LIFO: "Last In, First Out").
  - ¿Por qué no usar Stack? Es una clase bastante antigua y considerada ya en desuso que tiene mecanismos de sincronización innecesarios y menor eficiencia.
  - Ventaja de ArrayDeque: Es una alternativa más moderna y eficiente. Permite agregar (push) y quitar (pop) elementos en tiempo constante amortizado O(1), sin que el tiempo aumente al manejar mas elementos.
