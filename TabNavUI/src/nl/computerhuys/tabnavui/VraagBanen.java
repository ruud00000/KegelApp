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
	
	import nl.computerhuys.tabnavui.R;	
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
	
	/**
	 * In deze klasse wordt tabblad Spel getoond, met menubalk-items onderin het scherm 
	 * (oproepen met hardware menu-knop). Eén van de menuitems is voor het instellen van
	 * aantal banen waarop gegooid wordt en opgeven van baannummers. 
	 */
	public class VraagBanen extends FragmentActivity implements NumberPickerDialog.OnNumberSetListener {
	    private static final String TAG = VraagBanen.class.getSimpleName();
		private TextView textView1; 
	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        // Use a custom layout file
	        setContentView(R.layout.vraag_banen);
	        
	    	textView1 = (TextView) this.findViewById(R.id.textView1);
	    }

	    public void onClickOK(View v) {
	    	EditText editText1 = (EditText) this.findViewById(R.id.editText1); 
	    	EditText editText2 = (EditText) this.findViewById(R.id.editText2);
	    	int baan1 = Integer.valueOf(editText1.getText().toString());
	    	int baan2 = Integer.valueOf(editText2.getText().toString());
	    	InitSpel.addBaanNummer(baan1);
	    	InitSpel.addBaanNummer(baan2);
	    }

		@Override
		public void onNumberSet(int selectedNumber) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
