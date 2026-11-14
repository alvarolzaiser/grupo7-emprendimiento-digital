# Casos de Prueba — Sistema de Recepción de Clínica (Grupo 7)

Los siguientes casos de prueba verifican el cumplimiento de los requerimientos funcionales (RF-01 a RF-04) y las reglas de negocio sobre las cuatro estructuras de datos implementadas (`HashSet`, `ArrayDeque` como `Queue`, `PriorityQueue` y `ArrayDeque` como `Stack`), ejecutados en orden dentro de una misma sesión.

| N° | Módulo | Qué pruebo | Datos de entrada | Resultado esperado | Resultado obtenido | ¿Pasó? |
| :--- | :--- | :--- | :--- | :--- | :--- | :---: |
| 1 | Set (`RegistroPacientes`) | Alta de un paciente nuevo | DNI: `111`, Nombre: `Juan`, Apellido: `Perez` | Se registra en el padrón | `Paciente registrado: Perez, Juan (DNI 111)` | Sí |
| 2 | Set (`RegistroPacientes`) | Alta con DNI repetido | DNI: `111`, Nombre: `Juan`, Apellido: `Perez` | Se informa el error y no se duplica | `Error: ya existe un paciente con DNI 111` | Sí |
| 3 | Set (`Paciente`) | Alta con DNI vacío o en blanco | DNI: `""` (vacío), Nombre: `Ana`, Apellido: `Gomez` | Se rechaza por validación de entidad | `Error: El DNI no puede estar vacío` | Sí |
| 4 | Queue (`ColaTurnos`) | Dar turno a paciente registrado | DNI: `111` | Se asigna el Turno N° 1 y se encola al final | `Asignado: Turno N° 1 - Perez, Juan (DNI 111)` | Sí |
| 5 | Queue (`ColaTurnos`) | Dar turno a DNI inexistente | DNI: `999` | Se informa error sin crear el turno | `Error: primero hay que registrar al paciente` | Sí |
| 6 | Queue (`ColaTurnos`) | Ver próximo turno sin sacarlo (`peek`) | Opción `4` (Ver próximo turno) | Muestra el Turno N° 1 sin quitarlo de la fila | `Sigue: Turno N° 1 - Perez, Juan (DNI 111)` | Sí |
| 7 | PriorityQueue (`Urgencias`) | Ingreso con gravedad fuera de rango (1 a 5) | DNI: `111`, Gravedad: `9` | Se rechaza indicando el rango válido | `Error: La gravedad debe estar entre 1 y 5` | Sí |
| 8 | PriorityQueue (`Urgencias`) | Mismo paciente dos veces en guardia | DNI: `111` ingresado dos veces seguidas | Permite el primero y rechaza el segundo ingreso | `Error: el paciente ya está en guardia` | Sí |
| 9 | PriorityQueue (`Urgencias`) | Atención por prioridad (el más grave primero) | Ingresar a Ana (grav. `3`), Luis (grav. `5`), Juan (grav. `3`) y atender | Se atiende primero a Luis (gravedad 5) | `Atendiendo urgencia: Gravedad 5 - ... (Luis)` | Sí |
| 10 | PriorityQueue (`Urgencias`) | Desempate por orden de llegada a igual gravedad | Atender siguientes tras ingresar a Ana (grav. `3`) y luego a Juan (grav. `3`) | Se atiende a Ana antes que a Juan | Primero sale Ana y luego Juan | Sí |
| 11 | Stack (`Historial`) | Deshacer turno regular pendiente | Dar Turno N° 2 y elegir Opción `8` (Deshacer) | Se desapila la acción y se quita el turno de la cola | `Se canceló el Turno N° 2 - ...` | Sí |
| 12 | Stack (`Historial`) | Deshacer turno regular que ya fue atendido | Atender Turno N° 1 y luego elegir Opción `8` (Deshacer) | Avisa que ya fue atendido y no miente | `El Turno N° 1 ... ya fue atendido; no se puede cancelar` | Sí |
| 13 | Stack (`Historial`) | Deshacer urgencia pendiente (no atendida) | Ingresar paciente a guardia y elegir Opción `8` (Deshacer) antes de atenderlo | Se desapila la acción y se quita al paciente del heap de guardia | `Se quitó de guardia a Perez, Juan (DNI 111)` | Sí |
| 14 | Stack (`Historial`) | Deshacer urgencia que ya fue atendida | Ingresar a Juan a guardia, atender la urgencia (Opción `7`) y luego elegir Opción `8` (Deshacer) | Avisa que ya fue atendido en guardia | `Perez, Juan (DNI 111) ya fue atendido en guardia; no se puede quitar` | Sí |
| 15 | Stack (`Historial`) | Deshacer altas de pacientes en orden inverso (LIFO) | Ejecutar Opción `8` (Deshacer) 3 veces sobre las altas de Juan, Ana y Luis | Se eliminan del `HashSet` en orden inverso al que fueron registrados | `Se deshizo el alta de...` (primero Luis, luego Ana, luego Juan) | Sí |
| 16 | Stack (`Historial`) | Deshacer con la pila vacía | Elegir Opción `8` (Deshacer) cuando no quedan acciones en el historial | Informa que no hay acciones sin lanzar excepción | `No hay acciones para deshacer` | Sí |
| 17 | Interfaz (`Main`) | Ingreso de letras donde se espera un número | Escribir `"abc"` en el menú de opciones o en el nivel de gravedad | Captura `NumberFormatException` y vuelve a pedir el número sin cortar el programa | `Ingresá un número válido` | Sí |