import java.util.Objects;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Students{
    String name;
    int RollNo;
    float cgpa;
    String branch;
    String RegistrationTime;
    boolean sRegistered;
    boolean applied;
    boolean isPlaced;
    boolean isBlocked;

    Company[] availableComanies;
    Company[] appliedCompanies;
    int noNoOfAppliedCompanies = 0;
    int noOfAvailableCompanies = 0;

    Company OfferedCompany = new Company();
    Company PlacedCompany = new Company();


    Students(String sname, int sroll, float scg, String sbranch){
        this.name = sname;
        this.RollNo = sroll;
        this.cgpa = scg;
        this.branch = sbranch;
        this.sRegistered = false;
        this.applied = false;
        this.isBlocked = false;
        this.isPlaced = false;
        this.OfferedCompany.CompanyPackage = 0;
        this.PlacedCompany.CompanyPackage = 0;
        this.availableComanies = new Company[1000];
        this.appliedCompanies = new Company[1000];

    }
    public void getStatus(){
        if(!this.isBlocked){
        if(this.OfferedCompany.CompanyPackage == 0){
            System.out.println("Sorry, you have not been offered yet");
        }
        else{
            System.out.println("You have been offered by " + this.OfferedCompany.CompanyName);
            System.out.println("Please accept the offer.");

        }}
        else{
            System.out.println("You have been blocked by the placement cell");
        }
    }

    public void acceptOffer(){
        this.isPlaced = true;
    }

    public void rejectOffer(){
        if(this.applied && !this.isPlaced){
            this.isBlocked = true;
        }
    }

}

class IIITDPlacementCell{
    private static String CompanyRegistrationStartTime;
    private static String CompanyRegistrationEndTime;
    private static String StudentRegistrationStartTime;
    private static String StudentRegistrationEndTime;

    public static  void setCompanyRegistrationStartTime(String n){
        CompanyRegistrationStartTime = n;
    }

    public static  void setCompanyRegistrationEndTime(String n){
        CompanyRegistrationEndTime = n;
    }

    public static  void setStudentRegistrationStartTime(String n){
        StudentRegistrationStartTime = n;
    }

    public static  void setStudentRegistrationEndTime(String n){
        StudentRegistrationEndTime = n;
    }

    public static String getCompanyRegistrationStartTime(){
        return CompanyRegistrationStartTime;
    }
    public static String getCompanyRegistrationEndTime(){
        return CompanyRegistrationEndTime;
    }
    public static String getStudentRegistrationStartTime(){
        return StudentRegistrationStartTime;
    }
    public static String getStudentRegistrationEndTime(){
        return StudentRegistrationEndTime;
    }


