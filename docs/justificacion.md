# Justificación Técnica de la Estructuras de Datos - Clínica

Este documento fundamenta la selección de cada estructura de Java aplicada en el proyecto o "sistema", teniendo en cuenta el rendimiento y la idoneidad de las estructuras elegidas en base a los requerimientos y necesidades del "sistema".

---

## 1. Padrón Único de Pacientes: HashSet
- **Responsable:** Hernández  
- **implementación** java.util.HashSet<Paciente>, utilizado para almacenar los pacientes registrados en el sistema y evitar que existan registros duplicados.
**Justificación técnica y de negocio:** Se eligió HashSet porque el sistema necesita mantener un padrón único de pacientes, donde cada persona pueda registrarse una sola vez utilizando su DNI como identificador.
- El HashSet no permite almacenar elementos duplicados, lo que ayuda a evitar que un mismo paciente sea registrado varias veces.
- Para lograr que el sistema identifique correctamente a los pacientes repetidos, se utilizan los métodos equals() y hashCode() en la clase Paciente.
- El método equals() permite comparar dos pacientes y determinar si son iguales según su DNI, aunque tengan nombres o apellidos diferentes.
- El método hashCode() genera un código hash a partir del DNI, que permite al HashSet organizar y localizar los pacientes de manera eficiente.
- Estas operaciones permiten realizar búsquedas e inserciones con un costo promedio de O(1), es decir, sin tener que recorrer todos los pacientes en condiciones normales. De esta manera, se garantiza que cada DNI corresponda a un único registro dentro del padrón de pacientes.

---

## 2. Cola de Turnos Regulares: Queue (ArrayDeque)
- **Responsable:** Hinojosa  
- **implementación** se utiliza `java.util.Queue<turno>` mediante `java.util.ArrayDeque<turno>`
- **Justificación técnica y de negocio:**
  Para administrar los turnos regulares de la clínica se eligió la estructura Queue(cola). ya que los pacientes deben ser atendidos respetando el orden de llegada
  Esta estructura trabaja bajo el principio FIFO (First In, First Out), que significa que el primero en ingresar es el primero en salir. De esta manera, se garantiza un orden de atención justo y organizado.
  Se utiliza ArrayDeque en lugar de LinkedList porque almacena los elementos mediante un arreglo circular, evitando crear un nodo por cada turno y ofreciendo operaciones eficientes.
Los métodos principales son offer() para agregar turnos, poll() para atender al siguiente y peek() para consultar quién sigue sin eliminarlo

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
