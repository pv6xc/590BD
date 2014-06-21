import org.wiigee.device.Device;


public class DeviceLogAnalysis extends Device{
	static final String TAG = "DeviceLogAnalysis";

    private float x0, y0, z0, x1, y1, z1;
	public DeviceLogAnalysis(boolean autofiltering) {
		super(true);
		// 'Calibrate' values this has to be specific to the device
        this.x0 = 0;
        this.y0 = (float) -9.8;//SensorManager.STANDARD_GRAVITY;
        this.z0 = 0;
        this.x1 = (float)9.8;//SensorManager.STANDARD_GRAVITY;
        this.y1 = 0;
        this.z1 = (float)9.8;//SensorManager.STANDARD_GRAVITY;
	}
	//should change
	public void onGesturefound() {
		
		//data from log file
        float[] values = {1,1,1};//log.values;

        //no need of this condition
        if (this.accelerationEnabled){ //sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            double x, y, z;
            float xraw, yraw, zraw;
                /*
                 * calculation of acceleration vectors starts here. further
                 * information about normation exist in the public papers or
                 * the various www-sources.
                 *
                 */
                xraw = values[0];
                yraw = values[1];
                zraw = values[2];
                //normation of the raw data with the constants? not sure? May be to remove extra noise
                x = (double) (xraw - x0) / (double) (x1 - x0);
                y = (double) (yraw - y0) / (double) (y1 - y0);
                z = (double) (zraw - z0) / (double) (z1 - z0);
                
                this.fireAccelerationEvent(new double[] {x, y, z});
//                Log.i(TAG, "accel ("+x +","+y +","+z);
        }
    }
	
}