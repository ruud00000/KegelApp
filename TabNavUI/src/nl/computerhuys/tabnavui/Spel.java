/**
 * 
 */
package nl.computerhuys.tabnavui;

import java.util.ArrayList;

import android.widget.EditText;
import android.widget.Spinner;

/**
 * @author CRMADMIN
 *
 */
public class Spel {

	/**
	 * instantievariabele van Spel
	 */
	static final int SPELER_NUMMER = 0;
	private int spelNummer;
	private int baanNummer;
	private int worpNummer;
	private int volgNummer = 1;
	private int beurtNummer = 1;

	private ArrayList<int[][]> spelBanenWorpenScore = new ArrayList<int[][]>();
	// holding:
	private int[][] banenWorpenScore = new int [InitSpel.aantalWorpen][InitSpel.aantalParallel];
	// holding:
	private int[] banenWorpScore = new int [InitSpel.aantalParallel];
	
	private ArrayList<int[]> banenWorpenTotalen = new ArrayList<int[]>();
	// holding:
	private int[] banenWorpenTotaal = new int [InitSpel.aantalParallel];
	// holding:
	private int baanWorpenTotaal;

	private ArrayList<int[]> banenSpelerNummers = new ArrayList<int[]>();
	// holding:
    private int banenSpelerNummer[] = new int [InitSpel.aantalParallel];
    // holding: 
    private int baanSpelerNummer = 0;

    private int parallelNummer;
    private ArrayList<Integer> spelersNummer = new ArrayList<Integer>();
    // holding:
    private int SpelerNummer;

    private ArrayList<Integer> spelersBeurtNummer = new ArrayList<Integer>();
    // holding:
	// see: second element of spelerSpel: spelerBeurtNummer

    private ArrayList<Integer> spelersSpelTotaal = new ArrayList<Integer>();
    // holding:
    private int spelerSpelTotaal;

    
    private int aantalSpelers=0;
    
/*		private int[][][][] spelersSpel = {{{{1}},{{2}},{{70,80}},{{7,7,7,7,7,7,7,7,7,7},{8,8,8,8,8,8,8,8,8,8}},{{150}}},
								 {{{2}},{{2}},{{60,75}},{{6,6,6,6,6,6,6,6,6,6},{7,7,7,7,7,8,8,8,8,8}},{{135}}},
								 {{{3}},{{2}},{{80,50}},{{8,8,8,8,8,8,8,8,8,8},{5,5,5,5,5,5,5,5,5,5}},{{130}}},
								 {{{4}},{{2}},{{75,50}},{{7,7,7,7,7,8,8,8,8,8},{5,5,5,5,5,5,5,5,5,5}},{{125}}}};
*/		
	// containerarray spelersSpel:
	// private ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> spelersSpel = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
	private ArrayList<int[][][]> spelersSpel = new ArrayList<int[][][]>();
	
	// holding:
	// first-level element of spelersSpel:
	// {{{1}},{{2}},{{70,80}},{{7,7,7,7,7,7,7,7,7,7},{8,8,8,8,8,8,8,8,8,8}},{{150}}}
	
	private int[][][] spelerSpel = new int [6][][];
	// private int[][][] spelerSpel = {{{0}},{{0}},{{0,0}},{{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}},{{0}},{{0,0}}};
	
	// holding:
	
	// first element of spelerSpel:
	private int spelerNummer;
	
	// second element of spelerSpel:
	private int spelerBeurtNummer;
	
	// third element of spelerSpel:
	private int[][] spelerBeurtenWorpenScore = new int [InitSpel.aantalBeurten][];
	// holding:
	private int[] spelerBeurtWorpenScore = new int [InitSpel.aantalWorpen];
	// holding:
	private int spelerBeurtWorpScore;
	
	// fourth element of spelerSpel:
	private int[] spelerBeurtenTotaal = new int [InitSpel.aantalBeurten];
	// holding:
	private int spelerBeurtTotaal;
	
	// fifth element of spelerSpel:
	private int spelerTotaal;
	
	// sixth element of spelerSpel:
	private int[] spelerBanen = new int [InitSpel.aantalBeurten];
	// holding:
	private int spelerBaan;	
	
	
	/**
	 * constructor: bij creatie van nieuwe spel moet het spelNummer opgegeven worden 
	 */
	public Spel(int nummer) {
		this.spelNummer = nummer;
	}
	
