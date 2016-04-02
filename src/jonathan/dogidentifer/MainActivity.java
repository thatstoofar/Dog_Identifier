package jonathan.dogidentifer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import jonathan.dogidentifer.expert.EarExpert;
import jonathan.dogidentifer.expert.Expert;
import jonathan.dogidentifer.expert.HairExpert;
import jonathan.dogidentifer.expert.SizeExpert;
import jonathan.dogidentifer.expert.TailExpert;
import jonathan.dogidentifer.expert.Util;

public class MainActivity extends Activity {

	private int[] spinnerIds;
	private int[] textIds;

	private List<Expert> expertList = new ArrayList<Expert>();
	private List<Spinner> spinnerList = new ArrayList<Spinner>();

	private Button askButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] experts = null;
		setContentView(R.layout.activity_main);
		if (getIntent().hasExtra("experts")) {
			experts = getIntent().getStringArrayExtra("experts");
		}
		init(experts);
		Spinner s = (Spinner) findViewById(R.id.spinner1);
		s.setVisibility(View.GONE);
		s = (Spinner) findViewById(R.id.spinner2);
		s.setVisibility(View.GONE);
		s = (Spinner) findViewById(R.id.spinner3);
		s.setVisibility(View.GONE);
		s = (Spinner) findViewById(R.id.spinner4);
		s.setVisibility(View.GONE);
		
		TextView t = (TextView) findViewById(R.id.question1);
		t.setVisibility(View.GONE);
		 t = (TextView) findViewById(R.id.question2);
		t.setVisibility(View.GONE);
		 t = (TextView) findViewById(R.id.question3);
		t.setVisibility(View.GONE);
		 t = (TextView) findViewById(R.id.question4);
		t.setVisibility(View.GONE);

		
		
		


		int i = 0;
		for (Expert e : expertList) {
			TextView text = (TextView) findViewById(textIds[i]);
			Spinner spinner = (Spinner) findViewById(spinnerIds[i++]);
			String[] items = new String[e.getPossibleAnswers().size()];
			items = e.getPossibleAnswers().toArray(items);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
			// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			spinner.setPrompt(e.getPrompt());
			text.setText(e.getPrompt());
			// Log.i("onCreate", e.getPrompt());
			text.setVisibility(View.VISIBLE);
			spinner.setVisibility(View.VISIBLE);

			spinnerList.add(spinner);

		}
		askButton = (Button) findViewById(R.id.btnSubmit);
		askButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// This is the controller to talk to all experts
				List<String> match = new ArrayList<String>();
				for (int i = 0; i < expertList.size(); i++) {
					Spinner spinner = spinnerList.get(i);
					Expert e = expertList.get(i);
					String question = spinner.getSelectedItem().toString();
					e.postQuestion(question);
					List<String> potentialHit = e.getPotentialHit();
					if (i == 0) {
						Util.copy(match, potentialHit);
					} else {
						match = Util.and(match, potentialHit);
					}
				}
				Log.i("match", "" + match.size());

				showResult(match);

			}

		});

	}

	private void showResult(List<String> match) {
		StringBuffer sb = new StringBuffer();
		if (match.size() == 0) {
			sb.append("Unfortunately, we found no hit");
		} else {
			sb.append("We found the following match ...\n");
			for (String s : match) {
				sb.append("   ").append(s).append("\n");
			}
		}

		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
		alertDialog.setTitle("Result");
		alertDialog.setMessage(sb.toString());
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.show();

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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void init(String[] experts) {
		spinnerIds = new int[experts.length];
		textIds = new int[experts.length];

		for (int i = 0; i < experts.length; i++) {
			if ("Size".equals(experts[i])) {
				textIds[i] = R.id.question1;
				spinnerIds[i] = R.id.spinner1;
				expertList.add(new SizeExpert());

			} else if ("Hair".equals(experts[i])) {

				textIds[i] = R.id.question2;
				spinnerIds[i] = R.id.spinner2;
				expertList.add(new HairExpert());
			} else if ("Tail".equals(experts[i])) {

				textIds[i] = R.id.question3;
				spinnerIds[i] = R.id.spinner3;
				expertList.add(new TailExpert());

			} else if ("Ear".equals(experts[i])) {
				textIds[i] = R.id.question4;
				spinnerIds[i] = R.id.spinner4;
				expertList.add(new EarExpert());
			}

		}


	}

}
