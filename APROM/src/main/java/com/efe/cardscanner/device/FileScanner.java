package com.efe.cardscanner.device;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileScanner implements Scanner {

    @Override
    public String scan() {
        System.out.println("📂 Opening file selector... Please select the downloaded QR code image.");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Please Select the QR Code Image to Scan");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToScan = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(fileToScan);
                if (image == null) {
                    System.err.println("❌ ERROR: The selected file is not an image!");
                    return "";
                }

                BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                Result result = new MultiFormatReader().decode(bitmap);
                System.out.println("✅ BEEP! QR Code successfully read from the image.");

                return result.getText();

            } catch (Exception e) {
                System.err.println("❌ ERROR: No valid QR or Barcode found in this image.");
            }
        } else {
            System.out.println("❌ Scan canceled.");
        }

        return "";
    }
}