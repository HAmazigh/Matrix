package matrix;

public class Matrix {

	private int nligne;
	private int ncols;
	private double[][] data;

	public Matrix(double[][] dat) {
		this.data = dat;
		this.nligne = dat.length;
		this.ncols = dat[0].length;

	}

	public Matrix(int nrow, int ncol) {
		this.nligne = nrow;
		this.ncols = ncol;
		data = new double[nrow][ncol];
	
	}

	public int getNligne() {
		return nligne;
	}

	public void getNcol(int nrows) {
		this.nligne = nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public double[][] getValues() {
		return data;
	}

	public void setValues(double[][] values) {
		this.data = values;
	}

	public void setValueAt(int row, int col, double value) {
		data[row][col] = value;
	}

	public double getValueAt(int row, int col) {
		return data[row][col];
	}

	public boolean isSquare() {
		return nligne == ncols;
	}

	public int size() {
		if (isSquare())
			return nligne;
		return -1;
	}

	public Matrix multplierParCons(double constant) {
		Matrix mat = new Matrix(nligne, ncols);
		for (int i = 0; i < nligne; i++) {
			for (int j = 0; j < ncols; j++) {
				mat.setValueAt(i, j, data[i][j] * constant);
			}
		}
		return mat;
	}
	
	public Matrix insertColumnWithValue1() {
		Matrix X_ = new Matrix(this.getNligne(), this.getNcols()+1);
		for (int i=0;i<X_.getNligne();i++) {
			for (int j=0;j<X_.getNcols();j++) {
				if (j==0)
					X_.setValueAt(i, j, 1.0);
				else 
					X_.setValueAt(i, j, this.getValueAt(i, j-1));
				
			}
		}
		return X_;
	}
	
	//Une méthode pour afficher completement une matrice
	public String showMatrix(){
		String matrice="";
		double t[][]=this.getValues();
		
		for (int i=0; i<t.length; i++){	
			for (int j=0; j<t[i].length; j++){
				matrice=matrice+this.getValueAt(i, j)+',';
			}
		}
		return matrice;
	}
}
