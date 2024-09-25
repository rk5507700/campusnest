package models;

public class PropertyOwner extends User {
    private String propertyDetails;

    public PropertyOwner(String name, String email, String password, String propertyDetails) {
        super(name, email, password);
        this.propertyDetails = propertyDetails;
    }

    public String getPropertyDetails() {
        return propertyDetails;
    }

    @Override
    public String toString() {
        return "PropertyOwner [Name=" + getName() + ", Email=" + getEmail() + ", Property Details=" + propertyDetails + "]";
    }
}