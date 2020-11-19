/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Model.Voter;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author hayde
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button readButton;
    
   @FXML
    private Button findPartyandHasVoted;

    @FXML
    private Button fNameID;
    
      @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;



    @FXML
    void addVoter(ActionEvent event) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter First Name:");
        String firstName = input.next();
        
        System.out.println("Enter Last Name:");
        String lastName = input.next();
        
        System.out.println("Enter Political Party:");
        String politicalParty = input.next();
        
        System.out.println("Enter If Voted (true or false:");
        boolean hasVoted = input.nextBoolean();
        
        Voter voter = new Voter();
        
        voter.setId(id);
        voter.setFirstname(firstName);
        voter.setLastname(lastName);
        voter.setPoliticalparty(politicalParty);
        voter.setHasvoted(hasVoted);
               
        create(voter);
    }

    @FXML
    void deleteVoter(ActionEvent event) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Voter v = readById(id);
        System.out.println("we are deleting this student: "+ v.toString());
        delete(v);

    }
    
    @FXML
    void readPartyandHasVoted(ActionEvent event) {
        
         Scanner input = new Scanner(System.in);
        
        System.out.println("What party would you like to search?");
        String party = input.next();
        
        System.out.println("What vote status would you like to search (true or false)?");
        boolean hasVoted = input.nextBoolean();
        
       readPartyandHasVoted(party, hasVoted);
                
    }
    
    @FXML
    void fNameID(ActionEvent event) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("What name would you like to search?");
        String fname = input.next();
        
        System.out.println("What ID would you like to search?");
        Integer id = input.nextInt();
        
       readfNameID(fname, id);
    }
       

    
    @FXML
    void readInfo(ActionEvent event) {
        
        Voter voter = new Voter();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Would you like to search by id, first name(type fName), last name(type lName), party, or all?");
        String selection = input.next();
        
        if(selection.equals("id")){
            System.out.println("Enter id");
            int id = input.nextInt();
            
            Voter v = readById(id);
            System.out.println(v.toString());
        
        }
        
        else if(selection.equals("fName")){
            
            System.out.println("Enter first name:");
            String fName = input.next();
        
            List<Voter> v = readByfirstName(fName);
            System.out.println(v.toString());
            
        }
         
        else if(selection.equals("lName")){
            
            System.out.println("Enter last name:");
            String lName = input.next();
        
            List<Voter> v = readBylastName(lName);
            System.out.println(v.toString());
            
        }
        else if(selection.equals("party")){
            
            System.out.println("Enter party:");
            String party = input.next();
        
            List<Voter> v = readByParty(party);
            System.out.println(v.toString());
            
        }
        else if(selection.equals("all")){
            readAll();
        
    }
        
        
        
        

    }

    @FXML
    void updateInfo(ActionEvent event) {
        
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter First Name:");
        String Fname = input.next();
        
        System.out.println("Enter Last Name:");
        String Lname = input.next();
        
        System.out.println("Enter Party:");
        String party = input.next();
        
        System.out.println("Enter IF Voted (true or false):");
        boolean voted = input.nextBoolean();
        
    
        Voter voter = new Voter();
        
        // set properties
        voter.setId(id);
        voter.setFirstname(Fname);
        voter.setLastname(Lname);
        voter.setPoliticalparty(party);
        voter.setHasvoted(voted);
        
              
        update(voter);

    }

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    EntityManager myManager;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myManager = (EntityManager) Persistence.createEntityManagerFactory("HaydenLongFXML_3QuizPU").createEntityManager();

    }
    
     @FXML
    void searchfName(ActionEvent event) {
        
         System.out.println("Clicked");
    }
    
     public void create(Voter voter) {
        try {
            myManager.getTransaction().begin();
            
            if (voter.getId() != null) {
                
                myManager.persist(voter);
                
                myManager.getTransaction().commit();
                
                System.out.println(voter.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     public List<Voter> readAll(){
        Query query = myManager.createNamedQuery("Voter.findAll");
        List<Voter> voters = query.getResultList();

        for (Voter v : voters) {
            System.out.println(v.getId() + " " + v.getFirstname() + " " + v.getLastname()+ " " + v.getPoliticalparty()+ " "+ v.getHasvoted());
        }
        
        return voters;
    }
     
     public Voter readById(int id){
        Query query = myManager.createNamedQuery("Voter.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Voter voter = (Voter) query.getSingleResult();
        if (voter != null) {
            System.out.println(voter.getId() + " " + voter.getFirstname() + " " + voter.getLastname()+ " " + voter.               getPoliticalparty()+ " "+ voter.getHasvoted());
                    }
        
        return voter;
    }
     
      public List readByfirstName(String firstName){
        Query query = myManager.createNamedQuery("Voter.findByFirstname");
        
        // setting query parameter
        query.setParameter("firstname", firstName);
        
        // execute query
               List<Voter> voters = query.getResultList();

        for (Voter v : voters) {
            System.out.println(v.getId() + " " + v.getFirstname() + " " + v.getLastname()+ " " + v.getPoliticalparty()+ " "+ v.getHasvoted());
        }
        
        return voters;
    }
      
      public List readBylastName(String lastName){
        Query query = myManager.createNamedQuery("Voter.findByLastname");
        
        // setting query parameter
        query.setParameter("lastname", lastName);
        
        // execute query
            List<Voter> voters = query.getResultList();

        for (Voter v : voters) {
            System.out.println(v.getId() + " " + v.getFirstname() + " " + v.getLastname()+ " " + v.getPoliticalparty()+ " "+ v.getHasvoted());
        }
        
        return voters;
    
}
       public List readByParty(String politicalParty){
        Query query = myManager.createNamedQuery("Voter.findByPoliticalparty");
        
        // setting query parameter
        query.setParameter("politicalparty", politicalParty);
        
        // execute query
       List<Voter> voters = query.getResultList();

        for (Voter v : voters) {
            System.out.println(v.getId() + " " + v.getFirstname() + " " + v.getLastname()+ " " + v.getPoliticalparty()+ " "+ v.getHasvoted());
        }
        
        return voters;
       }
        
        public void update(Voter model) {
        try {

            Voter existingVoter = myManager.find(Voter.class, model.getId());

            if (existingVoter != null) {
                // begin transaction
                myManager.getTransaction().begin();
                
                // update all atttributes
                existingVoter.setId(model.getId());
                existingVoter.setFirstname(model.getFirstname());
                existingVoter.setLastname(model.getLastname());
                existingVoter.setPoliticalparty(model.getPoliticalparty());
                existingVoter.setHasvoted(model.getHasvoted());
                
                // end transaction
                myManager.getTransaction().commit();
                
                System.out.println(existingVoter.toString() + " is updated");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void delete(Voter voter) {
        try {
            Voter existingVoter = myManager.find(Voter.class, voter.getId());

       
            if (existingVoter != null) {
                
  
                myManager.getTransaction().begin();
                
    
                myManager.remove(existingVoter);
                
      
                myManager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
        public List readPartyandHasVoted(String party, boolean hasvoted){
            Query query = myManager.createNamedQuery("Voter.readPartyandHasVoted");
       
            query.setParameter("politicalparty", party);
            query.setParameter("hasvoted", hasvoted);
        
        
            List<Voter> voters =  query.getResultList();
            for (Voter v: voters) {
             System.out.println(v.getId() + " " + v.getFirstname() + " " + v.getLastname()+ " " + v.getPoliticalparty()+ " "+ v.getHasvoted());
        }
        
        return voters;
        }
        
        public List readfNameID(String firstname, Integer id){
            Query query = myManager.createNamedQuery("Voter.readfNameID");
       
            query.setParameter("firstname", firstname);
            query.setParameter("id", id);
        
        
            List<Voter> voters =  query.getResultList();
            for (Voter v: voters) {
             System.out.println(v.getId() + " " + v.getFirstname() + " " + v.getLastname()+ " " + v.getPoliticalparty()+ " "+ v.getHasvoted());
        }
        
        return voters;
        }
        
}



