# Horror helper

![image](https://i.imgur.com/OMsIuEu.png)

## Tabla de contenidos

1. [Descripción](#descripción)
2. [Comandos](#comandos)
3. [C#](#C#)

***

### Descripción

Horror helper es un bot hecho en C# destinado a ayudar
a los usuarios de Horror in the woods para mejorar su experiencia

***

### Comandos

* Comando Progress

El comando progress toma una operación y un canal para ejecutar dicha operación, sea añadir, resetar o colocar el número del canal de progreso.

Este comando solo puede ser ejecutado por el dueño del canal.

```
%progress [operación] [canal] [cantidad]

%p [operación] [canal] [cantidad]
```

**LOS ARGUMENTOS DE "OPERACION" Y "CANAL" SON OBLIGATORIOS**

> Introducir una operación no valida ocasiona un error

> Introducir palabras en el argumento de cantidad ocasiona un error

* Ejemplos de comando progress

```
%p add 1 50 // Añade 50 al canal de en la posición 1 de progresos

%p reset 3 // Reseta el canal en la posición 3 de progresos

%p set 2 8 // Coloca el número de progreso del canal en la posición 2 en 8

%p add 1 // Si no se especifica la cantidad a añadir, se añadira 1 al canal respectivo
```

* Comando Hug

El comando hug abraza a la persona mencionada, esta persona decide si la acepta o la rechaza.

```
%hug [mención]

%a [mención]
```

**EL ARGUMENTO DE "MENCIÓN" ES OBLIGATORIO**

> No introducir un argumento ocasiona en error
 
> Introducir un argumento erroneo ocasiona error

* Funcionameinto de comando hug
***

### C#

Lenguaje de programacón orientado a objetos basado en otro lenguaje de programación llamado "C" y desarollado por Microsoft.

Este lenguaje es altamente conocido por el motor de juegos Unity

En esta parte se te explicara como funciana C# y como programar en el (Al menos lo más basico) para que de una vez entiendas C# y puedas ayudar 
a detectar errores con más facilidad.
