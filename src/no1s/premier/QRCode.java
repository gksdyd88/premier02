package no1s.premier;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {
	
	public static void main(String... args) throws WriterException, IOException {
		final String url1 = "http://www.giants.jp/top.html";
		final String url2 = "https://www.amazon.co.jp/dp/B01BHPEC9G";
		final String url3 = "http://www.cosme.net/product/product_id/10023860/top";
		
		// ヒント生成
		ConcurrentHashMap<EncodeHintType, Object> hints = new ConcurrentHashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hints.put(EncodeHintType.MARGIN, 4);
		
		// QRCode生成
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix matrix1 = writer.encode(url1, BarcodeFormat.QR_CODE, 100, 100, hints);
		BufferedImage image1 = MatrixToImageWriter.toBufferedImage(matrix1);

		BitMatrix matrix2 = writer.encode(url2, BarcodeFormat.QR_CODE, 100, 100, hints);
		BufferedImage image2 = MatrixToImageWriter.toBufferedImage(matrix2);
		
		BitMatrix matrix3 = writer.encode(url3, BarcodeFormat.QR_CODE, 100, 100, hints);
		BufferedImage image3 = MatrixToImageWriter.toBufferedImage(matrix3);
		
		// ファイルへ保存
		ImageIO.write(image1, "jpg", new File("res/QRCode1.jpg"));
		ImageIO.write(image2, "jpg", new File("res/QRcode2.jpg"));
		ImageIO.write(image3, "jpg", new File("res/QRcode3.jpg"));
	}
}
