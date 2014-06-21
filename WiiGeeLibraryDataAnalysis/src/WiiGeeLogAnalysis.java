import java.io.IOException;
import java.util.logging.Logger;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;

public class WiiGeeLogAnalysis {

	static final int _TRAIN_BUTTON = 0x01;
	static final int _SAVE_BUTTON = 0x02;
	static final int _RECOGNIZE_BUTTON = 0x03;
	static GestureRecognition wiigee;
	static Logger logger;
	static boolean isRecording;
	static boolean isRecognizing;
	public static void main(String[] args) {
		
	
		start();
		
	}
	private static void start() {
		wiigee = new GestureRecognition();
		try {
			wiigee.getDevice().setAccelerationEnabled(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		isRecording = false;
		isRecognizing = false;
		
		wiigee.setTrainButton(_TRAIN_BUTTON);
		wiigee.setCloseGestureButton(_SAVE_BUTTON);
		wiigee.setRecognitionButton(_RECOGNIZE_BUTTON);
		wiigee.addGestureListener(new GestureListener() {
			//should change
			@Override
			public void gestureReceived(GestureEvent event) {
			//after receiving gesture from the sensor
				
				
			}
		});
		
	}
	
	// events from xml
		public static void onRecordButtonClick() {
			if(isRecording) {
				isRecording = false;
				wiigee.getDevice().fireButtonReleasedEvent(_TRAIN_BUTTON);
			}
			else {
				isRecording = true;
				wiigee.getDevice().fireButtonPressedEvent(_TRAIN_BUTTON);
			}
		}
		
		public static void onSaveButtonClick() {
			wiigee.getDevice().fireButtonPressedEvent(_SAVE_BUTTON);
			wiigee.getDevice().fireButtonReleasedEvent(_SAVE_BUTTON);
		}
		
		public static void onRecognizeButtonClick() {
			if(isRecognizing) {
				isRecognizing = false;
				wiigee.getDevice().fireButtonReleasedEvent(_RECOGNIZE_BUTTON);
			}
			else {
				isRecognizing = true;
				wiigee.getDevice().fireButtonPressedEvent(_RECOGNIZE_BUTTON);
			}
		}
	
	
}

