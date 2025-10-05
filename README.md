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

# ConsultApp

**ConsultApp** es una aplicación Java de consola que permite gestionar clientes, consultores y citas de manera sencilla.  
Está pensada para instituciones académicas, consultorías o servicios profesionales que deseen mantener una agenda de reuniones sin depender de bases de datos externas.

---

## 🧩 Instalación y configuración

### Requisitos previos
- **Java 11 o superior** (se recomienda Java 17)
- **NetBeans IDE** o cualquier entorno compatible con proyectos Java
- Sistema operativo: Windows, macOS o Linux

### Pasos de instalación
1. Clona este repositorio o descárgalo en formato `.zip`  
   ```bash
   git clone https://github.com/tuusuario/ConsultApp.git
   ```
2. Abre **NetBeans** → *Archivo* → *Abrir proyecto...*  
   Selecciona la carpeta del proyecto `ConsultApp`.
3. Verifica que el paquete raíz sea:
   ```
   com.tecmilenio.tecmilenio.consultapp
   ```
4. Ejecuta la clase:
   ```
   src/com/tecmilenio/tecmilenio/consultapp/Main.java
   ```
5. Al iniciar por primera vez, se creará la carpeta `consultapp_data/` con archivos:
   ```
   clientes.json
   consultores.json
   citas.json
   ```
   donde se almacenan los datos.

---

## ⚙️ Uso del programa

El programa funciona mediante un menú interactivo en la consola.

### Menú principal
```
1) Alta Cliente
2) Alta Consultor
3) Listar Clientes
4) Listar Consultores
5) Crear Cita
6) Listar Citas
7) Reprogramar Cita
8) Cancelar Cita
9) Guardar
0) Salir
```

### Ejemplo de flujo básico
1. Da de alta un **cliente** y un **consultor** (opciones 1 y 2).  
2. Crea una **cita** indicando sus IDs, fechas y descripción (opción 5).  
3. Lista las citas existentes (opción 6).  
4. Si intentas crear una cita con horario empalmado, el sistema **detectará el conflicto**.  
5. Usa la opción 9 para guardar manualmente (o la 0 para salir guardando automáticamente).

Los datos se almacenan como archivos JSON en el directorio `consultapp_data`, por lo que no se requiere base de datos.

---

## 👨‍💻 Créditos

**Autor:** Hever González  
**Institución:** Tecmilenio – Ingeniería en Computación Administrativa  
**Proyecto:** Sistema de gestión de citas (ConsultApp)

---

## 📜 Licencia

Este proyecto se distribuye bajo la licencia **MIT**, lo que permite su uso, modificación y redistribución con fines académicos o profesionales, siempre que se incluya el aviso de derechos de autor original.

```
MIT License

Copyright (c) 2025 Ever

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the “Software”), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 🧠 Próximas mejoras (opcional)
- Interfaz gráfica (Swing)
- Exportación de agenda en CSV
- Recordatorios automáticos
- Integración con base de datos SQLite

---

> 📂 **Ubicación recomendada:** coloca este archivo en la raíz del repositorio, con el nombre `README.md`.





