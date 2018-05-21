package edu.purdue.you54.testbluetooth;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;



        import java.util.ArrayList;
        import java.util.List;
        import java.util.Set;

        import android.os.Bundle;
        import android.app.Activity;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.content.Intent;
        import android.view.Menu;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

public class MainActivity extends Activity {

    private Button On,Off,Visible,list, search, test;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice>pairedDevices;
    private ListView lv;

    public ArrayList<String> foundDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        On = (Button)findViewById(R.id.button1);
        Off = (Button)findViewById(R.id.button2);
        Visible = (Button)findViewById(R.id.button3);
        list = (Button)findViewById(R.id.button4);
        search = (Button)findViewById(R.id.button5);
        test = (Button)findViewById(R.id.button6);
*/
        lv = (ListView)findViewById(R.id.listView1);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null){
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
        }
        //automatically turn on bluetooth
        if(!bluetoothAdapter.isEnabled()){
            Intent  turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn,0);
            Toast.makeText(getApplicationContext(), "Bluetooth not on, Turned On", Toast.LENGTH_LONG).show();
        }
        /*
        else{
            Toast.makeText(getApplicationContext(), "Bluetooth already on", Toast.LENGTH_LONG).show();
        }
        */

        String test = "" + bluetoothAdapter.isDiscovering();
        Toast.makeText(getApplicationContext(),test, Toast.LENGTH_LONG).show();

        if(!bluetoothAdapter.isDiscovering()){
            bluetoothAdapter.startDiscovery();
            Toast.makeText(getApplicationContext(), "does not discover automatically but I made it do", Toast.LENGTH_LONG).show();
        }

        for(int i = 1; i <= 10; i++){
            foundDevices.add("Bluetooth" + i);
        }


        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, foundDevices);
        lv.setAdapter(adapter);
    }




    public void list(View view){
        pairedDevices = bluetoothAdapter.getBondedDevices();

        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : pairedDevices)
            list.add(bt.getName());

        Toast.makeText(getApplicationContext(),"Showing Paired Devices",
                Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

    }
    public void off(View view){
        bluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(),"Turned off" ,
                Toast.LENGTH_LONG).show();
    }
    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.
                ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);

    }

    public void search(View view){
        Toast.makeText(getApplicationContext(),"Searching devices" ,
                Toast.LENGTH_LONG).show();
    }

    public void test(View view){
        Toast.makeText(getApplicationContext(),"testing" ,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}



