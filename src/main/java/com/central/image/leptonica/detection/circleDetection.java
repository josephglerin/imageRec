package com.central.image.leptonica.detection;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;

public class circleDetection {
    public static void main(String[] args){
        opencv_core.IplImage src = opencv_imgcodecs.cvLoadImage("/home/dxuser/workspace/works/nlp/stanford/ImageRec/src/main" +
                "/resources/sample-images/detection/circle3.png");
        opencv_core.IplImage gray = cvCreateImage(cvGetSize(src), 8, 1);

        cvCvtColor(src, gray, opencv_imgproc.CV_BGR2GRAY);
        cvSmooth(gray, gray);

        opencv_core.CvMemStorage mem = opencv_core.CvMemStorage.create();

        opencv_core.CvSeq circles = cvHoughCircles(
                gray, //Input image
                mem, //Memory Storage
                CV_HOUGH_GRADIENT, //Detection method
                1, //Inverse ratio
                100, //Minimum distance between the centers of the detected circles
                100, //Higher threshold for canny edge detector
                100, //Threshold at the center detection stage
                3, //min radius
                500 //max radius
        );

        for(int i = 0; i < circles.total(); i++){
            opencv_core.CvPoint3D32f circle = new opencv_core.CvPoint3D32f(cvGetSeqElem(circles, i));
            CvPoint center = cvPointFrom32f(new CvPoint2D32f(circle.x(), circle.y()));
            int radius = Math.round(circle.z());
            cvCircle(src, center, radius, opencv_core.CvScalar.GREEN, 6, CV_AA, 0);
        }

        cvShowImage("Result",src);
        cvWaitKey(0);

    }
}
