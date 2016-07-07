# KeyValueStoreSO2016
Proyecto parcial de la materia Sistemas Operativos 1er término 2016

## Requisitos:
Para la correcta compilación y ejecución de los modulos del proyecto se deberá tener instalado o siguiente:
- Ant
- Jdk

## Compilación
Antes de la ejecución de los módulos del proyecto se deberá realizar la compilación de los mismos, para ello se deberan seguir los siguientes pasos:

1. Dentro del directorio **KeyValueStoreSO2016/libreria_cliente/** ejecutar primero el comando **ant compile** y luego el comando **ant jar**.
2. Dentro del directorio **KeyValueStoreSO2016/programa_servidor/** ejecutar primero el comando **ant compile** y luego el comando **ant jar**.
3. Dentro del directorio **KeyValueStoreSO2016/programa_cliente/** ejecutar primero el comando **ant compile** y luego el comando **ant jar**.

## Ejecución
Para la ejecución del proyecto se debera correr primero el programa servidor, y luego el programa cliente ejecutando los siguientes comandos:

1. Dentro del directorio **KeyValueStoreSO2016/programa_servidor/** ejecutar primero el comando **ant run -Darg0=_\<número de puerto\>_**.
1. Dentro del directorio **KeyValueStoreSO2016/programa_cliente/** ejecutar primero el comando **ant run -Darg0=_\<dirección ip\>_ -Darg1=_\<número de puerto\>_**.
