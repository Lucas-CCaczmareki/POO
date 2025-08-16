# 🎮 Pokémon Game - POO UFPEL

## 📋 Descrição
Jogo Pokémon desenvolvido em Java para a disciplina de Programação Orientada a Objetos da UFPEL. O projeto implementa todas as funcionalidades solicitadas no trabalho, incluindo interface gráfica, batalhas, capturas, threads e persistência de dados.

## ✨ Funcionalidades Implementadas

### 🎯 Funcionalidades Principais
- ✅ **Interface Gráfica Completa** - Telas de boas-vindas, posicionamento e jogo
- ✅ **Sistema de Batalhas** - Batalhas entre Pokémon com interface visual
- ✅ **Sistema de Capturas** - Captura de Pokémon selvagens com Pokébolas
- ✅ **Threads** - Computador joga em paralelo simulando inteligência
- ✅ **Persistência** - Salvar/carregar jogos e histórico de partidas
- ✅ **Imagens dos Pokémon** - Interface visual com imagens dos Pokémon
- ✅ **Sistema de Dicas** - Máximo 3 dicas por jogo
- ✅ **Modo Debug** - Revelar todo o mapa para desenvolvimento

### 🏗️ Arquitetura e Padrões
- ✅ **Interface IAtaque** - Polimorfismo para diferentes tipos de ataque
- ✅ **Classes Abstratas** - Classe base Pokemon com herança
- ✅ **Tratamento de Exceções** - RegiaoInvalidaException e outras
- ✅ **Serialização** - Todas as classes implementam Serializable
- ✅ **Threads** - ComputadorJogador implementa Runnable

### 🎨 Interface Gráfica
- ✅ **TelaBoasVindas** - Menu inicial com opções
- ✅ **TelaPosicionamento** - Posicionar Pokémon manualmente
- ✅ **TelaJogo** - Interface principal do jogo
- ✅ **TelaBatalha** - Batalhas com interface visual
- ✅ **TelaSalvarCarregar** - Gerenciar saves

### 🐛 Tratamento de Erros
- ✅ **RegiaoInvalidaException** - Pokémon fora da região correta
- ✅ **Validação de Entrada** - Verificações de dados
- ✅ **Tratamento de Arquivos** - Erros de I/O

## 🚀 Como Executar

### Opção 1: Arquivo .bat (Recomendado)
```bash
# Clique duas vezes no arquivo
rodar-jogo.bat
```

### Opção 2: Terminal
```bash
# Compilar
"C:\Program Files\Java\jdk1.8.0_341\bin\javac.exe" -encoding UTF-8 -d build/classes src/pokemon/*.java

# Copiar imagens
mkdir build\classes\pokemons
copy src\pokemon\pokemons\*.png build\classes\pokemons\

# Executar
java -cp build/classes pokemon.Main
```

### Opção 3: IDE
- Abra o projeto no IntelliJ IDEA, Eclipse ou VS Code
- Execute a classe `Main.java`

## 🎮 Como Jogar

### 1. Tela de Boas-Vindas
- **Carregar Jogo Salvo**: Carrega uma partida anterior
- **Definir Posição**: Posiciona Pokémon manualmente
- **Posição Aleatória**: Distribui Pokémon automaticamente

### 2. Posicionamento
- Selecione o Pokémon no combo box
- Clique na célula desejada (respeitando as regiões)
- Use "DEBUG" para posicionar todos automaticamente

### 3. Jogo Principal
- **Clique nas células** para revelar conteúdo
- **Pokémon Selvagem**: Tenta capturar automaticamente
- **Pokémon Adversário**: Inicia batalha
- **Célula Vazia**: Nada encontrado

### 4. Controles
- **Trocar Pokémon Principal**: Escolhe Pokémon ativo
- **Dica**: Revela se há Pokémon na linha/coluna (máx. 3)
- **Debug**: Mostra todo o mapa
- **Salvar/Carregar**: Gerencia saves
- **Sair**: Termina o jogo

## 🏆 Sistema de Pontuação
- **Captura de Pokémon**: +10 pontos
- **Vitória em Batalha**: +50 pontos de experiência
- **Subida de Nível**: Aumenta força do Pokémon
- **Vencedor**: Maior pontuação total

## 🎨 Tipos de Pokémon

### 📋 Pokémon Disponíveis
O jogo utiliza apenas os 8 Pokémon que possuem imagens disponíveis:

- **🌊 Água**: Squirtle, Paras
- **🌳 Floresta**: Bulbasaur, Caterpie  
- **🗿 Terra**: Diglet, Sandshrew
- **⚡ Elétrico**: Magnemite, Pikachu

### 🌊 Água (Região 1)
- **Habilidade**: Redução de dano em ambientes adversos
- **Região**: Quadrante superior esquerdo
- **Pokémon**: Squirtle, Paras

### 🌳 Floresta (Região 2)
- **Habilidade**: Regeneração ao atacar
- **Região**: Quadrante superior direito
- **Pokémon**: Bulbasaur, Caterpie

### 🗿 Terra (Região 3)
- **Habilidade**: Ataque dobrado em turnos ímpares
- **Região**: Quadrante inferior esquerdo
- **Pokémon**: Diglet, Sandshrew

