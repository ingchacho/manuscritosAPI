# 📂 Estructura del Proyecto

```text
.
├── .mvn/                      # Scripts y herramientas de Maven
├── src/
│   ├── main/
│   │   ├── java/              # Lógica de la aplicación
│   │   │   └── manuscritos/    # Paquete base
│   │   │       ├── controller/    # (ClueController) - Controladores REST
│   │   │       ├── dto/           # (StatsDto) - Objetos de Transferencia de Datos
│   │   │       ├── model/         # (ManuscriptEntity) - Entidades de persistencia
│   │   │       ├── repository/    # (ManuscriptRepository) - Interfaces de acceso a datos
│   │   │       ├── service/       # (ClueService) - Lógica de negocio
│   │   │       └── util/          # (ClueUtils) - Clases de utilidad
│   │   └── resources/         # Archivos de configuración (application.properties) y SQL (opcionales)
│   └── test/                  # Pruebas unitarias
├── .gitignore                 # Archivos a ignorar por Git
├── pom.xml                    # Archivo de configuración de Maven (dependencias)
└── README.md                  # Documentación del proyecto (Este archivo)
```
### ✔ Incluir dentro del código:
```text
Código del endpoint /clue

Código del endpoint /stats

Repositorio JPA con H2

Entidad ManuscriptRecord

Lógica del algoritmo containsArtifactClue

Configuración H2 en application.properties
```
Ejemplo de configuración H2:
```text

spring.datasource.url=jdbc:h2:mem:clueDB
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```
### 📘 Clue Artifact API – README
  ## 🧙‍♂️ Descripción
```text
API REST basada en Java + Spring Boot que analiza manuscritos antiguos para determinar si contienen pistas sobre artefactos mágicos.
Incluye:
Nivel 1: Algoritmo para detectar 4 letras consecutivas en horizontal/vertical/diagonal
Nivel 2: API REST /clue
Nivel 3: Persistencia con H2 + endpoint /stats
```
### 🚀 1. Cómo ejecutar localmente
```text
Requisitos:
Java 17
Maven 3.8+
IntelliJ IDEA (opcional)
```

##Comandos:
```text
mvn clean package
java -jar target/clue-api-1.0.0.jar
```
## La API correrá en:
```text
http://localhost:8080
```
### 📡 2. Endpoints
## POST /clue
```text
Verifica si el manuscrito contiene la pista.
```

## Request JSON:
```text
{
  "manuscript": ["RTHGQW", "XRLORE", "NARURR"]
}
```
## Respuestas:
```text
200 OK → pista encontrada
403 Forbidden → sin pista
```

## GET /stats

Devuelve estadísticas acumuladas:
```text
{
  "count_clue_found": 40,
  "count_no_clue": 100,
  "ratio": 0.4
}
```

# 🗄 3. Base de datos
```text
Se usa H2 en memoria, activada por defecto.
URL consola (opcional):

http://localhost:8080/h2-console
```

# 🌍 4. Despliegue en AWS EC2 y Ambiente de desarrollo local:
```text
URL pública:

URL API POST 
http://3.17.60.1:8080/clue
http://localhost:8080/clue

URL API GET 
http://3.17.60.1:8080/stats
http://localhost:8080/stats
```

# 5. Consideraciones y decisiones de diseño e implementación
```text
Para esta solución se eligió una arquitectura ligera en Spring Boot desplegada sobre AWS EC2 porque
el enunciado exige soportar cargas extremadamente variables desde 100 hasta 1 millón de peticiones
por segundo, lo cual requiere control total del entorno, capacidad de tunear el servidor, usar autoscaling
y evitar los cold starts y límites de concurrencia propios de AWS Lambda. Además, se escogió EC2 en capa
gratuita porque permite un despliegue simple, directo y estable sin necesidad de servicios adicionales ni
complejidad operacional. Por otra parte, se utilizó base de datos H2 embebida porque el reto no exige
persistencia real en la nube y privilegia la simplicidad, H2 DB elimina la necesidad de administrar un motor
externo, reduce tiempos de despliegue y facilita la ejecución local y remota sin configuración adicional,
manteniendo el enfoque en la lógica del algoritmo y el rendimiento de la API, tal como pide la prueba técnica.
```


# 6. Información del Autor
```text
Developer by:
EFREN MORENO VALOYES
Ingeniero de sistemas – UCC
Desarrollador de software Senior Full Stack
Especialista en Ingeniería del Software – PUJ
Magister en Ingeniería del Software - PUJ
Magister en administración – UNAL
Email: efrenmorenovaloyes@gmail.com


```
