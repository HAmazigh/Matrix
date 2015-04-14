package fr.ter.asa.matrixthisforme;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

@SuppressWarnings("deprecation")
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder mHolder;
   
	private Camera mCamera;
	Context Context;
	List<Size> mSupportedPreviewSizes =null;
	Camera.Size myBestSize = null;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        Context=context;
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
    
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("CameraPreview1", "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
          // preview surface does not exist
          return;
        }
        Parameters parameters = mCamera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);  
        parameters.setPictureSize(w,h);
         /*mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
         if (mSupportedPreviewSizes != null) {
         	myBestSize = getOptimalPreviewSize(mSupportedPreviewSizes,2448 , 3264);
         }
         */
         Display display = ((WindowManager)getContext().getSystemService(android.content.Context.WINDOW_SERVICE)).getDefaultDisplay();

         if(display.getRotation() == Surface.ROTATION_0)
         {
             parameters.setPreviewSize(1280, 720);                           
             mCamera.setDisplayOrientation(90);
         }

         if(display.getRotation() == Surface.ROTATION_90)
         {
             parameters.setPreviewSize(720,1280);                           
         }

         if(display.getRotation() == Surface.ROTATION_180)
         {
             parameters.setPreviewSize(1280, 720);               
         }

         if(display.getRotation() == Surface.ROTATION_270)
         {
             parameters.setPreviewSize(1280,720);
             mCamera.setDisplayOrientation(180);
         }

        mCamera.setParameters(parameters);
        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d("CameraPreview1", "Error starting camera preview: " + e.getMessage());
        }
       
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    	final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);

        if (mSupportedPreviewSizes != null) {
        	myBestSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
        }
    }
    
    
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
  

}

