package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSlider;

import model.Model;

public class MyFrame extends JFrame {
    private View m_view;
    private Model m_model;
    private JComponent m_videoComp;
    private JSlider m_shiftSlider;
    private JSlider m_detectSlider;
    
    public MyFrame(View view, Model model) {
	m_view = view;
	m_model = model;
	setTitle("Spaß mit Webcams");
	
	setLocationRelativeTo(null);
	BorderLayout borderLayout = new BorderLayout();
	m_videoComp = new JComponent() {
	    {
		setPreferredSize(new Dimension(400,400));
		setMinimumSize(new Dimension(400,400));
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        System.out.println(getWidth());
	        Image imgToDraw = createImage(m_model.getLatestMIS(getWidth(), getHeight()));
	        g.drawImage(imgToDraw, 0, 0, getWidth(), getHeight(), Color.BLACK, this);
	    }
	};
	
	m_shiftSlider = new JSlider(0,20, 0);
	m_shiftSlider.addChangeListener(e->{
	    m_model.setShiftValue(m_shiftSlider.getValue());
	});
	m_detectSlider = new JSlider(0,1000, 100);
	m_detectSlider.addChangeListener(e->{
	    m_model.setDetectionValue(m_detectSlider.getValue());
	});
	
	setLayout(borderLayout);
	add(BorderLayout.CENTER, m_videoComp);	
	add(BorderLayout.SOUTH, m_shiftSlider);
	add(BorderLayout.WEST, m_detectSlider);
	pack();
	setVisible(true);
    }
    
    public JComponent getVideoComp() {
	return m_videoComp;
    }
    public JSlider getShiftSlider() {
	return m_shiftSlider;
    }
}
