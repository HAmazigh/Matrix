package fr.ter.asa.matrixthisforme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MatricesListe extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	 @Override
	    public View onCreateView(LayoutInflater inflater,
	    		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	    	View rootView=inflater.inflate(R.layout.activity_matrices_liste, container, false);
			return rootView;
	    }
}
