# SlideButton for Java

[Project created with Android Studio V3.4.1]

## Project Base.
Creación de un Botón Deslizante:
Un TextView se comportará como el botón deslizante. 
Éste está dentro de un RelativeLayout, que es el área donde se podrá desplazar. 
Se muestra un mensaje que cambia según la posición en que se encuentre el botón deslizado.

## Animations
Creación de tres animaciones para el botón deslizante:
[expandButton]: Cuando el botón llega hasta el final del área, el botón toma el tamaño del área.
[moveButtonBack]: Cuando el botón no llega hasta el final del área, el botón regresa hasta su posición inicial.
[collapseButton]: Cuando el botón está del tamaño del área, se podrá regresar a su tamaño original.

## Style
Al Botón Deslizante se le da estilo de esquinas redondeadas.
_Bug: Cuando el botón se ha expandido puede moverse, afectando cuando se resetea. { Corregido }