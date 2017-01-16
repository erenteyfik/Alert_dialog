package com.onepiece_eren.alert_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int ALERT_DIALOG_ID=0;
    private static final int ALERT_DIALOG_LISTE_ID=1;
    private static final int CUSTOM_DIALOG_ID=2;

    String renkler[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renkler = new String[]{"Kırmızı", "Yeşil", "Mavi", "Sarı", "Turkuaz", "Pembe"};

    }

    public void ButonaDokunuldu(View v){

        switch(v.getId()){

            case R.id.button_alert:
                showDialog(ALERT_DIALOG_ID);
                break;
            case R.id.button_liste_alert:
                //liste alert dialog
                showDialog(ALERT_DIALOG_LISTE_ID);
                break;
            //custom dialog özelleşmiş dialog demektir.
            case R.id.button_custom_dialog:
                //custom alert dialog
                showDialog(CUSTOM_DIALOG_ID);
                break;

        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id){

            case ALERT_DIALOG_ID:
                //alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Uyarı!!").setMessage("Silmek istediğinizden emin misiniz?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setCancelable(false);

                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Evet butonuna tıklandıgında yapılacak işlemler
                        dialog.dismiss();
                        //dialog gidiyor.
                    }
                });

                builder.setNegativeButton("Hayir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                    final AlertDialog dialog=builder.create();
                    return  dialog;
                default:
                    return super.onCreateDialog(id);

                // setcancelable herhangi bir yere tıklayınca alertin kaybolmamasını saglıyor
                // .setIconuda ekleyebiliriz kısa gösterim

                // alertDialogu oluşturduk showla gösterdi


            case ALERT_DIALOG_LISTE_ID:
                AlertDialog.Builder builder1= new AlertDialog.Builder(this);
                builder1.setTitle("Liste şeklinde dialog");
                builder1.setItems(renkler, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Şeçilen renk: "+renkler[which],Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog1=builder1.create();
                return dialog1;


            case CUSTOM_DIALOG_ID:
                final Dialog dialog2= new Dialog(this);
                dialog2.setTitle("Custom dialog");
                dialog2.setContentView(R.layout.custom_dialog);

                Button kaydet = (Button) dialog2.findViewById(R.id.btn_kaydet);
                Button vazgec = (Button) dialog2.findViewById(R.id.btn_vazgec);
                DatePicker datePicker= (DatePicker) dialog2.findViewById(R.id.datePicker2);

                kaydet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        //işlemler
                        dialog2.dismiss();
                    }
                });

                vazgec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.cancel();
                    }
                });

                return dialog2;
        }


    }
}
