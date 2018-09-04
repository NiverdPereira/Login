package e.user.login;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressDialog pdialog;
    private String email, password;

    EditText user;
    EditText pass;
    Button login;
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        pdialog=new ProgressDialog(this);

        user= (EditText)findViewById(R.id.editText);
        pass= (EditText)findViewById(R.id.editText2);
        login =(Button)findViewById(R.id.button);
        sign =(Button)findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    void signIn(){
        email=user.getText().toString().trim();
        password=pass.getText().toString().trim();
        pdialog.setMessage("Please wait");
        pdialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser use=mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Auth failed",Toast.LENGTH_SHORT).show();
                }
                pdialog.dismiss();

            }
        });
    }
}
