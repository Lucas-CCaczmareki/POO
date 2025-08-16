package pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PokemonImageManager {

    private static final Map<String, ImageIcon> pokemonImages = new HashMap<>();
    private static final int IMAGE_SIZE = 40;
    private static boolean initialized = false;

    private static void initializeIfNeeded() {
        if (!initialized) {
            loadPokemonImages();
            initialized = true;
        }
    }

    private static void loadPokemonImages() {
        String[] pokemonNames = {
            "bulbasaur", "caterpie", "diglet", "magnemite",
            "paras", "pikachu", "sandshrew", "squirtle"
        };

        for (String name : pokemonNames) {
            String resourcePath = "/pokemons/" + name + ".png";
            
            try (InputStream is = PokemonImageManager.class.getResourceAsStream(resourcePath)) {
                if (is != null) {
                    BufferedImage originalImage = ImageIO.read(is);
                    
                    if (originalImage != null) {
                        Image scaledImage = originalImage.getScaledInstance(
                            IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH
                        );
                        pokemonImages.put(name.toLowerCase(), new ImageIcon(scaledImage));
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao carregar imagem: " + name);
            }
        }
    }

    public static ImageIcon getPokemonImage(String pokemonName) {
        initializeIfNeeded();
        String normalizedName = pokemonName.toLowerCase();
        return pokemonImages.getOrDefault(normalizedName, createDefaultIcon());
    }

    private static ImageIcon createDefaultIcon() {
        BufferedImage defaultImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = defaultImage.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.GRAY);
        g2d.fillOval(2, 2, IMAGE_SIZE - 4, IMAGE_SIZE - 4);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (IMAGE_SIZE - fm.stringWidth("?")) / 2;
        int y = ((IMAGE_SIZE - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString("?", x, y);
        
        g2d.dispose();
        return new ImageIcon(defaultImage);
    }

    public static int getImageSize() {
        return IMAGE_SIZE;
    }
}