	/**
	 * methode voor ophalen van spelNummer van spel
	 * @return spelNummer
	 */
	public int getSpelNummer() {
		return spelNummer;
	}

	public void setSpelNummer(int nummer) {
		this.spelNummer = nummer;
	}

	/**
	 * @return the parallelNummer
	 */
	public int getParallelNummer() {
		return parallelNummer;
	}

	/**
	 * @param parallelNummer the parallelNummer to set
	 */
	public void setParallelNummer(int parallelNummer) {
		this.parallelNummer = parallelNummer;
	}

	/**
	 * @return the banenWorpScore
	 */
	public int[] getBanenWorpScore() {
		return banenWorpScore;
	}

	/**
	 * @param banenWorpScore the banenWorpScore to set
	 */
	public void setBanenWorpScore(int[] banenWorpScore) {
		this.banenWorpScore = banenWorpScore;
	}

	/**
	 * @return the banenWorpenTotaal
	 */
	public int[] getBanenWorpenTotaal() {
		return banenWorpenTotaal;
	}

	/**
	 * @param banenWorpenTotaal the banenWorpenTotaal to set
	 */
	public void setBanenWorpenTotaal(int[] banenWorpenTotaal) {
		this.banenWorpenTotaal = banenWorpenTotaal;
	}

	/**
	 * @param reset banenWorpenTotaal
	 */
	public void resetBanenWorpenTotaal() {
		for (int i = 0 ; i < banenWorpenTotaal.length; i++) {
			banenWorpenTotaal[i] = 0;
		}
	}

	
	/**
	 * @return the worpNummer
	 */
	public int getWorpNummer() {
		return worpNummer;
	}

	/**
	 * @param worpNummer the worpNummer to set
	 */
	public void setWorpNummer(int worpNummer) {
		this.worpNummer = worpNummer;
	}

	/**
	 * @return the banenWorpenScore
	 */
	public int[][] getBanenWorpenScore() {
		return banenWorpenScore;
	}

	/**
	 * @param banenWorpenScore the banenWorpenScore to set
	 */
	public void setBanenWorpenScore(int[][] banenWorpenScore) {
		this.banenWorpenScore = banenWorpenScore;
	}

	/**
	 * @return the banenWorpenTotalen
	 */
	public ArrayList<int[]> getBanenWorpenTotalen() {
		return banenWorpenTotalen;
	}

	/**
	 * @param banenWorpenTotalen the banenWorpenTotalen to set
	 */
	public void setBanenWorpenTotalen(ArrayList<int[]> banenWorpenTotalen) {
		this.banenWorpenTotalen = banenWorpenTotalen;
	}

	/**
	 * @return the volgNummer
	 */
	public int getVolgNummer() {
		return volgNummer;
	}

	/**
	 * @param volgNummer the volgNummer to set
	 */
	public void setVolgNummer(int volgNummer) {
		this.volgNummer = volgNummer;
	}

	/**
	 * @return the spelBanenWorpenScore
	 */
	public ArrayList<int[][]> getSpelBanenWorpenScore() {
		return spelBanenWorpenScore;
	}

	/**
	 * @param spelBanenWorpenScore the spelBanenWorpenScore to set
	 */
	public void setSpelBanenWorpenScore(ArrayList<int[][]> spelBanenWorpenScore) {
		this.spelBanenWorpenScore = spelBanenWorpenScore;
	}

	/**
	 * @return the banenSpelerNummers
	 */
	public ArrayList<int[]> getBanenSpelerNummers() {
		return banenSpelerNummers;
	}

	/**
	 * @param banenSpelerNummers the banenSpelerNummers to set
	 */
	public void setBanenSpelerNummers(ArrayList<int[]> banenSpelerNummers) {
		this.banenSpelerNummers = banenSpelerNummers;
	}

	/**
	 * @return the spelerSpel
	 */
	public int[][][] getSpelerSpel() {
		return spelerSpel;
	}

	/**
	 * @param spelerSpel the spelerSpel to set
	 */
	public void setSpelerSpel(int[][][] spelerSpel) {
		this.spelerSpel = spelerSpel;
	}

	/**
	 * @return the beurtNummer
	 */
	public int getBeurtNummer() {
		return beurtNummer;
	}

	/**
	 * @param beurtNummer the beurtNummer to set
	 */
	public void setBeurtNummer(int beurtNummer) {
		this.beurtNummer = beurtNummer;
	}

