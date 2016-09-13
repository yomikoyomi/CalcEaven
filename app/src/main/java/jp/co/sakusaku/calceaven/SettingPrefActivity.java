package jp.co.sakusaku.calceaven;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by yuya_ on 2016/09/04.
 */
public class SettingPrefActivity extends PreferenceActivity {

    public static final String PREF_KEY_FRACTION = "key_fraction";
    public static final String PREF_KEY_ROUNDUP = "key_roundup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(
                android.R.id.content, new PrefFragment()).commit();
    }

    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_pref);

            setSummaryFraction();
        }

        @Override
        public void onResume() {
            super.onResume();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.registerOnSharedPreferenceChangeListener(listerner);
        }

        @Override
        public void onPause() {
            super.onPause();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.registerOnSharedPreferenceChangeListener(listerner);
        }

        private OnSharedPreferenceChangeListener listerner
                = new OnSharedPreferenceChangeListener(){
            @Override
            public void onSharedPreferenceChanged(
                SharedPreferences sharedPreferences, String key) {
                if( key.equals(PREF_KEY_FRACTION)) {
                    setSummaryFraction();
                }
            }
        };

        private void setSummaryFraction() {
            ListPreference prefFraction = (ListPreference)findPreference(PREF_KEY_FRACTION);
            prefFraction.setSummary(prefFraction.getEntry());
        }
    }
}
