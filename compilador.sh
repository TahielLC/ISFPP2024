#!/bin/bash

# Crear carpeta bin si no existe
mkdir -p bin

# Compilar todos los archivos .java dentro de src
find src -name "*.java" > sources.txt

# Construir classpath con todos los JAR en lib/
CLASSPATH="lib/*"

# Compilar con classpath y salida en bin/
javac -cp "$CLASSPATH" -d bin @sources.txt

# Limpiar archivo temporal
rm sources.txt

echo "✔️ Compilación completa."

echo "▶️ Ejecutando AplicacionConsultas..."
java -cp "bin:$CLASSPATH" red.aplicacion.AplicacionConsultas
