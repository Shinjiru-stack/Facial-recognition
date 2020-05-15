package Attend;

import com.toedter.calendar.JDateChooser;

public class ModelPerson {

    private int id;
    private String first_name, last_name, office,image, date,email,contactNum,address,branch,year;
	String dob;

    public ModelPerson() {
    }

   /* public ModelPerson(int id,String first_name,String last_name, String office, String image,String date,String email,String dob,String contact,String address,String branch,String year) { //LastPerson
    	this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.office = office;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.branch = address;
        this.year=year;
        this.date = date;
        this.image = image;
        
    }*/

    public ModelPerson(int id, String first_name, String last_name,String image, String office, String date,String email,String dob,String contactNum,String address,String branch,String year) 
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.office = office;
        this.email = email;
        this.contactNum = contactNum;
        this.address = address;
        this.branch = address;
        this.year=year;
        this.date = date;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
    	return dob;
    }

    public void setDob(String strDate) {
       this.dob = strDate;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
