package com.central.image.leptonica.detection;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;

public class DetectFaceDemo {

    public static final String XML_FILE =   "/home/dxuser/workspace/works/nlp/stanford/ImageRec/src/main" +
            "/resources/sample-images/" +
            "detection/haarcascade_frontalface_default.xml";

    public static void main(String[] args){

        opencv_core.IplImage img = opencv_imgcodecs.cvLoadImage("/home/dxuser/workspace/works/nlp/stanford/ImageRec" +
                "/src/main/resources/sample-images/detection/face3 .jpg");
        detect(img);
    }

    public static void detect(IplImage src){

        CvHaarClassifierCascade cascade = new
                CvHaarClassifierCascade(opencv_core.cvLoad(XML_FILE));
        CvMemStorage storage = CvMemStorage.create();
        CvSeq sign = opencv_objdetect.cvHaarDetectObjects(
                src,
                cascade,
                storage,
                1.5,
                3,
                opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING);

        opencv_core.cvClearMemStorage(storage);

        int total_Faces = sign.total();

        for(int i = 0; i < total_Faces; i++){
            CvRect r = new CvRect(opencv_core.cvGetSeqElem(sign, i));
            opencv_imgproc.cvRectangle(
                    src,
                    opencv_core.cvPoint(r.x(), r.y()),
                    opencv_core.cvPoint(r.width() + r.x(), r.height() + r.y()),
                    CvScalar.RED,
                    2,
                    opencv_imgproc.CV_AA,
                    0);

        }

        opencv_highgui.cvShowImage("Result", src);
        opencv_highgui.cvWaitKey(0);

    }

}
