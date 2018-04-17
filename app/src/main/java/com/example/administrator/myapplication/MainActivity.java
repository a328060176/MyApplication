package com.example.administrator.myapplication;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_text;
    private WifiManager wifiManager;
    int i=0;
    List<ScanResult> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (Build.VERSION.SDK_INT >= 23 && !AppUtil.isGpsOPen(this)) {
//            Settings.Secure.putInt(getContentResolver(),Settings.Secure.LOCATION_MODE, 1);
//        }
        init();
    }

    private void init() {
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intToBytes(i);
            }
        });
     /*   openWifi();
        list = wifiManager.getScanResults();
        ListView listView = (ListView) findViewById(R.id.listView);
        if (list == null) {
            Toast.makeText(this, "wifi未打开！", Toast.LENGTH_LONG).show();
        }else {
            listView.setAdapter(new MyAdapter(this,list));
        }*/

    }

    /**
     *  打开WIFI
     */
    private void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;
        List<ScanResult> list;
        public MyAdapter(Context context, List<ScanResult> list) {
            // TODO Auto-generated constructor stub
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            view = inflater.inflate(R.layout.item_wifi_list, null);
            ScanResult scanResult = list.get(position);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(scanResult.SSID);
            TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);
            signalStrenth.setText(String.valueOf(Math.abs(scanResult.level)));
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            //判断信号强度，显示对应的指示图标
            if (Math.abs(scanResult.level) > 100) {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            } else if (Math.abs(scanResult.level) > 80) {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            } else if (Math.abs(scanResult.level) > 70) {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            } else if (Math.abs(scanResult.level) > 60) {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            } else if (Math.abs(scanResult.level) > 50) {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            }
            return view;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    //高位在前，低位在后
    public  byte[] int2bytes ( int num){
        i++;
        byte[] result = new byte[4];
        result[0] = (byte) ((num >>> 24) & 0xff);//说明一
        result[1] = (byte) ((num >>> 16) & 0xff);
        result[2] = (byte) ((num >>> 8) & 0xff);
        result[3] = (byte) ((num >>> 0) & 0xff);
        return result;
    }
    public  byte[] intToBytes(int value) {
        i++;
        byte[] des = new byte[4];
        des[0] = (byte) (value & 0xff);  // 低位(右边)的8个bit位
        des[1] = (byte) ((value >> 8) & 0xff); //第二个8 bit位
        des[2] = (byte) ((value >> 16) & 0xff); //第三个 8 bit位
        /**
         * (byte)((value >> 24) & 0xFF);
         * value向右移动24位, 然后和0xFF也就是(11111111)进行与运算
         * 在内存中生成一个与 value 同类型的值
         * 然后把这个值强制转换成byte类型, 再赋值给一个byte类型的变量 des[3]
         */
        des[3] = (byte) ((value >> 24) & 0xff); //第4个 8 bit位
        return des;
    }
}