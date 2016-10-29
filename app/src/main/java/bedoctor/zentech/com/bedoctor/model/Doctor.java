package bedoctor.zentech.com.bedoctor.model;

public class Doctor {
  private String name;
  private String department;
  private String limitPatient;
  private String id;
  private String beaconId;
  private String lastPatienNumber;
  private String currentPatienNumber;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getLimitPatient() {
    return limitPatient;
  }

  public void setLimitPatient(String limitPatient) {
    this.limitPatient = limitPatient;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBeaconId() {
    return beaconId;
  }

  public void setBeaconId(String beaconId) {
    this.beaconId = beaconId;
  }

  public String getLastPatienNumber() {
    return lastPatienNumber;
  }

  public void setLastPatienNumber(String lastPatienNumber) {
    this.lastPatienNumber = lastPatienNumber;
  }

  public String getCurrentPatienNumber() {
    return currentPatienNumber;
  }

  public void setCurrentPatienNumber(String currentPatienNumber) {
    this.currentPatienNumber = currentPatienNumber;
  }
}
