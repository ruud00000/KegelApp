package nl.computerhuys.tabnavui;

import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class VraagBanenDialogFragment extends DialogFragment {
	
	private View v;
	private EditText editText1; 
	private EditText editText2; 
	//private ArrayList<Baan> banen;
	private int[] oldBanen;
	private int[] currentBanen;
	private SharedPreferences prefs;
	private int baan1;
	private int baan2;
	private int aantalParallel;

	/*	public VraagBanenDialogFragment(ArrayList<Baan> banen) {
		this.baanNummers = banen;
	}
*/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Restore preferences
        
        // Dit zou ook kunnen:
        // prefs = InitSpel.prefsBanen;  
        // en dan verderop InitSpel.addPreference(prefs, "BAAN_01", baan1); om te bewaren
        prefs = getActivity().getSharedPreferences("numberPicker.preferences", 0);
        baan1 = prefs.getInt( "BAAN_01", 0 );
        baan2 = prefs.getInt( "BAAN_02", 0 );
        //aantalParallel = InitSpel.prefs.getInt( "AANTAL_PARALLEL", 0 );
        aantalParallel = prefs.getInt( "AANTAL_PARALLEL", 0 );
        //oldBanen = new int[InitSpel.aantalParallel];
        oldBanen = new int[aantalParallel]; 
        oldBanen[0] = baan1;
        oldBanen[1] = baan2;
		
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    v = inflater.inflate(R.layout.vraag_banen, null);
	    // velden vullen met opgeslagen waarden
 	    editText1 = (EditText) v.findViewById(R.id.editText1); 
 	    editText2 = (EditText) v.findViewById(R.id.editText2); 
 	    editText1.setText(String.valueOf(baan1));
 	    editText2.setText(String.valueOf(baan2));
 	    //editText1.setText(String.valueOf(banen.get(0).getBaanNummer()));
 	    //editText2.setText(String.valueOf(banen.get(1).getBaanNummer()));
	    builder.setView(v)
		// Add action buttons
		.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int id) {
		    	   
		 	   baan1 = Integer.valueOf(editText1.getText().toString());
		 	   baan2 = Integer.valueOf(editText2.getText().toString());
		 	   InitSpel.setBaanNummer(0, baan1);
		 	   InitSpel.setBaanNummer(1, baan2);
		    	   
		 	   // en banen nog bij de preferences op schijf opslaan.
		 	   prefs.edit()
		 	     	.putInt("BAAN_01", baan1)
		 	     	.putInt("BAAN_02", baan2)
		 	     	.commit();
		    }
		})
		.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		 	   // TODO cancel;
		    }
		});      
		return builder.create();
	}	
 }
