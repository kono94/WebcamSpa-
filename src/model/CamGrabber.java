package model;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class CamGrabber {
    private VideoCapture m_capture;
    
    public CamGrabber() {
	init();	
    }
    
    private void init() {
	m_capture = new VideoCapture(0);
	if(m_capture.isOpened()) {
	    System.out.println("Opened VideoCapture");
	}else {
	    System.out.println("Error opening VideoCapture");
	}
    }
    
    public void close() {
	m_capture.release();
    }
    
    public Image grabFrame() {
	Mat frame = new Mat();
	m_capture.read(frame);
	MatOfByte buffer = new MatOfByte();
	Imgcodecs.imencode(".png", frame, buffer);
	try {
	    return ImageIO.read(new ByteArrayInputStream(buffer.toArray()));
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }
    
}
