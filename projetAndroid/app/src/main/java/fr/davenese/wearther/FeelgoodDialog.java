package fr.davenese.wearther;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

public class FeelgoodDialog extends Dialog {

    // Champs
    private String title;
    private String subtitle;
    private Button closeButton;
    private TextView titleView, subtitleView;

    // Constructeur

    public FeelgoodDialog(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.feelgood_popup);

        //Instance de chaque objet
        this.title = "Mon titre";
        this.subtitle = "Mon sous-titre";
        this.closeButton = findViewById(R.id.closeButton);
        this.titleView = findViewById(R.id.title);
        this.subtitleView = findViewById(R.id.subtitle);
    }

    public void setTitle(String title) {this.title = title;}
    public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
    public Button getCloseButton() {return closeButton;}

    public void build() {
        show();
        titleView.setText(title);
        subtitleView.setText(subtitle);
    }

}
