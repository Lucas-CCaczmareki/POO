package projetofinal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Essa classe gerencia e fornece imagens dos Pokémons para a interface gráfica.
 */
public class PokemonImageManager {
    /*
     * Todos métodos e atributos da classe são static para que não sejam instanciados
     * ao utilizarmos essa classe
     */

    //Atributos
    //O map associa o nome do pokémon à sua imagem.
    private static final Map<String, ImageIcon> pokemonImages = new HashMap<>();
    private static final int IMAGE_SIZE = 40;
    private static boolean initialized = false;

    //Métodos

    /*
     * Só carrega as imagens quando é preciso (se já foram carregadas não faz nada)
     */
    private static void initializeIfNeeded() {
        //Se as imagens não foram carregadas, carrega e marca
        if (!initialized) {
            loadPokemonImages();
            initialized = true;
        }
    }

    /*
     * Tenta abrir a imagem, se conseguir, coloca ela no hash map associado ao nome
     * do Pokémon em formato de ícone pro swing.
     */
    private static void loadPokemonImages() {
        String[] pokemonNames = {
            "bulbasaur", "caterpie", "diglet", "magnemite",
            "paras", "pikachu", "sandshrew", "squirtle"
        };

        //Pra cada nome de pokémon no array, tenta carregar a png correspondente
        for (String name : pokemonNames) {
            //Define o nome da imagem
            String resourcePath = "/pokemons/" + name + ".png";
            
            //Procura o arquivo dentro da pasta e tenta abrir ele
            //Usa o try com recurso pra garantir que o arquivo será fechado automaticamente após usar
            try (InputStream is = PokemonImageManager.class.getResourceAsStream(resourcePath)) {
                //Se o arquivo existe
                if (is != null) {
                    //Aqui lê os dados do arquivo e transforma numa imagem
                    BufferedImage originalImage = ImageIO.read(is);
                    
                    //Se arquivo for uma imagem (conseguiu carregar como imagem)
                    if (originalImage != null) {
                        //Redimensiona ela pro tamanho padrão esperado
                        Image scaledImage = originalImage.getScaledInstance(
                            IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH
                        );
                        //Coloca no hash map a imagem no formato de ícone associada ao nome
                        pokemonImages.put(name.toLowerCase(), new ImageIcon(scaledImage));
                    }
                }
            //Se não conseguiu abrir o arquivo, mostra isso pro usuário
            } catch (IOException e) {
                System.err.println("Erro ao carregar imagem: " + name);
            }
        }
    }

    /*
     * Esse método garante que as imagens já foram carregadas
     * Recebe um nome de pokémon, trata a string pro nome em formato padrão
     * Retorna a imagem associada à este pokémon
     */
    public static ImageIcon getPokemonImage(String pokemonName) {
        initializeIfNeeded();   
        String normalizedName = pokemonName.toLowerCase();
        return pokemonImages.getOrDefault(normalizedName, createDefaultIcon());
    }

    /*
     * Cria uma immagem padrão para ser usada quando não existe uma imagem específica
     * para um pokémon. A imagem padrão é um círculo cinza com um "?"
     */
    private static ImageIcon createDefaultIcon() {
        //Cria uma imagem vazia com tamanho e tipo definidos
        BufferedImage defaultImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);

        //Permite desenhar gráficos e formas na imagem criada
        Graphics2D g2d = defaultImage.createGraphics();
        
        //Suavização de bordas
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Cria um círculo cinza
        g2d.setColor(Color.GRAY);
        g2d.fillOval(2, 2, IMAGE_SIZE - 4, IMAGE_SIZE - 4);
        
        //Coloca um ponto de interrogação branco no meio
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (IMAGE_SIZE - fm.stringWidth("?")) / 2;
        int y = ((IMAGE_SIZE - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString("?", x, y);
        
        //Finaliza o desenho e retorna a imagem como image icon.
        g2d.dispose();
        return new ImageIcon(defaultImage);
    }

    //Getter
    public static int getImageSize() {
        return IMAGE_SIZE;
    }
}