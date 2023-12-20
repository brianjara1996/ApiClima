## Contribución

Brian Nicolas Jara (Developer)

Email: brianjara1996@gmail.com

# ApiClima

Servicio Api para consultar el clima actual de una ciudad consultando por su codigo postal

## Instalación

Pasos para instalar el proyecto:

1. Clona el repositorio, en la siguientes url: 
2. Abrir el proyecto con Eclipse(o cualquier otro IDE) y ejecutarlo con Java 1.8 

## Uso

Cómo usar el proyecto:

1. Ejecutar la clase ApiclimaApplication.java para iniciar el proyecto
2. el Proyecto se Inicia en el puerto 8080
3. Ejecuar la URL con el Codigo postal de la ciudad que desea consultar: 
http://localhost:8080/clima/tiempo-actual/{codigoPostal}

4: La aplicacion le devuelve un Json con la ciudad consultada, su temperatura, su codigo postal y una ubicacionKey que seria el codigo de ubicacion que usa accuweather para obtener la ciudad 


## Estructura de Archivos

En el Archivo Application.properties estan las variables de entorno configuradas para apuntar a la api de accuweather
y la configuracion de la Base de datos H2
