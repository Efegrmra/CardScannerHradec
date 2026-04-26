package com.efe.cardscanner.device;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

public class WebcamScanner implements Scanner {

    @Override
    public String scan() {
        // 1. Bilgisayarın varsayılan kamerasını bul
        Webcam webcam = Webcam.getDefault();
        if (webcam == null) {
            System.err.println("❌ HATA: Kamera bulunamadı!");
            return "";
        }

        System.out.println("📷 Kamera açılıyor... Lütfen QR Kodu kameraya gösterin.");
        webcam.open();

        String decodedText = null;

        // 2. Kamera açıldıktan sonra sürekli fotoğraf çekip QR arayan döngü
        while (true) {
            BufferedImage image = webcam.getImage(); // Kameradan anlık kareyi al

            if (image != null) {
                try {
                    // Resmi ZXing'in okuyabileceği formata çevir
                    BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    // Resmi analiz et ve barkod/QR var mı bak
                    Result result = new MultiFormatReader().decode(bitmap);
                    decodedText = result.getText(); // QR içindeki metni al

                    System.out.println("✅ BİP! QR Kod başarıyla okundu.");
                    break; // Okuma başarılıysa döngüden çık

                } catch (Exception e) {
                    // Bu karede QR kod bulunamadı, döngü devam edecek
                }
            }

            try {
                Thread.sleep(100); // Kamerayı ve işlemciyi yormamak için 100 milisaniye bekle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. İşimiz bitince kamerayı kapat
        webcam.close();
        return decodedText;
    }
}