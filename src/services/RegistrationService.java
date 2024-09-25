package services;

import models.User;
import models.PropertyOwner;
import utils.InputHelper;

public class RegistrationService {

    public void registerUser() {
        System.out.println("\n--- Register as Normal User ---");
        String name = InputHelper.readString("Enter your name: ");
        String email = InputHelper.readString("Enter your email: ");
        String password = InputHelper.readString("Enter your password: ");

        User user = new User(name, email, password);
        System.out.println("Registration successful: " + user);
    }

    public void registerPropertyOwner() {
        System.out.println("\n--- Register as Property Owner ---");
        String name = InputHelper.readString("Enter your name: ");
        String email = InputHelper.readString("Enter your email: ");
        String password = InputHelper.readString("Enter your password: ");
        String propertyDetails = InputHelper.readString("Enter property details: ");

        PropertyOwner propertyOwner = new PropertyOwner(name, email, password, propertyDetails);
        System.out.println("Registration successful: " + propertyOwner);
    }
}