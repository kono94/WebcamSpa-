package gui;

import model.Model;

public class View {
    private Model m_model;
    private MyFrame m_myFrame;
    
    public View(Model model) {
	m_model = model;
	m_myFrame = new MyFrame(this, m_model);
    }
    
    public void displayNewFrame() {
	m_myFrame.getVideoComp().repaint();
    }
    
    public MyFrame getMyFrame() {
	return m_myFrame;
    }
}
