package fr.ter.asa.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class MatriceBDD {
	
	private static final int VERSION_BDD = 0;
	private static final String NOM_BDD = "matrice.db";

	private static final String TABLE_MATRICE = "table_matrice";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_TITRE = "Titre";
	private static final int NUM_COL_TITRE = 1;
	private static final String COL_DATE = "Date";
	private static final int NUM_COL_DATE = 2;
	
	private SQLiteDatabase bdd;
	private MaBaseSQLite maBaseSQLite;
	
	public MatriceBDD(Context context) {
		// On créer la BDD et sa table
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
	public void open() {
		// on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}

	public void close() {
		// on ferme l'accès à la BDD
		bdd.close();
	}

	public SQLiteDatabase getBDD() {
		return bdd;
	}
	
	// on insère une matrice
		public long insertMatrice(Matrice matrice) {
			ContentValues values = new ContentValues();
			values.put(COL_TITRE, matrice.getTitre());
			values.put(COL_DATE, matrice.getDate());
			return bdd.insert(TABLE_MATRICE, null, values);
		}
		
	// on supprime une matrice à partir de son Titre.
		public int removeMatriceWithTitle(String title) {
			return bdd.delete(TABLE_MATRICE, COL_TITRE + " = \"" + title + "\" ", null);
		}
	
		public ArrayList<Matrice> getMatrice(String name) {
			String selectQuery = " SELECT " + COL_ID + "," + COL_TITRE + "," + COL_DATE ;
			Log.i("request :", selectQuery);
			bdd = maBaseSQLite.getReadableDatabase();
			Cursor c = bdd.rawQuery(selectQuery, null);
			return cursorToMatrice(c);
		}

		// Cette méthode permet de convertir un cursor en une liste des photos
		private ArrayList<Matrice> cursorToMatrice(Cursor c) {

			ArrayList<Matrice> listeMatrice = new ArrayList<Matrice>();
			if (c.moveToFirst()) {
				do {
					Matrice m = new Matrice();
					m.setId(c.getInt(NUM_COL_ID));
					m.setTitre(c.getString(NUM_COL_TITRE));
					m.setDate(c.getString(NUM_COL_DATE));
					
					listeMatrice.add(m);
				} while (c.moveToNext());
			}
			maBaseSQLite.close();
			return listeMatrice;

		}
	

}