    public static void  NoOfStudentsRegistration(Students[] slist, int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
            if(slist[i].sRegistered){
                counter++;
            }
        }
        System.out.println("Number of registered students are : " + Integer.toString(counter));
    }

    public static void  NoOfCompanyRegistration(Company[] slist, int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
            if(slist[i].CompanyRegistered){
                counter++;
            }
        }
        System.out.println("Number of registered companies are : " + Integer.toString(counter));
    }

    public static void statistics(Students[] slist, int n){
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        System.out.println("Details of the blocked students are :");
        for(int i = 0; i < n; i++){
            if(slist[i].isBlocked && slist[i].sRegistered){
                counter1++;
                System.out.println("Name : " + slist[i].name);
                System.out.println("RollNo : " + Integer.toString(slist[i].RollNo));
                System.out.println("Branch : " + slist[i].branch);
                System.out.println("CGPA : "+ Float.toString(slist[i].cgpa));
                System.out.println("--");
            }
        }

        System.out.println("No of blocked students are : " + Integer.toString(counter1));
        System.out.println("Details of the placed students are :");
        for(int i = 0; i < n; i++){
            if(slist[i].isPlaced && slist[i].sRegistered){
                counter2++;
                System.out.println("Name : " + slist[i].name);
                System.out.println("RollNo : " + Integer.toString(slist[i].RollNo));
                System.out.println("Branch : " + slist[i].branch);
                System.out.println("CGPA : "+ Float.toString(slist[i].cgpa));
                System.out.println("--");
            }
        }

        System.out.println("No of placed students are : " + Integer.toString(counter2));
        System.out.println("Details of the unplaced students are :");
        for(int i = 0; i < n; i++){
            if(!slist[i].isPlaced && !slist[i].isBlocked && slist[i].sRegistered){
                counter3++;
                System.out.println("Name : " + slist[i].name);
                System.out.println("RollNo : " + Integer.toString(slist[i].RollNo));
                System.out.println("Branch : " + slist[i].branch);
                System.out.println("CGPA : "+ Float.toString(slist[i].cgpa));
                System.out.println("--");
            }
        }
        System.out.println("No of unplaced students are : " + Integer.toString(counter3));
    }

    public static void AveragePackage(Students[] slist, int n){
        float av = 0;
        int counter = 0;
        for(int i = 0; i < n; i++){
            if(slist[i].sRegistered){
                if(!slist[i].isBlocked){
                 av = av + slist[i].PlacedCompany.CompanyPackage;
                }
                counter++;
            }
        }
        System.out.println("Average package of the college is : " + Float.toString(av/counter));
    }

    public static void getCompanyDetails(String companyName, Company[] c, int n){
        for(int i = 0; i < n; i++){
            if(c[i].CompanyRegistered && Objects.equals(c[i].CompanyName, companyName)){
                System.out.println("Details of the company are ");

                System.out.println("CompanyName: " + c[i].CompanyName );
                System.out.println("Company role offering: "+ c[i].CompanyRole );
                System.out.println("Company Package:" + Float.toString(c[i].CompanyPackage));
                System.out.println("Company CGPA criteria:" + Float.toString(c[i].MinimumCgpa));

                if(c[i].NoOfSelectedStudents != 0){
                    System.out.println("Details of the offered students are ");
                    Random a = new Random();
                    int a1;
                    if(c[i].NoOfSelectedStudents == 1){
                         a1 = 0;
                    }
                    else{
                         a1 = a.nextInt(c[i].NoOfSelectedStudents - 1);

                    }

                    for(int j = 0; j <= a1; j++){
                        System.out.println("Name : " + c[i].slist[j].name);
                        System.out.println("RollNo : " + Integer.toString(c[i].slist[j].RollNo));
                        System.out.println("Branch : " + c[i].slist[j].branch);
                        System.out.println("CGPA : "+ Float.toString(c[i].slist[j].cgpa));
                        System.out.println("--");
                        if(c[i].CompanyPackage >= c[i].slist[j].OfferedCompany.CompanyPackage){
                                c[i].slist[j].OfferedCompany = c[i];

                        }
                    }
                }

                break;
            }
        }
    }

    public static void getStudentDetails(String name, int r, Students[] s, int n, Company[] c, int m){

        for(int i11 = 0; i11 < n; i11++){
            if(Objects.equals(s[i11].name, name) && s[i11].RollNo == r){
                System.out.println("Names of the company you applied for : ");
                for(int j = 0; j < s[i11].noNoOfAppliedCompanies; j++){
                    System.out.println(Integer.toString(j + 1) + ". " + s[i11].appliedCompanies[j].CompanyName );
                }
                System.out.println("Names of the company you didn't apply for :");
                for(int j1 = 0; j1 < m; j1++){
                    int f = 0;
                    for(int j = 0; j < s[i11].noNoOfAppliedCompanies; j++){
                        if(c[j1].CompanyRegistered && Objects.equals(c[j1].CompanyName,s[i11].appliedCompanies[j].CompanyName)){
                            f = 1;
                            break;
                        }
                    }
                    if(f == 0){
                        System.out.println(c[j1].CompanyName);
                    }
                }
                break;

            }
        }



    }

    public static void getCompanyResults(String companyName, Company[] c, int n){
        for(int i = 0; i < n; i++){
            if(c[i].CompanyRegistered && Objects.equals(c[i].CompanyName, companyName)){
                if(c[i].NoOfShortListed != 0){
                System.out.println("Details of the students selected by the company are : ");
                for(int j = 0; j < c[i].NoOfShortListed; j++){
                System.out.println(c[i].ShortListed[j].name + " " + Integer.toString(c[i].ShortListed[j].RollNo));
                }}
                else{
                    System.out.println("You have not selected any student");
                }
                break;
            }
        }
    }

    public static void updateCgpa(Students s, float oc, float nc){
        if(oc == s.cgpa){
            s.cgpa = nc;
        }
        else{
            System.out.println("Old cgpa is incorrect");
        }


    }
}
class Company{
    String CompanyName;

