package br.com.etecia.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Intent i;
    float lat, longi;
    //variável global
    private GoogleMap mMap;
    double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
      //  i = getIntent();
      //  lat = i.getExtras().getFloat("latitude");
      //  longi = i.getExtras().getFloat("longitude");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Variavel para configuração das estruturas do mapa.
        mMap = googleMap;

        //Tipo de mapas
        /*
        Normal
            Mapa rodoviário comum. Mostra vias, alguns elementos criados pelo homem e
            recursos naturais importantes, como rios. Etiquetas de estradas e de elementos
             também são visíveis.
        Híbrido
            Dados de fotografia de satélite com mapas rodoviários. Etiquetas de estradas
             e de elementos também são visíveis.
        Satélite
            Dados de fotografia de satélite. Marcadores de estradas e de elementos não são visíveis.
        Relevo
            Dados topográficos. O mapa inclui cores, curvas de nível e etiquetas, além de sombreamento
             de perspectiva. Algumas vias e etiquetas também são visíveis.
        Nenhum
            Nenhum bloco. O mapa será renderizado como uma grade vazia, sem carregar blocos.
        */

        //método para alterar o tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        latitude = -23.5856101;
        longitude = -46.6669873;

        String local = "Parque do Ibirapuera";

        final LatLng posicao = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions()
                .position(posicao)
                .snippet("Local do parque do ibirapuera")
                //.icon(BitmapDescriptorFactory.defaultMarker(
                //        BitmapDescriptorFactory.HUE_VIOLET
                // ))
                //Icone costumizado
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapa))
                .title(local));

        //Movimento da camera conforme a necessidade.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao,15));

        LatLng etecia = new LatLng(-23.702723, -46.6914657);
/*
        //Criando Circulos
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(etecia);
        circleOptions.fillColor(Color.argb(50,0,255,255));
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.argb(50,47,79,79));
        //Medida em metros
        circleOptions.radius(500.00);
        //Aplicando o circulo no mapa
         mMap.addCircle(circleOptions);
*/
         /*Criando Poligonos
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.701221, -46.685091));
        polygonOptions.add(new LatLng(-23.706370, -46.688433));
        polygonOptions.add(new LatLng(-23.700058, -46.691930));
        polygonOptions.add(new LatLng(-23.702861, -46.701149));
        polygonOptions.strokeColor(Color.BLUE);
        polygonOptions.fillColor(Color.argb(100, 204, 0, 153));
        polygonOptions.strokeWidth(10);
        //Aplicando o poligono no mapa
        mMap.addPolygon(polygonOptions);*/


        //Adicionando evento de click no mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                double latitude, longitude;

                latitude = latLng.latitude;
                longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "Click curto - Lat: " + latitude + " Long: " + longitude,
                        Toast.LENGTH_SHORT).show();

                //Desenhando linhas com base em um ponto

                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.add(posicao);
                polygonOptions.add(latLng);
                polygonOptions.strokeColor(Color.BLUE);
                polygonOptions.strokeWidth(10);

                mMap.addPolygon(polygonOptions);

                //Gerando marcador de click curto
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Clique curto")
                        .snippet("Descrição do click curto")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapa))
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        });

        //Gerando click longo

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                //Salvando a latitude e longitude
                double latitute, longitude;

                latitute = latLng.latitude;
                longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "Click Longo - Lat: " + latitute + "Lon: " + longitude,
                        Toast.LENGTH_SHORT).show();
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Click longo")
                        .snippet("Descrição do click longo")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapa))
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        });


    }
}