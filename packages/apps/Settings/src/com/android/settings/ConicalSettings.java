/*
* Copyright (C) 2010 Conical Roms 
*
* All rights reserved.
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*  * Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer.
*  * Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in
*    the documentation and/or other materials provided with the
*    distribution.
*
*
*    You should have received a copy of the GNU General Public License
*/


package com.android.settings;


import android.app.ActivityManager;
import android.content.Context;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.widget.Toast;

//import com.android.settings.R;




public class ConicalSettings extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	public Preference mAlpha;
    private static final String TAG = "ConicalSettings";
	private boolean DEBUG = true;

	// preference keys
	  private static final String UISCREENPREF = "Screen_prefs";
	  private static final String UIELEMPREF = "UI_Elements";
	  private static final String ROOTPREF = "iconics_main";
          private static final String UILISTPREF = "num_screens";

	    
	    // Preference string names
	    private static final String THREE = "Three";
	    private static final String FIVE = "Five";
	    private static final String SEVEN = "Seven";
	    public static final String SCREENSETTINGS = "NUM_SCREENS";

	    
	    // Activity Names 
	    private static final String LAUNCHER = "com.android.launcher";

	// Preference types for conical_settings_main.xml - Please maintain the hierchy
	    private PreferenceScreen   mPreferenceRoot;
	    private PreferenceScreen   mUIScreenPreference;
	    private PreferenceScreen   mUIElemPreference;
	    private ListPreference     mUIScreenListPreference;

    private SharedPreferences  mPreferences;
    
    private ActivityManager activityManager;
    
    
    
    


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      
     addPreferencesFromResource(R.xml.conical_settings_main);
     
     setupCustomUIprefs();
     

	activityManager = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
     

      
     
    }
    
    
  
      
    
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
   
    	//toastmsg("Preference tree clicked");

    	
    		if(preferenceScreen ==  mUIScreenPreference)
    		{

        		//mUIScreenListPreference.getEntry().toString();
    		
    		}
    		

        return true;
        
    }
    
   

    public void setupCustomUIprefs(){
    	
    mPreferenceRoot = (PreferenceScreen) getPreferenceScreen().findPreference(ROOTPREF);
    mUIScreenPreference = (PreferenceScreen) getPreferenceScreen().findPreference(UIELEMPREF);
    mUIScreenListPreference = (ListPreference) getPreferenceScreen().findPreference(UILISTPREF);   	
   
   // mUIScreenListPreference.

	mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	
	
	

      
    	
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    
    
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    	//handle event for the Screen list preference
    	if(key == mUIScreenListPreference.getKey())
    	{
    		Log.v(TAG, "on shared preference change in conical settings");
    		registerScreenChange(mUIScreenListPreference.getEntry().toString());

    		restartLauncher2(activityManager);
    		
    		
    	}
    	

    }

    void registerScreenChange(String st){
          
    	if(st == SEVEN)
    	  Settings.System.putInt(getContentResolver(), SCREENSETTINGS, 0);
    	else if (st == FIVE)
      	  Settings.System.putInt(getContentResolver(), SCREENSETTINGS, 1);
    	else 
      	  Settings.System.putInt(getContentResolver(), SCREENSETTINGS, 2);
    	
    }
 
    
    
    void toastMsg(String msg){
    	Context context = getApplicationContext();
     	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, msg, duration);
    	toast.show();
    }
    
    boolean toBool(int i){
    	if(i == 0)
    		return false;
    	else 
    		return true; 
    	
    }
    
    
       
    public void restartLauncher2(ActivityManager activity)
    {
		if(DEBUG)
			Log.d(TAG, "About to kill the launcher application");
		
	 	activity.killBackgroundProcesses(LAUNCHER);	
    }
    
    
    
 

    






}
