package nl.computerhuys.tabnavui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class VraagSpelersDialogFragment extends DialogFragment implements OnItemSelectedListener {
	private String myMessage;
	private String[] mySpelers;
	private View v;
	private Spinner s1;
	private Spinner s2;
	
	public VraagSpelersDialogFragment(String message, String[] spelers) {
		myMessage = message;
		mySpelers = spelers;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    v = inflater.inflate(R.layout.vraag_spelers, null);
	    
    	//TextView textView2 = (TextView) v.findViewById(R.id.textView2);
    	TextView textView3 = (TextView) v.findViewById(R.id.textView3);
    	TextView textView4 = (TextView) v.findViewById(R.id.textView4);
 
    	textView3.setText(String.valueOf(InitSpel.baanNummers[0])+".");
    	textView4.setText(String.valueOf(InitSpel.baanNummers[1])+".");
        		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mySpelers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1 = (Spinner) v.findViewById(R.id.spinner1);
        s1.setAdapter(adapter);
        s2 = (Spinner) v.findViewById(R.id.spinner2);
        s2.setAdapter(adapter);

        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        myMessage =  "Selecteer spelers (beurt "+SpeelSpel.getSpellen().get(0).getBeurtNummer()+")";

	    
        
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        OnClickListener p;
		builder.setView(v)
			   .setMessage(myMessage)
               .setPositiveButton(R.string.dialog_ok, p = new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       // Ga naar instellingen
                	   // startActivity(new Intent(getActivity(), NumberPickerPreferenceActivity.class));
                	   // TODO verder naar spel
                	   // getActivity().setIntent(null);
                	   // startActivity(new Intent(getActivity(), Spel.class));
                   }
               });
/*               .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       // User cancelled the dialog
                   }
               });
 */       
		// Create the AlertDialog object and return it
        return builder.create();
    }

	@Override
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
    	// this: Spel, parent: Spinner, view: TextView
    	
    	// Flowchart proces 2. Vraag om spelers:
    	if (pos!=0) {
	    	int parallelNummer = 0;
			if (parent.equals(s1)) {
		    	parallelNummer =  1;
		        ((SpeelSpel) getActivity()).getTextView6().setText(mySpelers[pos]); 
	    	}
	    	if (parent.equals(s2)) {
		    	parallelNummer =  2;
		        ((SpeelSpel) getActivity()).getTextView7().setText(mySpelers[pos]);
	    	}
	    	
	    	SpeelSpel.getSpellen().get(0).setBaanSpelerNummer(pos); // wordt gebruikt in addSpelerToSpel()
	    	SpeelSpel.getSpellen().get(0).getBanenSpelerNummer()[parallelNummer-1] = pos;
	    	SpeelSpel.getSpellen().get(0).addSpelerToSpel();
    	}
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	/**
	 * @return the s1
	 */
	public Spinner getS1() {
		return s1;
	}

	/**
	 * @return the s2
	 */
	public Spinner getS2() {
		return s2;
	}

	
 
}
