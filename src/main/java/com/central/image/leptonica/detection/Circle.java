package com.central.image.leptonica.detection;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;

public class Circle {

    public static void drawCircle(){
        opencv_core.Mat src, src_gray = null;
        src = opencv_imgcodecs.imread("/home/dxuser/workspace/works/nlp/stanford/ImageRec/src/main/" +
                "resources/sample-images/detection/circle3.png");
        src_gray = new opencv_core.Mat(src.size());
        opencv_imgproc.cvtColor(src,src_gray,opencv_imgproc.COLOR_RGB2GRAY);
        opencv_highgui.namedWindow("Circle",opencv_highgui.CV_WINDOW_AUTOSIZE);

        /// Reduce the noise so we avoid false circle detection
        opencv_core.Size size = new opencv_core.Size(9,9);
        opencv_imgproc.GaussianBlur(src_gray, src_gray, size, 2,2,1);

        opencv_core.Mat circles  = new opencv_core.Mat(src_gray.size());
        opencv_imgproc.HoughCircles(src_gray, circles,opencv_imgproc.CV_HOUGH_GRADIENT, 1,src_gray.rows() / 8,
                200, 100, 0, 0);


        opencv_highgui.imshow("circle", src_gray);
        opencv_highgui.waitKey(0);
    }

    public static void main(String[] args) {
        drawCircle();
    }

}
