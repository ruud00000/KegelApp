package nl.computerhuys.tabnavui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class VraagMeerSpelersDialogFragment extends DialogFragment {
	private String myMessage;
    // Use this instance of the interface to deliver action events
    private NoticeDialogListener mListener;
	
	public VraagMeerSpelersDialogFragment(String message) {
		myMessage = message;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
    	// FragmentActivity ac = getActivity(); // ->InitSpel
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        OnClickListener p;
		builder.setMessage(myMessage)
               .setPositiveButton(R.string.dialog_yes, p = new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                 	   // ((SpeelSpel) getActivity()).setMeerSpelers(true);
                	   mListener.onVMSDialogPositiveClick(VraagMeerSpelersDialogFragment.this);}
               })
               .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   // ((SpeelSpel) getActivity()).setMeerSpelers(false);
                	   mListener.onVMSDialogNegativeClick(VraagMeerSpelersDialogFragment.this);}
               });
		// Create the AlertDialog object and return it
        return builder.create();
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onVMSDialogPositiveClick(DialogFragment dialog);
        public void onVMSDialogNegativeClick(DialogFragment dialog);
    }
    
    
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }    
}
