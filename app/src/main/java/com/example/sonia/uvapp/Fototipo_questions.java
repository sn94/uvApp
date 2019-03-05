package com.example.sonia.uvapp;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Fototipo_questions extends AppCompatActivity {

    
    int score= 0;
    int score_temp= 0;

    String[] questions= null;
    int[] questions_options= new int[]{ R.array.q1_options, R.array.q2_options, R.array.q3_options,
                                R.array.q4_options, R.array.q5_options, R.array.q6_options,
                                R.array.q7_options};
    int[] opcs_weights= new int[]{ R.array.w1_options, R.array.w2_options, R.array.w3_options,
            R.array.w4_options, R.array.w5_options, R.array.w6_options,
            R.array.w7_options};

    int questionIndex= 0;


    //views
    TextView tquestion;
    Button fq_ant, fq_sig, finish_b;
    RadioGroup list_opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_questions);

        questions = getResources().getStringArray(  R.array.test_questions);

        //views
       crear_views();

        load_questions();
    }





    void previous_button_action(View v){
        if( questionIndex >= 0 )
            questionIndex= questionIndex -1;

        if( questionIndex== 0){
            fq_ant.setVisibility( View.INVISIBLE);
        }
        if( questionIndex == ( questions.length -2 )){
            fq_sig.setVisibility( View.VISIBLE);
            finish_b.setVisibility( View.INVISIBLE);
        }
        load_questions();
    }

    void forward_button_action(View v){
        int vv=list_opc.getCheckedRadioButtonId();
        Log.i("checked rb", vv + "  ");
        incr_score( v);

        if( questionIndex < (questions.length - 1) )
            questionIndex= questionIndex + 1;

        if( questionIndex == 1) fq_ant.setVisibility( View.VISIBLE );
        if( questionIndex == ( questions.length -1 )){

            fq_sig.setVisibility( View.INVISIBLE);
            finish_b.setVisibility(View.VISIBLE);

        }

        load_questions();
    }





    void crear_views(){
        tquestion= (TextView) findViewById(R.id.fq_question_text );
        fq_ant= (Button) findViewById(R.id.fq_ant);
        fq_sig= (Button) findViewById(R.id.fq_sig);
        finish_b= (Button) findViewById( R.id.fq_check);
        list_opc= (RadioGroup) findViewById(R.id.fq_list_opc);

    }



    void load_questions(){
        tquestion.setText(  questions[  questionIndex ] );

        //Crear botones de opciones
        //lista de opciones
        String[] stringArray =  getResources().getStringArray( questions_options[ questionIndex] );
        list_opc.removeAllViews();
        for( int x=0; x< stringArray.length; x++ ){
            RadioButton rb= new RadioButton( this );
            rb.setText( stringArray[x] );
            final int z= x;
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registrar_score(   z   );
                }
            });
            list_opc.addView( rb); }
    }




    void registrar_score( int indice_opcion_respuesta){

        int[] intArray = getResources().getIntArray(opcs_weights[questionIndex]);
        score_temp= intArray[ indice_opcion_respuesta ];
        Log.i("AHORA PESO ES " , "  "+ score_temp);
    }

    void incr_score( View v){

        score= score + score_temp;
        Log.i("acumulado es  " , "  "+ score);
    }


}

