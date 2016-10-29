package bedoctor.zentech.com.bedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
  private DatabaseReference numberCustomer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
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
    startActivity(new Intent(this, ManagerCheckin.class));
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
}
