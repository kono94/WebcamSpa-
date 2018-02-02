package controller;

import gui.View;
import model.Model;

public class Controller {
    private View m_view;
    private Model m_model;
    private EarManager m_earManager;
    private VideoManager m_videoManager;
    
    public Controller() {
	m_model = new Model();
	m_view = new View(m_model);
	m_earManager = new EarManager(this);
	m_earManager.applyWindowListeners();
	
	m_videoManager = new VideoManager(this);
	startCamStream();
    }    
    

    
    void startCamStream() {
	m_videoManager.initVideoThread();
    }
    
    void stopCamStream() {
	m_videoManager.destroyVideoThread();
    }
    
    Model getModel() {
	return m_model;
    }
    
    View getView() {
	return m_view;
    }
    
    boolean isStreamRunning() {
	return m_videoManager.isThreadRunning();
    }
}
