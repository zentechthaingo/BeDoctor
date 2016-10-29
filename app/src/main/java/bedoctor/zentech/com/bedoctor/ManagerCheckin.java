package bedoctor.zentech.com.bedoctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ManagerCheckin extends AppCompatActivity {
  private static final String TAG = ManagerCheckin.class.getSimpleName();
  private DatabaseReference numberCustomer;
  @Bind(R.id.current_patient_number) TextView currentPatientNumber;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manager__checkin);
    ButterKnife.bind(this);
    numberCustomer = FirebaseDatabase.getInstance().getReference().child("number_checkin");
    numberCustomer.child("number_checkin")
        .child("currentPatientNumber").getDatabase();

    listener();
  }

  @OnClick(R.id.bt_back) public void onBackPress(){
    onBackPressed();
  }

  private void listener() {
    ValueEventListener numberListener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        NumberCheckin numberCheckin = dataSnapshot.getValue(NumberCheckin.class);
        currentPatientNumber.setText(numberCheckin.getCurrentCheckinNumber());
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
      }
    };
    numberCustomer.addValueEventListener(numberListener);
  }
}
