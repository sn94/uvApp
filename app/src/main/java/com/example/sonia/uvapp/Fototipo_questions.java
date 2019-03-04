package com.example.sonia.uvapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Fototipo_questions extends AppCompatActivity {

    
    int score= 0;
    String[] questions= null;
    int[] questions_options= new int[]{ R.array.q1_options, R.array.q2_options, R.array.q3_options,
                                R.array.q4_options, R.array.q5_options, R.array.q6_options,
                                R.array.q7_options};
    int questionIndex= 0;


    //views
    TextView tquestion;
    Button fq_ant, fq_sig;
    LinearLayout list_opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_questions);

        questions = getResources().getStringArray(  R.array.test_questions);

        //views
       crear_views();

        load_questions();
    }
    

    void crear_views(){
        tquestion= (TextView) findViewById(R.id.fq_question_text );
        fq_ant= (Button) findViewById(R.id.fq_ant);
        fq_sig= (Button) findViewById(R.id.fq_sig);
        list_opc= (LinearLayout) findViewById(R.id.fq_list_opc);

        fq_ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( questionIndex >= 0 )
                questionIndex= questionIndex -1;

                if( questionIndex== 0){
                    fq_ant.setVisibility( View.INVISIBLE);
                }
                if( questionIndex == ( questions.length -2 ))  fq_sig.setVisibility( View.VISIBLE);
                load_questions();
            }
        });
        fq_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( questionIndex < (questions.length - 1) )
                    questionIndex= questionIndex + 1;

                if( questionIndex == 1) fq_ant.setVisibility( View.VISIBLE );
                if( questionIndex == ( questions.length -1 ))  fq_sig.setVisibility( View.INVISIBLE);

                load_questions();
            }
        });

    }
    void load_questions(){
        tquestion.setText(  questions[  questionIndex ] );
        //lista de opciones
        String[] stringArray =  getResources().getStringArray( questions_options[ questionIndex] );

        //Crear botones de opciones
        list_opc.removeAllViews();
    for( String op : stringArray ){
        RadioButton rb= new RadioButton( this );
        rb.setText( op );
        list_opc.addView( rb);

    }
    }





}

