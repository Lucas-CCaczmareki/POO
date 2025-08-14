# Pokémon-UFPel

## Jogo de Pokémon para a disciplina de Programação Orientada a Objetos 2025/1

Este repositório contém o trabalho final da disciplina de Programação Orientada a Objetos da Universidade Federal de Pelotas (UFPel), ministrada pelos professores Felipe Marques e Rafael Burlamaqui.

O projeto consiste na implementação de um jogo inspirado na franquia Pokémon, utilizando a linguagem Java e conceitos de orientação a objetos, como herança, polimorfismo, classes abstratas, interfaces e tratamento de exceções.

## Visão Geral do Jogo

O jogo é uma versão adaptada do universo Pokémon, onde os jogadores, chamados de Treinadores, podem capturar, treinar e batalhar com criaturas ficcionais. O objetivo principal é construir a equipe de Pokémon com a maior pontuação de experiência, capturando todos os Pokémon selvagens disponíveis no mapa.

O jogo se desenrola em um tabuleiro retangular dividido em células, com quatro regiões distintas, cada uma abrigando tipos específicos de Pokémon: Água, Floresta, Terra e Eletricidade.

## Funcionalidades

### Treinadores e Equipes:
- **Dois treinadores:** um jogador (usuário) e um computador com jogadas aleatórias.
- Cada treinador possui uma **equipe Pokémon**, iniciando com apenas um.
- Uma **mochila** para armazenar os Pokémon capturados.
- **Pontuação** da equipe, que é atualizada a cada jogada.

### Pokémon:
- **Atributos:** energia, força, pontos de experiência, nível e tipo.
- **Tipos de Pokémon:** Água, Floresta, Terra e Elétrico, cada um com habilidades especiais.
    - **Floresta:** Habilidade de regeneração.
    - **Terra:** Ataque com força dobrada em turnos ímpares.
    - **Elétrico:** Chance de paralisar o adversário por uma rodada.
    - **Água:** Redução de dano recebido em ambientes adversos.
- **Mecânicas de jogo:**
    - **Captura:** Pokémon selvagens podem ser capturados com uma Pokébola.
    - **Batalha:** Treinadores batalham entre si ao encontrarem um Pokémon de outro treinador.
    - **Evolução:** Pokémon ganham experiência, sobem de nível e se tornam mais fortes.

### Interface Gráfica:
- **Tabuleiro Interativo:** O tabuleiro do jogo é composto por botões clicáveis, onde cada botão representa uma célula do mapa.
- **Janelas e Menus:**
    - **Janela de Boas-Vindas:** Com opções para "carregar jogo salvo", "posicionar time Pokémon" ou "distribuir Pokémons de maneira aleatória".
    - **Tela Principal do Jogo:** Exibe o tabuleiro, pontuações e opções como "Trocar Pokémon Principal", "Dica" e "Debug".
    - **Fim de Jogo:** Uma janela informa o vencedor e oferece a opção de "Novo Jogo".

## Requisitos Técnicos

- **Linguagem:** Java.
- **Interface Gráfica:** Obrigatória.
- **Threads:** Uso de threads para as jogadas do computador, que devem ocorrer em paralelo com as do jogador.
- **Leitura/Escrita de Arquivos:** Para salvar e carregar o progresso do jogo.
- **Tratamento de Exceções:** Implementação de exceções personalizadas, como `RegiaoInvalidaException` ao posicionar um Pokémon em uma região incorreta.
- **Uso de Interfaces:** Implementação da interface `IAtaque` para o cálculo de dano dos Pokémon.

### Desafio (Opcional - 1 Ponto Extra)

Implementação de um dos seguintes padrões de projeto:
- **Strategy:** Para definir diferentes estratégias de ataque.
- **Factory Method:** Para a criação de Pokémon de acordo com o tipo e a região.
- **Observer:** Para atualizar a interface gráfica automaticamente conforme o estado do jogo muda.

## Como Compilar e Executar

As instruções detalhadas para compilação e execução do projeto, incluindo o sistema operacional e a IDE utilizados, estarão descritas no relatório final.

## Entregas

Os resultados a serem entregues para avaliação são:
- **Implementação:** Código-fonte compilável (60% da nota).
- **Relatório:** Documento detalhado sobre o projeto (20% da nota).
- **Apresentação:** (20% da nota).

## Datas Importantes

- **Entrega Final:** 17/08/2025 - Domingo às 23:59 (para valor integral).
- **Entrega com Atraso:** Até as 23:59 do dia 20/08/2025 (com desconto de 1 ponto por dia de atraso).

**Atenção:** Trabalhos que não compilarem não serão avaliados.

## Conteúdo do Relatório

O relatório deverá conter as seguintes seções:
- Capa com o nome dos participantes.
- Introdução.
- Diagrama de Classes.
- Descrição do uso dos conceitos da disciplina.
- Passos para compilar e rodar o programa.
- Exemplos de utilização (telas do jogo).
- Resumo das dificuldades encontradas.
- Conclusão.