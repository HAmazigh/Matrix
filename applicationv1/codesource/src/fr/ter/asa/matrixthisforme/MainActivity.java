package fr.ter.asa.matrixthisforme;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity {

	
	Pager adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Création de la liste de Fragments que fera défiler le PagerAdapter
				ArrayList<Fragment> fragments = new ArrayList<Fragment>();
				
				// Ajout des Fragments dans la liste
				fragments.add(Fragment.instantiate(this,CapturerMatrice.class.getName()));
				fragments.add(Fragment.instantiate(this,MatricesListe.class.getName()));

				this.adapter = new Pager(super.getSupportFragmentManager(), fragments);

				ViewPager pager = (ViewPager) super.findViewById(R.id.panelpager);
				// Affectation de l'adapter au ViewPager
				pager.setAdapter(this.adapter);
			}
		
	}
	
