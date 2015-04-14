package fr.ter.asa.matrixthisforme;


import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;




public class Pager extends FragmentPagerAdapter{

	private final ArrayList<Fragment> fragments;

	//On fournit à l'adapter la liste des fragments à afficher
	public Pager (FragmentManager fm, ArrayList<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
        case 0:
        	Fragment frag2 =new CapturerMatrice();
            return frag2;
            
        case 1:
        	Fragment frag = new MatricesListe();
            return frag;
        }
		return null;
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
	

}
