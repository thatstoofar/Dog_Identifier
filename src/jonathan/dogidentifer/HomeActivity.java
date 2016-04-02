package jonathan.dogidentifer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import jonathan.ui.ExpertDialog;

public class HomeActivity extends Activity {

	Button identifyButton;
	private Button displayButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		identifyButton = (Button) findViewById(R.id.identifyButton);
		identifyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final List<String> expertList = new ArrayList<String>();

				DialogFragment newFragment = new ExpertDialog(expertList, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						AlertDialog a = (AlertDialog) dialog;

						CheckBox size = (CheckBox) a.findViewById(R.id.checkbox_Size);
						CheckBox hair = (CheckBox) a.findViewById(R.id.checkbox_Hair);
						CheckBox tail = (CheckBox) a.findViewById(R.id.checkbox_Tail);
						CheckBox ear = (CheckBox) a.findViewById(R.id.checkbox_Ear);
						
						

						if (size.isChecked()) {
							expertList.add("Size");

						}
						if (hair.isChecked()) {
							expertList.add("Hair");
						}
						if (tail.isChecked()) {
							expertList.add("Tail");
						}
						if (ear.isChecked()) {
							expertList.add("Ear");
						}
						try {
							Intent intent = new Intent(HomeActivity.this, MainActivity.class);
							intent.putExtra("experts", expertList.toArray(new String[expertList.size()]) );
							startActivity(intent);
							
						}
						catch(Exception e){
							
						}
						
					
					}
				}, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
				newFragment.show(getFragmentManager(), "ExpertDialog");
			}
		});
		
		displayButton = (Button) findViewById(R.id.displayPetStoreButton);
		displayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(HomeActivity.this, MapActivity.class);
				startActivity(intent);
				
			}
			
		});
		
		
		
		
		

	}
}
