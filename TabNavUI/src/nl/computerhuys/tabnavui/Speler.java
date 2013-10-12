/**
 * 
 */
package nl.computerhuys.tabnavui;

/**
 * @author CRMADMIN
 *
 */
public class Speler {

	/** 
	 * instantievarabelen
	 */
	private int spelerNummer;
	private String spelerNaam;
	private String spelerAchternaam;
	private String spelerVoornaam;
	private String spelerTv;
	private boolean selected=false;
	
	/**
	 * constructor waarin alle gegevens van speler worden geïnitialiseerd o.b.v. spelerNummer
	 * @param nummer
	 */
	public Speler(int nummer) {
		this.spelerNummer = nummer;
		readSpelerData(nummer);
	}

	/**
	 * Alle gegevens van speler ophalen vanaf sdcard o.b.v. spelerNummer
	 * @param veldNaam
	 * @param nummer
	 * @return
	 */
	private void readSpelerData(int nummer) {
		// TODO juiste veldwaarde van juiste spelerNummer ophalen uit bestandje met spelergegevens
		switch (nummer) {
		case 1 : 
			this.spelerNaam = "PietM";
			this.spelerAchternaam = "Moester";
			this.spelerVoornaam = "Piet";
			this.spelerTv = "";
			break;
		case 2 : 
			this.spelerNaam = "JanKr";
			this.spelerAchternaam = "Kruisselbrink";
			this.spelerVoornaam = "Jan";
			this.spelerTv = "";
			break;
		case 3 : 
			this.spelerNaam = "RoelD";
			this.spelerAchternaam = "Dekker";
			this.spelerVoornaam = "Roel";
			this.spelerTv = "";
			break;
		case 4 : 
			this.spelerNaam = "KeesS";
			this.spelerAchternaam = "Schieven";
			this.spelerVoornaam = "Kees";
			this.spelerTv = "";
			break;
		case 5 : 
			this.spelerNaam = "TheoC";
			this.spelerAchternaam = "Corts";
			this.spelerVoornaam = "Theo";
			this.spelerTv = "";
			break;
		case 6 : 
			this.spelerNaam = "JohKr";
			this.spelerAchternaam = "Kruisselbrink";
			this.spelerVoornaam = "Johan";
			this.spelerTv = "";
			break;
		case 7 : 
			this.spelerNaam = "WimgK";
			this.spelerAchternaam = "groot Kormelinck";
			this.spelerVoornaam = "Wim";
			this.spelerTv = "";
			break;
		case 8 : 
			this.spelerNaam = "RuudN";
			this.spelerAchternaam = "Naastepad";
			this.spelerVoornaam = "Ruud";
			this.spelerTv = "";
			break;
		case 9 : 
			this.spelerNaam = "HenSl";
			this.spelerAchternaam = "Slotboom";
			this.spelerVoornaam = "Henk";
			this.spelerTv = "";
			break;
		case 10 : 
			this.spelerNaam = "JanKW";
			this.spelerAchternaam = "Klein Wolterink";
			this.spelerVoornaam = "Jan";
			this.spelerTv = "";
			break;
		case 11 : 
			this.spelerNaam = "AlbeB";
			this.spelerAchternaam = "Bartels";
			this.spelerVoornaam = "Albert";
			this.spelerTv = "";
			break;
		case 12 : 
			this.spelerNaam = "MarkL";
			this.spelerAchternaam = "Langewouters";
			this.spelerVoornaam = "Mark";
			this.spelerTv = "";
			break;
		case 13 : 
			this.spelerNaam = "HenSp";
			this.spelerAchternaam = "Spekkink";
			this.spelerVoornaam = "Henk";
			this.spelerTv = "";
			break;
		case 14 : 
			this.spelerNaam = "HermPl";
			this.spelerAchternaam = "Pleiter";
			this.spelerVoornaam = "Herman";
			this.spelerTv = "";
			break;
		case 15 : 
			this.spelerNaam = "JanBe";
			this.spelerAchternaam = "Beltman";
			this.spelerVoornaam = "Jan";
			this.spelerTv = "";
			break;
		case 16 : 
			this.spelerNaam = "WimMe";
			this.spelerAchternaam = "Memelink";
			this.spelerVoornaam = "Wim";
			this.spelerTv = "";
			break;
		default:
			this.spelerNaam = "";
			this.spelerAchternaam = "";
			this.spelerVoornaam = "";
			this.spelerTv = "";
			break;
		}			
	}

	/**
	 * methode voor ophalen van spelerNummer van speler
	 * @return spelerNummer
	 */
	public int getSpelerNummer() {
		return spelerNummer;
	}

	public void setSpelerNummer(int nummer) {
		this.spelerNummer = nummer;
	}

	/**
	 * @return the spelerNaam
	 */
	public String getSpelerNaam() {
		return spelerNaam;
	}

	/**
	 * @param spelerNaam the spelerNaam to set
	 */
	public void setSpelerNaam(String spelerNaam) {
		this.spelerNaam = spelerNaam;
	}

	/**
	 * @return the spelerAchternaam
	 */
	public String getSpelerAchternaam() {
		return spelerAchternaam;
	}

	/**
	 * @param spelerAchternaam the spelerAchternaam to set
	 */
	public void setSpelerAchternaam(String spelerAchternaam) {
		this.spelerAchternaam = spelerAchternaam;
	}

	/**
	 * @return the spelerVoornaam
	 */
	public String getSpelerVoornaam() {
		return spelerVoornaam;
	}

	/**
	 * @param spelerVoornaam the spelerVoornaam to set
	 */
	public void setSpelerVoornaam(String spelerVoornaam) {
		this.spelerVoornaam = spelerVoornaam;
	}

	/**
	 * @return the spelerTv
	 */
	public String getSpelerTv() {
		return spelerTv;
	}

	/**
	 * @param spelerTv the spelerTv to set
	 */
	public void setSpelerTv(String spelerTv) {
		this.spelerTv = spelerTv;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
