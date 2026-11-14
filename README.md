# grupo7-emprendimiento-digital
Simulador por consola de una Clínica. Proyecto académico en Java que implementa Set, Queue y Stack para la administración básica de turnos y atención médica.

**Integrantes:**
- Ledesma (Usuario GitHub: @alvarolzaiser)
- Hinojosa(usuario GitHub: @NataliaHinojosa2026)
- Hernandez (usuario GitHub: @ximexixi)

**Tablero Kanban:** [Enlace a Trello](https://trello.com/invite/b/6af1cb1ac8bf5db8a590cc29/ATTI37080f0c9531417fa8f7ae4b059e7b53BD3AFE93/grupo7-emprendimiento-digital)

## Descripción del negocio
Clínica que permite gestionar la atención médica mediante la recepcion de pacientes, turnos regulares y atención de urgencias.

## Problema que resuelve
El objetivo es resolver el desorden en la "sala de espera" quitando registros duplicados mediante "Set", priorizando pacientes críticos por sobre el orden de llegada mediante "Queue" y "PriorityQueue" y permitiendo deshacer operaciones erróneas de la recepción sin pérdida de datos con "Stack".

## Tema elegido
Clase 8: Sets, Queues y Stacks. Se seleccionó este tema para modelar los flujos del establecimiento:
- **Set (HashSet):** Lista de pacientes únicos identificados por DNI.
- **Queue (ArrayDeque):** Modalidad fila FIFO para turnos de consulta regular.
- **PriorityQueue:** Modalidad para urgencias ordenadas por gravedad.
- **Stack (ArrayDeque):** Modalidad de pila LIFO para deshacer acciones administrativas.

## Tecnologías
- Java (JDK 17+)
- Control de versiones: Git & GitHub
- Planificación: Trello

## Cómo ejecutar el proyecto
1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/alvarolzaiser/grupo7-emprendimiento-digital.git
   cd grupo7-emprendimiento-digital
   ```

2. **Compilar los archivos desde la raíz del proyecto:**
   ```bash
   javac -d bin src/clinica/modelo/*.java src/clinica/estructuras/*.java src/clinica/*.java
   ```

3. **Ejecutar el programa interactivo:**
   ```bash
   java -cp bin clinica.Main
   ```

> También puede abrirse directamente la carpeta del proyecto en **VS Code** o **Eclipse** y ejecutar `src/clinica/Main.java`

## Funcionalidades

1. **Registrar paciente (`Set`):** Da de alta un paciente validando que el DNI no esté vacío ni repetido en el padrón.
2. **Buscar paciente por DNI (`Set`):** Verifica la existencia en O(1) y recupera los datos del paciente registrado.
3. **Dar turno regular (`Queue`):** Asigna un número incremental de turno a un paciente registrado y lo encola al final de la fila FIFO.
4. **Ver próximo turno (`Queue - peek`):** Muestra quién sigue en la fila de turnos regulares sin retirarlo de la espera.
5. **Atender turno (`Queue - poll`):** Llama y retira de la cola al primer paciente en espera.
6. **Ingresar urgencia (`PriorityQueue`):** Ingresa un paciente registrado a la guardia con nivel de gravedad (1 a 5), validando que no esté ya en guardia.
7. **Atender urgencia (`PriorityQueue - poll`):** Atiende al paciente de mayor gravedad (y menor orden de llegada en caso de empate).
8. **Deshacer última acción (`Stack`):** Revierte la última alta, turno o ingreso a guardia. Por regla de negocio, la atención de un paciente ya realizada no se deshace, y si se intenta cancelar un turno/urgencia ya atendido, el sistema lo informa.

## Capturas de pantalla

### Salida de Consola (Ejemplo de ejecución real)

```text
=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 1
DNI: 42067067
Nombre: Alvaro
Apellido: Ledesma
Paciente registrado: Ledesma, Alvaro (DNI 42067067)

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 3
DNI: 42067067
Asignado: Turno N° 1 - Ledesma, Alvaro (DNI 42067067)

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 6
DNI: 42067067
Gravedad (1 a 5): 4
Ingresó a guardia: Gravedad 4 - Ledesma, Alvaro (DNI 42067067)

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 8
Se quitó de guardia a Ledesma, Alvaro (DNI 42067067)

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 5
Atendiendo: Turno N° 1 - Ledesma, Alvaro (DNI 42067067)

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 2
DNI: 42067067
Ledesma, Alvaro (DNI 42067067)

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 4 
No hay turnos en espera

=== CLÍNICA - GRUPO 7 ===
1. Registrar paciente
2. Buscar paciente por DNI
3. Dar turno regular
4. Ver próximo turno
5. Atender turno
6. Ingresar urgencia
7. Atender urgencia
8. Deshacer última acción
0. Salir
Opción: 0
Hasta luego.
```

## Pruebas realizadas

Se ejecutaron **17 casos de prueba** en orden dentro de una misma sesión, validando caminos felices, validaciones de datos vacíos/fuera de rango, duplicados en `HashSet`, orden FIFO en `Queue`, prioridad y desempate en `PriorityQueue`, reversión LIFO en `Stack` y entradas no numéricas en consola.

* Ver el detalle completo en: [`pruebas/casos-de-prueba.md`](./pruebas/casos-de-prueba.md)
* Ver el seguimiento diario en: [`bitacora.md`](./bitacora.md) y en la carpeta [`/kanban`](./kanban/)

## Conclusiones
* **Qué aprendimos:** Comprendimos cómo elegir cada estructura de datos en función de una regla de negocio concreta y su costo algorítmico real (O(1) en `HashSet` y `ArrayDeque`, O(log n) en `PriorityQueue`), además de entender por qué un `PriorityQueue` requiere objetos inmutables en sus claves de ordenamiento (`Urgencia`) y un criterio explícito de desempate para garantizar estabilidad. También aplicamos el patrón Fachada (`Clinica`) para desacoplar la lógica de negocio de la interfaz de consola.
* **Qué dificultades tuvimos:** Durante el desarrollo paralelo en Git nos encontramos con dependencias entre clases que aún no habían sido subidas por otros integrantes (como `Turno` y `Paciente` al programar `Accion` y `Clinica`), además de archivos duplicados fuera de sus directorios de paquete. Lo resolvimos coordinando el contrato de métodos, reestructurando los paquetes con Git y documentando el avance diario en el tablero Kanban.
* **Qué mejoraríamos:** En una siguiente iteración incorporaríamos un `Map<String, Paciente>` (Clase 7) para que la búsqueda y recuperación del objeto `Paciente` por DNI también sea O(1) en lugar de recorrer el `HashSet` en O(n), y agregaríamos persistencia en archivos CSV (Clase 10) para conservar el padrón entre sesiones.
