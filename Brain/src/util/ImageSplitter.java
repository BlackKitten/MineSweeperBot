package util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class ImageSplitter {

	public BufferedImage[] split(BufferedImage image) throws IOException,
			ImageSplitterException {

		int rows = 16; // You should decide the values for rows and cols
						// variables
		int cols = 30;
		int chunks = rows * cols;
		int count = 0;
		BufferedImage imgs[] = new BufferedImage[chunks]; // Image array to hold
															// image chunks

		int y_max = 0;
		for (int x = 0; x < rows; x++) {
			Integer[] y_minMax = getInterBorderHeight(y_max, image);
			int y_min = y_minMax[0];
			y_max = y_minMax[1];

			int x_max = 0;
			for (int y = 0; y < cols; y++) {
				try {
					// Initialize the image array with image chunks

					// int int_interBorderWidth=getInterBorderWidth(total_x,
					// image)+2;
					Integer[] x_minMax = getInterBorderWidth(x_max, image);
					int x_min = x_minMax[0];
					x_max = x_minMax[1];
					// System.out.println( x+" "+ y+ " "+x_min + "--" + x_max +
					// " width: "+ (x_max - x_min));
					System.out.println(x + " " + y + "  " + y_min + "---"
							+ y_max + " heigth " + (y_max - y_min));
					// total_x=total_x+int_interBorderWidth;
					imgs[count] = new BufferedImage(x_max - x_min, y_max
							- y_min, image.getType());

					// draws the image chunk
					Graphics2D gr = imgs[count++].createGraphics();

					// if (y==0){
					// gr.drawImage(image, 0,0, chunkWidth, chunkHeight, 1 ,
					// chunkHeight * x + 1 , chunkWidth * y + chunkWidth +1,
					// chunkHeight * x + chunkHeight + 1, null);
					// }
					gr.drawImage(image, 0, 0, x_max - x_min, y_max - y_min,
							x_min, y_min, x_max, y_max, null);
					/**
					 * img - the specified image to be drawn. This method does
					 * nothing if img is null. dx1 - the x coordinate of the
					 * first corner of the destination rectangle. dy1 - the y
					 * coordinate of the first corner of the destination
					 * rectangle. dx2 - the x coordinate of the second corner of
					 * the destination rectangle. dy2 - the y coordinate of the
					 * second corner of the destination rectangle. sx1 - the x
					 * coordinate of the first corner of the source rectangle.
					 * sy1 - the y coordinate of the first corner of the source
					 * rectangle. sx2 - the x coordinate of the second corner of
					 * the source rectangle. sy2 - the y coordinate of the
					 * second corner of the source rectangle. observer - object
					 * to be notified as more of the image is scaled and
					 * converted.
					 */

					gr.dispose();
				} catch (ImageSplitterException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return imgs;
		// System.out.println("Splitting done");

		// writing mini images into image files
		// for (int i = 0; i < imgs.length; i++) {
		// ImageIO.write(imgs[i], "png", new File("img" + i + ".png"));
		// }
		// System.out.println("Mini images created");
	}

	public BufferedImage crop(BufferedImage image) {
		int int_topBorder = getTopBorder(100, image) + 1;
		int int_botBorder = getBotBorder(100, image);
		int int_leftBorder = getLeftBorder(100, image) + 1;
		int int_rightBorder = getRightBorder(100, image);

		BufferedImage cropped_image = new BufferedImage(
				image.getWidth()
						- (int_leftBorder + image.getWidth() - int_rightBorder),
				(image.getHeight() - (int_topBorder + image.getHeight() - int_botBorder)),
				image.getType());

		Graphics2D gr = cropped_image.createGraphics();

		gr.drawImage(image, 0, 0, cropped_image.getWidth(),
				cropped_image.getHeight(), int_leftBorder, int_topBorder,
				int_rightBorder, int_botBorder, null);
		gr.dispose();
		return cropped_image;
	}

	public int getLeftBorder(BufferedImage image) {
		return getLeftBorder(80, image);
	}

	public int getTopBorder(BufferedImage image) {
		return getTopBorder(10, image);
	}

	public int getLeftBorder(int y, BufferedImage image) {

		int border;
		border = 0;
		for (int x = 0; x < 100 && border == 0; x++) {
			int rgb = image.getRGB(x, y);
			Color color = new Color(rgb, true);
			// System.out.println("x: " + Integer.toString(x) + "  " +
			// Integer.toString(rgb) + "  " + "red: "+ color.getRed()+
			// "green: "+color.getGreen()+"blue: "+color.getBlue());
			if (isBorder(color)) {
				border = x;
			}
		}
		return border;

	}

	public int getTopBorder(int x, BufferedImage image) {
		int border;
		border = 0;
		for (int y = 0; y < image.getHeight() && border == 0; y++) {
			int rgb = image.getRGB(x, y);
			Color color = new Color(rgb, true);
			if (isBorder(color)) {
				border = y;
			}
		}

		return border;
	}

	private Boolean isBorder(Color color) {
		return (color.getBlue() <= 50 && color.getRed() <= 50 && color
				.getGreen() <= 50);
	}

	private Boolean isBorder(int x, int y, BufferedImage image) {
		// TODO: fix y=172 bug
		// if (y==172){
		// y=172;
		// }
		Color color = new Color(image.getRGB(x, y), true);
		//System.out.println("x:"+x+" y:"+y+" "+color.getBlue()+" "+color.getRed()+ " "+color.getGreen());
		return (color.getBlue() <= 95 && color.getRed() <= 95 && color
				.getGreen() <= 95);
	}

	public int getBotBorder(int x, BufferedImage image) {
		int border;
		border = 0;
		for (int y = image.getHeight() - 1; y > 0 && border == 0; y--) {
			int rgb = image.getRGB(x, y);
			Color color = new Color(rgb, true);
			if (isBorder(color)) {
				border = y;
			}
		}
		return border;
	}

	public int getRightBorder(int y, BufferedImage image) {
		int border;
		border = 0;
		for (int x = image.getWidth() - 1; x > 0 && border == 0; x--) {
			int rgb = image.getRGB(x, y);
			Color color = new Color(rgb, true);
			if (isBorder(color)) {
				border = x;
			}
		}
		return border;
	}

	public int getXBorder(int from_x, int to_x, int from_y, int to_y,
			BufferedImage image) throws ImageSplitterException {
		// TODO: checks on variables
		int border;
		border = -1;
		int int_evolution;
		if (from_x < to_x) {
			int_evolution = 1;
		} else if (from_x > to_x) {
			int_evolution = -1;
		} else {
			return from_x;
			// throw new
			// ImageSplitterException("getXBorder_range needs to be higer");
		}
		for (int x = from_x; ((int_evolution == 1 && x < to_x && x >= 0) || (int_evolution == -1 && x > to_x))
				&& border == -1; x = x + int_evolution) {
			if (isBorder(x, from_y, image)) {
				border = x;
			}
		}
		if (border == -1) {
			throw new ImageSplitterException("no border found");
		}
		return border;
	}

	public int getYBorder(int from_x, int to_x, int from_y, int to_y,
			BufferedImage image) throws ImageSplitterException {
		// TODO: checks on variables
		int border;
		border = -1;
		int int_evolution;
		if (from_y < to_y) {
			int_evolution = 1;
		} else if (from_y > to_y) {
			int_evolution = -1;
		} else {
			return from_y;
			// throw new
			// ImageSplitterException("getYBorder_range needs to be higer");
		}
		for (int y = from_y; ((int_evolution == 1 && y < to_y) || (int_evolution == -1 && y > to_y))
				&& border == -1; y = y + int_evolution) {
			if (isBorder(from_x, y, image)) {
				border = y;
			}
		}
		if (border == -1) {
			throw new ImageSplitterException("no border found+ from_x,to_x,from_y,to_y"+from_x+","+to_x+","+from_y+","+to_y);
		}
		return border;
	}

	public int getInterBorderWidth(BufferedImage image)
			throws ImageSplitterException {

		return getInterBorderWidth(0, image)[1]
				- getInterBorderWidth(0, image)[0];

	}

	public Integer[] getInterBorderWidth(int x, BufferedImage image)
			throws ImageSplitterException {
		int y = 100;
		x = getXBorder(x, image.getWidth(), y, y, image);

		while (x < image.getWidth() && isBorder(x, y, image)) {
			x++;
		}
		// for (x=x;isBorder(x,y,image);x++){}
		int x_min = x;
		int x_max = getXBorder(x_min, image.getWidth(), y, y, image);
		Integer[] returnsel = new Integer[2];
		returnsel[0] = x_min;
		returnsel[1] = x_max;
		return returnsel;
	}

	public int getInterBorderHeight(BufferedImage image)
			throws ImageSplitterException {
		int x = 100;
		int y = 0;
		y = getYBorder(x, x, y, image.getHeight() - 1, image);
		while (isBorder(x, y, image)) {
			y++;
		}
		int y_min = y;
		int y_max = getYBorder(x, x, y_min, image.getHeight(), image);
		return y_max - y_min;
	}

	public Integer[] getInterBorderHeight(int y, BufferedImage image)
			throws ImageSplitterException {
		int x = 100;

		y = getYBorder(x, x, y, image.getHeight(), image);
		while (y < image.getHeight() && isBorder(x, y, image)) {
			y++;
		}
		int y_min = y;
		int y_max = getYBorder(x, x, y_min, image.getHeight(), image);
		Integer[] returnsel = new Integer[2];
		returnsel[0] = y_min;
		returnsel[1] = y_max;
		return returnsel;

	}

	public Integer[][][] getDimensions(BufferedImage image)
			throws ImageSplitterException {
		// [x],[y],[x_min,y_min,width,height]
		int rows = 16; // You should decide the values for rows and cols
						// variables
		int cols = 30;
		int chunks = rows * cols;
		
		BufferedImage imgs[] = new BufferedImage[chunks]; // Image array to hold
															// image chunks
		Integer[][][] int3_dimensions = new Integer[cols][rows][4];
		int y_max = 60;
		for (int y = 0; y < rows; y++) {
			Integer[] y_minMax = getInterBorderHeight(y_max, image);
			int y_min = y_minMax[0];
			y_max = y_minMax[1];

			int x_max = 45;
			for (int x = 0; x < cols; x++) {
				try {
					// Initialize the image array with image chunks

					// int int_interBorderWidth=getInterBorderWidth(total_x,
					// image)+2;
					Integer[] x_minMax = getInterBorderWidth(x_max, image);
					int x_min = x_minMax[0];
					x_max = x_minMax[1];
					//System.out.println( x+" "+ y+ " "+x_min + "--" + x_max +" width: "+ (x_max - x_min));
					//System.out.println( x+ " " +y+"  "+y_min +"---" + y_max +" heigth "+ (y_max - y_min));
					int3_dimensions[x][y][0] = x_min;
					int3_dimensions[x][y][1] = y_min;
					int3_dimensions[x][y][2] = x_max - x_min;
					int3_dimensions[x][y][3] = y_max - y_min;

					// total_x=total_x+int_interBorderWidth;

				} catch (ImageSplitterException e) {
					System.out.println(e.getMessage());
				}
			}

		}
		return int3_dimensions;
	}

	public BufferedImage getImage(BufferedImage image, int x, int y, int widht, int height) {
		BufferedImage img=new BufferedImage(widht, height, image.getType());
		Graphics2D gr = img.createGraphics();
		gr.drawImage(image, 0, 0, widht, height,x, y, x+widht, y+height, null);
		gr.dispose();
		return img;
	}

}
