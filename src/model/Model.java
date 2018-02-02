package model;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Model {
    private CamGrabber m_camGrabber;
    private Image m_latestRawFrame;
    private ImageProcessor m_imgProcessor;
    private int m_shiftValue;
    private int m_detectionValue;
    
    public Model() {
	m_camGrabber = new CamGrabber();
	m_imgProcessor = new ImageProcessor();
	try {
	    m_latestRawFrame = ImageIO.read(new File("placeholderImage.png"));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public CamGrabber getCamGrabber() {
	return m_camGrabber;
    }
    
    public MemoryImageSource getLatestMIS(int compWidth, int compHeight) {
	m_imgProcessor.feedImage(m_latestRawFrame, compWidth, compHeight);
	//m_imgProcessor.shift(m_shiftValue);
	m_imgProcessor.detectEdges(m_detectionValue);
	return m_imgProcessor.getMIS();
    }
    
    public void pullNewFrame() {
	m_latestRawFrame = m_camGrabber.grabFrame();
    }
    
    public void setShiftValue(int i) {
	m_shiftValue = i;
    }
    public int getShiftValue() {
	return m_shiftValue;
    }
   
    public void setDetectionValue(int i) {
   	m_detectionValue = i;
       }
     public int getDetectonValue() {
   	return m_detectionValue;
       }
}
