package mx.uach.srtm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bluebite.android.eddystone.Global;
import com.bluebite.android.eddystone.Scanner;
import com.bluebite.android.eddystone.ScannerDelegate;
import com.bluebite.android.eddystone.Url;
import mx.uach.srtm.models.Usuario;
import mx.uach.srtm.utils.ReadJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ScannerDelegate {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Url> mUrls = new ArrayList<>();
    private BeaconAdapter mBeaconAdapter;
    public static ReadJson readJson = new ReadJson();
    public static String URL_USUARIO;
    private ListView lstVwUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView beaconListView = (ListView) findViewById(R.id.beaconListView);
        mBeaconAdapter = new BeaconAdapter(this, R.layout.beacon_list_item, mUrls);
        beaconListView.setAdapter(mBeaconAdapter);

        Global.logging = true;
        Global.expireTimer = 30000;
        Scanner.start(this);
       /*this.lstVwUsuarios = (ListView) findViewById(R.id.lstVwUsuarios);
        List<Usuario> usuarios = getUsuarios();
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this,
                android.R.layout.activity_list_item, android.R.id.text1, usuarios);
        this.lstVwUsuarios.setAdapter(adapter);*/
    }

    @Override
    public void eddytoneNearbyDidChange() {
        System.out.println("gkdfl{sgkfdṕgfdogksdfpokgsfpokgdfsṕo");
        System.out.println("gkdfl{sgkfdṕgfdogksdfpokgsfpokgdfsṕo");
        System.out.println("gkdfl{sgkfdṕgfdogksdfpokgsfpokgdfsṕo");
        mUrls = Arrays.asList(Scanner.nearbyUrls());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBeaconAdapter.clear();
                mBeaconAdapter.addAll(mUrls);
            }
        });
        for (Url url : mUrls) {
            String str = url.getUrl().toString();
            if (str.equals("http://bit.ly/2a2QDMc")) {
                URL_USUARIO = str;
            }
            System.out.println("URL_USUARIO = " + URL_USUARIO);
            System.out.println("URL_USUARIO = " + URL_USUARIO);
            System.out.println("URL_USUARIO = " + URL_USUARIO);

        }
//        Log.i(TAG, Arrays.toString(Scanner.nearbyUrls()));
//        mBeaconAdapter.
//        System.out.println(mUrls.isEmpty());
    }

    public List<Usuario> getUsuarios(){

        //ConnectServer server = new ConnectServer();
        //server.execute();
        List<Usuario> usuarios = new ArrayList<>();

        try {
            //String json = server.get();
            String json = ReadJson.read(URL_USUARIO);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Usuario>>(){}.getType();
            usuarios = gson.fromJson(json, listType);
        } catch (Exception e){
            Log.e("Error", "No pude leer el JSON.");
        }

        return usuarios;
    }

    /*private class ConnectServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String json = ReadJson.read(URL_USUARIO);
            System.out.println("URL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + URL_USUARIO);
            return json;
        }
    }*/
}
