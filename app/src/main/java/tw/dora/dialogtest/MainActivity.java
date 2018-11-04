package tw.dora.dialogtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Timer timer;
    private int which_final = -1;
    private boolean[] choices = new boolean[]{true, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();

    }

    public void test1(View view) {
        timer.schedule(new CloseAlertTask(),3*1000);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message(顯示3秒後消失)")
                .setCancelable(false);
        alertDialog = builder.create();

        alertDialog.show();;

    }

    private class  CloseAlertTask extends TimerTask{
        @Override
        public void run() {
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
        }
    }

    public void test2(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("輸入帳號")
                        .setCancelable(false);
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("brad",""+input.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel",null);
        alertDialog = builder.create();

        alertDialog.show();
    }

    public void test3(View view) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this)
                            .setTitle("我是標題")
                            .setIcon(R.drawable.ic_assignment_black_24dp)
                            .setCancelable(false);

//            builder.setItems(new String[]{"Android", "iOS","Windows"}, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Log.v("brad","which = "+which);
//                }
//            });

            builder.setSingleChoiceItems(new String[]{"Android", "iOS","Windows"},0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        which_final = which;
                    }
                });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.v("brad","which = "+which_final);
                    }
                 });



            alertDialog = builder.create();

            alertDialog.show();
    }

    public void test4(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("我是標題")
                        .setIcon(R.drawable.ic_assignment_black_24dp)
                        .setCancelable(false);



        builder.setMultiChoiceItems(new String[]{"Android", "iOS", "Windows"},
                choices,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.v("brad","which = "+which+":"+choices[which]);

                    }
                });




        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("brad","which = "+which_final);
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void test5(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("標題");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.mydialog);

        final EditText account = dialog.findViewById(R.id.dialog_account);
        final EditText password = dialog.findViewById(R.id.dialog_passwd);
        Button ok = dialog.findViewById(R.id.dialog_ok);
        Button cancel = dialog.findViewById(R.id.dialog_cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Log.v("brad","Account:"+account.getText().toString()+" ,Password:"+password.getText().toString());
            }
        });
        dialog.show();
    }

    public void test6(View view) {
        Toast t = Toast.makeText(this,"Hello, World!",Toast.LENGTH_SHORT);
        t.setGravity(Gravity.FILL_HORIZONTAL+Gravity.CENTER_VERTICAL,0,300);
        t.show();
    }
}
