# Horror helper

![image](https://i.imgur.com/OMsIuEu.png)

## Tabla de contenidos

1. [Descripción](#descripción)
2. [Comandos](#comandos)
3. [Java](#java)

***

### Descripción

Horror helper es un bot hecho en Java destinado a ayudar
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

> Introducir un canal no existente ocuasiona un error de tipo: null

> Introducir palabras en el argumento de cantidad ocasiona un error de tipo: input String

* Ejemplos de comando progress

```
%p add 1 50 // Añade 50 al canal de en la posición 1 de progresos

%p reset 3 // Reseta el canal en la posición 3 de progresos

%p set 2 8 // Coloca el número de progreso del canal en la posición 2 en 8

%p add 1 // Si no se especifica la cantidad a añadir, se añadira 1 al canal respectivo
```
