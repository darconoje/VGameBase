# VGameBase
Proyecto Final FP Superior Desarrollo Aplicaciones Web

Manual de Uso

Para el funcionamiento del proyecto es recomendable usar el siguiente software:

-Eclipse IDE for Enterprise Java Developers  
-Apache Tomcat 8.5.53  
  
El proyecto usa dependencias con Maven, así que es necesario inicializarlo para su correcto funcionamiento.   
  
Los archivos de recursos están en la carpeta resources, hay dos opciones de crear la base de datos, la mas recomendable es automáticamente mediante el script SQL ya que mediante los archivos JSON requeriría de mucho más tiempo:  
  
-El script SQL está en resources/sql  
-Los archivos JSON están en resources/json  
  
Si se toma la segunda opción (rellenar base de datos con JSON) hay que tomar algunas consideraciones:  
  
-Hay que insertar el usuario administrador manualmente antes de entrar en la página   
-Hay que cambiar el tipo de dato de la imagen en la tabla Image de tipo 'tinyblob' a 'longblob'  
-Hay que popular la base de datos en el orden correcto teniendo en cuenta las claves foráneas, por ejemplo no se puede popular la tabla Game sin antes haber rellenado la tabla Genre  
  
  
-El archivo Configuration/src/main/resources/application.properties contiene la configuración para el acceso a la base de datos, es recomendable usar el nombre vgamebase para ella, ya que el proyecto está configurado para que funcione de esta manera. En jdbc.username y en jdbc.password habrá que añadir el nombre de usuario y contraseña del usuario MySQL, respectivamente  
  
La url por defecto para acceder al sitio web con el servidor desplegado en local es http://localhost:8080/Web/login  
