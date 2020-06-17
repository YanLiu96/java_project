package customersManagement;

public class Customers {
	private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    
    public Customers(int id, String firstName, String lastName,String phone,String address,String city,String state,String postalCode)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address=address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastNAme()
    {
        return lastName;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public String getAddress()
    {
        return address;
    }
    public String getCity()
    {
        return city;
    }
    public String getState()
    {
        return state;
    }
    public String getPostalCode()
    {
        return postalCode;
    }
}
