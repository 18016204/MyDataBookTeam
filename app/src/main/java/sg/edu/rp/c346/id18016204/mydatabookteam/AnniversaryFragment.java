package sg.edu.rp.c346.id18016204.mydatabookteam;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AnniversaryFragment extends Fragment {

    private static final int MODE_PRIVATE = 0;
    Button btnEdit;
    private SharedPreferences savedText;
    TextView tvData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anniversary, container, false);
        btnEdit = view.findViewById(R.id.btnEdit);

         tvData = (TextView) view.findViewById(R.id.tvData);

        LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = inflater.inflate(R.layout.dialog_edit, null );

        EditText etData = (EditText) viewDialog.findViewById(R.id.etData);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(getContext());
                myBuilder.setTitle("Edit Anniversary");
                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("Cancel", null);
                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String data = etData.getText().toString();
                        tvData.setText(data);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        return view;
    }
    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedText.edit();
        editor.putString("savedText",tvData.getText().toString());
        editor.commit();
        super.onPause();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResume() {
        savedText = getActivity().getSharedPreferences("savedText",MODE_PRIVATE);
        tvData.setText(savedText.getString("savedText",""));
        super.onResume();
    }
}