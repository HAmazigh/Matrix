 package fr.ter.asa.matrixthisforme;

import java.io.File;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import fr.ter.asa.calcul.Matrix;
import fr.ter.asa.calcul.MatrixOperation;
import fr.ter.asa.calcul.NoSquareException;


public class AfficherImage extends Activity {
	
	
	Mat tmp=null ;
	Bitmap bmp = null;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_image);
        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_2, this, mOpenCVCallBack))
        {
            Log.e("TEST", "Cannot connect to OpenCV Manager");
        }
        TextView titre= (TextView) findViewById(R.id.AfficherMatrice);
        titre.setText("Ma matrice");
        
        ImageView image= (ImageView) findViewById(R.id.back);
        image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
        
    }
    
    private BaseLoaderCallback  mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                    {
                    	new chargementImage().execute() ;    
                    } break;
                    default:
                    {
                        super.onManagerConnected(status);
                        Log.i("adel","iyeeeh2");
                    } break;
                }
        }
        };
        private class chargementImage extends AsyncTask<Void, Integer, Void> {
    		
        	private ProgressDialog Dialog = new ProgressDialog(AfficherImage.this);
        	Bitmap bm =null;
        	Mat mask=null;
        	String matrice;
        	
    		@Override
    		protected void onPreExecute() {
    			Log.e("yep","hoop");
    			Dialog.setMessage("Chargement..");
    			Dialog.show();
    		}
    		@Override
    		protected Void doInBackground(Void... arg0) {
    			
    			
    			//String path= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"matrice3.png";
    			Bundle extras = getIntent().getExtras();
    			String path = extras.getString("imageSelected");
    			Mat src=Highgui.imread(path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
            /*
    			Imgproc.GaussianBlur(src, src, new Size(3,3), 0);
    			Imgproc.adaptiveThreshold(src, src, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, 
                		Imgproc.THRESH_BINARY, 75, 10);
    			Core.bitwise_not(src, src);
    			
    			Mat src_copy=src.clone();
    			List<MatOfPoint> contours = new ArrayList<MatOfPoint>(); 
    		    Imgproc.findContours(src_copy, contours, new Mat(), Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_TC89_KCOS);
    			
    		  

    		    MatOfPoint2f         approxCurve = new MatOfPoint2f();
    		    
    		    
    		    //For each contour found
    		    for (int i=0; i<contours.size(); i++)
    		    {
    		        //Convert contours(i) from MatOfPoint to MatOfPoint2f
    		        MatOfPoint2f contour2f = new MatOfPoint2f( contours.get(i).toArray() );
    		        //Processing on mMOP2f1 which is in type MatOfPoint2f
    		        double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
    		        Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

    		        //Convert back to MatOfPoint
    		        MatOfPoint points = new MatOfPoint( approxCurve.toArray() );

    		        // Get bounding rect of contour
    		        Rect rect = Imgproc.boundingRect(points);

    		         // draw enclosing rectangle (all same color, but you could use variable i to make them unique)
    		        Core.rectangle(src, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(255), 3); 
    		        mask = Mat.zeros(src.size(), CvType.CV_8UC1);
    		        Imgproc.drawContours(src, contours,i, new Scalar(255), 5);
    		        Log.i("index","est : "+i);
    		        
    		    }
    		   */
    			//Imgproc.drawContours(src, contours, 1, new Scalar(0,0,255));
    			bm = Bitmap.createBitmap(src.width(),src.height(),Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(src, bm);
                matrice=detectText(bm);
                Dialog.dismiss();
    			
							return null;	
    		    }
    		   

    		@SuppressLint("NewApi") @SuppressWarnings("null")
			@Override
    		protected void onPostExecute(Void result) {
    			
    			String lines[] = matrice.split("\\r?\\n");
    			String colonnes[] ;
    			colonnes=lines[0].split("\\s+");
    			final int [][] m=new int[lines.length][colonnes.length];
    			for(int i=0;i<lines.length;i++)
    			{
    				Log.i("Ligne "+i, lines[i]);
    				colonnes=lines[i].split("\\s+");
    				for(int j=0;j<colonnes.length;j++)
        			{
    					m[i][j]=Integer.parseInt(colonnes[j]);
        			}
    			}
    			;
    			//String resultat=printMatrix(m);
    			TableLayout tl = (TableLayout) findViewById(R.id.matrice);
			for (int i = 0; i < lines.length; i++) {
				// Create a TableRow and give it an ID
				TableRow tr = new TableRow(AfficherImage.this);
				tr.setId(100 + i);
				tr.setGravity(Gravity.CENTER_HORIZONTAL);
				colonnes = lines[i].split("\\s+");
				for (int j = 0; j < colonnes.length; j++) {
					TextView ligne = new TextView(AfficherImage.this);
					ligne.setId(200 + j);
					ligne.setTextColor(Color.BLUE);
					ligne.setPadding(0, 0, 60, 0);
					ligne.setText("" + m[i][j]);
					ligne.setTextSize(25);
					tr.addView(ligne);
				}

				tl.addView(tr);
			}
			ImageView valider = (ImageView) findViewById(R.id.valider);
			valider.setVisibility(View.VISIBLE);
	        ImageView modifier = (ImageView) findViewById(R.id.modifier);
	        modifier.setVisibility(View.VISIBLE);
	        
	        valider.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					LinearLayout item = (LinearLayout)findViewById(R.id.operations);
					item.removeAllViews();
			        View child = getLayoutInflater().inflate(R.layout.operations, null);
			        item.addView(child);
			        Button determinant= (Button) findViewById(R.id.determinant);
			           determinant.setOnClickListener(new OnClickListener() {
						
						@SuppressWarnings("deprecation")
						@Override
						public void onClick(View v) {
							
							final AlertDialog alertDialog = new AlertDialog.Builder(
			                        AfficherImage.this).create();
			               alertDialog.setTitle("Détérminant");
			               Matrix ma= new Matrix(m);
			               int det;
						try {
							det = MatrixOperation.determinant(ma);
							alertDialog.setMessage("Le détérminant est : "+det);
						} catch (NoSquareException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			               
			               //alertDialog.setIcon(R.drawable.tick);
			               alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int which) {
			                alertDialog.hide();
			                }
			        });
			 
			        // Showing Alert Message
			        alertDialog.show();
							
						}
					});
					
				}
			});
	        
         modifier.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					final LinearLayout item = (LinearLayout)findViewById(R.id.operations);
					item.removeAllViews();
			        View child = getLayoutInflater().inflate(R.layout.modifications, null);
			        item.addView(child);
			        final EditText colonne= (EditText) findViewById(R.id.colonneAModifier);
			        final EditText ligne=(EditText) findViewById(R.id.ligneAModifier);
			        final EditText nouvelleValeur=(EditText) findViewById(R.id.nouvelleVal);
			        
			        Button update= (Button) findViewById(R.id.update);
			        
			        update.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							 String col=colonne.getText().toString().trim();
					         String row=ligne.getText().toString().trim();
					         Log.i("String",row);
					         String val=nouvelleValeur.getText().toString().trim();
							Log.i("String",row);
							if(!val.equals("") && !row.equals("") && !col.equals(""))
					        {
								
					        	m[Integer.parseInt(row)][Integer.parseInt(col)]=Integer.parseInt(val);
					        	item.removeAllViews();
					        	TableLayout tl = (TableLayout) findViewById(R.id.matrice);
					        	tl.removeAllViews();
								for (int i = 0; i < m.length; i++) {
									// Create a TableRow and give it an ID
									TableRow tr = new TableRow(AfficherImage.this);
									tr.setId(100 + i);
									tr.setGravity(Gravity.CENTER_HORIZONTAL);
									
									for (int j = 0; j < m[i].length; j++) {
										TextView ligne = new TextView(AfficherImage.this);
										ligne.setId(200 + j);
										ligne.setTextColor(Color.BLUE);
										ligne.setPadding(0, 0, 60, 0);
										ligne.setText("" + m[i][j]);
										ligne.setTextSize(25);
										tr.addView(ligne);
									}

									tl.addView(tr);
								}
					        	
					        }
							
						}
					});
			        
				}
			});
           
	       
	        

		}
    		
    		
    		
    		
    		
    		public String printMatrix(int[][] matrix) {
    			String resultat="";
    			String newLine = System.getProperty("line.separator");
    		    for (int row = 0; row < matrix.length; row++) {
    		        for (int col = 0; col < matrix[row].length; col++) {
    		        	 resultat += String.format("%d ", matrix[row][col]);
    		        }
    		        resultat+=newLine;
    		    }
    		    Log.i("resultat :",resultat);
    		    return resultat;
    		}
    		
    		 public String detectText(Bitmap bitmap) {
    		        Log.e("Yes","I am here");
    		        TessBaseAPI tessBaseAPI = new TessBaseAPI();
    		        tessBaseAPI.setDebug(true);
    		        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ocr-datas/";
    		        tessBaseAPI.init(path, "eng+equ"); //Init the Tess with the trained data file, with english language
    		        tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
    		        tessBaseAPI.setImage(bitmap);
    		        String text = tessBaseAPI.getUTF8Text();
    		        Log.e("text",text);
    		        tessBaseAPI.end();
    		        return text;
    		    }
    		 
    		/*
    		void PreProcessImage(Mat inImage,Mat outImage,int sizex, int sizey)
    		{
    			Mat grayImage = null,blurredImage = null,thresholdImage = null,contourImage = null,regionOfInterest;
    			//vector<vector<Point> > contours; 
    			Imgproc.cvtColor(inImage, outImage, Imgproc.COLOR_BGR2GRAY);
    			org.opencv.core.Size size = new Size(2,2);
    			Imgproc.GaussianBlur(grayImage,blurredImage,size, 2, 2);
    			Imgproc.adaptiveThreshold(blurredImage, thresholdImage, 255,
    		               1, 1,11,2);
    			 thresholdImage.copyTo(contourImage); 
    			 List<MatOfPoint> contours;
    			 Imgproc.findContours(contourImage,contours,contourImage, Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
    			 //adaptiveThreshold(blurredImage, thresholdImage, 255, 1, 1, 11, 2); 
    			
    			
    		}*/
    		 
    		

        }
}
