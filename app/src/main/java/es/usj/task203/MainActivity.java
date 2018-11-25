package es.usj.task203;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnIniciar, Detener, Pausar, Continuar,btnNoLoop;
    MediaPlayer mp;
    int posicion = 0;
    boolean circular=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar();
            }
        });

        btnNoLoop = findViewById(R.id.btnNoLoop);
    }


    public void destruir() {
        if (mp != null)
            mp.release();
    }

    private void Iniciar() {
        destruir();
        mp = MediaPlayer.create(this, R.raw.sound);
        mp.start();
        mp.setLooping(circular);
    }

    public void detener(View v) {
        if (mp != null) {
            mp.stop();
            posicion = 0;
        }
    }

    public void circular(View v) {
        detener(null);
        if (circular) {
            btnNoLoop.setText("NO REPORDUCIR EN FORMA CIRCULAR");
            circular= false;
        }
        else {
            btnNoLoop.setText("REPORDUCIR EN FORMA CIRCULAR");
            circular = true;
        }
    }

    public void pausar(View v) {
        if (mp != null && mp.isPlaying()) {
            posicion = mp.getCurrentPosition();
            mp.pause();
        }
    }
    public void continuar(View v) {
        if (mp != null && mp.isPlaying() == false) {
            mp.seekTo(posicion);
            mp.start();
        }
    }

}
