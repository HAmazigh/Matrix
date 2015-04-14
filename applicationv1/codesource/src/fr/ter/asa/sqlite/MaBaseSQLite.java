package fr.ter.asa.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSQLite extends SQLiteOpenHelper {

	private static final String TABLE_MATRICE = "table_matrice";
	private static final String COL_ID = "ID";
	private static final String COL_TITRE = "Titre";
	private static final String COL_DATE = "Date";
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_MATRICE
			+ " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_TITRE + " TEXT NOT NULL, " + COL_DATE + " TEXT NOT NULL);";

	public MaBaseSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	// Création de la table matrice dans la base de données.
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
	}

	// au cas d'un changement de la version de la base de données
	// on supprime la table puis on recrée à nouveau
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE_MATRICE + ";");
		onCreate(db);

	}

}
