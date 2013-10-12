/*
 * Copyright (C) 2008 The Android Open Source Project
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

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.Application;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * An example of tab content that launches an activity via {@link android.widget.TabHost.TabSpec#setContent(android.content.Intent)}
 */
@SuppressWarnings("deprecation")
public class TabsMenu extends TabActivity {

    private GestureDetector gestureDetector;
    public static Intent tab1, tab2, tab3, tab4, tab5; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TabHost tabHost = getTabHost();
        
        // debuginfo
/*        @SuppressWarnings("unused")
        Application app = this.getApplication();
        Context ac = this.getApplicationContext();
        Context bc = this.getBaseContext();
        ComponentName ca = this.getCallingActivity();
*/        
        tab1 = new Intent(this, InitSpel.class);
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("Init")
                .setContent(tab1
                		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        tab2 = new Intent(this, SpeelSpel.class);
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("Speel")
                .setContent(tab2));
        
        tab3 = new Intent(this, List8.class);
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("List8n")
                .setContent(tab3));
        
        tab4 = new Intent(this, List1.class);
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("List1")
                .setContent(tab4));

		// This tab sets the intent flag so that it is recreated each time
        // the tab is clicked.
        tab5 = new Intent(this, Controls2.class);
        tabHost.addTab(tabHost.newTabSpec("tab5")
                .setIndicator("Spel")
                .setContent(tab5
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
    }
}
