/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
	
package nl.computerhuys.tabnavui;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import nl.computerhuys.tabnavui.R;	
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
	
	/**
 * In deze klasse wordt tabblad Spel getoond, met menubalk-items onderin het scherm 
 * (oproepen met hardware menu-knop). Eén van de menuitems is voor het instellen van
 * aantal banen waarop gegooid wordt en opgeven van baannummers. 
 */
public class SpeelSpel extends FragmentActivity implements NumberPickerDialog.OnNumberSetListener, VraagMeerSpelersDialogFragment.NoticeDialogListener {
    private static final String TAG = Spel.class.getSimpleName();
	private static final int SPELER_BEURTNUMMER = 1;
	private static final int SPELER_BEURTENWORPENSCORE = 2;
	private static final int SPELER_BEURTENTOTAAL = 3;
	private static final int SPELER_TOTAAL = 4;
	private static final int SPELER_BANEN = 5;
	private GestureDetector gestureDetector;
	private TextView textView1; 
	private TextView textView2; 
	private TextView textView3; 
	private TextView textView4; 
	private TextView textView5; 
	private TextView textView6; 
	private TextView textView7; 
	private int spelNummer=1;
	private boolean meerSpelers = true;
	private File spelFile;
	private String[] spelers;
	private ArrayList<Speler> spelerNummers;
    private static ArrayList<Spel> spellen;
    public int spelNummers[];
	public SharedPreferences prefs;
    int nextSpel;
	private EditText editText1;
    private EditText editText2;
    private VraagSpelersDialogFragment vraagSpelers;
	private Spel spel1;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Speler objecten creëren met spelerNummer en toevoegen aan arrayList spelerNummers
        spelNummers = new int [0];
        spelerNummers = new ArrayList(20);
        addSpelerNummer(1);
        addSpelerNummer(2);
        addSpelerNummer(3);
        addSpelerNummer(4);
        addSpelerNummer(5);
        addSpelerNummer(6);
        addSpelerNummer(7);
        addSpelerNummer(8);
        addSpelerNummer(9);
        addSpelerNummer(10);
        addSpelerNummer(11);
        addSpelerNummer(12);
        addSpelerNummer(13);
        addSpelerNummer(14);
        addSpelerNummer(15);
        addSpelerNummer(16);

        // Spel object creëren met spelNummer en toevoegen aan arrayList spellen
        prefs = getSharedPreferences("numberPicker.preferences", MODE_WORLD_READABLE);
        spellen = new ArrayList();
        nextSpel = prefs.getInt( "NEXT_SPEL", 0 );
        addSpelNummer(nextSpel);
        spel1 = spellen.get(0);

        // VraagSpelersDialogFragment aanroepen, van daaruit verder naar/met spel spellen[0]
        spelers = new String[17];  
		spelers[0] = "";    // een lege bovenaan zetten bij wijze van default, 
        					// rest vullen vanuit 'SpelerNummers' in SpeelSpel 
        for (int i = 1;i<spelerNummers.size()+1;i++) {
	        spelers[i] = spelerNummers.get(i-1).getSpelerVoornaam();
	        spelers[i] += " "+ spelerNummers.get(i-1).getSpelerAchternaam();
        }
    	
        // scherm 'vraagSpelers' maken o.b.v. klasse VraagSpelers(DialogFragment) in lib 
        String myMessage =  "Selecteer spelers (beurt "+spel1.getBeurtNummer()+")";
        
		vraagSpelers = new VraagSpelersDialogFragment(myMessage, spelers);
		//    en tonen
        vraagSpelers.show(getSupportFragmentManager(), "vraagSpelers");	
    
        setContentView(R.layout.spel);
        