    String CompanyRole;
    float CompanyPackage;
    float MinimumCgpa;
    String CompanyRegistrationDateTime;
    int NoOfSelectedStudents = 0;
    Students[] slist;
    Students[] ShortListed;
    int NoOfShortListed = 0;
    boolean CompanyRegistered;

    Company(){}
    Company(String cname, String crole, float cpackage, float cmincgpa){
        this.CompanyName = cname;
        this.CompanyPackage = cpackage;
        this.CompanyRole = crole;
        this.MinimumCgpa = cmincgpa;
        this.CompanyRegistered = false;
        this.slist = new Students[1000];
        this.ShortListed = new Students[1000];
    }

    public void getSelectedStudents(Students[] studentList){
        //CODE
    }
    public void setCompanyRegistrationDateTime(String companyRegistrationDateTime) {
        this.CompanyRegistrationDateTime = companyRegistrationDateTime;
    }

    public void setCompanyRole(String companyRole) {
        this.CompanyRole = companyRole;
    }

    public void setMinimumCgpa(float minimumCgpa) {
        this.MinimumCgpa = minimumCgpa;
    }

    public void setCompanyPackage(float companyPackage) {
        this.CompanyPackage = companyPackage;
    }


}
public class FutureBuilder{
    public static void main(String[] args) {
        Company[] companies = new Company[1000];
        Students[] StudentList = new Students[1000];

        int noOfCompanies = 0;
        int noOfStudents = 0;




        Scanner sc = new Scanner(System.in);


        while(true){
            System.out.println("Welcome to FutureBuilder");
            System.out.println("Type: 'Enter FutureBuilder' to enter the Application");
            System.out.println("Type: 'Exit FutureBuilder' to exit the Application");
            String option = sc.nextLine();
//            System.out.println(option);

            while(Objects.equals(option, "Enter FutureBuilder")){
                
            System.out.println("Choose the mode you want to enter in:");
            System.out.println("Press [1] : Enter as Student Mode");
            System.out.println("Press [2] : Enter as Company Mode");
            System.out.println("Press [3] : Enter as Placement Cell Mode");
            System.out.println("Press [4] : Return To Main Application");

            int c1 = sc.nextInt();

            if(c1 == 1){
                int y1 = 1;
                while(y1 == 1){
                    System.out.println("Choose the Student Query to perform");
                    System.out.println("Press [1] : Enter as a Student(Give Student Name, and Roll No.)");
                    System.out.println("Press [2] : Add students");
                    System.out.println("Press [3] : Back");
                    int c11 = sc.nextInt();
                    if(c11 == 1){
                        int flag = 0;
                        sc.nextLine();
                        System.out.println("Enter Student's Name : ");
                        String ssname = sc.nextLine();
                        System.out.println("Enter Student's RollNumber : ");
                        int ssroll = sc.nextInt();
                        Students testStudent = null;

                        for(int i11 = 0; i11 < noOfStudents; i11++){
                            if(Objects.equals(StudentList[i11].name, ssname) && StudentList[i11].RollNo == ssroll){
                                testStudent = StudentList[i11];
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 0){
                            System.out.println("Student not found");
                        }
                        else{
                            int y11 = 1;
                            while(y11 == 1){
                                System.out.println("Welcome " + testStudent.name + "!!");
                                System.out.println("Press [1] : Register For Placement Drive ");
                                System.out.println("Press [2] : Register For Company");
                                System.out.println("Press [3] : Get All available companies");
                                System.out.println("Press [4] : Get Current Status");
                                System.out.println("Press [5] : Update CGPA");
                                System.out.println("Press [6] : Accept offer");
                                System.out.println("Press [7] : Reject offer");
                                System.out.println("Press [8] : Back");
                                int c111 = sc.nextInt();
                                if(c111 == 1){

                                    LocalDateTime localDate = LocalDateTime.now();
                                    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd/MM/YYYY, hh:mm");
                                    String dt = dateformatter.format(localDate);

                                    if(dt.compareTo(IIITDPlacementCell.getStudentRegistrationStartTime()) < 0 || dt.compareTo(IIITDPlacementCell.getStudentRegistrationEndTime()) > 0 ){
                                        System.out.println("Sorry!!! You cannot register");
                                        System.out.println("Either registration period for students has ended or not started");
                                     }
                                    else{
                                    testStudent.sRegistered = true;
                                    testStudent.RegistrationTime = dt;
                                    System.out.println( testStudent.name + " Registered for the Placement Drive at IIITD at "+ dt);
                                    System.out.println("Your details are : ");
                                    System.out.println("Name : " + testStudent.name);
                                    System.out.println("RollNo : " + Integer.toString(testStudent.RollNo));
                                    System.out.println("Branch : " + testStudent.branch);
                                    System.out.println("CGPA : "+ Float.toString(testStudent.cgpa));}
                                }
                                else if(c111 == 2){
                                    if(testStudent.sRegistered){
                                        sc.nextLine();
                                    System.out.println("Enter the company's name : ");
                                    String rcompany = sc.nextLine();



                                    int f1 = 0;

                                    for(int i33 = 0; i33 < testStudent.noOfAvailableCompanies; i33++){
                                        if(Objects.equals(testStudent.availableComanies[i33].CompanyName, rcompany)){
                                            if(!testStudent.isBlocked){
                                                if(testStudent.availableComanies[i33].CompanyPackage >= 3*(testStudent.OfferedCompany.CompanyPackage)){
                                                    testStudent.availableComanies[i33].slist[testStudent.availableComanies[i33].NoOfSelectedStudents] = testStudent;
                                                    testStudent.availableComanies[i33].NoOfSelectedStudents++;
                                                    testStudent.applied = true;
                                                    testStudent.appliedCompanies[testStudent.noNoOfAppliedCompanies] = testStudent.availableComanies[i33];
                                                    testStudent.noNoOfAppliedCompanies++;
                                                    System.out.println("Successfully registered for "+testStudent.availableComanies[i33].CompanyRole + " at "+ testStudent.availableComanies[i33].CompanyName);

                                                }
                                                else{
                                                    System.out.println("You cannot apply to this company as per the placement's cell rule");
                                                }

                                            }

                                            else if(testStudent.isBlocked){
                                                System.out.println("You have been blocked by the placement cell");
                                                System.out.println("You cannot apply to any comapny");
                                            }

                                            f1 = 1;
                                            break;
                                        }
                                    }

                                    if(f1 == 0){
                                        System.out.println("Either you have misspelled the name or you cannot apply in this company");
                                    }
                                    }
                                    else{
                                        System.out.println("You need to register yourself first");
                                    }

                                }
                                else if(c111 == 3){

                                    for(int i33 = 0; i33 < noOfCompanies; i33++){
                                        if(companies[i33].CompanyRegistered){
                                            if(testStudent.cgpa >= companies[i33].MinimumCgpa){
                                                testStudent.availableComanies[testStudent.noOfAvailableCompanies] = companies[i33];
                                                testStudent.noOfAvailableCompanies++;
                                            }
                                        }
                                    }
                                    if(testStudent.noOfAvailableCompanies == 0){
                                        System.out.println("You are not eligible for any company");
                                    }
                                    else{
                                        System.out.println("List of available companies are ");
                                        for(int i33 = 0; i33 < testStudent.noOfAvailableCompanies; i33++){
                                            System.out.println("CompanyName: " + testStudent.availableComanies[i33].CompanyName );
                                            System.out.println("Company role offering: "+ testStudent.availableComanies[i33].CompanyRole );
                                            System.out.println("Company Package:" + Float.toString(testStudent.availableComanies[i33].CompanyPackage));
                                            System.out.println("Company CGPA criteria:" + Float.toString(testStudent.availableComanies[i33].MinimumCgpa));
                                            System.out.println("---");


                                        }
                                    }

                                }
                                else if(c111 == 4){
                                    testStudent.getStatus();
                                }
                                else if(c111 == 5){
                                    System.out.println("Enter your old cgpa : ");
                                    float ocg = sc.nextFloat();
                                    System.out.println("Enter youe new cgpa : ");
                                    float ncg = sc.nextFloat();

                                    IIITDPlacementCell.updateCgpa(testStudent,ocg, ncg);

                                }
                                else if(c111 == 6){
                                    if(testStudent.isPlaced){
                                        Students[] newArray = new Students[1000];
                                        int cr = 0;
                                        for(int i234 = 0; i234 < testStudent.PlacedCompany.NoOfShortListed; i234++){
                                            if(Objects.equals(testStudent.PlacedCompany.ShortListed[i234].name, testStudent.name) && testStudent.PlacedCompany.ShortListed[i234].RollNo == testStudent.RollNo){
                                            }
                                            else{
                                                newArray[cr] = testStudent.PlacedCompany.ShortListed[i234];
                                                cr++;
                                            }
                                        }
                                        testStudent.PlacedCompany.ShortListed = newArray;
                                        testStudent.PlacedCompany.NoOfShortListed--;

                                    }
                                    testStudent.acceptOffer();
                                    testStudent.PlacedCompany = testStudent.OfferedCompany;
                                    testStudent.PlacedCompany.ShortListed[testStudent.OfferedCompany.NoOfShortListed] = testStudent;
                                    testStudent.PlacedCompany.NoOfShortListed++;
                                    System.out.println("Congratulations" + testStudent.name + "You have accepted the offer by " + testStudent.PlacedCompany.CompanyName);


                                }
                                else if(c111 == 7){
                                    testStudent.rejectOffer();
                                    System.out.println("You have rejected the offer by "+ testStudent.OfferedCompany.CompanyName);
                                }
                                else{
                                    y11 = 0;
                                }
                            }
                        }




                    }
                    else if(c11 == 2){

                        System.out.println("Enter number of students to add");
                        int scounter = sc.nextInt();

                        sc.nextLine();
                        for(int i3 = 0; i3 < scounter; i3++){

                            System.out.println("Enter Student's Name : ");
                            String sname = sc.nextLine();

                            System.out.println("Enter Student's Roll Number : ");
                            int sroll = sc.nextInt();

                            System.out.println("Enter Student's CGPA : ");
                            float scgpa = sc.nextFloat();
                            sc.nextLine();
                            System.out.println("Enter Student's Branch : ");
                            String sbranch = sc.nextLine();

                            System.out.println("Details successfully entered");



                            Students newStudent = new Students(sname, sroll, scgpa, sbranch);
                            StudentList[noOfStudents] = newStudent;
                            noOfStudents++;

                        }


                    }
                    else{
                        y1 = 0;
                    }

                }

            }
            else if (c1 == 2) {
                int y2 = 1;
                while(y2 == 1){
                    System.out.println("Choose the Company Query to perform");
                    System.out.println("Press [1] : Add Company and Details");
                    System.out.println("Press [2] : Choose Company");
                    System.out.println("Press [3] : Get Available Companies");
                    System.out.println("Press [4] : Back");
                    int c12 = sc.nextInt();
                    if(c12 == 1){
                        sc.nextLine();
                        System.out.println("Enter name of the Company");
                        String cyname = sc.nextLine();
                        System.out.println("Enter role offered in the Company");
                        String cyrole = sc.nextLine();
                        System.out.println("Enter package offered by the Company in LPA");
                        float cypackage = sc.nextFloat();
                        System.out.println("Enter the CGPA criteria of the company");
                        float cycgpa = sc.nextFloat();
                        Company newCompany = new Company(cyname,cyrole,cypackage,cycgpa);
                        companies[noOfCompanies] = newCompany;
                        noOfCompanies++;
                    }
                    else if(c12 == 2){
                        System.out.println("The available companies are : ");
                        for(int i = 0; i < noOfCompanies; i++){
                            System.out.println(Integer.toString(i + 1) + " " + companies[i].CompanyName);
                        }

                        System.out.println("Enter the company number");
                        int Cchoice = sc.nextInt();
                        Cchoice--;


                        System.out.println("Welcome" + " " + companies[Cchoice].CompanyName);
                        int y22 = 1;
                        while(y22 == 1){
                            System.out.println("Press [1] : Update Role");
                            System.out.println("Press [2] : Update Package");
                            System.out.println("Press [3] : Update CGPA criteria");
                            System.out.println("Press [4] : Register To Institute Drive");
                            System.out.println("Press [5] : Back");

                            int c121 = sc.nextInt();
                            if(c121 == 1){
                                sc.nextLine();
                                System.out.println("Enter new Role");
                                String newRole = sc.nextLine();
                                companies[Cchoice].setCompanyRole(newRole);
                            }
                            else if(c121 == 2){
                                sc.nextLine();
                                System.out.println("Enter new Package");
                                float newPackage = sc.nextFloat();
                                companies[Cchoice].setCompanyPackage(newPackage);
                            }
                            else if(c121 == 3){
                                sc.nextLine();
                                System.out.println("Enter new minimum Cgpa");
                                float newCgpa = sc.nextFloat();
                                companies[Cchoice].setMinimumCgpa(newCgpa);
                            }
                            else if(c121 == 4){
                                LocalDateTime localDate = LocalDateTime.now();
                                DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd/MM/YYYY, hh:mm");
                                String td = dateformatter.format(localDate);
//                                System.out.println(td);
                                if(companies[Cchoice].CompanyRegistered){
                                    System.out.println("You have already registered");
                                    System.out.println("You can only register once!!!");
                                }
                                else{
                                    String aa = IIITDPlacementCell.getCompanyRegistrationStartTime();
                                    String bb = IIITDPlacementCell.getCompanyRegistrationEndTime();



                                if ( td.compareTo(aa) < 0 || td.compareTo(bb) > 0){
                                    System.out.println("Sorry!!! You cannot register");
                                    System.out.println("Either registration period for companies has ended or not started");
                                }
                                else{
                                    System.out.println("Registered Successfully at " + td);
                                    companies[Cchoice].setCompanyRegistrationDateTime(td);
                                    companies[Cchoice].CompanyRegistered = true;
                                }}
                            }
                            else{
                                y22 = 0;
                            }

                        }




                    }
                    else if(c12 == 3){
                            for(int i31 = 0; i31 < noOfCompanies; i31++){
                                if(companies[i31].CompanyRegistered){
                                    System.out.println(companies[i31].CompanyName);
                                }
                            }

                    }
                    else{
                        y2 = 0;
                    }
                }

            }
            else if(c1 == 3){
                System.out.println("Welcome to IIITD Placement cell");
                int y3 = 1;
                while(y3 == 1){
                    System.out.println("Press [1] : Open Student Registrations ");
                    System.out.println("Press [2] : Open Company Registrations");
                    System.out.println("Press [3] : Get Number of Student Registrations");
                    System.out.println("Press [4] : Get Number of Company Registrations");
                    System.out.println("Press [5] : Get Number of Placed/Unplaced/Blocked Students");
                    System.out.println("Press [6] : Get Student Details");
                    System.out.println("Press [7] : Get Company Details");
                    System.out.println("Press [8] : Get Average Package");
                    System.out.println("Press [9] : Get Company Process Results");
                    System.out.println("Press [10] : Back");
                    int c13 = sc.nextInt();
                    if(c13 == 1){
                        sc.nextLine();
                        System.out.println("Fill in the details:-");
                        System.out.println("Set the Opening time for student registrations in the format 'dd/MM/YYYY, hh:mm' ");
                        String Sstart;

                        while(true){
                            Sstart = sc.nextLine();
                            String aa = IIITDPlacementCell.getCompanyRegistrationEndTime();
                            if(Sstart.compareTo(aa) > 0){
                                break;
                            }
                            else{
                                System.out.println("Student registration can start only after company registration ends");
                                System.out.println("Enter a time after" + " " + IIITDPlacementCell.getCompanyRegistrationEndTime());
                            }
                        }
                        System.out.println("Set the Closing time for student registrations in the format 'dd/MM/YYYY, hh:mm'");
                        String Send = sc.nextLine();
                        IIITDPlacementCell.setStudentRegistrationStartTime(Sstart);
                        IIITDPlacementCell.setStudentRegistrationEndTime(Send);
                    }
                    else if(c13 == 2){
                        sc.nextLine();

                        System.out.println("Fill in the details:-");
                        System.out.println("Set the Opening time for company registrations in the format 'dd/MM/YYYY, hh:mm' ");
                        String cstart = sc.nextLine();
                        System.out.println("Set the Closing time for company registrations");
                        String cend = sc.nextLine();
                        IIITDPlacementCell.setCompanyRegistrationStartTime(cstart);
                        IIITDPlacementCell.setCompanyRegistrationEndTime(cend);

                    }
                    else if(c13 == 3){
                        IIITDPlacementCell.NoOfStudentsRegistration(StudentList, noOfStudents);

                    }
                    else if(c13 == 4) {
                        IIITDPlacementCell.NoOfCompanyRegistration(companies, noOfCompanies);
                    }
                    else if(c13 == 5){
                        IIITDPlacementCell.statistics(StudentList, noOfStudents);

                    }
                    else if(c13 == 6){
                        System.out.println("Enter student's roll number : ");
                        int sr = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter student's name : ");
                        String sname = sc.nextLine();

                        IIITDPlacementCell.getStudentDetails(sname, sr, StudentList,noOfStudents,companies,noOfCompanies);

                    }
                    else if(c13 == 7){
                        sc.nextLine();
                        System.out.println("Enter company's name : ");
                        String cname = sc.nextLine();
                        IIITDPlacementCell.getCompanyDetails(cname,companies,noOfCompanies);

                    }
                    else if(c13 == 8){

                        IIITDPlacementCell.AveragePackage(StudentList, noOfStudents);

                    }
                    else if(c13 == 9){
                        sc.nextLine();
                        System.out.println("Enter company's name : ");
                        String cname = sc.nextLine();
                        IIITDPlacementCell.getCompanyResults(cname, companies,noOfCompanies);

                    }
                    else{
                        y3 = 0;
                    }


                }
            }
            else{
                option = "a";

            }
        }
        if(Objects.equals(option, "Exit FutureBuilder")){
            break;
        }

        }

    }
}