package controller;

class VideoManager {
    
    private Controller m_c;
    private boolean m_fStreamVideo;
    
    public VideoManager(Controller c) {
	m_c = c;
    }
    
    void initVideoThread() {
	m_fStreamVideo = true;
	new VideoThread();
    }
    
    void destroyVideoThread() {
	m_fStreamVideo = false;
    }
    
    boolean isThreadRunning() {
	return m_fStreamVideo;
    }
   private class VideoThread extends Thread{	
	public VideoThread() {
	   Thread t = new Thread(this);
	   t.start();
	}
	
	@Override
	public void run() {
	    while(m_fStreamVideo) {
		    m_c.getModel().pullNewFrame();
		    m_c.getView().displayNewFrame();
		    try {
			Thread.sleep(33);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	}
    }
}
