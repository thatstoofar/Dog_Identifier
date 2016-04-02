package jonathan.ui;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import jonathan.dogidentifer.R;


public class ExpertDialog<Expert> extends DialogFragment {

	DialogInterface.OnClickListener okay, cancel;
	private List<String> expertList;

	public ExpertDialog(List<String> expertList, DialogInterface.OnClickListener okay, DialogInterface.OnClickListener cancel) {
		this.expertList = expertList;
		this.okay = okay;
		this.cancel = cancel;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = null;
		AlertDialog dialog = null;
		try {
			builder = new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View view = inflater.inflate(R.layout.expert_dialog, null);
		    builder.setView(view).setPositiveButton("okay", okay).setNegativeButton("cancel", cancel);
		    dialog = builder.create();

			TextView title = new TextView(getActivity().getApplicationContext());
			title.setText("Expert Dialog");
			title.setGravity(Gravity.CENTER);
			title.setTextSize(18);
			title.setBackgroundColor(Color.GRAY);
			title.setTextColor(Color.WHITE);
			dialog.setCustomTitle(title);
			
			



		} catch (Exception e) {
			e.printStackTrace();
		}
		return dialog;

	}

}
