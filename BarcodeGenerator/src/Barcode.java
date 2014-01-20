import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;

import com.google.zxing.common.BitMatrix;

import com.google.zxing.oned.Code128Writer;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Yusata Infotech
 */
public class Barcode {
	private static final int WHITE = 0xFFFFFFFF;
	private static final int BLACK = 0xFF000000;

	public static void main(String[] args) {
		BitMatrix bitMatrix;
		Writer writer = new QRCodeWriter();
		try {
			// Write Barcode
			bitMatrix = new Code128Writer().encode("123456789",
					BarcodeFormat.CODE_128, 150, 80, null);
			writeToFile(bitMatrix, "png", new File("c://code128_123456789.png"));
			System.out.println("Code128 Barcode Generated.");
			// Write QR Code
			bitMatrix = writer.encode("123456789", BarcodeFormat.QR_CODE, 200,
					200);
			writeToFile(bitMatrix, "png", new File("c://qrcode_123456789.png"));
			System.out.println("QR Code Generated.");
			// Write PDF417
			writer = new PDF417Writer();
			bitMatrix = writer.encode("123456789", BarcodeFormat.PDF_417, 80,
					150);
			writeToFile(bitMatrix, "png", new File("c://pdf417_123456789.png"));
			System.out.println("PDF417 Code Generated.");
		} catch (Exception e) {
			System.out.println("Exception Found." + e.getMessage());
		}

	}

	public static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {

		BufferedImage image = toBufferedImage(matrix);

		ImageIO.write(image, format, file);

	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {

		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);

			}

		}

		return image;

	}
}
