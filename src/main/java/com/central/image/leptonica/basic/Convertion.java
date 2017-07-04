package com.central.image.leptonica.basic;

import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;

import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.Mat;


public class Convertion {

    public static void convertToGray() throws IOException, Exception {
        final String source_image = "/home/dxuser/workspace/works/nlp/stanford/ImageRec/src/main/" +
                "resources/sample-images/other/color-img.jpg";
        Mat source = opencv_imgcodecs.imread(source_image);
        opencv_highgui.namedWindow("display image ", opencv_highgui.WINDOW_AUTOSIZE);
        opencv_highgui.imshow("img",source);
        Mat dest = new Mat(source.size());
        opencv_imgproc.cvtColor(source, dest, opencv_imgproc.COLOR_RGB2GRAY);
        opencv_imgcodecs.imwrite("gray.png",dest);
    }

    public static void main(String[] args) throws Exception {
        convertToGray();
    }
}
