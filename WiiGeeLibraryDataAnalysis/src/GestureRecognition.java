import org.wiigee.control.Wiigee;
import org.wiigee.event.GestureListener;
import org.wiigee.filter.Filter;
import org.wiigee.util.Log;

/*
 * Core java implementation of Wiigee library
 * to work with a logfile, which has the 
 * gesture data.
 * 
 * 
 * @author prakash reddy vaka
 * 06/20/2014
 * CS5090BD
 * SCE , UMKC
 */
public class GestureRecognition extends Wiigee{

	 protected static String pluginversion = "1.0.3 alpha - untested!";
	 protected static String pluginreleasedate = "20101011";
	DeviceLogAnalysis device;
	 public GestureRecognition() {
	        super();
	        Log.write("This is wiigee-plugin-android (Andgee) version "+pluginversion+" ("+pluginreleasedate+")");
	        //true for autofiltering
	        device=new DeviceLogAnalysis(true);
	        // create a device class which can read data from a file when triggers passed from here.
	    }

	 
	 public void addGestureListener(GestureListener listener) {
         device.addGestureListener(listener);
	 }

	 public void addFilter(Filter filter) {
		 device.addAccelerationFilter(filter);
	 }


	 public DeviceLogAnalysis getDevice() {
		 return device;
	 }
	 
	 /**
	     * Sets the Trainbutton from the log file start
	     *
	     * @param b Button encoding, see static values
	     */
	    public void setTrainButton(int b) {
	            device.setTrainButton(b);
	    }

	    /**
	     * Sets the Recognitionbutton from the log file start;
	     *
	     * @param b Button encoding, see static values
	     */
	    public void setRecognitionButton(int b) {
	            device.setRecognitionButton(b);
	    }

	    /**
	     * Sets the CloseGesturebutton from the log file start;
	     *
	     * @param b Button encoding, see static values
	     */
	    public void setCloseGestureButton(int b) {
	            device.setCloseGestureButton(b);
	    }
	 //end
	
}
