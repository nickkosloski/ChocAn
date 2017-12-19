package Models;

import java.util.List;

public class ProviderReport {
    private String fName;
    private String lName;
    private String number;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private List<ProviderService> serviceList;
    private int numConsultations = 0;
    private Double totalFee;

    public int getNumConsultations() {
        return serviceList.size();
    }
    public Double getTotalFee() {
        for(ProviderService service : serviceList)
            totalFee += service.getServiceFee();
        return totalFee;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String FName) {
        this.fName = FName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String LName) {
        this.lName = LName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<ProviderService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ProviderService> serviceList) {
        this.serviceList = serviceList;
    }

}
