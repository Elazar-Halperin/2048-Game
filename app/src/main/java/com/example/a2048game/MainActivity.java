package com.example.a2048game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<TableRow> rowList;
    int PLACES = 16;
    CardView [][] cv_holderMat = new CardView[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowList = new ArrayList<>();

        rowList.add(findViewById(R.id.tr_1));
        rowList.add(findViewById(R.id.tr_2));
        rowList.add(findViewById(R.id.tr_3));
        rowList.add(findViewById(R.id.tr_4));

        fillTheMat();

        placeTwo();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                swipeUp();
            }
        }, 4000);


    }

    private void swipeUp() {
        for(int i = 0; i < cv_holderMat.length; i++) {
            for(int j = cv_holderMat.length - 1;  j > 0; j--) {
                TextView currentChild = (TextView) cv_holderMat[j][i].getChildAt(0);
                if(currentChild == null)
                    continue;
                Log.d("shit", currentChild.getText().toString());
                View nextChild = cv_holderMat[j - 1][i].getChildAt(0);
                if(nextChild != null)
                    continue;

                CardView currentBox = cv_holderMat[j][i];
                CardView nextBox = cv_holderMat[j - 1][i];

                currentBox.removeView(currentChild);
                nextBox.addView(currentChild);

            }
        }
    }

    private void fillTheMat() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                cv_holderMat[i][j] = (CardView) rowList.get(i).getChildAt(j);
            }
        }
    }

    private void placeTwo() {
        int[] firstPlacements = getTwoRandomPlaces();

        for(int i = 0; i < firstPlacements.length; i++) {
            TextView tv_firstCube = new TextView(getApplicationContext());
            tv_firstCube.setText("2");
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.titillium_web_bold);
            //or to support all versions use
            tv_firstCube.setTypeface(typeface);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tv_firstCube.setGravity(Gravity.CENTER);
            tv_firstCube.setTextSize(34);

            int rowPos = firstPlacements[i] / 4;
            CardView cardView = (CardView) rowList.get(rowPos).getChildAt(firstPlacements[i] - (4 * rowPos));
            cardView.addView(tv_firstCube);
        }
    }

    // get the first position number to place the
    // textvies
    private int[] getTwoRandomPlaces() {
        int first = (int) ( Math.floor(Math.random() * 16));
        int second = (int) ( Math.floor(Math.random() * 16));

        while(second == first) second = (int) ( Math.floor(Math.random() * 16));

        Log.d("shit", "first: " + first + "\nsecond: " + second);

        return new int[]{first, second};
    }

}