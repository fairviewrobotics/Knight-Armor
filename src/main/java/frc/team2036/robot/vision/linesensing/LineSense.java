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

public class LineSense {
    public Mat image = new Mat();
    private VideoCapture cam;
    public LineSensingAlgorithm algorithm = new LineSensingAlgorithm();

    static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

    public void openCamera(int index) {
        VideoCapture camera = new VideoCapture(index);
        if(!camera.isOpened()){
            throw new RuntimeException("Camera will not open");
        }

        cam = camera;
    }

    public void runAlgorithm(){
        cam.read(image);
        algorithm.run(image);
    }
}