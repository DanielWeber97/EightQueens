package com.example.eightqueens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
 boolean[] hasQueen;
 Button[] buttons;
 boolean[] lightBlue, rowHasQueen, colHasQueen;
 HashSet<Integer> topEdge = new HashSet<Integer>();
 HashSet<Integer> leftEdge = new HashSet<Integer>();
 HashSet<Integer> bottomEdge = new HashSet<Integer>();
 HashSet<Integer> rightEdge = new HashSet<Integer>();
 int numQueens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reset = findViewById(R.id.resetButton);

        numQueens = 0;

        rowHasQueen = new boolean[8];
        colHasQueen = new boolean[8];
        buttons = new Button[64];
        hasQueen = new boolean[64];
        lightBlue = new boolean[64];

        for(int i = 1; i <=8; i++){
            topEdge.add(i);
        }
        for(int i = 57; i <= 64; i++){
            bottomEdge.add(i);
        }
        for(int i = 1; i<=7;i++){
            leftEdge.add(1+(i*8));
            rightEdge.add(8+(i*8));
        }



        for (int i = 0; i<8; i++){
            for(int j = 0; j< 8; j++){
                if((i%2 == 0 && j%2 == 0) || (i %2 ==1 && j % 2 == 1) ){
                    lightBlue[i*8 + j] = true;
                }
            }
        }
        populateButtons();

    }

    public void populateButtons(){
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);
        buttons[11] = findViewById(R.id.button12);
        buttons[12] = findViewById(R.id.button13);
        buttons[13] = findViewById(R.id.button14);
        buttons[14] = findViewById(R.id.button15);
        buttons[15] = findViewById(R.id.button16);
        buttons[16] = findViewById(R.id.button17);
        buttons[17] = findViewById(R.id.button18);
        buttons[18] = findViewById(R.id.button19);
        buttons[19] = findViewById(R.id.button20);
        buttons[20] = findViewById(R.id.button21);
        buttons[21] = findViewById(R.id.button22);
        buttons[22] = findViewById(R.id.button23);
        buttons[23] = findViewById(R.id.button24);
        buttons[24] = findViewById(R.id.button25);
        buttons[25] = findViewById(R.id.button26);
        buttons[26] = findViewById(R.id.button27);
        buttons[27] = findViewById(R.id.button28);
        buttons[28] = findViewById(R.id.button29);
        buttons[29] = findViewById(R.id.button30);
        buttons[30] = findViewById(R.id.button31);
        buttons[31] = findViewById(R.id.button32);
        buttons[32] = findViewById(R.id.button33);
        buttons[33] = findViewById(R.id.button34);
        buttons[34] = findViewById(R.id.button35);
        buttons[35] = findViewById(R.id.button36);
        buttons[36] = findViewById(R.id.button37);
        buttons[37] = findViewById(R.id.button38);
        buttons[38] = findViewById(R.id.button39);
        buttons[39] = findViewById(R.id.button40);
        buttons[40] = findViewById(R.id.button41);
        buttons[41] = findViewById(R.id.button42);
        buttons[42] = findViewById(R.id.button43);
        buttons[43] = findViewById(R.id.button44);
        buttons[44] = findViewById(R.id.button45);
        buttons[45] = findViewById(R.id.button46);
        buttons[46] = findViewById(R.id.button47);
        buttons[47] = findViewById(R.id.button48);
        buttons[48] = findViewById(R.id.button49);
        buttons[49] = findViewById(R.id.button50);
        buttons[50] = findViewById(R.id.button51);
        buttons[51] = findViewById(R.id.button52);
        buttons[52] = findViewById(R.id.button53);
        buttons[53] = findViewById(R.id.button54);
        buttons[54] = findViewById(R.id.button55);
        buttons[55] = findViewById(R.id.button56);
        buttons[56] = findViewById(R.id.button57);
        buttons[57] = findViewById(R.id.button58);
        buttons[58] = findViewById(R.id.button59);
        buttons[59] = findViewById(R.id.button60);
        buttons[60] = findViewById(R.id.button61);
        buttons[61] = findViewById(R.id.button62);
        buttons[62] = findViewById(R.id.button63);
        buttons[63] = findViewById(R.id.button64);

    }
    public void buttonClicked(View v){

     Log.v("MY_TAG", "button clicked");
        String s =(String) v.getTag();
        Integer num = Integer.parseInt(s);
        int row = num/8 + 1;
        int col = num%8;
        if (col == 0){
            col = 8;
            row--;
        }
        boolean queenOnD = queenOnDiagonal(num, hasQueen, topEdge,bottomEdge,leftEdge,rightEdge);
        if((queenOnD || rowHasQueen[row-1] || colHasQueen[col-1]) && !hasQueen[num-1]){

            Toast.makeText(getApplicationContext(), "Illegal Move", Toast.LENGTH_SHORT).show();
        } else {
            if (lightBlue[num - 1]) {
                if (hasQueen[num - 1]) {
                    v.setBackgroundResource(R.color.carolinaBlue);
                    numQueens--;
                } else {
                    v.setBackgroundResource(R.drawable.light_queen);
                    numQueens++;
                }
            } else {
                if (hasQueen[num - 1]) {
                    v.setBackgroundResource(R.color.navy);
                    numQueens--;

                } else {
                    v.setBackgroundResource(R.drawable.dark_queen);
                    numQueens++;
                }
            }
            if(numQueens ==8){
                celebrate();
            }
            colHasQueen[col-1] = !colHasQueen[col-1];
            rowHasQueen[row-1] = !rowHasQueen[row-1];
            hasQueen[num-1] = !hasQueen[num-1];
        }



       // Toast.makeText(getApplicationContext(), s + " row: " + row + " col:" + col,Toast.LENGTH_SHORT ).show();
    }

    public void celebrate(){
       Toast.makeText(getApplicationContext(),"YOU WIN!!!", Toast.LENGTH_SHORT).show();
    }
    public void reset(View v){
        numQueens = 0;
        for(int i = 0; i < rowHasQueen.length; i++){
            rowHasQueen[i] = false;
            colHasQueen[i] = false;
        }

        for(int i = 0; i< 64; i ++){
            hasQueen[i] = false;
            if(lightBlue[i]){
                buttons[i].setBackgroundResource(R.color.carolinaBlue);
            } else{
                buttons[i].setBackgroundResource(R.color.navy);
            }
        }
    }

    public static boolean queenOnDiagonal(int n,boolean[] queens, HashSet<Integer> top
    ,HashSet<Integer> bottom,HashSet<Integer> left,HashSet<Integer> right){
        int nw = n;
        int ne = n;
        int sw = n;
        int se = n;
        boolean tryNW= true, tryNE = true, trySE = true, trySW = true;
        if(right.contains(n)){
            trySE = false;
            tryNE = false;
        }
        if(left.contains(n)){
            tryNW = false;
            trySW = false;
        }
        for(int i = 1; i< 8; i++){
            nw-=9;
            ne-=7;
            sw+=7;
            se+=9;




            if(n == 1){
                trySE = true;
                trySW = false;
                tryNE = false;
                tryNW = false;
            }
            if(n == 8){
                trySW = true;
                tryNE = false;
                tryNW = false;
                trySE = false;
            }
            if(n == 57){
                tryNE = true;
                trySW = false;
                tryNW = false;
                trySE = false;
            }
            if(n == 64){
                tryNW = true;
                trySE = false;
                tryNE = false;
                trySW = false;
            }


            if(1 <= nw && nw <= 64 && tryNW){
         //       Log.v("MY_TAG", "nw = " + nw);
                if(nw >= 1 && queens[nw-1]) {
                    Log.v("MY_TAG", "QonD at nw " + nw);
                    return true;
                }
            }
            if(1 <= ne && ne <= 64  && tryNE){
                Log.v("MY_TAG", "ne = " + ne);
                if(ne >=1 && queens[ne-1]) {
                    Log.v("MY_TAG", "QonD at ne " + ne);
                    return true;
                }
            }
            if(1 <= sw && sw <= 64 && trySW){
             //   Log.v("MY_TAG", "sw = " + sw);
                if(sw >=1 && queens[sw-1]) {
                    Log.v("MY_TAG", "QonD at sw " + sw);
                    return true;
                }
            }
            if(1 <= se && se <= 64 && trySE){
             //   Log.v("MY_TAG", "se = " + se);
                if(se >=1 && queens[se-1]) {
                    Log.v("MY_TAG", "QonD at se " + se);
                    return true;
                }
            }




            if(bottom.contains(se) || right.contains(se)|| left.contains(se) && ! left.contains(n)){
                trySE = false;
            }

            if(left.contains(nw) || top.contains(nw) || (right.contains(nw)&& ! right.contains(n))){
                tryNW = false;
            }

            if(top.contains(ne)|| right.contains(ne) || (left.contains(ne) && !left.contains(n))){
                tryNE = false;
            }

            if(left.contains(sw) || bottom.contains(sw) || right.contains(sw) && ! right.contains(n)){
                trySW = false;
            }
        }
        return false;
    }
}
