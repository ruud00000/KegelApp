/**
 * 
 */
package nl.computerhuys.tabnavui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.computerhuys.tabnavui.SpeelSpel.MyGestureDetector;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.DialogFragment;
import android.view.GestureDetector;
import android.widget.TextView;

/**
 * @author CRMADMIN
 *
 */
public class InitSpel extends FragmentActivity {

    int clubNummer;
    int spelSoort;
    public static int aantalBeurten;
    public static int aantalWorpen;
    int startBaan;
    public static int aantalParallel;
    int maxBanen;
    int nextSpel;
    int baan1;
    int baan2;
    private static ArrayList<Baan> banen = new ArrayList();
    public static int baanNummers[] = new int [0];
    
	public static SharedPreferences prefs;
	// public static SharedPreferences prefsBanen;
    // TODO prefs hier naartoe verplaatsen want NumberPickerPreferenceActivity wordt na defaults dialog 
    // weer keurig opgeruimd dus is prefs in voor vraagBanen dialog niet meer beschikbaar (null)  
	
	/**
	 * Doet alles wat er gedaan moet worden om een spel te initialiseren 
	 * Heeft zelf geen scherm (view/layout)
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // (opm.: onderstaande declaratie en initialisatie in contructor zetten leidt tot
        // nullPointerException...)
        prefs = getSharedPreferences("numberPicker.preferences", MODE_WORLD_READABLE);
        
        clubNummer = prefs.getInt( "CLUB_NUMMER", 0 );
        spelSoort = prefs.getInt( "SPELSOORT", 0 );
        aantalBeurten = prefs.getInt( "AANTAL_BEURTEN", 0 );
        aantalWorpen = prefs.getInt( "AANTAL_WORPEN", 0 );
        startBaan = prefs.getInt( "STARTBAAN", 0 );
        aantalParallel = prefs.getInt( "AANTAL_PARALLEL", 0 );
        maxBanen = prefs.getInt( "MAX_BANEN", 0 );
        nextSpel = prefs.getInt( "NEXT_SPEL", 0 );
        // banen zouden ook in apart instellingenbestandje gestopt kunnen worden: 
        // prefsBanen = getSharedPreferences("banen.preferences", MODE_WORLD_READABLE);
        // baan1 = prefsBanen.getInt( "BAAN_01", 0 );
        // baan2 = prefsBanen.getInt( "BAAN_02", 0 );
        baan1 = prefs.getInt( "BAAN_01", 0 );
        baan2 = prefs.getInt( "BAAN_02", 0 );
        
        // even standaard instellingen erin zetten (zodat dat niets steeds opnieuw hoeft na herinstallatie)
        if (clubNummer == 0) {
        	addPreference(prefs, "CLUB_NUMMER", 1);
        	addPreference(prefs, "SPELSOORT", 1);
        	addPreference(prefs, "AANTAL_BEURTEN", 2);
        	addPreference(prefs, "AANTAL_WORPEN", 10);
        	addPreference(prefs, "STARTBAAN", 2);
        	addPreference(prefs, "AANTAL_PARALLEL", 2);
        	addPreference(prefs, "MAX_BANEN", 6);
        	addPreference(prefs, "NEXT_SPEL", 1);
        	addPreference(prefs, "BAAN_01", 2);
        	addPreference(prefs, "BAAN_02", 1);
        }
        
        // Baan objecten creëren met baanNummer en toevoegen aan arrayList banen
        addBaanNummer(baan1);
        addBaanNummer(baan2);

        // Toon popup scherm met default waarden, met knop voor wijzigen of ok
		// 1. tekst voor in popup-scherm incl. defaultwaardes opbouwen
        String myMessage;
        myMessage =  "Club nummer: " + String.valueOf(clubNummer);
        myMessage += "\r\nSpelsoort: " + String.valueOf(spelSoort);
        myMessage += "\r\nAantal beurten: " + String.valueOf(aantalBeurten);
        myMessage += "\r\nAantal worpen: " + String.valueOf(aantalWorpen);
        myMessage += "\r\nStart baan: " + String.valueOf(startBaan);
        myMessage += "\r\nAantal banen voor spel: " + String.valueOf(aantalParallel); 
        myMessage += "\r\nAantal beschikbare banen: " + String.valueOf(maxBanen); 
        myMessage += "\r\nVolgende spel: " + String.valueOf(nextSpel);
        
        // 2. scherm 'defaults' maken o.b.v. klasse Defaults(DialogFragment) in lib 
		DefaultsDialogFragment defaults = new DefaultsDialogFragment(myMessage, banen);
		//    en tonen
        defaults.show(getSupportFragmentManager(), "defaults");	
        
        
        // 3. scherm 'vraagBanen' maken: wordt gedaan vanuit scherm 'defaults', knop 'Banen' 
        
        
    }
    
    
    /**
     * Baan objecten creëren met baanNummer en toevoegen aan arrayList banen en 
     * nummer zelf toevoegen aan array baanNummers
     * @param nummer
     */
    public static void addBaanNummer(int nummer) {
    	Baan baan = new Baan(nummer); 
    	banen.add(baan);
    	int size = baanNummers.length;
    	// Arrays.copyOf() beschikbaar vanaf Android API 9... 
    	baanNummers = (int[]) MyUtils.resizeArray(baanNummers, size+1);
    	baanNummers[size] = nummer;
    }

    /** 
     * Wijzigt waarde van eigenschap nummer van instantie van klasse Baan op positie index in array banen.
     * 
     * Roept daarbij methode setBaanNummer van Baan aan. 
     * @param index
     * @param nummer
     */
    public static void setBaanNummer(int index, int nummer) {
    	Baan baan = banen.get(index);
    	baan.setBaanNummer(nummer);
    }
    
    /**
     * Methode voor ophalen van waarde van eigenschap nummer van Baan op positie index in array banen
     * @param index
     * @return
     */
    public static int getBaanNummer(int index) {
    	return banen.get(index).getBaanNummer();
    }
    
    /**
     * Methode voor toevoegen of wijzigen van een instelling m.b.v. naam (key) en waarde
     * @param preferenceFile
     * @param key
     * @param value
     * @return
     */
	public static boolean addPreference(SharedPreferences preferenceFile, String key, int value) {
		Editor e = preferenceFile.edit();
		Editor pi = e.putInt(key, value);
		boolean c = pi.commit();
		return c;
		//ofwel kortweg: return prefs.edit().putInt(key, value).commit();
	}
}
