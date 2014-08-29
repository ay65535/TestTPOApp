package jp.ac.ritsumei.cs.ubi.ats.testtpoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import jp.ac.ritsumei.cs.ubi.logger.client.api.matching.EventDetectionRequest;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            /// http://netlog.jpn.org/r271-635/2014/04/android_fragment_main.html
            // エディットボックスの初期文字列を入力
            EditText text1 = (EditText) rootView.findViewById(R.id.editText1);
            TextView textView2 = (TextView) rootView.findViewById(R.id.textView2);
            text1.setText("文字列の初期値");
            textView2.setText("Log data displays here.");

            // ボタン押下イベントリスナーの登録
            Button button = (Button)rootView.findViewById(R.id.button1);
            button.setOnClickListener(mButton1Listener);

            return rootView;
        }

        // ボタン押下イベントリスナー
        private View.OnClickListener mButton1Listener = new View.OnClickListener() {
            public void onClick(View v) {

                final EditText editView = new EditText(getActivity());
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                dlg.setTitle("テキスト入力のAlertDialog");
                dlg.setMessage("メイン画面のEditTextに反映します");
                // テキストボックスに初期値を入力する
                dlg.setView(editView);
                EditText text1 = (EditText) getActivity().findViewById(R.id.editText1);
                editView.setText(text1.getText().toString());
                dlg.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //Yesボタンが押された時の処理
                        Toast.makeText(getActivity(), "Yesが押されました", Toast.LENGTH_LONG).show();
                        // 入力値をメインダイアログに書き戻す
                        EditText text1 = (EditText) getActivity().findViewById(R.id.editText1);
                        text1.setText(editView.getText().toString());
                    }});
                dlg.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //Noボタンが押された時の処理
                        Toast.makeText(getActivity(), "Noが押されました", Toast.LENGTH_LONG).show();
                    }});
                dlg.show();

            }
        };

        // イベント検知機構
        private void detectEvent() {
            EventDetectionRequest query[] = new EventDetectionRequest[2];

            double latlng[] = {34.9795,135.9639,34.9795,135.9640};
            Intent locReplayIntent = new Intent(BroadcastIntentAction.EVENT_ON_CHANGE_BUILDING);
        }
    }
}
