package bedoctor.zentech.com.bedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import bedoctor.zentech.com.bedoctor.model.Doctor;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
  private DatabaseReference numberCustomer;
  private DatabaseReference doctorInfor;
  @Bind(R.id.bt_back) ImageView btBack;
  @Bind(R.id.doctor_name) TextView nameDoctor;
  @Bind(R.id.department) TextView department;
  @Bind(R.id.limit_patient) TextView limitPatient;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    btBack.setVisibility(View.GONE);
  }

  private void setUpDoctorInfor() {
    doctorInfor = FirebaseDatabase.getInstance().getReference();
    Doctor doctor = new Doctor();

    UUID uuid = UUID.randomUUID();
    doctor.setId(uuid.toString());

    if (nameDoctor.getText() != null){
      doctor.setName(nameDoctor.getText().toString());
    }

    if (department.getText() != null){
      doctor.setDepartment(department.getText().toString());
    }

    if (limitPatient.getText() != null ){
      doctor.setLimitPatient(limitPatient.getText().toString());
    }

    doctorInfor.child("doctor").child(doctor.getId()).setValue(doctor);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    numberCustomer = FirebaseDatabase.getInstance().getReference();
    setNumberCustomer();
    return true;
  }

  private void setNumberCustomer() {
    NumberCheckin numberCheckin = new NumberCheckin();
    numberCheckin.setCurrentCheckinNumber("0");
    numberCheckin.setLastCheckinNumber("0");
    numberCustomer.child("number_checkin").setValue(numberCheckin);
  }

  @OnClick(R.id.ok)  public void okButton(){
    setUpDoctorInfor();
    startActivity(new Intent(this, ManagerCheckin.class));
  }

  @OnClick(R.id.bt_back) public void onBackPress(){
    onBackPressed();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int i = item.getItemId();
    if (i == R.id.refesh_number_checkin) {
      NumberCheckin numberCheckin = new NumberCheckin();
      numberCheckin.setCurrentCheckinNumber("0");
      numberCheckin.setLastCheckinNumber("0");
      numberCustomer.child("number_checkin").setValue(numberCheckin);
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  public DatabaseReference getDoctorInfor() {
    return doctorInfor;
  }

  public void setDoctorInfor(DatabaseReference doctorInfor) {
    this.doctorInfor = doctorInfor;
  }
}