	/**
	 * @return the spelerBeurtNummer
	 */
	public int getSpelerBeurtNummer() {
		return spelerBeurtNummer;
	}

	/**
	 * @param spelerBeurtNummer the spelerBeurtNummer to set
	 */
	public void setSpelerBeurtNummer(int spelerBeurtNummer) {
		this.spelerBeurtNummer = spelerBeurtNummer;
	}

	/**
	 * @return the spelerBeurtenWorpenScore
	 */
	public int[][] getSpelerBeurtenWorpenScore() {
		return spelerBeurtenWorpenScore;
	}

	/**
	 * @param spelerBeurtenWorpenScore the spelerBeurtenWorpenScore to set
	 */
	public void setSpelerBeurtenWorpenScore(int[][] spelerBeurtenWorpenScore) {
		this.spelerBeurtenWorpenScore = spelerBeurtenWorpenScore;
	}

	/**
	 * @return the spelersBeurtNummer
	 */
	public ArrayList<Integer> getSpelersBeurtNummer() {
		return spelersBeurtNummer;
	}

	/**
	 * @param spelersBeurtNummer the spelersBeurtNummer to set
	 */
	public void setSpelersBeurtNummer(ArrayList<Integer> spelersBeurtNummer) {
		this.spelersBeurtNummer = spelersBeurtNummer;
	}

	/**
	 * @return the spelerBeurtWorpScore
	 */
	public int getSpelerBeurtWorpScore() {
		return spelerBeurtWorpScore;
	}

	/**
	 * @param spelerBeurtWorpScore the spelerBeurtWorpScore to set
	 */
	public void setSpelerBeurtWorpScore(int spelerBeurtWorpScore) {
		this.spelerBeurtWorpScore = spelerBeurtWorpScore;
	}

	/**
	 * @return the spelerBeurtWorpenScore
	 */
	public int[] getSpelerBeurtWorpenScore() {
		return spelerBeurtWorpenScore;
	}

	/**
	 * @param spelerBeurtWorpenScore the spelerBeurtWorpenScore to set
	 */
	public void setSpelerBeurtWorpenScore(int[] spelerBeurtWorpenScore) {
		this.spelerBeurtWorpenScore = spelerBeurtWorpenScore;
	}

	/**
	 * @return the spelerBeurtenTotaal
	 */
	public int[] getSpelerBeurtenTotaal() {
		return spelerBeurtenTotaal;
	}

	/**
	 * @param spelerBeurtenTotaal the spelerBeurtenTotaal to set
	 */
	public void setSpelerBeurtenTotaal(int[] spelerBeurtenTotaal) {
		this.spelerBeurtenTotaal = spelerBeurtenTotaal;
	}

	/**
	 * @return the baanWorpenTotaal
	 */
	public int getBaanWorpenTotaal() {
		return baanWorpenTotaal;
	}

	/**
	 * @param baanWorpenTotaal the baanWorpenTotaal to set
	 */
	public void setBaanWorpenTotaal(int baanWorpenTotaal) {
		this.baanWorpenTotaal = baanWorpenTotaal;
	}

	/**
	 * @return the spelerBeurtTotaal
	 */
	public int getSpelerBeurtTotaal() {
		return spelerBeurtTotaal;
	}

	/**
	 * @param spelerBeurtTotaal the spelerBeurtTotaal to set
	 */
	public void setSpelerBeurtTotaal(int spelerBeurtTotaal) {
		this.spelerBeurtTotaal = spelerBeurtTotaal;
	}

	/**
	 * @return the spelerTotaal
	 */
	public int getSpelerTotaal() {
		return spelerTotaal;
	}

	/**
	 * @param spelerTotaal the spelerTotaal to set
	 */
	public void setSpelerTotaal(int spelerTotaal) {
		this.spelerTotaal = spelerTotaal;
	}

	/**
	 * @return the spelerSpelTotaal
	 */
	public int getSpelerSpelTotaal() {
		return spelerSpelTotaal;
	}

	/**
	 * @param spelerSpelTotaal the spelerSpelTotaal to set
	 */
	public void setSpelerSpelTotaal(int spelerSpelTotaal) {
		this.spelerSpelTotaal = spelerSpelTotaal;
	}

	/**
	 * @return the spelersSpelTotaal
	 */
	public ArrayList<Integer> getSpelersSpelTotaal() {
		return spelersSpelTotaal;
	}

