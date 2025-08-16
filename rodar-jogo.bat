@echo off
echo Compilando o jogo Pokemon...
"C:\Program Files\Java\jdk1.8.0_341\bin\javac.exe" -encoding UTF-8 -d build/classes src/pokemon/*.java

if %errorlevel% equ 0 (
    echo Copiando imagens dos Pokemon...
    if not exist build\classes\pokemons mkdir build\classes\pokemons
    copy src\pokemon\pokemons\*.png build\classes\pokemons\ >nul 2>&1
    
    echo Compilacao bem-sucedida! Executando o jogo...
    java -cp build/classes pokemon.Main
) else (
    echo Erro na compilacao!
    pause
)
