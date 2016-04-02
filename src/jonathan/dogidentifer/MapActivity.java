package jonathan.dogidentifer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebSettings;
import android.webkit.WebView;
import jonathan.map.Rectangle;

public class MapActivity extends Activity {
	
	private static final String FILEURL = "file:///storage/emulated/0/";
	private WebView mapView;
	Rectangle mapBox = new Rectangle();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_petmap);

		mapView = (WebView) findViewById(R.id.mapView);
		WebSettings webSettings = mapView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		try {
			mapView.loadDataWithBaseURL(FILEURL, renderMap(), "text/html", "UTF-8", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String renderMap() throws Exception {
		// TODO Auto-generated method stub
		InputStreamReader inputPetStoreReader = new InputStreamReader(getClass().getResourceAsStream("petStoreLocation.txt"));
		StringBuffer marker = new StringBuffer();
		
		BufferedReader bufInp = new BufferedReader(inputPetStoreReader);
		String inStr;

		int i  = 0; 
		while ((inStr = bufInp.readLine()) != null) {
			String[] petStoreInfo = inStr.split("\t");
			
			marker.append("var marker").append(i).append(" = new google.maps.Marker({\n");
			marker.append("position: {lat: ").append(petStoreInfo[2]).append(",   lng:").append(petStoreInfo[3]).append("},\n");
			marker.append( "icon: 'images/20DogIcon.png',\n");
			marker.append( "map: map,\n");
			marker.append( " title: '").append(petStoreInfo[0]).append("'\n");
			marker.append(" });\n");
			
			i+=1;
		}
		bufInp.close();
		
		
		StringBuffer location = new StringBuffer();
		
		InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("map1.tmpl"));
	    String t1 = readTmplFromReader(inputStreamReader);

		
		mapBox.ne.setLat(mapBox.nw.getLat());
		mapBox.ne.setLng(mapBox.se.getLng());
		
		mapBox.sw.setLat(mapBox.se.getLat());
		mapBox.sw.setLng(mapBox.nw.getLng());
		
		String ne = String.format("lat: %3.8f, lng: %3.8f", mapBox.ne.getLat(), mapBox.ne.getLng());
		String sw = String.format("lat: %3.8f, lng: %3.8f", mapBox.sw.getLat(), mapBox.sw.getLng());
        
		
		location.append(t1.replace("$NE", ne).replace("$SW", sw).replace("$EVENT", marker.toString()));
		

		String filePath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "petStore.html";	
				
        FileOutputStream outputStream = new FileOutputStream(new File(filePath2));
        outputStream.write(location.toString().getBytes());
        outputStream.flush();
        outputStream.close();
		return location.toString();
	}

	private String readTmplFromReader(InputStreamReader reader) throws Exception {
		BufferedReader bufInp = new BufferedReader(reader);
		String inStr;

		StringBuffer t = new StringBuffer();
		while ((inStr = bufInp.readLine()) != null) {
			t.append(inStr).append("\n");
		}
		bufInp.close();
		return t.toString();
	}

}
