package model;

import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ImageProcessor {
    private int[] m_currentPix;
    private int[] m_originalPix;
    private MemoryImageSource m_imgSrc;
    private PixelGrabber m_pixelGrabber;
    private int m_W;
    private int m_H;

    public ImageProcessor() {
    }

    public void feedImage(Image img, int width, int height) {
	m_W = width;
	m_H = height;
	m_currentPix = new int[m_W * m_H];
	m_originalPix = new int[m_W * m_H];
	img = img.getScaledInstance(m_W, m_H, Image.SCALE_SMOOTH);
	m_imgSrc = new MemoryImageSource(m_W, m_H, m_currentPix, 0, m_W);
	m_imgSrc.setAnimated(true);
	m_pixelGrabber = new PixelGrabber(img, 0, 0, m_W, m_H, m_originalPix, 0, m_W);
	try {
	    m_pixelGrabber.grabPixels();
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	hardCopyArray(m_currentPix, m_originalPix);
    }

    // values from b[] are written into a[]
    private void hardCopyArray(int[] a, int[] b) {
	for (int i = 0; i < a.length; i++) {
	    a[i] = b[i];
	}
    }

    public void shift(int shift) {
	for (int i = 0; i < m_currentPix.length; i++) {
	    m_currentPix[i] >>= shift;
	}
	m_imgSrc.newPixels();
    }

    public MemoryImageSource getMIS() {
	return m_imgSrc;
    }

    public void detectEdges(int detectionValue) {
	for (int x = 0; x < m_W; ++x) {
	    for (int y = 0; y < m_H; ++y) {
		m_currentPix[y * m_W + x] = compColor(m_originalPix, x, y, detectionValue);
	    }
	}
    }

    private int getRed(int col) {
	return (col >> 16) & 255;
    }

    private int getGreen(int col) {
	return (col >> 8) & 255;
    }

    private int getBlue(int col) {
	return col & 255;
    }

    private int compColor(int[] srcPix, int x, int y, int detectionValue) {
	int red = 0;
	int green = 0;
	int blue = 0;
	int cnt = 0;
	final int IDX = y * m_W + x;
	final int RED = getRed(srcPix[IDX]);
	final int GREEN = getGreen(srcPix[IDX]);
	final int BLUE = getBlue(srcPix[IDX]);
	for (int dx = -1; dx <= 1; ++dx) {
	    for (int dy = -1; dy <= 1; ++dy) {
		if (dx != 0 || dy != 0) {
		    final int X = x + dx;
		    final int Y = y + dy;
		    final int LOCAL_IDX = Y * m_W + X;
		    if (0 <= X && X < m_W && 0 <= Y && Y < m_H) {
			++cnt;
			red += Math.abs(RED - getRed(srcPix[LOCAL_IDX]));
			green += Math.abs(GREEN - getGreen(srcPix[LOCAL_IDX]));
			blue += Math.abs(BLUE - getBlue(srcPix[LOCAL_IDX]));
		    }
		}
	    }
	}
	if(red + green + blue > detectionValue)
	    return 0xff000000;
	else
	    return 0xffffffff;
	// return 0xff000000 | (255 - (red / cnt) << 16) | (255 - (green / cnt) << 8) | (255 - (blue / cnt));
    }

}
