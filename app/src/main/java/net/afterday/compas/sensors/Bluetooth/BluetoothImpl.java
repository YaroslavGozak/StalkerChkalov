package net.afterday.compas.sensors.Bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Justas Spakauskas on 4/2/2018.
 */
@TargetApi(18)
public class BluetoothImpl implements Bluetooth
{
    private static final String TAG = "BluetoothImpl";
    private Context context;
    private IntentFilter intentFilter;
    private Observable<Double> resultStream = PublishSubject.create();
    private BluetoothAdapter bla;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private BluetoothReceiver br = new BluetoothReceiver();
    private BluetoothAdapter.LeScanCallback callback;
    private List<String> registeredMacs = new ArrayList<>();
    private Disposable resetter;

    public BluetoothImpl(Context context)
    {
        this.context = context;
        setup();
        br = new BluetoothReceiver();
        bla = BluetoothAdapter.getDefaultAdapter();
        //BluetoothAdapter.ACTION_DISCOVERY_FINISHED
        intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        callback = new LeScanCallback();
    }

    private void setup()
    {

        // Game
        registeredMacs.add("00:13:AA:00:17:FC");
        registeredMacs.add("00:13:AA:00:02:30");
        registeredMacs.add("00:13:AA:00:0A:77");
        registeredMacs.add("00:13:AA:00:40:7F");
        registeredMacs.add("00:13:AA:00:0A:8D");
        registeredMacs.add("00:13:AA:00:40:1E");
        registeredMacs.add("00:13:AA:00:35:4C");
        registeredMacs.add("00:13:AA:00:09:84");
        registeredMacs.add("00:13:AA:00:1F:35");
        registeredMacs.add("00:13:AA:00:0A:AB");
    }

    @Override
    public void start()
    {
        //context.registerReceiver(br, intentFilter);
        isRunning.set(true);
        bla.startLeScan(callback);
    }

    @Override
    public void stop()
    {
        isRunning.set(false);
    }

    @Override
    public Observable<Double> getSensorResultsStream()
    {
        return resultStream;
    }

    private class LeScanCallback implements BluetoothAdapter.LeScanCallback
    {
        @Override
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes)
        {
            long now = System.currentTimeMillis();
            if(registeredMacs.contains(bluetoothDevice.getAddress()))
            {
                if(resetter != null && !resetter.isDisposed())
                {
                    resetter.dispose();
                }
                ((Subject<Double>)resultStream).onNext(new Double(i + 100));
                resetter = Observable.timer(3, TimeUnit.SECONDS).subscribe((x) -> ((Subject<Double>)resultStream).onNext(0d));
                //((Subject<BluetoothScanResult>)resultStream).onNext(new BluetoothScanResultImpl(bluetoothDevice.getAddress(), i, now));
            }
            if(!isRunning.get())
            {
                bla.stopLeScan(callback);
            }
            //Log.e(TAG, "SCANNED!!!!!!" + bluetoothDevice.getAddress() + " " + bluetoothDevice.getName() + " " + i + " " + bytes);
        }
    }

    private class BluetoothReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(BluetoothDevice.ACTION_FOUND.equals(intent.getAction()))
            {

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("BLUETOOTH RECEIVED!", "" + intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE));
            }
            if(isRunning.get())
            {
                Log.e(TAG,"START DISCOVERY");
                bla.startDiscovery();
            }
        }
    }
}