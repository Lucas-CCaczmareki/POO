# Como Rodar no Windows 10/11

## Problema: Shift+F10 não funciona

Se o botão de play (▶️) não funciona no IntelliJ IDEA, siga estes passos:

### Solução 1: Reconfigurar o Projeto
1. **Feche o IntelliJ IDEA**
2. **Delete a pasta `.idea`** (se existir)
3. **Abra o IntelliJ IDEA novamente**
4. **Clique em "Open"** e selecione a pasta do projeto
5. **Aguarde carregar** (vai detectar como projeto Java)

### Solução 2: Configurar Manualmente
1. **Clique com botão direito** na pasta `src`
2. **Selecione "Mark Directory as" → "Sources Root"**
3. **Clique com botão direito** no arquivo `Main.java`
4. **Selecione "Run 'Main.main()'"**

### Solução 3: Usar Ant Build
1. **Abra a aba "Ant"** (geralmente na lateral direita)
2. **Clique no ícone +** para adicionar build file
3. **Selecione o arquivo `build.xml`**
4. **Clique duas vezes em "run"** na aba Ant

### Solução 4: Terminal do IntelliJ
1. **Abra o terminal** (Alt+F12)
2. **Digite:**
   ```cmd
   javac -d build/classes src/pokemon/*.java
   java -cp build/classes pokemon.Main
   ```

## Verificações Importantes

### 1. JDK Configurado
- **File → Project Structure**
- **Project Settings → Project**
- **Project SDK**: deve mostrar uma versão do Java (8, 11, 17, etc.)

### 2. Módulo Configurado
- **File → Project Structure**
- **Project Settings → Modules**
- **Sources**: pasta `src` deve estar marcada como "Sources"

### 3. Run Configuration
- **Run → Edit Configurations**
- **+ → Application**
- **Main class**: `pokemon.Main`
- **Module**: `pokemon-game`

## Comandos Úteis

### Compilar via Terminal
```cmd
javac -d build/classes src/pokemon/*.java
```

### Executar via Terminal
```cmd
java -cp build/classes pokemon.Main
```

### Usar Ant (se instalado)
```cmd
ant compile
ant run
```

## Se Nada Funcionar

1. **Reinstale o IntelliJ IDEA**
2. **Use o Eclipse** como alternativa
3. **Use o terminal** diretamente
4. **Use o VS Code** com extensão Java

## Estrutura Esperada
```
projeto/
├── src/
│   └── pokemon/
│       ├── Main.java
│       ├── Jogo.java
│       └── ... (outros arquivos)
├── build.xml
├── pokemon-game.iml
└── .idea/
```
