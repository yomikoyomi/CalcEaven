package jp.co.sakusaku.calceaven;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button)findViewById(R.id.calcButton);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText personCount = (EditText)findViewById(R.id.personCount);
                EditText amount = (EditText)findViewById(R.id.amount);
                TextView calcAmount = (TextView) findViewById(R.id.calcAmount);

                // 設定取得
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String frac = pref.getString(SettingPrefActivity.PREF_KEY_FRACTION, "500");
                Boolean roundUpFlag = pref.getBoolean(SettingPrefActivity.PREF_KEY_ROUNDUP, false);
                Integer fracInt = Integer.parseInt(frac);

                String strCount = personCount.getText().toString();
                String strAmount = amount.getText().toString();
                System.out.println(strCount);
                System.out.println(strAmount);

                int intCount = Integer.parseInt(strCount);
                int intAmount = Integer.parseInt(strAmount);

                int result = intAmount / intCount;

                if( roundUpFlag && result % fracInt != 0) {
                    result += fracInt;
                }
                result = result / fracInt * fracInt;

                calcAmount.setText(Integer.toString(result));
                System.out.println(result);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // 設定ボタン押下処理
                Intent intent = new Intent(MainActivity.this, SettingPrefActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
