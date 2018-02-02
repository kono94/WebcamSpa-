import org.opencv.core.Core;

import controller.Controller;

public class MyMain {
    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	new Controller();
    }
}
