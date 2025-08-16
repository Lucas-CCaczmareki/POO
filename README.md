# Pokémon Game

Jogo de Pokémon desenvolvido em Java

## Como Rodar

### Opção 1: IntelliJ IDEA (Recomendado)
1. Abra o IntelliJ IDEA
2. Clique em "Open" e selecione a pasta do projeto
3. Aguarde o IntelliJ carregar o projeto
4. Clique no botão ▶️ (Run) ou pressione `Shift + F10`
5. Pronto! O jogo vai abrir

### Opção 2: Terminal
```bash
# Compilar
javac -d out src/pokemon/*.java

# Executar
java -cp out pokemon.Main
```

### Opção 3: Script (Linux/Mac)
```bash
chmod +x run.sh
./run.sh
```

## Estrutura do Projeto
- `src/pokemon/` - Código fonte Java
- `Main.java` - Classe principal
- Interface gráfica com Swing

## Requisitos
- Java 8 ou superior
- IntelliJ IDEA (opcional, mas recomendado)