	/**
	 * @param spelersSpelTotaal the spelersSpelTotaal to set
	 */
	public void setSpelersSpelTotaal(ArrayList<Integer> spelersSpelTotaal) {
		this.spelersSpelTotaal = spelersSpelTotaal;
	}

	/**
	 * @return the spelerBanen
	 */
	public int[] getSpelerBanen() {
		return spelerBanen;
	}

	/**
	 * @param spelerBanen the spelerBanen to set
	 */
	public void setSpelerBanen(int[] spelerBanen) {
		this.spelerBanen = spelerBanen;
	}

	/**
	 * @return the baanNummer
	 */
	public int getBaanNummer() {
		return baanNummer;
	}

	/**
	 * @param baanNummer the baanNummer to set
	 */
	public void setBaanNummer(int baanNummer) {
		this.baanNummer = baanNummer;
	}

	/**
	 * @return the spelerBaan
	 */
	public int getSpelerBaan() {
		return spelerBaan;
	}

	/**
	 * @param spelerBaan the spelerBaan to set
	 */
	public void setSpelerBaan(int spelerBaan) {
		this.spelerBaan = spelerBaan;
	}

	/**
	 * @return the spelersNummer
	 */
	public ArrayList<Integer> getSpelersNummer() {
		return spelersNummer;
	}

	/**
	 * @param spelersNummer the spelersNummer to set
	 */
	public void setSpelersNummer(ArrayList<Integer> spelersNummer) {
		this.spelersNummer = spelersNummer;
	}

	/**
	 * @return the aantalSpelers
	 */
	public int getAantalSpelers() {
		return aantalSpelers;
	}

	/**
	 * @param aantalSpelers the aantalSpelers to set
	 */
	public void setAantalSpelers(int aantalSpelers) {
		this.aantalSpelers = aantalSpelers;
	}

	/**
	 * @return the spelersSpel
	 */
	public ArrayList<int[][][]> getSpelersSpel() {
		return spelersSpel;
	}

	/**
	 * @param spelersSpel the spelersSpel to set
	 */
	public void setSpelersSpel(ArrayList<int[][][]> spelersSpel) {
		this.spelersSpel = spelersSpel;
	}

	/**
	 * @return the banenSpelerNummer
	 */
	public int[] getBanenSpelerNummer() {
		return banenSpelerNummer;
	}

	/**
	 * @param banenSpelerNummer the banenSpelerNummer to set
	 */
	public void setBanenSpelerNummer(int banenSpelerNummer[]) {
		this.banenSpelerNummer = banenSpelerNummer;
	}

	/**
	 * @return the baanSpelerNummer
	 */
	public int getBaanSpelerNummer() {
		return baanSpelerNummer;
	}

	/**
	 * @param baanSpelerNummer the baanSpelerNummer to set
	 */
	public void setBaanSpelerNummer(int baanSpelerNummer) {
		this.baanSpelerNummer = baanSpelerNummer;
	}
	
    public void addSpelerToSpel() {
    	// Flowchart proces 2.1: Speler toevoegen aan spel:
    	if (!spelersNummer.contains(baanSpelerNummer)) {
	    	aantalSpelers++;
			int[][][] spelerSpel = {{{0}},{{0}},{{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}},{{0,0}},{{0}},{{0,0}}};
	    	spelerSpel[SPELER_NUMMER][0][0] = baanSpelerNummer;
	        // spelerscores (kloon) toevoegen aan spel 
	    	// (klonen met .clone of System.arraycopy werkt niet 123, dus dan maar even met lokale variabele spelerSpel werken)
	        spelersSpel.add(spelerSpel);    	
	    	spelersNummer.add(baanSpelerNummer);
	        spelersBeurtNummer.add(0);
	        spelersSpelTotaal.add(0);
    	}
    }
    
    public void addVolgNummerToSpel() {
    	
    	aantalSpelers++;
		int[][][] spelerSpel = {{{0}},{{0}},{{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}},{{0,0}},{{0}},{{0,0}}};
    	spelerSpel[SPELER_NUMMER][0][0] = baanSpelerNummer;
    	spelersNummer.add(baanSpelerNummer);
        // spelerscores (kloon) toevoegen aan spel 
    	// (klonen met .clone of System.arraycopy werkt niet 123, dus dan maar even met lokale variabele spelerSpel werken)
        spelersSpel.add(spelerSpel);    	
    }
}