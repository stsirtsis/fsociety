package gr.ntua.ece.softeng.textualWatermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VisibleTextualWatermark {
	/**
	 * Embeds a textual watermark over a source image to produce
	 * a watermarked one.
	 * @param text The text to be embedded as watermark.
	 * @param sourceImageFile The source image file.
	 * @param destImageFile The output image file.
	 */
	
	static void addTextWatermark(String text, File sourceImageFile, File destImageFile) {
	    try {
	        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
	        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
	 
	        // initializes necessary graphic properties
	        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
	        g2d.setComposite(alphaChannel);
	        g2d.setColor(Color.BLUE);
	        g2d.setFont(new Font("Arial", Font.BOLD, 64));
	        FontMetrics fontMetrics = g2d.getFontMetrics();
	        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
	 
	        // calculates the coordinate where the String is painted
	        int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
	        int centerY = sourceImage.getHeight() / 2;
	 
	        // paints the textual watermark
	        g2d.drawString(text, centerX, centerY);
	 
	        ImageIO.write(sourceImage, "png", destImageFile);
	        g2d.dispose();
	 
	        System.out.println("The tex watermark is added to the image.");
	 
	    } catch (IOException ex) {
	        System.err.println(ex);
	    }
	}
	/*
	public static void main(String[] args) {
		File sourceImageFile = new File("/home/panagiotis/Pictures/geni-portal.png");
		File destImageFile = new File("/home/panagiotis/geni-portal.png");
		 
		addTextWatermark("CodeJava", sourceImageFile, destImageFile);
	}
	*/
	
	public void mkWatermark(String src,String dest,String msg) {
		File sourceImageFile = new File(src);
		File destImageFile = new File(dest);
		addTextWatermark(msg, sourceImageFile, destImageFile);
		
	}
	
}
