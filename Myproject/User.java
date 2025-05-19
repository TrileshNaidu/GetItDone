package Myproject;

public class User {
    private String id;
    private String name;
    private String password;
    private String gender;
    private int age;
    private String mobileNumber;
    private String location;
    private String pinCode;
    private String upiIdOrMobile;
    private boolean isServiceProvider;

    public User(String id, String name, String password, String gender, int age, String mobileNumber, String location,
                String pinCode, String upiIdOrMobile, boolean isServiceProvider) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.location = location;
        this.pinCode = pinCode;
        this.upiIdOrMobile = upiIdOrMobile;
        this.isServiceProvider = isServiceProvider;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getMobileNumber() { return mobileNumber; }
    public String getLocation() { return location; }
    public String getPinCode() { return pinCode; }
    public String getUpiIdOrMobile() { return upiIdOrMobile; }
    public boolean isServiceProvider() { return isServiceProvider; }
}