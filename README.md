# Servicio REST Usuario
## _Servicio API RESTful de creación de usuarios_

## Adecuar Ambiente
- Clonar los fuentes del repositorio. mediante  GIT 
- Abrir los fuentes desde IDE Intellij
- Intalar en Intellij el plugin de lombok.
- Activar en Intellij en las configuraciones de Procesador de Anotaciones la opción Habilitar Anotaciones.
- Ejecutar el servicio desde IDE

## Invocar Servicio.
El servicio se encuentra expuesto por el puerto 8080 mediante metodo POST y recibe objetos formato json.
Ejemplo:

```json
url: localhost:8080/user/create
{
"name": "Juan Rodriguez",
"email": "juanro@driguez.org9",
"password": "2Rhh",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
},
{
"number": "234234234",
"citycode": "1",
"contrycode": "57"
}
]
}

```

Ejemplo de response:

```json
{
    "id": "fea0c809-74fe-45d8-b2a3-81ab4418a4a6",
    "name": "Juan Rodriguez",
    "email": "juanro@driguez.cop",
    "password": "2Rh2h",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "234234234",
            "cityCode": "1",
            "countryCode": "57"
        }
    ],
    "created": "11-06-2021;",
    "modified": "11-06-2021;",
    "last_login": "11-06-2021;",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdCI6MTYzNjIxMTkyNH0.BSVsAJsHplscBN_5ZRz8V8c1_EUgz82z103htFlJuy8",
    "isActive": true
}
```


## Tecnologias

- Spring Boot.
-  H2 Base de Datos en memoria.
- Gradle.
- Hibernate.
- Java 8


## Recursos Adicionales

- Postman para las peticiones. User\src\test\resources\user.postman_collection.json
- Soap UI para las pruebas de aceptación User\src\test\resources\User.xml
- Jmeter para las pruebas de performance.  User\src\test\resources\UserPerformance.jmx
- Docker para contenerizar el servicio. User\docker\Dockerfile
- Script sql para la base de datos H2  User\src\main\resources\data.sql
- Manual Paso a Paso User\documentation\Manual de Adecuación de Ambiente y Ejecución.docx
- Diagrama de secuencia User\documentation\diagrama de secuencia.png
- Diagrama de componente User\documentation\diagrama de componente.png
- Diagrama de base de datos User\documentation\diagrama de base de datos.png


## URLs

| Plugin | README |
| ------ | ------ |
| Postman | https://www.postman.com/downloads/ |
| Soap UI | https://www.soapui.org/downloads/soapui/ |
| Jmeter | https://jmeter.apache.org/download_jmeter.cgi |
| Docker | https://www.docker.com/products/docker-desktop |

