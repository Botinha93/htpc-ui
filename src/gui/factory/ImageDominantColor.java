package gui.factory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ImageDominantColor {

    public static Color getHexColor(Image image) {

        Map<Integer, Integer> colorMap = new HashMap<>();
        int height = image.getHeight(null);
        int width = image.getWidth(null);
        BufferedImage bimage = null;
        try {
        	bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }catch(IllegalArgumentException e) {
        	return Color.ORANGE;
        }

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bimage.getRGB(i, j);
                if (!isGray(getRGBArr(rgb))) {
                    Integer counter = colorMap.get(rgb);
                    if (counter == null) {
                        counter = 0;
                    }

                    colorMap.put(rgb, ++counter);
                }
            }
        }

        return getMostCommonColor(colorMap);
    }

    private static Color getMostCommonColor(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2)
                -> ((Comparable) obj1.getValue()).compareTo(obj2.getValue()));

        Map.Entry<Integer, Integer> entry = list.get(list.size() /2);
        int[] rgb = getRGBArr(entry.getKey());

        return new Color(rgb[0],rgb[1],rgb[2]);
    }

    private static int[] getRGBArr(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        
        return new int[]{red, green, blue};
    }

    private static boolean isGray(int[] rgbArr) {
        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];
        // Filter out black, white and grays...... (tolerance within 10 pixels)
        int tolerance = 10;
        if (rgDiff > tolerance || rgDiff < -tolerance) {
            if (rbDiff > tolerance || rbDiff < -tolerance) {
                return false;
            }
        }
        return true;
    }
}