    	textView1 = (TextView) this.findViewById(R.id.textView1);
    	textView2 = (TextView) this.findViewById(R.id.textView2);
    	textView3 = (TextView) this.findViewById(R.id.textView3);
    	textView4 = (TextView) this.findViewById(R.id.textView4);
    	textView5 = (TextView) this.findViewById(R.id.textView5);
    	textView6 = (TextView) this.findViewById(R.id.textView6);
    	textView7 = (TextView) this.findViewById(R.id.textView7);

    	spel1.setBaanNummer(InitSpel.baanNummers[0]);
    	textView1.setText(String.valueOf(spel1.getBaanNummer())+".");
    	spel1.setBaanNummer(InitSpel.baanNummers[1]);
    	textView2.setText(String.valueOf(spel1.getBaanNummer())+".");

    	spel1.setWorpNummer(1);
    	textView3.setText("Worp "+String.valueOf(spel1.getWorpNummer())+" van "+String.valueOf(InitSpel.aantalWorpen));
    	
    	// textView 6 en 7 worden later gevuld vanuit VraagSpelersDialogFragment
        // textView6.setText(spelers[spel1.getBanenSpelerNummer()[0]]); 
        // textView7.setText(spelers[spel1.getBanenSpelerNummer()[1]]);
        
	    editText1 = (EditText) findViewById(R.id.editText1);
		editText1.addTextChangedListener(new TextWatcher() {
	
			@Override
			public void afterTextChanged(Editable s) {
			}
	
			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
			}
	
			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				if (before==0) {  // alleen doen als editText1 leeg was
					String v = s.toString();
					if (v.equals("0") || v.equals("1") || v.equals("2") || v.equals("3") || v.equals("4") || v.equals("5") || v.equals("6") || v.equals("7") || v.equals("8") || v.equals("9")) {
		                editText2.requestFocus();
		                int baanWorpScore = Integer.parseInt(v);
		        		spel1.setParallelNummer(1);
		        		spel1.getBanenWorpScore()[spel1.getParallelNummer()-1] = baanWorpScore;
						spel1.getBanenWorpenTotaal()[spel1.getParallelNummer()-1] += baanWorpScore;
				    	//banenWorpenScore[worpNummer-1][parallelNummer-1] = baanWorpScore;
				    	textView4.setText(String.valueOf(spel1.getBanenWorpenTotaal()[spel1.getParallelNummer()-1]));
		        		
					} 
/*						else {
							// blijf wachten op goede invoer
							editText1.setText(null);
						}						
*/					}
	
			}
		});
		
        editText2 = (EditText) findViewById(R.id.editText2);
		editText2.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
			}
	
			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
			}
	
			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				
				Runnable mFilterTask = new Runnable() {
		
				     @Override
				     public void run() {
					        editText1.setText(null);
					        editText2.setText(null);
							editText1.requestFocus();
							textView3.setText("Worp "+String.valueOf(spel1.getWorpNummer())+" van "+String.valueOf(InitSpel.aantalWorpen));
				     }       
				};
		
				Handler mHandler = new Handler();
				
				if (before==0) {
					String v = s.toString();
					if (v.equals("0") || v.equals("1") || v.equals("2") || v.equals("3") || v.equals("4") || v.equals("5") || v.equals("6") || v.equals("7") || v.equals("8") || v.equals("9")) {
						mHandler.removeCallbacks(mFilterTask); 
						mHandler.postDelayed(mFilterTask, 1000);
						
						int baanWorpScore = Integer.parseInt(v);
		        		spel1.setParallelNummer(2);
						spel1.getBanenWorpScore()[spel1.getParallelNummer()-1] = baanWorpScore;
						spel1.getBanenWorpenTotaal()[spel1.getParallelNummer()-1] += baanWorpScore;
				    	spel1.getBanenWorpenScore()[spel1.getWorpNummer()-1] = spel1.getBanenWorpScore().clone();
						spel1.setWorpNummer(spel1.getWorpNummer() + 1);
				    	textView5.setText(String.valueOf(spel1.getBanenWorpenTotaal()[spel1.getParallelNummer()-1]));
				    	if (spel1.getWorpNummer()>InitSpel.aantalWorpen) {
				    		// de drie banen-arrayLists uitbreiden met een 'volgNummer' en het spul daarin zetten
				    		spel1.getBanenWorpenTotalen().add(spel1.getVolgNummer()-1, spel1.getBanenWorpenTotaal().clone());
				    		spel1.getSpelBanenWorpenScore().add(spel1.getVolgNummer()-1, spel1.getBanenWorpenScore().clone());
				    		spel1.getBanenSpelerNummers().add(spel1.getVolgNummer()-1, spel1.getBanenSpelerNummer().clone());
				    		spel1.setWorpNummer(1);
				    		
				    		// subproces 3.1 Scores en totalen bij speler noteren
				    		spel1.setParallelNummer(1);
				    		while (spel1.getParallelNummer()<=InitSpel.aantalParallel) {
					    		int spelerIndex = spel1.getSpelersNummer().indexOf(spel1.getBanenSpelerNummer()[spel1.getParallelNummer()-1]);
				    			// attn: copy back into spelersSpel not necessary since spelerSpel is now copied by reference: 
				    			spel1.setSpelerSpel(spel1.getSpelersSpel().get(spelerIndex)); 
				    			spel1.getSpelerSpel()[SPELER_BEURTNUMMER][0][0] = spel1.getBeurtNummer();
				    			spel1.setSpelerBeurtNummer(spel1.getBeurtNummer());
				    			spel1.getSpelersBeurtNummer().set(spelerIndex,spel1.getSpelerBeurtNummer());
				    			
				    			// subproces 3.1.1. worpscores bij speler noteren 
				    			spel1.setSpelerBeurtenWorpenScore(spel1.getSpelerSpel()[SPELER_BEURTENWORPENSCORE]); // attn: copy by reference
				    			for (int worpNummer=1; worpNummer <= InitSpel.aantalWorpen; worpNummer++) {
				    				spel1.setBanenWorpScore(spel1.getBanenWorpenScore()[worpNummer-1]); // attn: copy by reference
				    				baanWorpScore = spel1.getBanenWorpScore()[spel1.getParallelNummer()-1]; // attn: copy by reference
				    				spel1.setSpelerBeurtWorpScore(baanWorpScore);
				    				spel1.getSpelerBeurtWorpenScore()[worpNummer-1] = spel1.getSpelerBeurtWorpScore();
				    			}
				    			
				    			spel1.getSpelerBeurtenWorpenScore()[spel1.getBeurtNummer()-1] = spel1.getSpelerBeurtWorpenScore().clone();
				    			// no need to copy back now:
				    			// spelerSpel[SPELER_BEURTENWORPENSCORE] = spelerBeurtenWorpenScore.clone();

				    			spel1.setSpelerBeurtenTotaal(spel1.getSpelerSpel()[SPELER_BEURTENTOTAAL][0]); // attn: copy by reference
				    			spel1.setBaanWorpenTotaal(spel1.getBanenWorpenTotaal()[spel1.getParallelNummer()-1]); // attn: copy by reference
				    			spel1.setSpelerBeurtTotaal(spel1.getBaanWorpenTotaal());
				    			spel1.getSpelerBeurtenTotaal()[spel1.getBeurtNummer()-1] = spel1.getSpelerBeurtTotaal();
				    			// no need to copy back now:					    			
				    			// spelerSpel[SPELER_BEURTENTOTAAL][0] = spelerBeurtenTotaal;
				    			
				    			// deelproces 3.1.2. Totaal bij speler noteren
				    			if (spel1.getBeurtNummer() == InitSpel.aantalBeurten) {
				    				spel1.setSpelerTotaal(0);
				    				for (int i=0;i<InitSpel.aantalBeurten;i++) {
				    					spel1.setSpelerTotaal(spel1
												.getSpelerTotaal()
												+ spel1.getSpelerBeurtenTotaal()[i]);
				    				}
				    				spel1.getSpelerSpel()[SPELER_TOTAAL][0][0] = spel1.getSpelerTotaal();
				    				spel1.setSpelerSpelTotaal(spel1.getSpelerTotaal());
				    				spel1.getSpelersSpelTotaal().set(spelerIndex, spel1.getSpelerSpelTotaal()); 
				    			}
				    			
				    			spel1.setSpelerBanen(spel1.getSpelerSpel()[SPELER_BANEN][0]); // attn: copy by reference
				    			spel1.setBaanNummer(InitSpel.baanNummers[spel1.getParallelNummer()-1]);
				    			spel1.setSpelerBaan(spel1.getBaanNummer());
				    			spel1.getSpelerBanen()[spel1.getBeurtNummer()-1] = spel1.getSpelerBaan();
				    			// no need to copy back now:					    			
				    			// spelerSpel[SPELER_BANEN][0] = spelerBanen;
				    			
				    			// hele bijgwerkte spelerarray op juiste plek in spelersarray zetten:
				    			// (toevoegen speler aan spelersarray is al gedaan in 2.1)  
				    			// echter: no need to copy back now:					    			
				    			// int[][][] x = spelersSpel.get(spelerIndex);
				    			// x = spelerSpel.clone(); 
				    			
				    			// TODO scores van spelerskoppel opslaan op sdcard
				    	    	makeFile(spelNummer);
				    	    	try {
				    				printArray();
				    			} catch (FileNotFoundException e) {
				    				// TODO Auto-generated catch block
				    				e.printStackTrace();
				    			}
				    			
				    			// en door naar de volgende speler (andere baan, zelfde beurt, alle worpen)
				    			spel1.setParallelNummer(spel1.getParallelNummer() + 1);
				    		}
				    		
				    		// nu is 3.1 (scores en totalen bij speler noteren) beëindigd 
				    		// en heel subproces 3. (vraag om scores) ook
				    		
				    		// nu door naar volgende 'volgnummer' (nieuwe spelers)
				    		spel1.setVolgNummer(spel1.getVolgNummer() + 1);
				    		
				    		// spelers vragen
				            String myMessage;
				            myMessage =  "Zijn er nog spelers die beurt "+ spel1.getBeurtNummer() +" nog niet gegooid hebben?";
				            // scherm 'vraagMeerSpelers' maken o.b.v. klasse Defaults(DialogFragment) in lib 
				    		VraagMeerSpelersDialogFragment vraagMeerSpelers = new VraagMeerSpelersDialogFragment(myMessage);
				    		//    en tonen
				    		vraagMeerSpelers.show(getSupportFragmentManager(), "vraagMeerSpelers");	
				    		// hierna volgen nog meer acties: 
				    		// - vragen om meer spelers in deze beurt (in VraagMeerSpelersDialogFragment)
				    		// - beurtnummer ophogen (in onVMSDialogNegativeClick)
				    		// - totalen en views resetten (in onVMSDialogPositiveClick en onVMSDialogNegativeClick)
				    	}
					} 
				}
			}
		});

       
        gestureDetector = new GestureDetector(new MyGestureDetector());
    }

    
    /**
     * Sla de hele array spelersSpel op in bestand spelFile
     * @throws FileNotFoundException
     */
    public void printArray() throws FileNotFoundException  {
    	PrintWriter pw = new PrintWriter(spelFile);
    	String printLine;
    	for (int i=0; i<spel1.getSpelersSpel().size();i++) {
	    	printLine = Arrays.deepToString(spel1.getSpelersSpel().get(i));
	    	pw.println(printLine);
    	}
    	if (pw.checkError()) {
    		//TODO fout afhandelen
    	}
    	pw.close();
    	
    }
    
    public ArrayList<int[][][]> getArray() {
    	// 
    	
    	return spel1.getSpelersSpel();
    }
    /**
     * Maakt bestand in datamap van kegelApp met naam bestaande uit parameter spelNummer
     * gevolgd door datum vandaag in vorm YYYYMMDD
     * @return
     */
    public boolean makeFile(int spelNummer) {
    	if (!isExternalStorageWritable()) return false;  
    	File kegelAppDataDirectory = getStorageDir("KegelAppData");
    	
    	// Bestandsnaam maken
    	// bestandsnaam bestaat uit spelNummer (links uitgevuld met voorloopnullen
    	// gevolgd door datum in format: YYYYMMDD
    	Calendar rightNow = Calendar.getInstance();
    	String date;
    	int length;
    	String year = Integer.toString(rightNow.get(Calendar.YEAR));
    	String month = Integer.toString(rightNow.get(Calendar.MONTH)+1);
    	length = month.length();
    	month = ("0"+month).substring(1+length-2);
    	String day = Integer.toString(rightNow.get(Calendar.DAY_OF_MONTH));
    	length = day.length();
    	day = ("0"+day).substring(1+length-2);
    	date = year+month+day;

    	String fileName = Integer.toString(spelNummer);
    	length = fileName.length();
    	fileName = ("0000"+fileName).substring(4+length-5); // geef laatste 5 cijfers

    	fileName += date; 
    	
    	// ken bestandsnaam toe aan spelFile en maak bestand zonodig aan
    	this.spelFile = new File(kegelAppDataDirectory, fileName);  
    	
    	return true;
    }
    
    /**
     *  Checks if external storage is available for read and write 
     */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        Toast t = Toast.makeText(this, "State: " + state, Toast.LENGTH_SHORT);
        t.show();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Get or of necessary create the datadirectory in the root of public external storage.
     * @param dirName
     * @return
     */
    public File getStorageDir(String dirName) {
        // Get the directory for the user's public kegelApp directory. 
    	File d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(d, dirName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sample_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_dialog_item) {
            NumberPickerDialog dialog = new NumberPickerDialog(this, -1, 2);
            dialog.setTitle(getString(R.string.dialog_picker_title));
            dialog.setOnNumberSetListener(this);
            dialog.show();

            return true;
        } else if (item.getItemId() == R.id.menu_preferences_item) {
            startActivity(new Intent(this, NumberPickerPreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNumberSet(int number) {
        Log.d(TAG, "Number selected: " + number);
        Toast t = Toast.makeText(this, "Number selected: " + number, Toast.LENGTH_SHORT);
        t.show();
    }	    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    
    class MyGestureDetector extends SimpleOnGestureListener {
		  private static final int SWIPE_MAX_OFF_PATH = 200;
		  private static final int SWIPE_MIN_DISTANCE = 50;
		  private static final int SWIPE_THRESHOLD_VELOCITY = 200;
          
        @Override
        public boolean onDown (MotionEvent e) {
        	return true;
        }
          
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
	    		return false;
	    	   	// left to right swipe and right to left swipe
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
	    	     && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    	    // left swipe
	    	        Toast t = Toast.makeText(SpeelSpel.this, "Left swipe", Toast.LENGTH_LONG);
	    	        t.show();
	    	        // startActivity(TabsMenu.tab3);
	    	        startActivityForResult(TabsMenu.tab3, 500);
	    	        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		    	    return true;
			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
	    	     && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    	    // right swipe
	    	        Toast t = Toast.makeText(SpeelSpel.this, "Right swipe", Toast.LENGTH_LONG);
	    	        t.show();
	    	        // startActivity(TabsMenu.tab1);
	    	        startActivityForResult(TabsMenu.tab1, 500);
	    	        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		    	    return true;
			}
	    	return false;
		}    
    }

	/**
	 * @param meerSpelers the meerSpelers to set
	 */
	public void setMeerSpelers(boolean meerSpelers_) {
		meerSpelers = meerSpelers_;
	}

	/**
	 * methode voor ophalen van spelNummer van spel
	 * @return spelNummer
	 */
	public int getSpelNummer() {
		return spelNummer;
	}

	/**
	 * @return spel uit spellen o.b.v. index
	 */
	public Spel getSpel(int index) {
		return spellen.get(index);
	}	
	   
	/**
	* Methode voor ophalen van waarde van eigenschap nummer van Spel op positie index in array spellen
	* @param index
	* @return
	*/
	public int getSpelNummer(int index) {
		return spellen.get(index).getSpelNummer();
	}

	public void setSpelNummer(int nummer) {
		this.spelNummer = nummer;
	}

	/** 
	* Wijzigt waarde van eigenschap nummer van instantie van klasse Spel op positie index in array spellen.
	* 
	* Roept daarbij methode setSpelNummer van Spel aan. 
	* @param index
	* @param nummer
	*/
	public void setSpelNummer(int index, int nummer) {
	   	Spel spel = spellen.get(index);
	   	spel.setSpelNummer(nummer);
	}

    /**
    * Spel objecten creëren met spelNummer en toevoegen aan arrayList spellen en 
    * nummer zelf toevoegen aan array spelNummers
    * @param nummer
    */
   public void addSpelNummer(int nummer) {
   	Spel spel = new Spel(nummer); 
   	spellen.add(spel);
   	int size = this.spelNummers.length;
   	// Arrays.copyOf() beschikbaar vanaf Android API 9... 
   	spelNummers = (int[]) MyUtils.resizeArray(spelNummers, size+1);
   	spelNummers[size] = nummer;
   }
	
   /**
   * Speler objecten creëren met spelerNummer en toevoegen aan arrayList spelerNummers
   * @param nummer
   */
  public int addSpelerNummer(int nummer) {
  	Speler speler = new Speler(nummer); 
  	spelerNummers.add(speler);
  	return spelerNummers.indexOf(speler);
  }


	/**
	 * @return the spellen
	 */
	public static ArrayList<Spel> getSpellen() {
		return spellen;
	}
	
	/**
	 * @return the textView6
	 */
	public TextView getTextView6() {
		return textView6;
	}
	
	
	/**
	 * @param textView6 the textView6 to set
	 */
	public void setTextView6(TextView textView6) {
		this.textView6 = textView6;
	}


	/**
	 * @return the textView7
	 */
	public TextView getTextView7() {
		return textView7;
	}


	/**
	 * @param textView7 the textView7 to set
	 */
	public void setTextView7(TextView textView7) {
		this.textView7 = textView7;
	}

	@Override
	public void onVMSDialogPositiveClick(DialogFragment dialog) {
		if (spel1.getBeurtNummer()<=InitSpel.aantalBeurten) {
	        vraagSpelers.show(getSupportFragmentManager(), "vraagSpelers");
	        // reset totalen, tellers en textViews
	        textView6.setText(null); 
	        textView7.setText(null);
			spel1.resetBanenWorpenTotaal();
	    	textView4.setText("0");
	    	textView5.setText("0");
		}
	}

	@Override
	public void onVMSDialogNegativeClick(DialogFragment dialog) {
		if (spel1.getBeurtNummer()<=InitSpel.aantalBeurten) {
			// op naar de volgende beurt
			spel1.setBeurtNummer(spel1.getBeurtNummer() + 1);
			if (spel1.getBeurtNummer()>InitSpel.aantalBeurten) {
				// TODO dialog gameover
			} 
			else {
				vraagSpelers.show(getSupportFragmentManager(), "vraagSpelers");
			}
	        // reset totalen, tellers en textViews
	        textView6.setText(null); 
	        textView7.setText(null);
			spel1.resetBanenWorpenTotaal();
	    	textView4.setText("0");
	    	textView5.setText("0");
		}
	}
}

