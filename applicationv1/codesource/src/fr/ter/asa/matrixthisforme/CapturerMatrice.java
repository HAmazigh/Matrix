package fr.ter.asa.matrixthisforme;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;


@SuppressWarnings("deprecation")
public class CapturerMatrice extends Fragment {

	 private String REPERTOIRE_IMAGE="MatrixMe";
 	 private Camera mCamera;
	 private CameraPreview mPreview;
	 PictureCallback rawCallback;
	 ShutterCallback shutterCallback;
	 PictureCallback jpegCallback;
	 protected FragmentActivity mActivity;

	    
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }

    @SuppressLint("NewApi") @Override
    public View onCreateView(LayoutInflater inflater,
    		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    	View rootView=inflater.inflate(R.layout.activity_capturer_matrice, container, false);
    	
    	
        mCamera = getCameraInstance();
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(mActivity, mCamera);
        FrameLayout preview = (FrameLayout) rootView.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        ImageView capture= new ImageView(getActivity());
        capture.setImageResource(R.drawable.camera);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(new LayoutParams(90, 90));
        lp.gravity=Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        lp.bottomMargin=100;
        capture.setLayoutParams(lp);
        preview.addView(capture,lp);
        ImageView gallery= new ImageView(getActivity());
        gallery.setImageResource(R.drawable.gallery);
        FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(new LayoutParams(90, 90));
        lp2.gravity=Gravity.BOTTOM | Gravity.END;
        lp2.bottomMargin=50;
        lp2.rightMargin=20;
        gallery.setLayoutParams(lp2);
        preview.addView(gallery,lp2);
        gallery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (Environment.getExternalStorageState().equals("mounted")) {
				    Intent intent = new Intent();
				    intent.setType("image/*");
				    intent.setAction(Intent.ACTION_PICK);
				    startActivityForResult(Intent.createChooser(intent,"Select Picture:"),100);
				}
				
			}
		});
        
       capture.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			 captureImage();
		}

		
	});
       jpegCallback = new PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                	String dir = Environment.getExternalStorageDirectory()
							+ File.separator + REPERTOIRE_IMAGE;
					File folder = new File(dir);
					if (!folder.exists())
						folder.mkdirs();
					File image = new File(folder,"MAT_"+ System.currentTimeMillis()+ ".jpg");
					if (image.exists())
						image.delete();
					String filePath=image.getAbsolutePath();
                    outStream = new FileOutputStream(image);
                    outStream.write(data);
                    outStream.close();
                    Intent intent = new Intent(mActivity,AfficherImage.class);
	    		    intent.putExtra("imageSelected", filePath);
	    		    startActivity(intent);
                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
                Log.d("Log", "onPictureTaken - jpeg");
                
            }
        };
    	
		return rootView;
    }
    
    
    private void captureImage() {
		 mCamera.takePicture(shutterCallback, rawCallback, jpegCallback);	
		}
	 
	 @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    Uri selectedImageUri = data.getData();
		    String selectedImagePath = getPath(selectedImageUri);
		    Log.i("ImagePath", selectedImagePath);
		    Intent intent = new Intent(getActivity(),AfficherImage.class);
		    intent.putExtra("imageSelected", selectedImagePath);
		    startActivity(intent);
		    //Bitmap photo = getPreview(selectedImagePath);
	}
	
		public String getPath(Uri uri) {
		    String[] projection = { MediaStore.Images.Media.DATA };
		    Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
		    int column_index = cursor
		        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		    cursor.moveToFirst();
		    return cursor.getString(column_index);
		}


	 public static Camera getCameraInstance(){
		    Camera c = null;
		    Log.e("ana ","hna");
		    try {
		        c = Camera.open(); // attempt to get a Camera instance
		        Log.e("ana ","hna2");
		    }
		    catch (Exception e){
		      Log.i("caméra",e.getMessage());
		    }
		    return c; // returns null if camera is unavailable
		}
	
}
