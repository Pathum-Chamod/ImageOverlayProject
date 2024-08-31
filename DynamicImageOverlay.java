import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class DynamicImageOverlay {

    private static final String EXCEL_FILE_PATH = "prices/item_prices.xlsx";

    public static void main(String[] args) {
        String backgroundFolderPath = "backgrounds/";
        String itemImageFolderPath = "items/";
        String outputFolderPath = "finalImages/";

        // Load prices from Excel sheet
        Map<String, String> priceMap = loadPricesFromExcel(EXCEL_FILE_PATH);

        File outputFolder = new File(outputFolderPath);
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        try {
            File backgroundFolder = new File(backgroundFolderPath);
            File[] backgroundImages = backgroundFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
            if (backgroundImages == null || backgroundImages.length == 0) {
                System.out.println("No background images found.");
                return;
            }
            BufferedImage backgroundImage = ImageIO.read(backgroundImages[0]); // Assuming only one background image

            File itemFolder = new File(itemImageFolderPath);
            File[] itemImages = itemFolder.listFiles((dir, name) -> 
                name.toLowerCase().endsWith(".jpg") || 
                name.toLowerCase().endsWith(".jpeg") || 
                name.toLowerCase().endsWith(".png"));
            if (itemImages == null || itemImages.length == 0) {
                System.out.println("No item images found.");
                return;
            }

            for (File itemImageFile : itemImages) {
                // Load the transparent item image
                BufferedImage itemImage = ImageIO.read(itemImageFile);

                // Crop transparent borders (if necessary)
                itemImage = cropTransparentBorders(itemImage);

                // Resize the item image, maintaining the aspect ratio
                int newWidth = backgroundImage.getWidth() / 2;
                int newHeight = newWidth * itemImage.getHeight() / itemImage.getWidth(); // Maintain aspect ratio
                Image scaledItemImage = itemImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                // Create a new BufferedImage with transparency
                BufferedImage resizedItemImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resizedItemImage.createGraphics();
                g2d.drawImage(scaledItemImage, 0, 0, null);
                g2d.dispose();

                // Prepare the final image with the background
                BufferedImage finalImage = new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = finalImage.createGraphics();
                g.drawImage(backgroundImage, 0, 0, null);
                g.drawImage(resizedItemImage, (backgroundImage.getWidth() - resizedItemImage.getWidth()) / 2, (backgroundImage.getHeight() - resizedItemImage.getHeight()) / 2, null);

                // Get the product name from the file name (without the extension)
                String productName = itemImageFile.getName().replaceFirst("[.][^.]+$", "");
                
                // Fetch the price from the map using the product name
                String itemPrice = priceMap.getOrDefault(productName, "Price not available");
                    
                // Draw the item price on the image if available
                g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30));
                g.setColor(java.awt.Color.RED);
                g.drawString(itemPrice, 20, 50); // Position at the top left of the image

                g.dispose();

                // Save the final image
                String finalImageName = "final_" + productName + ".png";
                File outputFile = new File(outputFolderPath + finalImageName);
                ImageIO.write(finalImage, "png", outputFile);

                System.out.println("Saved: " + outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error processing images.");
            e.printStackTrace();
        }
    }

    // Function to crop transparent borders from the image
    private static BufferedImage cropTransparentBorders(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int minX = width, minY = height, maxX = 0, maxY = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((image.getRGB(x, y) >> 24) != 0x00) { // Check for non-transparent pixel
                    if (x < minX) minX = x;
                    if (y < minY) minY = y;
                    if (x > maxX) maxX = x;
                    if (y > maxY) maxY = y;
                }
            }
        }

        if (minX <= maxX && minY <= maxY) {
            return image.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
        } else {
            return image;
        }
    }

    // Function to load prices from Excel sheet
    private static Map<String, String> loadPricesFromExcel(String excelFilePath) {
        Map<String, String> priceMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell nameCell = row.getCell(0);
                Cell priceCell = row.getCell(1);

                if (nameCell != null && priceCell != null) {
                    String itemName = nameCell.getStringCellValue();
                    String itemPrice = priceCell.getStringCellValue();
                    priceMap.put(itemName, itemPrice);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceMap;
    }
}
