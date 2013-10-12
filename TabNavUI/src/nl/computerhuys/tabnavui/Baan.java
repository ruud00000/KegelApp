/**
 * 
 */
package nl.computerhuys.tabnavui;

/**
 * @author CRMADMIN
 *
 */
public class Baan {

	/**
	 * instantievariabele van Baan
	 */
	private int baanNummer;
	
	/**
	 * constructor: bij creatie van nieuwe baan moet het baanNummer opgegeven worden 
	 */
	public Baan(int nummer) {
		this.baanNummer = nummer;
	}
	
	/**
	 * methode voor ophalen van baanNummer van baan
	 * @return baanNummer
	 */
	public int getBaanNummer() {
		return baanNummer;
	}

	public void setBaanNummer(int nummer) {
		this.baanNummer = nummer;
	}
}
