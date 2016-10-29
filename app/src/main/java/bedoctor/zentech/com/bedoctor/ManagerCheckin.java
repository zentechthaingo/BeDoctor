package bedoctor.zentech.com.bedoctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import bedoctor.zentech.com.bedoctor.model.Doctor;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerCheckin extends AppCompatActivity {
  private static final String TAG = ManagerCheckin.class.getSimpleName();
  private DatabaseReference numberCustomer;
  private DatabaseReference doctorInfor;
  @Bind(R.id.current_patient_number) TextView currentPatientNumber;
  @Bind(R.id.bt_back) ImageView btBack;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manager_checkin);
    ButterKnife.bind(this);
    numberCustomer = FirebaseDatabase.getInstance().getReference().child("number_checkin");
    btBack.setVisibility(View.GONE);
    listener();
    setUpDoctorInfor();
  }

  private void setUpDoctorInfor() {
    doctorInfor = FirebaseDatabase.getInstance().getReference();
    Doctor doctor = new Doctor();
    doctor.setName("Dr. LE TRI DAT");
    doctor.setDepartment("Plastic Surgery");
    doctor.setBeaconId("edd1ebeac04e5defa017fa2daab6f2c1");
    doctor.setId("d3");
    doctor.setCurrentPatienNumber("1");
    doctor.setLastPatienNumber("0");
    doctorInfor.child("doctor").child(doctor.getId()).setValue(doctor);
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
