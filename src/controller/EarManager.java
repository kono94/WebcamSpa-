package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.MyFrame;


class EarManager{
    private MyFrame m_myFrame;
    private Controller m_c;
    
    public EarManager(Controller c) {
	m_c = c;
	m_myFrame = m_c.getView().getMyFrame();	
    }
        
    public void applyWindowListeners() {
	//MyFrame Window Listeners	
	m_myFrame.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		super.windowClosing(e);	
		m_c.stopCamStream();
		m_c.getModel().getCamGrabber().close();
	        m_myFrame.dispose();
	    }
	});	
	
	m_myFrame.getVideoComp().addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
	        if(m_c.isStreamRunning())
	            m_c.stopCamStream();
	        else
	            m_c.startCamStream();
	    }
	});
    }
    
    
}
