package com.example.practical5;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private static final String URL_BEIJING = "https://english.beijing.gov.cn/";
    private static final String URL_SHANGHAI = "https://english.shanghai.gov.cn/";
    private static final String URL_TIANJIN = "https://en.tj.gov.cn/";
    private static final String URL_CHONGQING = "https://english.cq.gov.cn/";

    private TextView nameTextView;
    private TextView infoTextView;
    private ImageView imageView;
    private Button visitSiteButton;

    private String municipalityName;
    private String websiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        nameTextView = findViewById(R.id.tv_municipality_name);
        infoTextView = findViewById(R.id.tv_municipality_info);
        imageView = findViewById(R.id.iv_municipality);
        visitSiteButton = findViewById(R.id.btn_visit_site);

        TextProcessor textProcessor = new TextProcessor();
        municipalityName = textProcessor.processMunicipality(
                getIntent().getStringExtra(TextProcessor.EXTRA_MUNICIPALITY));

        if (municipalityName == null) {
            Toast.makeText(this, R.string.error_invalid_municipality, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        displayMunicipality();
        visitSiteButton.setOnClickListener(view -> openWebsite());
    }

    private void displayMunicipality() {
        int colourRes;
        int imageRes;
        int summaryIndex;
        String url;

        switch (municipalityName) {
            case TextProcessor.SHANGHAI:
                colourRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                summaryIndex = TextProcessor.INDEX_SHANGHAI;
                url = URL_SHANGHAI;
                break;
            case TextProcessor.TIANJIN:
                colourRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                summaryIndex = TextProcessor.INDEX_TIANJIN;
                url = URL_TIANJIN;
                break;
            case TextProcessor.CHONGQING:
                colourRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                summaryIndex = TextProcessor.INDEX_CHONGQING;
                url = URL_CHONGQING;
                break;
            case TextProcessor.BEIJING:
            default:
                colourRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                summaryIndex = TextProcessor.INDEX_BEIJING;
                url = URL_BEIJING;
                break;
        }

        int colour = ContextCompat.getColor(this, colourRes);
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);

        nameTextView.setText(municipalityName);
        nameTextView.setBackgroundColor(colour);
        nameTextView.setTextColor(ContextCompat.getColor(this, R.color.color_text_light));

        imageView.setImageResource(imageRes);

        infoTextView.setText(summaries[summaryIndex]);
        infoTextView.setTextColor(colour);

        visitSiteButton.setBackgroundTintList(ColorStateList.valueOf(colour));
        visitSiteButton.setTextColor(ContextCompat.getColor(this, R.color.color_text_light));

        websiteUrl = url;
    }

    private void openWebsite() {
        if (websiteUrl == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_no_app, Toast.LENGTH_SHORT).show();
        }
    }
}