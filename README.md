<div align="center">
  <h1>☕ Desarrollo Orientado por Objetos (DOPO/POOB)</h1>
  
  ![Java](https://img.shields.io/badge/Language-Java-orange) 
  ![Status](https://img.shields.io/badge/Status-Educational-blue) 
  ![Paradigm](https://img.shields.io/badge/Paradigm-OOP-green)

  <p><strong>Guía completa desde fundamentos hasta patrones de diseño</strong></p>
</div>

Bienvenido al repositorio de **DOPO/POOB**. Esta guía condensa todo el recorrido desde los fundamentos de la programación orientada a objetos hasta patrones de diseño avanzados, estructurado sesión por sesión.

---

## 🗺️ Mapa de Navegación del Curso

### 🟢 Fase 1: Los Cimientos (La Materia Prima)

#### 📂 S02: Introducción, Clases y Objetos
Entendiendo la diferencia fundamental entre el plano y la construcción.
* **Clases:** El molde o plantilla para crear software (ej. `public class Coche {}`).
* **Objetos:** La instancia viva que ocupa memoria (ej. `new Coche("Rojo")`).
* **Herramientas:** Uso del entorno BlueJ para visualización (ej. *inspeccionar objetos en tiempo real*).
* **Estructura:** Definición de atributos y métodos (ej. *color, velocidad* y *acelerar()*).

#### 📂 S03: Encapsulamiento y Control
Protegiendo la información y controlando el flujo.
* **Modificadores de Acceso:** Quién puede ver qué (ej. `private` para secretos, `public` para la fachada).
* **Tipos de Datos:** Primitivos vs. Referencia (ej. `int` vs. `String`).
* **Estructuras de Control:** Toma de decisiones e iteraciones (ej. `if`, `switch`, `for`, `while`).
* **Encapsulamiento:** Ocultamiento de la implementación (ej. *usar getters/setters en lugar de acceder a variables directas*).

#### 📂 S04 (POOB): Interacción y Ciclo de Vida
El software no es solo código, es un proceso de ingeniería.
* **Ciclo de Desarrollo:** Pasos lógicos para construir software (ej. *Requisitos -> Análisis -> Diseño -> Pruebas*).
* **UML vs. Código:** Modelado visual antes de programar (ej. *Diagramas de Clase representando relaciones*).
* **Descomposición:** Funcional vs. Orientada a Objetos (ej. *dividir por tareas vs. dividir por entidades responsables*).

---

### 🔵 Fase 2: Relaciones y Polimorfismo (El Núcleo)

#### 📂 S07: Relaciones y Herencia
Cómo los objetos interactúan y comparten rasgos.
* **Asociación:** Objetos que colaboran (ej. *un `Estudiante` tiene un `Curso`*).
* **Herencia:** Reutilización de código basada en jerarquías (ej. `class Gato extends Animal`).
* **Sobreescritura:** Cambiar el comportamiento heredado (ej. *un `Perro` ladra diferente a como un `Animal` genérico hace ruido*).
* **Palabra clave `super`:** Acceso al padre (ej. *llamar al constructor de la superclase*).

#### 📂 S08: Abstracción e Interfaces
Definiendo contratos y plantillas obligatorias.
* **Clases Abstractas:** No se pueden instanciar, solo heredar (ej. `Clase Figura` no existe físicamente, pero `Circulo` sí).
* **Interfaces:** Contratos de comportamiento puro (ej. `interface Volador` obliga a implementar `despegar()`).
* **Polimorfismo:** Tratar objetos distintos de forma uniforme (ej. *una lista de `Animal` que contiene `Gatos` y `Perros`*).

---

### 🟠 Fase 3: Robustez y Estructuras (El Sistema)

#### 📂 S09: Manejo de Excepciones
Haciendo el software resistente a errores.
* **Jerarquía Throwable:** Diferencia entre errores fatales y manejables (ej. `Error` vs. `Exception`).
* **Bloques de Control:** Captura y manejo (ej. `try` para intentar, `catch` para atrapar el error).
* **Excepciones Propias:** Creación de errores personalizados (ej. `SaldoInsuficienteException`).
* **Buenas Prácticas:** No ignorar errores silenciosamente (ej. *siempre loguear o notificar en el catch*).

#### 📂 S12: Interfaz Gráfica (GUI)
Interacción visual con el usuario.
* **Componentes Swing:** Elementos visuales básicos (ej. `JButton`, `JTextField`, `JPanel`).
* **Manejo de Eventos:** Reaccionar a acciones (ej. `ActionListener` cuando se hace clic).
* **Patrón MVC:** Separación de responsabilidades (ej. *Modelo: Datos, Vista: Ventana, Controlador: Lógica*).

#### 📂 S13: Colecciones
Más allá de los arreglos estáticos.
* **Framework de Colecciones:** Estructuras dinámicas de Java (ej. `java.util.Collection`).
* **Tipos Principales:** Listas, Conjuntos y Mapas (ej. `ArrayList` ordenado, `HashSet` sin duplicados, `HashMap` clave-valor).
* **Generics:** Seguridad de tipos en colecciones (ej. `List<Estudiante>` evita meter un `Profesor` por error).
* **Iteradores:** Recorrer colecciones eficientemente (ej. *usar `foreach` o `Iterator`*).

#### 📂 S14: Entrada y Salida (I/O)
Persistencia de datos y comunicación externa.
* **Flujos (Streams):** Canales de lectura/escritura (ej. `InputStream` para bytes, `Reader` para caracteres).
* **Manejo de Archivos:** Leer y escribir texto (ej. `File`, `Scanner`, `PrintWriter`).
* **Serialización:** Guardar objetos completos (ej. *convertir un objeto `Juego` en un archivo binario `.dat`*).

---

### 🟣 Fase 4: Arquitectura y Calidad (Nivel Experto)

#### 📂 S15 + Patrones: Patrones de Diseño
Soluciones probadas a problemas recurrentes ("La lengua franca de los desarrolladores").
* **Patrones Creacionales:** Controlan la creación de objetos (ej. **Singleton**: *garantizar una sola instancia de `BaseDeDatos`*).
* **Patrones GRASP:** Principios de asignación de responsabilidades (ej. **Experto**: *la clase que tiene los datos debe procesarlos*, **Creador**: *quién debe instanciar a quién*).
* **Patrones GoF:** El catálogo clásico (ej. **Factory**, **Observer**, **Composite**).
* **Arquitectura:** Organización de alto nivel (ej. *Capas, Cliente-Servidor*).

---

## 🛠️ Tecnologías Usadas
* **Lenguaje:** Java ☕
* **IDE Recomendado:** BlueJ (para aprendizaje), Eclipse/IntelliJ (para producción).
* **Formatos:** PDF, UML, Código Fuente.

> "El código es leído más veces de las que es escrito. Escribe pensando en el lector." 📖

---
*Repositorio generado para consolidar los conocimientos de DOPO/POOB 2025-02.*

*TODO PFD PROPORCIONADO EN ESTE REPOSITORIO NO SE DA AUTORIZACIÓN DE DISTRIBUIRLO, ES PROPIEDAD DE LA UNIVERSIDAD ESCUELA COLOMBIANA DE INGENIERIA JULIO GARAVITO*
