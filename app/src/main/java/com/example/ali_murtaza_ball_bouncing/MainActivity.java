package com.example.ali_murtaza_ball_bouncing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int height;
    private int width;
    private float posX;
    private float posY;
    private int velX;
    private int velY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cogemos las imagenes
        final ImageView ball = findViewById(R.id.imageview_ball);
        final ImageView rectangle = findViewById(R.id.imageview_rectangle);
        DisplayMetrics display = this.getBaseContext().getResources().getDisplayMetrics();
        //Cogemos la altura y anchura de la pantalla
        height = display.heightPixels;
        width = display.widthPixels;
        //Las posiciones iniciales de la pelota
        posX = 400;
        posY = 0;
        velX = 5;
        velY = 10;
        TimerTask timertTask = new TimerTask() {
            @Override
            public void run() {
                if (posX >= 0 && posX <= width - ball.getWidth()) {
                    posX = posX + velX;
                    ball.setX(posX);
                    posY = posY + velY;
                    ball.setY(posY);
                } else { //x se encuentra a la misma posicion que la pared, entonces rebota
                    ball.setX(posX);
                    velX = velX * -1;
                    posX = posX + velX;
                }

                if ((posY <= height - ball.getHeight()) && posY > 0) {
                    posX = posX + velX;
                    posY = posY + velY;
                    ball.setX(posX);
                    ball.setY(posY);
                } else { //y se encuentra a la misma posicion que la pared, entonces rebota
                    ball.setY(posY);
                    velY = velY * -1;
                    posY = posY + velY;
                }

                if(posX+ball.getHeight() > rectangle.getX() && posY+ball.getHeight()> rectangle.getY()){
                    velX = velX * -1;
                }

                else if(posX<=rectangle.getX()+rectangle.getWidth() && posY+ball.getWidth()<=rectangle.getHeight() && ball.getX()+ball.getWidth()>=rectangle.getX()+rectangle.getWidth() && posY+ball.getWidth()>=rectangle.getY()){
                    velX = velX * -1;
                }
            }

        };
        Timer timer = new Timer();
        timer.schedule(timertTask, 100, 30);

    }
}
