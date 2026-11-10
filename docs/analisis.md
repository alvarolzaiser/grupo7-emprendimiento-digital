# Documento de Análisis y Requerimientos - Clínica

## 1. Descripción del Negocio y Problemática
La Clínica atiende consultas y urgencias médicas. Actualmente, la recepción experimenta una situación de saturación y desorganización operativa por los siguientes motivos:
- Errores de carga y duplicidad en los registros de pacientes.
- Falta de un criterio estricto para diferenciar turnos programados por llegada frente a emergencias de gravedad.
- No tienen una forma de revertir las fallas administrativas sin romper la aplicacion.

El objetivo del sistema es automatizar estos flujos mediante la aplicación de estructuras de datos lineales y conjuntos en Java.

---

## 2. Requerimientos Funcionales

### RF-01: Gestión y Validación de Pacientes (Padrón Único)
> **Responsable:** Hernández  
> **Estructura asignada** 'HashSet<Paciente>' para almacenar pacientes sin duplicados, utilizando el DNI como identificador único.
El sistema debe permitir registrar nuevos pacientes ingresando sus datos personales (DNI, nombre y apellido).
Antes de registrar un paciente, el sistema debe verificar que no exista otro paciente con el mismo DNI en el padrón.
- El sistema debe impedir el registro de pacientes duplicados, garantizando que cada DNI corresponda a un único paciente.
- El sistema debe permitir consultar si un paciente se encuentra registrado mediante su DNI. 
- El sistema debe permitir buscar un paciente por DNI y recuperar sus datos personales.
- Si el DNI ya se encuentra registrado, el sistema debe informar que el paciente ya existe y evitar un nuevo registro.

---

### RF-02: Turnos de Consulta Regular (Orden de Llegada)
> **Responsable:** Hinojosa  
> *(lo completa Hinojosa, solo dejo la estructura)*

---

### RF-03: Atención de Urgencias Médicas (Prioridad por Gravedad)
- **Responsable:** Ledesma
- **Estructura asignada:** `PriorityQueue<Paciente>` ordenada mediante un `Comparator` por nivel de gravedad.
- El sistema debe admitir el ingreso inmediato de pacientes a la sala de urgencias asignándoles un nivel de gravedad del 1 al 5 (donde 5 representa lo mas grave / código rojo y 1 consulta leve).
- La atención de urgencias debe priorizar siempre al paciente con mayor numero o nivel de gravedad, sin considerar el orden de llegada.
- Ante igual nivel de gravedad entre dos pacientes, se toma el criterio de orden de llegada.
- Al solicitar "Atender siguiente urgencia", el sistema remueve al paciente más grave de la cola de prioridad y expone sus datos (el del pacienta que ya fue atendido).

### RF-04: Módulo de Reversión (Historial / Deshacer)
- **Responsable:** Ledesma
- **Estructura asignada:** `ArrayDeque<Accion>` utilizada como "Pila" (Stack / LIFO).
- El sistema debe registrar de forma automática cada operación administrativa de ingreso (dar turno normal, registrar urgencia o alta de paciente) encapsulada en un objeto `Accion`.
- La función "Deshacer" debe desapilar la última acción realizada y revertir su efecto en el sistema (eliminar de la cola el turno otorgado erróneamente o remover el registro). Cada acción es justamente una Acción efectuada y se guarda en la Pila para poder deshacerse desde la ultima hasta la primera.
- Si el usuario solicita deshacer y la pila se encuentra vacía, el sistema debe informar "No hay acciones para deshacer" sin interrumpir la ejecución, es decir, debe fallar de manera correcta.

---

## 3. Historias de Usuario

### Módulo: Urgencias e Historial (Ledesma)

| ID | Rol | Quiero... | Para... | Criterio de Aceptación |
|---|---|---|---|---|
| **HU-04** | Enfermero | Ingresar un paciente a la guardia asignando su nivel de gravedad (1 a 5) | Que reciba atención acorde a la gravedad de su estado y no al orden de llegado o cuantas personas había antes | El paciente se guarda en la `PriorityQueue` y se reordena según su nivel de gravedad. |
| **HU-05** | Médico de Guardia | Atender la próxima urgencia | Tratar inmediatamente al siguiente paciente con mayor riesgo o con la gravedad mas alta | El sistema extrae al paciente con el índice de gravedad más alto registrado. |
| **HU-06** | Recepcionista | Deshacer la última acción administrativa realizada por error | Corregir una equivocación operativa sin alterar el resto de los datos | Se desapila el último evento del `Stack` y se revierte el cambio en la estructura correspondiente. |

---

### Módulo: Pacientes y Padrón Único (Hernández)

| ID | Rol | Quiero... | Para... | Criterio de Aceptación |
|---|---|---|---|---|
| **HU-01** | Recepcionista | Registrar un nuevo paciente ingresando su DNI, nombre y apellido | Incorporarlo al padrón único de la clínica y disponer de sus datos para futuras consultas | El sistema verifica que el DNI no esté registrado previamente. Si no existe, guarda al paciente en el `HashSet`. Si ya existe, rechaza el registro para evitar duplicados. |
| **HU-02** | Recepcionista | Buscar un paciente mediante su DNI | Consultar sus datos personales y verificar si se encuentra registrado en la clínica | El sistema busca al paciente por su DNI en el padrón. Si existe, devuelve sus datos personales; de lo contrario, informa que no se encontró ningún paciente. |
---

### Módulo: Turnos Regulares y Menú (Hinojosa)
> *(lo completa Hinojosa, solo dejo la estructura: historias de usuario HU-03 y HU-07)*
