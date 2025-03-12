# Usamos una imagen oficial de Java (ajústala según la versión que necesites)
FROM openjdk:17-jdk-slim

# Establecemos el directorio de trabajo en el contenedor
WORKDIR /app

# Copiamos el archivo JAR al contenedor
COPY build/libs/*.jar app.jar

# Exponemos el puerto en el que corre la aplicación (ajústalo si es diferente)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]