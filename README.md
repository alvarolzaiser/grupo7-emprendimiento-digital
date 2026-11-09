# grupo7-emprendimiento-digital
Simulador por consola de una Clínica. Proyecto académico en Java que implementa Set, Queue y Stack para la administración básica de turnos y atención médica.

**Integrantes:**
- Ledesma (Usuario GitHub: @alvarolzaiser)
- 
- 

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
1. Clonar el repositorio: `git clone https://github.com/alvarolzaiser/grupo7-emprendimiento-digital.git`
2. Abrir en Eclipse / IDE de preferencia como proyecto Java existente.
3. Ejecutar la clase `clinica.Main`.

## Funcionalidades
- Registro y validación de pacientes sin duplicados.
- Poner en colas y atender turnos por orden cronológico.
- Ingreso y atención de urgencias por nivel de prioridad.
- Módulo de reversión (deshacer última acción).
