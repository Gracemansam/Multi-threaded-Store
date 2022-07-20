package services;

import interfaces.iManager;
import models.Cashier;

public class ManagerServiceImpl implements iManager {
    @Override
    public void hireCashier(String ApplicantName, int ApplicantAge ,   Cashier cashier) {

        String CashierID = "CAS";
        //Splits the Applicant Name to get the FirstName
        String[] IDGENERATOR = ApplicantName.split(" ");

        if (ApplicantAge >= 21 ){
    //        System.out.print("Hello " + ApplicantName + " You Are Applying for the Role Of A manager,  \nYour Role Is: \n1. Sell Products To Customers \n2. Print Invoices \nCan you perform these Roles Effectively? (Yes/No) : ");
          //  String ApplicantReply = input.next();


                CashierID = CashierID +"-"+ IDGENERATOR[0].toUpperCase();

                cashier.setId(CashierID);
                cashier.setName(ApplicantName);
                cashier.setAge(String.valueOf(ApplicantAge));

                System.out.println("Congratulations " + ApplicantName + " You Have Been Hired As A Cashier" + " Here is Your ID: " + CashierID);

        }else {
            System.out.println("Sorry, We Cannot Hire You At The moment :( ");
        }

    }
}
