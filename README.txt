1.- Abrir el proyecto con el IDE preferido. Se sugiere el uso de IntelliJ
2.- Configurar el uso de Java 8 para el correcto funcionamiento del proyecto
3.- Ejecutar el proyecto
4.- Los servicios que cuenta el proyecto son los siguientes:
	- sign-up (post) : Método que permite crear un nuevo usuario
	- login (post) : Método que permite loguearse, retornando un jwt
	- find-users(get) : Método que permite retornar la información del usuario, previa validación del token generado en el servicio anterior (Authentication Bearer)