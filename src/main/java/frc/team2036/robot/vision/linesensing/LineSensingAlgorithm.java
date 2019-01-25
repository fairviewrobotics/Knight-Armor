package frc.team2036.robot.vision.linesensing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;


import org.opencv.core.*;
import org.opencv.core.Core.*;
//import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

public class LineSensingAlgorithm {
    public Mat downscaled = new Mat(), gray = new Mat(), thresholded = new Mat(), blured = new Mat();

    /* Angle Info:
     * 0 is straight up
     * 0-90 indicates that we need to turn right
     * 180-90 indicates that we need to turn left
     */

    //angle - only updated if good angle found
    public double angle;
    public boolean angle_updated;
    //x and y pos of centroid;
    public double centroid_x;
    public double centroid_y;

    //algorithm vars
    //downscale size
    public int sizeX = 420, sizeY = 320;
    //blur size
    public int blurSize = 11;

    public void run(Mat input){
        //resize
        Imgproc.resize(input, downscaled, new Size(sizeX, sizeY));
        //grayscale
        Imgproc.cvtColor(downscaled, gray, Imgproc.COLOR_BGR2GRAY);
        //guassian blur
        Imgproc.GaussianBlur(gray, blured, new Size(blurSize, blurSize), 0);

        //threshold
        //Otsu's method automatically chooses a global threshold value - should work well if line is clearly brighter from most of the rest of the image
        Imgproc.threshold(blured, thresholded,0,255,Imgproc.THRESH_BINARY+Imgproc.THRESH_OTSU);

        //Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat contourHierarchy = new Mat();
        Imgproc.findContours(thresholded, contours, contourHierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        int largestIdx = findLargestContourIdx(contours);

        //find moments
        Moments moments = Imgproc.moments(contours.get(largestIdx));
        Point centroid = new Point();
        //centroid
	    centroid.x = moments.get_m10() / moments.get_m00();
        centroid.y = moments.get_m01() / moments.get_m00();

        //fit ellipse
        MatOfPoint2f contour_points = new MatOfPoint2f();
        contours.get(largestIdx).convertTo(contour_points, CvType.CV_32F);
        //5 points needed for elipse fitting algorithm
        if(contour_points.rows() >= 5){
            RotatedRect ellipse = Imgproc.fitEllipse(contour_points);

            //check major and minor axis
            double axis1 = ellipse.size.width;
            double axis2 = ellipse.size.height;

            double major_axis = axis1 > axis2 ? axis1 : axis2;
            double minor_axis = axis1 > axis2 ? axis2 : axis1;

            //check major axis is certain amount more than minor axis
            if(major_axis/minor_axis > 3.0 && ellipse.angle != 0.0){
                //angle of 0.0 indicates that the ellipse fitting algorithm didn't work
                synchronized(this){
                    angle = ellipse.angle;
                    angle_updated = true;
                }
            } else {
                synchronized(this){
                    angle_updated = false;
                }
            }

            synchronized(this){
                centroid_x = centroid.x;
                centroid_y = centroid.y;
            }

            Imgproc.drawContours(blured, contours, largestIdx, new Scalar(0,255,0), 5);
Imgproc.circle(blured, centroid, 3, new Scalar(0,0,0), 3);
        }




    }

    //find the largest contour index
    public int findLargestContourIdx(List<MatOfPoint> contours){
        double maxVal = 0;
        int maxValIdx = 0;
        for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++)
        {
            double contourArea = Imgproc.contourArea(contours.get(contourIdx));
            if (maxVal < contourArea)
            {
                maxVal = contourArea;
                maxValIdx = contourIdx;
            }
        }

        return maxValIdx;
    }

    public void setDownscaleSize(int w, int h){
        sizeX = w;
        sizeY = h;
    }

    public void setBlurSize(int size){
        blurSize = size;
    }
}