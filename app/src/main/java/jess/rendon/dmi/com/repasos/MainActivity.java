package jess.rendon.dmi.com.repasos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //auth tiene tres funciones: sing in, sing up, sing out.
        auth= FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();

        //añade a autenticacion
        //onCompleteListener es para veirificar si esta bien o exitosa todo el email o contraseña.
        auth.createUserWithEmailAndPassword("carolinac98@hotmail.com","11370953").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //task es el que verifica si es exitoso
                if(task.isSuccessful()){
                    //Aqui ya estoy logeado

                    //pedir el usuario actual.pero en formato String
                   String uid=auth.getCurrentUser().getUid();
                   Usuario nuevo_usuario = new Usuario();
                   nuevo_usuario.uid=uid;
                   nuevo_usuario.nombre="Carolina";
                   nuevo_usuario.correo="carolinac98@hotmail.com";
                   nuevo_usuario.pass="11370953";

                   //conseguir la rama
                   db.getReference().child("usuario").child(uid).setValue(nuevo_usuario);


                }else{
                    //aqui no estoy logeado
                    Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();;
                }

            }
        });
    }
}
