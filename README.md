# Sistema de Administración de Citas Médicas

Este proyecto es una aplicación en **Java** que simula la gestión de un consultorio clínico, permitiendo:

- Dar de alta doctores.
- Dar de alta pacientes.
- Crear citas con fecha y hora.
- Relacionar cada cita con un doctor y un paciente.
- Cancelar citas existentes.
- Control de acceso mediante login de administrador.

---

## 🚀 Instalación y configuración

### Requisitos previos
- [Java JDK 17 o superior](https://adoptium.net/)
- [Apache Maven](https://maven.apache.org/) (para compilar y gestionar dependencias)
- Un editor/IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [VS Code](https://code.visualstudio.com/)

### Pasos de instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/clinicapp.git
   cd clinicapp

Uso del programa

Login inicial

Usuario: admin

Contraseña: admin123

Opciones del menú ADMIN

1 Alta Doctor → Registrar nombre y especialidad.

2 Alta Paciente → Registrar nombre, email y teléfono.

3 Crear Cita → Seleccionar doctor, paciente y horario (valida solapamientos).

4 Listar Citas → Muestra todas las citas programadas.

5 Cancelar Cita → Cambia el estado de una cita a CANCELADA.

6 Listar Doctores → Muestra doctores registrados.

7 Listar Pacientes → Muestra pacientes registrados.

8 Cerrar Sesión → Regresar a login.

9 Salir → Finaliza el programa.

# Validaciones

No se permiten citas en el pasado.

Un doctor no puede tener citas que se solapen en horario.

Se requiere que el doctor y el paciente existan antes de crear una cita.

# Créditos

Desarrollado por Hever González

Proyecto académico para el curso de Programación en Java

Con el apoyo de diagramas de flujo, pseudocódigo y diseño orientado a objetos.

# Licencia

Este proyecto está bajo la licencia MIT.
Puedes usar, modificar y distribuir este software libremente, siempre y cuando se incluya el aviso de copyright y la licencia original.

Ver el archivo LICENSE
 para más detalles.
