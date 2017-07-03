package com.central.image.leptonica.basic;

import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.*;

public class Scale {

    public static void main(String[] args) {
        PIX sourceImg = lept.pixRead("test-images/scale.jpg");
        PIX scaledImage = lept.pixScale(sourceImg,0.50f,0.50f);
        lept.pixWrite("test-images/downscaled-example.png",scaledImage,lept.IFF_PNG);
        lept.pixDestroy(sourceImg);
        lept.pixDestroy(scaledImage);
    }
}
