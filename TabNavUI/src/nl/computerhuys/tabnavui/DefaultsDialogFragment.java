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

public class DefaultsDialogFragment extends DialogFragment {
	private String myMessage;
	private ArrayList<Baan> myBaanNummers;
	
	public DefaultsDialogFragment(String message, ArrayList<Baan> baanNummers) {
		myMessage = message;
		myBaanNummers = baanNummers;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
    	// FragmentActivity ac = getActivity(); // ->InitSpel
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        OnClickListener p;
		builder.setMessage(myMessage)
               .setPositiveButton(R.string.dialog_setup, p = new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Ga naar instellingen
                	   startActivity(new Intent(getActivity(), NumberPickerPreferenceActivity.class));
                   }
               })
               .setNeutralButton(R.string.dialog_banen, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Ga naar popup voor opgeven van baannummers
                   	   VraagBanenDialogFragment vraagBanen = new VraagBanenDialogFragment();
                       vraagBanen.show(getActivity().getSupportFragmentManager(), "banen_vragen");	
                	   //startActivity(new Intent(getActivity(), VraagBanenDialogFragment.class));
                   }
               })
               .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        AlertDialog x= builder.create();
		// Create the AlertDialog object and return it
        return x ;
    }
    
}
