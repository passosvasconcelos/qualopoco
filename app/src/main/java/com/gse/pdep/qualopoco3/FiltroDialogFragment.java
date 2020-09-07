package com.gse.pdep.qualopoco3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

public class FiltroDialogFragment extends DialogFragment {

    public interface FiltroDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    FiltroDialogListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            listener = (FiltroDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            + "must implement FiltroDialogListener");        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.filtros, null));
        builder.setMessage("novo alerta dialog de filtros")
                .setPositiveButton("positivo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity}
                        listener.onDialogPositiveClick(FiltroDialogFragment.this);
                    }
                })
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(FiltroDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
