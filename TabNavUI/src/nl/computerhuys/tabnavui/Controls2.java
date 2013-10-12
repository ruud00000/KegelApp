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

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;


/**
 * A gallery of basic controls: Button, EditText, RadioButton, Checkbox,
 * Spinner. This example uses the default theme.
 */
public class Controls2 extends Activity {

	private GestureDetector gestureDetector;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_1);
        
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
    }

    private static final String[] mStrings = {
	    "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"
    };
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
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
		    		// do nothing   
		    	    return true;
	    	   } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
	    	     && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    	    // right swipe
	    	        Toast t = Toast.makeText(Controls2.this, "Right swipe", Toast.LENGTH_LONG);
	    	        t.show();
	    	        startActivity(TabsMenu.tab2);
		    	    return true;
	    	   }
	    	   return false;
  	  }    
  }

}
