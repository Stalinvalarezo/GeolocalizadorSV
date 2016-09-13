package com.example.stalin.geosv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivitySV extends AppCompatActivity {

    LocationManager locManager; //administa la lista de proveedores gps wifi.
    private double lati; //obtener las latitudes y mantener en variables
    private double longi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_sv);
        //Inicializar LocManager
        locManager =(LocationManager) getSystemService(LOCATION_SERVICE); //similar FindBy, administra  location manager
        //Obtener lista de proveedores
        List<String> listaProviders = locManager.getAllProviders();  //declara el location manager servicios de ubicacion se llama el metod get para matener la lista de string
        //Obtener el primer proveedor de la lista
        LocationProvider provider = locManager.getProvider(listaProviders.get(0));//administra especificamente 1
    }
    public void ActualizarLatLongClick(View v){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
        )!= PackageManager.PERMISSION_GRANTED){

        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);

    }

    private final LocationListener locationListenerGPS =new LocationListener() {//Listener clase declarada en otra clase tareas asincronas y servicios web
        @Override
        public void onLocationChanged(Location location) {
            longi = location.getLongitude();
            lati = location.getLatitude();
            double altitud = location.getAltitude();
            float velocidad =location.getSpeed();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongi = (EditText)findViewById(R.id.txtLongi);
                    EditText txtLati = (EditText)findViewById(R.id.txtLati);
                    //aqui agregamos la altitud y la velocida
                    txtLati.setText(lati+"");
                    txtLongi.setText(longi+"");
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
