package fr.android.wearther.ui.notifications;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import fr.android.wearther.R;
import fr.android.wearther.data.BodyPart;
import fr.android.wearther.data.Cloth;
import fr.android.wearther.data.DataBaseHelper;
import fr.android.wearther.data.User;
import fr.android.wearther.ui.dashboard.DashboardFragment;
import fr.android.wearther.ui.home.HomeFragment;

public class NotificationsFragment extends Fragment {
    private Context context;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        this.context = getContext();
        final TextView tv_date = root.findViewById(R.id.tv_date_reaction);
        final TextView tv_heure = root.findViewById(R.id.tv_heure_reaction);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        this.user = dataBaseHelper.getUser("default","default");

        // date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE).format(calendar.getTime());

        // heure
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        simpleDateFormat.setTimeZone(timeZone);
        String currentDateTime = simpleDateFormat.format(calendar.getTime());

        String maj = currentDate.charAt(0) + "";
        tv_date.setText(maj.toUpperCase() + currentDate.substring(1));
        tv_heure.setText(currentDateTime);

        final Button btnCold = root.findViewById(R.id.btn_cold);
        final Button btnGood = root.findViewById(R.id.btn_good);
        final Button btnHot = root.findViewById(R.id.btn_hot);

        btnCold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog fbDialogue = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);

                fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                fbDialogue.setContentView(R.layout.popup_bad);
                fbDialogue.setCancelable(true);
                final TextView popup = fbDialogue.findViewById(R.id.text_popup_bad);
                popup.setText("Nous sommes désolé si vous avez eu froid !\n\nPour que nous puissions nous améliorer dites nous où vous avez eu froid !");
                final Button btn_close = fbDialogue.findViewById(R.id.btn_close_popup_bad);

                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fbDialogue.cancel();
                    }
                });

                final Button btn_validation = fbDialogue.findViewById(R.id.btn_soumettre);
                btn_validation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<CheckBox> checkboxs = new ArrayList<>();
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_tete));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_cou));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_haut));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_mains));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_bas));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_pieds));

                        for(CheckBox check : checkboxs) {
                            if(check.isChecked()) {
                                for(Cloth cloth : DashboardFragment.suggestion) {
                                    if(cloth.getBodyPart().equals(BodyPart.tete)) {
                                        new DataBaseHelper(context).tooCold(HomeFragment.temperature, cloth, user);
                                    }
                                }
                            }
                        }

                        fbDialogue.cancel();
                    }
                });

                fbDialogue.show();
            }
        });

        btnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog fbDialogue = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);

                fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                fbDialogue.setContentView(R.layout.popup_bad);
                fbDialogue.setCancelable(true);
                final TextView popup = fbDialogue.findViewById(R.id.text_popup_bad);
                popup.setText("Nous sommes désolé si vous avez eu chaud !\n\nPour que nous puissions nous améliorer dites nous où vous avez eu chaud !");
                final Button btn_close = fbDialogue.findViewById(R.id.btn_close_popup_bad);

                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fbDialogue.cancel();
                    }
                });

                final Button btn_validation = fbDialogue.findViewById(R.id.btn_soumettre);
                btn_validation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<CheckBox> checkboxs = new ArrayList<>();
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_tete));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_cou));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_haut));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_mains));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_bas));
                        checkboxs.add(fbDialogue.findViewById(R.id.ck_pieds));

                        for(CheckBox check : checkboxs) {
                            if(check.isChecked()) {
                                for(Cloth cloth : DashboardFragment.suggestion) {
                                    if(cloth.getBodyPart().name().equals(check.getText().toString().toLowerCase())) {
                                        new DataBaseHelper(context).tooHot(HomeFragment.temperature, cloth, user);
                                        break;
                                    }
                                }
                            }
                        }

                        fbDialogue.cancel();
                    }
                });

                fbDialogue.show();
            }
        });

        btnGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog fbDialogue = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);

                fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                fbDialogue.setContentView(R.layout.popup_good);
                fbDialogue.setCancelable(true);
                final TextView popup = fbDialogue.findViewById(R.id.text_popup_good);
                popup.setText("Votre remarque a été enregistrée !\n\nNous sommes heureux que notre tenue vous ait convenue !");
                final Button btn_close = fbDialogue.findViewById(R.id.btn_close_popup_good);

                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fbDialogue.cancel();
                    }
                });
                fbDialogue.show();
            }
        });

        return root;
    }
}