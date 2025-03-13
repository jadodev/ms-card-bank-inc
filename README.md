# ms-card-bank-inc

```textplain

 📦 ms_card_bankInc (Microservicio de Tarjetas Bancarias)
├── 📂 src
│   ├── 📂 main
│   │   ├── 📂 java/com/jonathanUrrego/ms_card_bankInc  # Paquete base del microservicio
│   │   │   ├── 📂 application          # Capa de aplicación (Casos de uso y servicios)
│   │   │   │   ├── 📂 dto              # Data Transfer Objects (DTOs)
│   │   │   │   │   ├── CardPaymentDto.java   # DTO para pagos con tarjeta
│   │   │   │   │   ├── CardRechargeDto.java  # DTO para recargas con tarjeta
│   │   │   │   │   ├── CardRequestDto.java   # DTO para solicitudes de tarjeta
│   │   │   │   │   ├── CardResponseDto.java  # DTO para respuestas de tarjeta
│   │   │   │   ├── 📂 service          # Lógica de negocio de la aplicación
│   │   │   │   │   ├── CardService.java      # Interfaz del servicio de tarjetas
│   │   │   │   │   ├── CardServiceImpl.java  # Implementación del servicio
│   │   │   │
│   │   │   ├── 📂 domain              # Capa de dominio (Reglas de negocio y modelos)
│   │   │   │   ├── 📂 model           # Entidades del dominio
│   │   │   │   │   ├── Card.java       # Entidad de tarjeta
│   │   │   │   │   ├── CardType.java   # Enum para tipos de tarjeta
│   │   │   │   ├── 📂 port            # Definición de puertos de la arquitectura hexagonal
│   │   │   │   │   ├── CardRepository.java  # Puerto de salida para la persistencia de tarjetas
│   │   │   │
│   │   │   ├── 📂 infraestructure     # Capa de infraestructura (Adaptadores, persistencia, API)
│   │   │   │   ├── 📂 configuration   # Configuración de la infraestructura
│   │   │   │   ├── 📂 controller      # Controladores REST
│   │   │   │   ├── 📂 persistence     # Persistencia de datos
│   │   │   │
│   │   │   ├── MsCardBankIncApplication.java  # Clase principal de Spring Boot
│   │   │
│   │   ├── 📂 resources                   # Configuraciones y archivos de recursos
│   │   │   ├── application-cloud.properties  # Configuración en la nube
│   │   │   ├── application.properties       # Configuración local
│   │
│   ├── 📂 test/java/com/jonathanUrrego/ms_card_bankInc  # Pruebas unitarias
│   │   ├── 📂 application
│   │   │   ├── CardServiceImplTest.java     # Pruebas del servicio de tarjetas
│   │   ├── 📂 domain/model
│   │   │   ├── CardTest.java                # Pruebas de la entidad Card
│   │   ├── 📂 infraestructure/persistence/adapter
│   │   │   ├── CardRepositoryAdapterTest.java  # Pruebas del adaptador de persistencia
│   │   ├── MsCardBankIncApplicationTests.java  # Pruebas de integración de la aplicación
│   │
│   ├── 📂 resources
│   │   ├── application-test.properties       # Configuración para pruebas
│
├── .gitattributes                            # Configuración de Git
├── .gitignore                                # Archivos ignorados por Git
├── Dockerfile                                # Archivo Docker para el contenedor
├── README.md                                 # Documentación del proyecto


```

# Microservicio de Backend: Gestión de tarjetas.

Este proyecto es un microservicio desarrollado en **Java** y **SpringBoot** que permite la creación y consulta de usuarios. Está diseñado para ser **altamente escalable**, forma parte de una **arquitectura de microservicios** y sigue una **arquitectura hexagonal** para garantizar la separación de responsabilidades, la facilidad de mantenimiento y la escalabilidad.

## Características Principales

- Creación, Consulta y Recarga de saldo de tarjetas de credito o debito.
- Base de datos MySQL para almacenamiento de datos.
- **Arquitectura hexagonal** para una clara separación entre la lógica de negocio y las capas de infraestructura.
- Despliegue automatizado con **GitHub Actions**, **Docker** y **AWS**.
- Configuración flexible mediante variables de entorno o archivo `application.properties`.

## Estructura del Proyecto

El proyecto está estructurado siguiendo los principios de la **arquitectura hexagonal**, lo que permite una clara separación entre:

- **Capa de Dominio:** Contiene la lógica de negocio y las entidades principales.
- **Capa de Aplicación:** Gestiona los casos de uso y la orquestación de operaciones.
- **Capa de Infraestructura:** Se encarga de la interacción con bases de datos, APIs externas y otros servicios.

Esta estructura facilita la escalabilidad, el mantenimiento y la prueba del microservicio.

## Despliegue Local con Docker

Para ejecutar el proyecto localmente, puedes utilizar Docker. A continuación, se detallan los pasos necesarios para desplegar el microservicio en tu entorno local.

### Requisitos Previos

- **Docker** instalado en tu máquina. Puedes descargarlo desde [aquí](https://www.docker.com/get-started).

### Pasos para Desplegar el Proyecto

1. **Clona el repositorio** en tu máquina local:

   ```bash
   https://github.com/jadodev/ms-card-bank-inc.git
   cd ms-card-bank-inc
  ``
2. **Configura el archivo application.properties:

   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/transaccions?createDatabaseIfNotExist=true&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

    server.port=8082
  ```
3. **Construye la imagen de Docker para el micros:

  ```bash
    docker build -t ms-cards .
  ```

4. **Levanta una instancia de MySQL con Docker:
  ```bash
    docker run -d --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=transactions -p 3306:3306 mysql:8.0
  ```

5. **Ejecuta el contenedor para iniciar el microservicio:

  ```bash
    docker run -p 8082:8082 --env-file .env ms-cards
  ```

### Una vez que el contenedor esté en ejecución, puedes acceder al microservicio en:

**http://localhost:8082


##Funcionalidad

### Este microservicio cuanta con  diferentes endpoints para crear una tarjeta de credito o debito, traer los datos de la tarjeta y agregarle dinero a esta.
