#!/bin/bash

# Script para compilar e executar o Pokémon Game

echo "Compilando Pokémon Game..."
javac -d build/classes src/pokemon/*.java

if [ $? -eq 0 ]; then
    echo "Compilação bem-sucedida! Executando o jogo..."
    java -cp build/classes pokemon.Main
else
    echo "Erro na compilação!"
    exit 1
fi
