package com.central.image.leptonica.basic;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_photo;

import static org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_core.scharSaturateCast;

public class Denoising {

    public static void deNoiseGray(){
        Mat src_image = opencv_imgcodecs.imread("/home/dxuser/workspace/works/nlp/stanford/ImageRec/src/main/resources" +
                        "/sample-images/denoise/Noisy Signal.png",
                opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        System.out.println(src_image.channels());
        Mat dst_image = new Mat();
        opencv_photo.fastNlMeansDenoising(src_image, dst_image,23,5,19);
        opencv_imgcodecs.imwrite("deNoise_gray.png",dst_image);
        System.out.println("Done!..");
    }

    public static void deNoiseColored(){
        Mat src_image = opencv_imgcodecs.imread("/home/dxuser/workspace/works/nlp/stanford/ImageRec/src/main/resources" +
                "/sample-images/denoise/peppers_gnoise.png");
        System.out.println(src_image.channels());
        Mat dst_image = new Mat();
        opencv_photo.fastNlMeansDenoisingColored(src_image, dst_image, 13, 9, 7, 21);
        opencv_imgcodecs.imwrite("deNoise_colored.png",dst_image);
        dst_image.convertTo(dst_image,-1,1,50);
        opencv_imgcodecs.imwrite("deNoise_colored1.png",dst_image);

        System.out.println("Done!..");
    }

    public static void makeQuality(Mat src, Mat des){
        double alpha = 2;
        int beta = 1;
        for( int y = 0; y < src.rows(); y++ ){
            for( int x = 0; x < src.cols(); x++ ){
                for( int c = 0; c < 3; c++ ){
                    des.ptr(y,x).put(c,scharSaturateCast(alpha * (src.ptr(y,x).get(c)) + beta));
                }
            }
        }
        opencv_imgcodecs.imwrite("deNoise_colored_improved.png",des);
    }


    public static void main(String[] args) {
        deNoiseColored();
    }
}
