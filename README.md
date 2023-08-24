# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

---
##  Primeros Pasos:



## 🖥️ Tecnologías Utilizadas:

- Java
- Eclipse
- IntelliJ IDEA
- Biblioteca JCalendar
- MySql Hosting (https://www.freemysqlhosting.net/)
- Plugin WindowBuilder </br>

---
## ⚠️ Importante! ⚠️

☕ Use Java versión 8 o superior para compatibilidad. </br>
📝 Recomendado usar el editor de Eclipse para compatibilidad con la Interfaz Gráfica.(Plugin WindowBuilder)</br>
❓ Al usar MySql Hosting, las peticiones pueden hacer que el aplicativo se trabe.</br>

## ⬇️ Download

### Cómo descargar:

#### 🔹 Fork

1 - Haz clic en la opción <strong>Code</strong>. Se mostrarán tres formas de instalar el repositorio en su máquina, y destacamos dos:

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/173166461-e62d9704-98d5-4773-a60e-57d5729575ae.png">
</p></br>

#### 🔹 Clonar o descargar el ZIP

1 - Para clonar, simplemente copia el <em>url</em> resaltado en la imagen y ubicado justo debajo del HTTPS, crea una carpeta en tu computadora, abre el <em>cmd</em> o el <em>git bash</em> dentro de esa carpeta y luego ingresa el comando <strong>git clone</strong> y con el botón derecho del mouse dentro del terminal haz click en la opcion <strong>Paste</strong> para pegar el <em>url</em> y presiona <em>Enter</em>.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/173256523-79d38ee2-8668-435c-b31a-ac6ba78bb813.png">
</p>

2 - La segunda opción es descargar el código en un paquete <strong>"zipado"</strong> y extraer la carpeta a tu computadora.
</br></br>

## 🚧 Proyecto

#### Al clonar o descargar el proyecto tendra las siguientes vistas:
1. Vista Inicial
<p align="center" > 
Esta vista es la primera que se muestra al ejecutar el aplicativo. </br> Seleccionar el botón <strong>LOGIN</strong> para ingresar al aplicativo.
     <br><img width="700" heigth="700" alt="Vista Inicial" src="https://i.postimg.cc/KYj9jWHr/principal.png">
</p>

2. Vista de Login
<p align="center" > 
Aqui se debe ingresar el usuario y contraseña para ingresar al aplicativo. </br>
Con credenciales que esten registradas en la base de datos.
    <br> <img width="700" heigth="700" src="https://i.postimg.cc/RV1pDKtf/login.png" alt="Vista login">
</p>

3. Vista de Home
<p align="center" >
Al ingresar al aplicativo se muestra esta vista. </br>
Donde muestra las opciones de registro de reservas, huéspedes y busqueda de registros.
     <br><img width="700" heigth="700" src="https://i.postimg.cc/SRC1qztB/home.png" alt="Vista Home">
</p>

4. Vista Registro de Reservas
<p align="center" >
Se debe seleccionar la fecha de ingreso y salida, el tipo de medio de pago. </br>
Al seleccionar el botón <strong>Calcular precio</strong> se calcula el valor. </br>
Luego seleccionar el botón <strong>Siguiente</strong> para guardar la reserva.
    <br> <img width="700" heigth="700" src="https://i.postimg.cc/tgcBx500/reserva.png" alt="Vista Reservas">
</p>

5. Vista Registro de Huéspedes
<p align="center" >
Si la reserva se guardo correctamente se muestra esta vista. </br>
Aqui se debe ingresar los datos del huésped. </br>
Al seleccionar el botón <strong>Guardar</strong> se guarda el huésped.
Y se redirige a la vista de Home.
    <br><img width="700" heigth="700" src="https://i.postimg.cc/qvkDVqcF/huesped.png" alt="Vista Huéspedes">
</p>

6. Vista de Busqueda de Reservas y Huéspedes
<p align="center" >
Se mostrará todos los registros de reservas y huéspedes. </br>
Se puede buscar por el apellido del huésped o por el número de reserva. </br>
Para editar o eliminar un registro se debe seleccionar el registro y luego seleccionar el botón <strong>Editar</strong> (Luego de hacer la edición de la fila) o <strong>Eliminar</strong>.
La tabla se actualiza automaticamente.
</p>
    <img src="https://i.postimg.cc/zDgcT9Xx/huespedes.png" alt="buscar">
    <img src="https://i.postimg.cc/1z3d6XfF/buscar-Apellido.png" alt="buscar por apellido">
    <img src="https://i.postimg.cc/C1WrW4JC/buscarId.png" alt="buscar por reserva">

7. Vista de Perfil
<p align="center" >
Se muestra el perfil del usuario que ingreso al aplicativo. </br>
Con sus datos personales, que se encuentran en la base de datos </br>
Desde aquí tiene la opción de cerrar sesión, o al seleccionar la <strong>X</strong> en cualquier vista puede hacer lo mismo.
    <br> <img width="700" heigth="700" src="https://i.postimg.cc/151YBJ8s/perfil.png" alt="Vista Perfil">
</p>

8. Vistas de Error y Exito
<p align="center" >
Se muestran estas vistas cuando se produce un error o cuando se realiza una acción correctamente.
        <br> <img width="400" src="https://i.postimg.cc/QMYyqDTy/error.png">
        <img width="400" src="https://i.postimg.cc/Y94ynPNq/exito.png" alt="Vista Exito">
</p>


### Modelado de tablas:

EL aplicativo necesita de 3 tablas: <strong>Usuarios,</strong> <strong>Reservas</strong> y <strong>Huéspedes</strong>. La tabla de huéspedes debe contener la clave externa <em>(foreign key)</em> <strong>idReserva</strong>.

<p align="center" >
     <img width="500" src="https://github.com/alura-challenges/challenge-one-alura-hotel-latam/assets/55146667/3b7e4a5b-9fdc-448d-8d3d-c2800dc5b910">
</p>

### 📝 Nota:
Actualizar las credenciales de acceso a la base de datos en la clase <strong>ConexionFactory</strong> ubicada en el paquete <strong>factory</strong>.
#### 🔹 Se debe importar el archivo .sql o crear las tablas manualmente:

```sql
CREATE DATABASE `hotel_alura`;
USE `hotel_alura`;
CREATE TABLE `usuarios` (
    `id` int NOT NULL AUTO_INCREMENT,
    `usuario` varchar(20) NOT NULL,
    `contrasena` varchar(20) NOT NULL,
    `nombre` varchar(50) NOT NULL,
    `apellido` varchar(50) NOT NULL,
    `email` varchar(80) NOT NULL,
    `telefono` varchar(25) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `reservas` (
    `id` int NOT NULL AUTO_INCREMENT,
    `fecha_entrada` date NOT NULL,
    `fecha_salida` date NOT NULL,
    `valor` double NOT NULL,
    `forma_pago` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `huespedes`(
    `id` int NOT NULL AUTO_INCREMENT,
    `nombre` varchar(50) NOT NULL,
    `apellido` varchar(50) NOT NULL,
    `fecha_nacimiento` date NOT NULL,
    `nacionalidad` varchar(70) NOT NULL,
    `telefono` varchar(25) NOT NULL,
    `id_reserva` int NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_reserva`) REFERENCES `reservas`(`id`)
) ENGINE=InnoDB;
```
<p style="color: chartreuse; font-size: larger; text-align: center">
Realizados todos estos pasos, ya puede ejecutar el aplicativo.
</p>
<p style="color: red; font-size: larger; text-align: center">
Credenciales de acceso al aplicativo: 
</p>
<p style="color: darkgoldenrod; font-size: larger; text-align: center">
<strong>usuario: </strong> admin <br>
<strong> contraseña: </strong> admin12345
</p>

👨🏻‍💻 <strong>Emerson Blanco</strong></br>
<a href="https://www.linkedin.com/in/emerson-blanco-alfonso/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>


🧡 <strong>Oracle</strong></br>
<a href="https://www.linkedin.com/company/oracle/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

💙 <strong>Alura Latam</strong></br>
<a href="https://www.linkedin.com/company/alura-latam/mycompany/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