### ⚡ Elétrico (Região 4)
- **Habilidade**: Chance de paralisar adversário
- **Região**: Quadrante inferior direito
- **Pokémon**: Magnemite, Pikachu

## 📁 Estrutura do Projeto

```
POO/
├── src/pokemon/
│   ├── Main.java                    # Ponto de entrada
│   ├── Pokemon.java                 # Classe abstrata base
│   ├── PokemonAgua.java            # Pokémon de água
│   ├── PokemonTerra.java           # Pokémon de terra
│   ├── PokemonFloresta.java        # Pokémon de floresta
│   ├── PokemonEletrico.java        # Pokémon elétrico
│   ├── PokemonFactory.java         # Fábrica de Pokémon
│   ├── IAtaque.java                # Interface de ataque
│   ├── Jogo.java                   # Lógica principal
│   ├── Tabuleiro.java              # Gerenciamento do tabuleiro
│   ├── Celula.java                 # Célula individual
│   ├── Treinador.java              # Jogador/Computador
│   ├── TelaBoasVindas.java         # Menu inicial
│   ├── TelaPosicionamento.java     # Posicionamento
│   ├── TelaJogo.java               # Interface principal
│   ├── TelaBatalha.java            # Batalhas
│   ├── TelaSalvarCarregar.java     # Save/Load
│   ├── PokemonImageManager.java    # Gerenciamento de imagens
│   ├── GameSaver.java              # Persistência
│   ├── ComputadorJogador.java      # Thread do computador
│   ├── RegiaoInvalidaException.java # Exceção personalizada
│   └── pokemons/                   # Imagens dos Pokémon
│       ├── bulbasaur.png
│       ├── caterpie.png
│       ├── diglet.png
│       ├── magnemite.png
│       ├── paras.png
│       ├── pikachu.png
│       ├── sandshrew.png
│       └── squirtle.png
├── build/                          # Arquivos compilados
├── rodar-jogo.bat                  # Script de execução
├── build.xml                       # Configuração Ant
└── README_ATUALIZADO.md            # Este arquivo
```

## 🔧 Requisitos Técnicos

### Sistema Operacional
- ✅ Windows 10/11
- ✅ Linux (com ajustes)
- ✅ macOS (com ajustes)

### Java
- ✅ JDK 8 ou superior
- ✅ JRE 8 ou superior

### IDE (Opcional)
- ✅ IntelliJ IDEA
- ✅ Eclipse
- ✅ VS Code com extensão Java

## 🎯 Conceitos de POO Implementados

### 1. **Herança e Polimorfismo**
```java
public abstract class Pokemon implements IAtaque, Serializable
public class PokemonAgua extends Pokemon
public class PokemonTerra extends Pokemon
public class PokemonFloresta extends Pokemon
public class PokemonEletrico extends Pokemon
```

### 2. **Interfaces**
```java
public interface IAtaque {
    int calcularDano();
}
```

### 3. **Tratamento de Exceções**
```java
public class RegiaoInvalidaException extends Exception
```

### 4. **Threads**
```java
public class ComputadorJogador implements Runnable
```

### 5. **Serialização**
```java
public class Jogo implements Serializable
```

### 6. **Encapsulamento**
- Getters e setters apropriados
- Atributos privados/protegidos

### 7. **Factory Pattern**
```java
public class PokemonFactory {
    public static Pokemon criarPokemon(String nome)
}
```

## 🏆 Funcionalidades Extras

### 📊 Sistema de Histórico
- Salva todas as partidas em `historico.txt`
- Inclui data, jogadores, pontuações e vencedor

### 💾 Sistema de Save/Load
- Salva estado completo do jogo
- Carrega partidas anteriores
- Interface gráfica para gerenciar saves

### 🎨 Interface Visual
- Imagens dos Pokémon nas células
- Cores diferentes por região
- Interface moderna e responsiva

### 🧠 Inteligência Artificial
- Computador joga em paralelo
- Simula "tempo de pensar"
- Escolhas aleatórias inteligentes

## 🐛 Solução de Problemas

### Erro de Compilação
```bash
# Verificar se o JDK está instalado
java -version
javac -version

# Compilar com encoding UTF-8
javac -encoding UTF-8 -d build/classes src/pokemon/*.java
```

### Imagens não Carregam
```bash
# Verificar se as imagens existem
dir src\pokemon\pokemons\*.png

# Copiar manualmente
copy src\pokemon\pokemons\*.png build\classes\pokemons\
```

### Erro de Memória
```bash
# Aumentar heap do Java
java -Xmx512m -cp build/classes pokemon.Main
```

## 📝 Notas de Desenvolvimento

### Padrões de Projeto Utilizados
- **Strategy Pattern**: Diferentes estratégias de ataque por tipo
- **Factory Pattern**: Criação de Pokémon baseado nas imagens disponíveis
- **Observer Pattern**: Atualização da interface

### Melhorias Futuras
- [ ] Sistema de evolução de Pokémon
- [ ] Mais tipos de Pokémon
- [ ] Sistema de itens
- [ ] Modo multiplayer
- [ ] Sons e música
- [ ] Animações

## 👥 Autores
- Desenvolvido para a disciplina de POO da UFPEL
- Professores: Felipe Marques, Rafael Burlamaqui

## 📄 Licença
Este projeto foi desenvolvido para fins educacionais.

---

**🎮 Divirta-se jogando Pokémon!** 🚀
