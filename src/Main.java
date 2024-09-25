/*
 * 
 * Project Structure
 * CampusNest/
│
├── src/
│   ├── Main.java
│   ├── models/
│   │   ├── User.java
│   │   └── PropertyOwner.java
│   ├── services/
│   │   └── RegistrationService.java
│   └── utils/
│       └── InputHelper.java
│
├── .gitignore
├── README.md
└── CampusNest.iml
 */


 import models.User;
 import models.PropertyOwner;
 import services.RegistrationService;
 import utils.InputHelper;
 
 public class Main {
 
     public static void main(String[] args) {
         System.out.println("Welcome to CampusNest!");
         
         RegistrationService registrationService = new RegistrationService();
         boolean isRunning = true;
 
         while (isRunning) {
             System.out.println("\n--- CampusNest Main Menu ---");
             System.out.println("1. Register as a Normal User");
             System.out.println("2. Register as a Property Owner");
             System.out.println("3. Exit");
             System.out.print("Choose an option: ");
 
             int choice = InputHelper.readInt();
 
             switch (choice) {
                 case 1:
                     registrationService.registerUser();
                     break;
                 case 2:
                     registrationService.registerPropertyOwner();
                     break;
                 case 3:
                     isRunning = false;
                     System.out.println("Exiting CampusNest. Goodbye!");
                     break;
                 default:
                     System.out.println("Invalid choice! Please try again.");
                     break;
             }
         }
     }
 }