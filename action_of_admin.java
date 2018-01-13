import java.sql.*;
import java.util.*;

public class action_of_admin {
	static final String DB_URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USERID = "zli36";
    static final String PASSWORD = "200159301"; 
    static List<String> Semester_record = new LinkedList<String>();
    static List<Integer> Year_record = new LinkedList<Integer>();
 
public static void main(String[] args) {
	Connection Conn = null;
	PreparedStatement Stmt = null;
	boolean flag_admin = true;
	while(flag_admin) {
	try {
		Class.forName(JDBC_DRIVER);
		Conn = DriverManager.getConnection(DB_URL, USERID, PASSWORD);
		Scanner input = new Scanner(System.in);
		System.out.println("You are an administrator:" + '\n');
		System.out.println("Please choose the operation you want to do:" + '\n');
		System.out.println("1. View your Profile:" + '\n');
		System.out.println("2. Enroll A new student" + '\n');
		System.out.println("3. View student details" + '\n');
		System.out.println("4. View one course" + '\n');
		System.out.println("5. View all courses" + '\n');
		System.out.println("6. add a course" + '\n');
		System.out.println("7. Search an offering a course" + '\n');
		System.out.println("8. ADD an offering of a course" + '\n');
		System.out.println("9. View/Approve Special Enrollment Requests" + '\n');
		System.out.println("10. Enforce Add/Drop Deadline" + '\n');
		System.out.println("11. log out" + "\n");
		int num = input.nextInt();
		if(num == 11){
			flag_admin = false;
		}
		if(num == 1) {
         input.nextLine();
         
         String USERNAME = args[0];
        // System.out.println("Please enter your password: " + "\n");
         //String Password = args[1];
			String sqlstr = "SELECT SSN, EID, LASTNAME, FIRSTNAME, DOB FROM ADMIN WHERE USERNAME = ? ";			//AND PASSWORD = ?
			Stmt = Conn.prepareStatement(sqlstr);			
			Stmt.clearParameters();
			Stmt.setString(1, USERNAME);
			//Stmt.setString(2, Password);	
			ResultSet rs = Stmt.executeQuery();
			while(rs.next()) {
				int SSN = rs.getInt("SSN");
				int EID = rs.getInt("EID");
				String LASTNAME = rs.getString("LASTNAME");
				String FIRSTNAME = rs.getString("FIRSTNAME");
				String DOB = rs.getString("DOB");
				System.out.println("\n" + "SSN: "+ SSN + "\n" + "EmployeeID: " + EID + "\n" + "Lastname: " + LASTNAME + "\n" + "firstname: " + FIRSTNAME + "\n" + "Date of birth: " + DOB + "\n");
				System.out.println("\n");
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();	
			}			
		}
		
		if(num == 2) {
			System.out.println("Please enter Student ID: " + "\n");
			int SID = input.nextInt();
			input.nextLine();
			System.out.println("Please enter Student's firstname: " + "\n");
			String FIRSTNAME = input.nextLine();
			System.out.println("Please enter Student's lastname: " + "\n");
			String LASTNAME = input.nextLine();
			System.out.println("Please enter the date of birth of this student: " + "\n");
			String DOB = input.nextLine();
			System.out.println("Please enter the department the student is in: " + "\n");
			String DEPT = input.nextLine();
			System.out.println("Please enter the GPA of this student: " + "\n");
			float GPA = input.nextFloat();
			input.nextLine();
			System.out.println("Please enter the Bill amount of this student: " + "\n");
			String BILL = input.nextLine();
			System.out.println("Please enter the PASSWORD of this student's account: " + "\n");
			String PASSWORD = input.nextLine();
			System.out.println("Please enter the email address of this student: " + "\n");
			String EMAIL = input.nextLine();
			System.out.println("Please enter the level of this student: " + "\n");
			String LEV = input.nextLine();
			System.out.println("Please enter the residence status of this student: " + "\n");
			String RESIDENCE = input.nextLine();
			System.out.println("Please enter the course list of the student: " + "\n");
			String COURSE_LIST = input.nextLine();
			System.out.println("Please enter the fare rate of the student:" + "\n");
			String RATE = input.nextLine();
			System.out.println("Please enter the min credit of the student:" + "\n");
			int MIN_CREDIT = input.nextInt();
			System.out.println("Please enter the max credit of the student:" + "\n");
			int MAX_CREDIT = input.nextInt();
			System.out.println("Please enter the current credit of the student:" + "\n");
			int NOW_CREDIT = input.nextInt();
			System.out.println("Please enter the phone number of the student:" + "\n");
			int PHONE_NUM = input.nextInt();
			input.nextLine();
			System.out.println("Please enter the username of the student:" + "\n");
			String USERNAME = input.nextLine();
			String sqlstr = "INSERT INTO STUDENT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			Stmt = Conn.prepareStatement(sqlstr);			
			Stmt.clearParameters();
			Stmt.setInt(1,SID);
			Stmt.setString(2, FIRSTNAME);
			Stmt.setString(3, LASTNAME);
			Stmt.setString(4, DOB);
			Stmt.setString(5, DEPT);
			Stmt.setFloat(6, GPA);
			Stmt.setString(7, BILL);
			Stmt.setString(8, PASSWORD);
			Stmt.setString(9, EMAIL);
			Stmt.setString(10, LEV);
			Stmt.setString(11, RESIDENCE);
			Stmt.setString(12, COURSE_LIST);
			Stmt.setString(13, RATE);
			Stmt.setInt(14, MIN_CREDIT);
			Stmt.setInt(15, MAX_CREDIT);
			Stmt.setInt(16, NOW_CREDIT);
			Stmt.setInt(17, PHONE_NUM);
			Stmt.setString(18, USERNAME);
			ResultSet rs = Stmt.executeQuery();
			System.out.println("\n" + "Already inserted" + "\n");
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();	
		}
		
		if(num == 3) {
			System.out.println("Please enter the student ID:" + "\n");
			int SID = input.nextInt();
			input.nextLine();
			String sqlstr = "SELECT FIRSTNAME, LASTNAME, DOB, EMAIL, PHONE_NUM, GPA, LEV, RESIDENCE, BILL FROM STUDENT WHERE SID = ?";
			Stmt = Conn.prepareStatement(sqlstr);
			Stmt.clearParameters();
			Stmt.setInt(1, SID);
			ResultSet rs = Stmt.executeQuery();
			int count1 = 0;
			while(rs.next()) {
				count1 ++;
				String FIRSTNAME = rs.getString("FIRSTNAME");
				String LASTNAME = rs.getString("LASTNAME");
				String DOB = rs.getString("DOB");
				String EMAIL = rs.getString("EMAIL");
				String PHONE_NUM = rs.getString("PHONE_NUM");
				float GPA = rs.getFloat("GPA");
				String LEV = rs.getString("LEV");
				String RESIDENCE = rs.getString("RESIDENCE");
				String BILL = rs.getString("BILL");
				
				System.out.println("FirstName: " + FIRSTNAME + "\n" + "Lastname: " + LASTNAME + "\n" + "Date of Birth: " + DOB + "\n" + "Email address: " + EMAIL + "\n" + "Phone number: " + PHONE_NUM);
				System.out.println("GPA: " + GPA +  "\n" + "Level of the student: " + LEV + "\n" + "Residency status of the student: " + RESIDENCE);
				if(BILL.charAt(0) != '0')
				System.out.println("Amount Owed: " + BILL + "\n");
				else
				System.out.println("Amount Owed: no" + "\n");
				System.out.println("\n");
			}
			if(count1 == 0) {
              System.out.println("You input the wrong ID, please press enter");			
			}
			else {
              System.out.println("Do you want to view the grade of this student? 1.Yes 2.No (if No, please press enter)" + "\n");
              String judgeh = input.nextLine();
              if(judgeh.charAt(0) == '1') {
                  System.out.println("Please enter the course ID");
                  int CID = input.nextInt();
                  sqlstr = "SELECT GRADE FROM ENROLL WHERE CID = ? AND SID = ?";
                  Stmt = Conn.prepareStatement(sqlstr);
			         Stmt.clearParameters();
			         Stmt.setInt(1, CID);
			         Stmt.setInt(2, SID);
			         rs = Stmt.executeQuery();
      			while(rs.next()) {
                    String GRADE = rs.getString("GRADE");
				        System.out.println("\n" + "GRADE: " + GRADE +  "\n");

			      }    
			      input = new Scanner(System.in);
			      System.out.println("Do you want to change the Grade? 1.Yes 2.No ");
			      String judgehh = input.nextLine();
			      if(judgehh.charAt(0) == '1') {
                 System.out.println("Please enter the Grade: ");
                 String GRADE1 = input.nextLine();
                 sqlstr = "UPDATE ENROLL SET GRADE = ? WHERE CID = ? AND SID = ? ";
                  Stmt = Conn.prepareStatement(sqlstr);
			         Stmt.clearParameters();
			         Stmt.setString(1,GRADE1);
			         Stmt.setInt(2, CID);
			         Stmt.setInt(3, SID);
			         rs = Stmt.executeQuery();
			         List<Double> res = new ArrayList<Double>();
			         sqlstr = "SELECT GRADE FROM ENROLL WHERE SID = ?";
			         Stmt = Conn.prepareStatement(sqlstr);
			         Stmt.clearParameters();
			         Stmt.setInt(1, SID);
			         rs = Stmt.executeQuery();
      			    while(rs.next()) {
                    String GRADE = rs.getString("GRADE");
                    if(GRADE.equals("A+"))
                         res.add(4.33);
                    if(GRADE.equals("A"))
                         res.add(4.00);
                    if(GRADE.equals("A-"))
                         res.add(3.67);
                    if(GRADE.equals("B+"))
                         res.add(3.33);
                    if(GRADE.equals("B"))
                         res.add(3.00);
                    if(GRADE.equals("B-"))
                         res.add(2.67);
                    if(GRADE.equals("C+"))
                         res.add(2.33);
                    if(GRADE.equals("C-"))
                         res.add(1.67);
                    if(GRADE.equals("C"))
                         res.add(2.00);      

			           }      
			           double count2 = 0;
			           for(int i = 0; i < res.size(); i++) {
                           count2 += res.get(i);			           
			           }
			           count2 /= res.size();
			           /*if(count2 >= 4.33){
                       GRADE3 = "A+";			           
			           }else if(count2 >= 4.00){
			           	 GRADE3 = "A";	
			           }else if (count2>=3.67){
                       GRADE3 = "A-";				           
			           }else if(count2 >= 3.33){
                       GRADE3 = "B+";				           
			           }else if(count2 >= 3.00){
                       GRADE3 = "B";				           
			           }else if(count2 >= 2.67){
                       GRADE3 = "B-";				            
			            }else if(count2 >= 2.33){
                       GRADE3 = "C+";			            
			            }else if(count2 >= 2.00){
                       GRADE3 = "C";			             
			             }else if(count2 >= 1.67){
                        GRADE3 = "C-";			              
			              }*/
			         sqlstr = "UPDATE STUDENT SET GPA = ? WHERE SID = ?";
                  Stmt = Conn.prepareStatement(sqlstr);
			         Stmt.clearParameters();
			         float count3 =(float)count2;
			         float res1 = (float)(Math.round(count3 * 100))/100;
			         Stmt.setFloat(1, res1);
			         Stmt.setInt(2, SID);
			         rs = Stmt.executeQuery();  
			         System.out.println("Updated, Please press enter");		         
                 			      
			      }                           
              }
              			
			}
			input.nextLine();
						      input = new Scanner(System.in);
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();			
		}
		
		if(num == 4) {
			boolean flag = true;

			System.out.println("Please enter the course ID: " + "\n");
			int CID = input.nextInt();
			input.nextLine();
			System.out.println("Please enter the department of this course: " + "\n");
			String DEPT = input.nextLine();
			String sqlstr = "SELECT DEPT, TITLE, LEV, GPAREQ, PREREQUISITE, CREDIT FROM COURSE WHERE CID = ? AND DEPT = ?";
			Stmt = Conn.prepareStatement(sqlstr);
			Stmt.clearParameters();
			Stmt.setInt(1, CID);
			Stmt.setString(2,DEPT);
			ResultSet rs = Stmt.executeQuery();
			while(rs.next()) {
				String DEPT1 = rs.getString("DEPT");
				String TITLE = rs.getString("TITLE");
				String LEV = rs.getString("LEV");
				float GPAREQ = rs.getFloat("GPAREQ");
				String PREREQUISITE = rs.getString("PREREQUISITE");
				int CREDIT = rs.getInt("CREDIT");
				System.out.println("Department: " + DEPT1 + "\n" + "Course Name: " + TITLE + "\n" + "Level: " + LEV);
            if(GPAREQ == 0.0)
            System.out.println("No GPA requirement");
            else				
				System.out.println("GPA requirement: " + GPAREQ);
				if(PREREQUISITE.charAt(0) == 'N')
				   System.out.println("No Prerequisite and No Special Approval Required");
				if(PREREQUISITE.charAt(0) == 'S')
				   System.out.println("Special Approval Required");
				if(PREREQUISITE.charAt(0) != 'N' && PREREQUISITE.charAt(0) != 'S'){
				   System.out.println("No Special Approval Required");
					  System.out.println("Prerequisite course:");
				     String[] ans = PREREQUISITE.split(",");
				     for(int i = 0; i < ans.length; i++) {
                     System.out.println(ans[i]);				     
				     }
			   }
			   if(CREDIT < 100)
			   System.out.println("Credit: " + CREDIT + "\n");
			   else{
			   int b = CREDIT - 10 * (CREDIT/10);
			   System.out.println("Credit: from " + CREDIT/100 + " to " + b);
			   }
				System.out.println("\n");
			}
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();	
		}
		
		if(num == 5) {
			input.nextLine();
			String sqlstr = "SELECT CID, DEPT, TITLE, LEV, GPAREQ, PREREQUISITE, CREDIT FROM COURSE ORDER BY CID";
			Stmt = Conn.prepareStatement(sqlstr);
			Stmt.clearParameters();
			ResultSet rs = Stmt.executeQuery();
			while(rs.next()) {
				int CID = rs.getInt("CID");
				String DEPT1 = rs.getString("DEPT");
				String TITLE = rs.getString("TITLE");
				String LEV = rs.getString("LEV");
				float GPAREQ = rs.getFloat("GPAREQ");
				String PREREQUISITE = rs.getString("PREREQUISITE");
				int CREDIT = rs.getInt("CREDIT");
				System.out.println("\n" + "Course ID: " + CID + "\n" + "Department: " + DEPT1 + "\n" + "Title: " + TITLE + "\n" + "Level: " + LEV + "\n" + "GPA requirement: " + GPAREQ + "\n" + "Prerequisite: " + PREREQUISITE);
			   if(CREDIT < 100)
			   System.out.println("Credit: " + CREDIT + "\n");
			   else{
			   int b = CREDIT - 10 * (CREDIT/10);
			   System.out.println("Credit: from " + CREDIT/100 + " to: " + b);
			   }
				System.out.println("\n");
			}
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();	
		}

		if(num == 6) {
			System.out.println("Please enter the course ID: " + "\n");
			int CID = input.nextInt();
			input.nextLine();
			System.out.println("Please enter the department of this course: " + "\n");
			String DEPT = input.nextLine();
			System.out.println("Please enter the name of this course: " + "\n");
			String TITLE = input.nextLine();
			System.out.println("Please enter the level of this course: " + "\n");
			String LEV = input.nextLine();
			System.out.println("Please enter the GPA requirement of this course(If no requirement please enter 0.0): " + "\n");
			float GPAREQ = input.nextFloat();
			input.nextLine();
			System.out.println("Please enter the prerequisite of this course(If Special Approval Required, enter SPECIAL here): " + "\n");
         String PREREQUISITE = input.nextLine();
			System.out.println("Please enter the credit of this course: " + "\n");
			int CREDIT = input.nextInt();
			input.nextLine();
			String sqlstr = "INSERT INTO COURSE VALUES (?, ?, ?, ?, ?, ?, ?)";
			Stmt = Conn.prepareStatement(sqlstr);
			Stmt.clearParameters();
			Stmt.setInt(1, CID);
			Stmt.setString(2,DEPT);
			Stmt.setString(3,TITLE);
			Stmt.setString(4, LEV);
			Stmt.setFloat(5, GPAREQ);
			Stmt.setString(6, PREREQUISITE);
			Stmt.setInt(7, CREDIT);
			ResultSet rs = Stmt.executeQuery();
			System.out.println("\n" + "Already inserted" + "\n");
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();	
		}

		if(num == 7) {
			System.out.println("Please enter the course ID:" + "\n");
			int CID = input.nextInt();
			System.out.println("Please enter the year:" + "\n");
			int A_YEAR = input.nextInt();
			input.nextLine();
			System.out.println("Please enter the semester: " + "\n");
			String SEMESTER = input.nextLine();
			String sqlstr = "SELECT A_DAY, A_HOUR, A_LOCATION, SECTION_ID, DEADLINE, ENROLL_SUM, WAITING_SUM, ENROLL_NOW, WAITING_NOW, FACULTY FROM ARRANGEMENT WHERE CID = ? AND A_YEAR = ? AND SEMESTER = ? ";
			Stmt = Conn.prepareStatement(sqlstr);
			Stmt.clearParameters();
			Stmt.setInt(1, CID);
			Stmt.setInt(2, A_YEAR);
			Stmt.setString(3, SEMESTER);
			ResultSet rs = Stmt.executeQuery();
			while(rs.next()) {
				String A_DAY = rs.getString("A_DAY");
				String A_HOUR = rs.getString("A_HOUR");
				String A_LOCATION = rs.getString("A_LOCATION");
				String SECTION_ID = rs.getString("SECTION_ID");
				String DEADLINE = rs.getString("DEADLINE");
				int ENROLL_SUM = rs.getInt("ENROLL_SUM");
				int WAITING_SUM = rs.getInt("WAITING_SUM");
				int ENROLL_NOW = rs.getInt("ENROLL_NOW");
				int WAITING_NOW = rs.getInt("WAITING_NOW");
				String FACULTY = rs.getString("FACULTY");
				System.out.println("\n" + "DAY of the class: " + A_DAY);
				String[] ans = A_HOUR.split("-");
				System.out.println("Start time: "+ ans[0] + "\n" + "End time: " + ans[1] + "\n" + "Location of class: " + A_LOCATION + "\n" + "section ID: " + SECTION_ID + "\n" +  "deadline of the class: " + DEADLINE + "\n" + "capacity of this class: "+ ENROLL_SUM);
				System.out.println("capacity of the waitlist " + WAITING_SUM + "\n" + "Current number of students enrolled: " + ENROLL_NOW + "\n" + "Current number of students in waiting list: " + WAITING_NOW + "\n" + "Professor of this class: " + FACULTY + "\n");
				System.out.println("\n");
			}
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();				
		}
	
		if(num == 8) {
			System.out.println("Please enter course ID: " + "\n");
			int CID = input.nextInt();
			input.nextLine();	
			System.out.println("Please enter day of the class: " + "\n");
			String A_DAY = input.nextLine();
			System.out.println("Please hour of the class: " + "\n");
			String A_HOUR = input.nextLine();
			System.out.println("Please enter the semester of the class: " + "\n");
			String SEMESTER = input.nextLine();
			System.out.println("Please enter the year the class: " + "\n");
			int A_YEAR = input.nextInt();
			input.nextLine();	
			System.out.println("Please enter the location of the class: " + "\n");
			String A_LOCATION = input.nextLine();
			System.out.println("Please enter section id of the class: " + "\n");
			String SECTION_ID = input.nextLine();
			System.out.println("Please enter the DEADLINE of the class: " + "\n");
			String DEADLINE = input.nextLine();
			System.out.println("Please enter the capacity of the class: " + "\n");
			int ENROLL_SUM = input.nextInt();
			System.out.println("Please enter the capacity of the waitlist: " + "\n");
			int WAITING_SUM = input.nextInt();
			System.out.println("Please enter the number of students current enrolled: " + "\n");
			int ENROLL_NOW = input.nextInt();
			System.out.println("Please enter the number of students in waiting list: " + "\n");
			int WAITING_NOW = input.nextInt();
			input.nextLine();	
			System.out.println("Please enter the name of the professor:" + "\n");
			String FACULTY = input.nextLine();
			String sqlstr = "INSERT INTO ARRANGEMENT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Stmt = Conn.prepareStatement(sqlstr);			
			Stmt.clearParameters();
			Stmt.setString(1, A_DAY);
			Stmt.setString(2, A_HOUR);
			Stmt.setString(3, SEMESTER);
			Stmt.setInt(4, A_YEAR);
			Stmt.setString(5, A_LOCATION);
			Stmt.setString(6, SECTION_ID);
			Stmt.setString(7, DEADLINE);
			Stmt.setInt(8, ENROLL_SUM);
			Stmt.setInt(9, WAITING_SUM);
			Stmt.setInt(10, ENROLL_NOW);
			Stmt.setInt(11, WAITING_NOW);
			Stmt.setString(12, FACULTY);
			Stmt.setInt(13, CID);
			ResultSet rs = Stmt.executeQuery();	
			System.out.println("\n" + "ALready inserted" + "\n");
			System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			String judge1 = input.nextLine();		
		}		
		
		if(num == 9) {
			String sqlstr = "SELECT SEMESTER, A_YEAR, CID, SECTION_ID, STATUS, WL_NUM, SID, CREDIT FROM ENROLL WHERE STATUS = 'PENDING_W' OR STATUS = 'PENDING_E'";
			Stmt = Conn.prepareStatement(sqlstr);			
			Stmt.clearParameters();
			ResultSet rs = Stmt.executeQuery();	
			int count = 0;
			List<Integer> StuID = new ArrayList<Integer>();
			List<Integer> CouID = new ArrayList<Integer>();
			List<String>  judge = new ArrayList<String>();
			while(rs.next()) {
				count ++;
				String SEMESTER = rs.getString("SEMESTER");
				int A_YEAR = rs.getInt("A_YEAR");
				int CID = rs.getInt("CID");
				CouID.add(CID);
				String SECTION_ID = rs.getString("SECTION_ID");
				String STATUS = rs.getString("STATUS");
				judge.add(STATUS);
			   if(STATUS.equals("PENDING_E"))
			      STATUS = "Want to be enrolled in the class;";
			   if(STATUS.equals("PENDING_W"))
			      STATUS = "Want to be enrolled in waitlist;";
				int WL_NUM = rs.getInt("WL_NUM");
				int SID = rs.getInt("SID");
				StuID.add(SID);
				int CREDIT = rs.getInt("CREDIT");
				System.out.println("\n" +"No." + count + "\n" + "SEMESTER: " + SEMESTER + "\n" + "year of the class: " + A_YEAR + "\n" + "Course ID: " + CID + "\n" + "section ID: " + SECTION_ID + "\n" +  "Status of the student: " + STATUS + "\n" + "waitlist number of the student: "+  WL_NUM + "\n");
				System.out.println("Student ID " + SID + "\n" + "Credit of the course " + CREDIT + "\n");
				System.out.println("\n");
			}
			int count1 = 0;
			boolean flag = true;
			if(count == 0) {
			  System.out.println("NO special enrollment requests");
			  flag = false;
			}
			while(flag){
			  count1++;
			  System.out.println("\n" + "Do you want to leave this part? 1. leave 2.stay and continue doing things" + "\n");
			  int a = input.nextInt();
			  if(a == 1)
			    break;
			  if(count1 > 1){
			     sqlstr = "SELECT SEMESTER, A_YEAR, CID, SECTION_ID, STATUS, WL_NUM, SID, CREDIT FROM ENROLL WHERE STATUS = 'PENDING_W' OR STATUS = 'PENDING_E'";
			     Stmt = Conn.prepareStatement(sqlstr);			
			     Stmt.clearParameters();
			     rs = Stmt.executeQuery();	
			     count = 0;
			     StuID.clear();
			     CouID.clear();
			     judge.clear();
			     System.out.println("Stu:" + StuID.size());
			     while(rs.next()) {
				    count ++;
				    String SEMESTER = rs.getString("SEMESTER");
				    int A_YEAR = rs.getInt("A_YEAR");
				    int CID = rs.getInt("CID");
				    CouID.add(CID);
				    String SECTION_ID = rs.getString("SECTION_ID");
				    String STATUS = rs.getString("STATUS");
				    judge.add(STATUS);
			       if(STATUS.equals("PENDING_E"))
			          STATUS = "Want to be enrolled in the class;";
			       if(STATUS.equals("PENDING_W"))
			          STATUS = "Want to be enrolled in waitlist;";
				    int WL_NUM = rs.getInt("WL_NUM");
				    int SID = rs.getInt("SID");
				    StuID.add(SID);
				    int CREDIT = rs.getInt("CREDIT");
				    System.out.println("\n" +"No." + count + "\n" + "SEMESTER: " + SEMESTER + "\n" + "year of the class: " + A_YEAR + "\n" + "Course ID: " + CID + "\n" + "section ID: " + SECTION_ID + "\n" +  "Status of the student: " + STATUS + "\n" + "waitlist number of the student: "+  WL_NUM + "\n");
				    System.out.println("Student ID " + SID + "\n" + "Credit of the course " + CREDIT + "\n");
				    System.out.println("\n");
			     }            			  
			  }
			  System.out.println("\n" + "which one you want to select" + "\n");
			  int No = input.nextInt();
			  System.out.println("\n" + "1. approve 2. reject" + "\n");
			  int selection = input.nextInt();
			  if(selection == 1) {
			  	   int CID = CouID.get(No - 1);
			  	   int SID = StuID.get(No - 1);
			  	   if(judge.get(No - 1).equals("PENDING_E"))
			  	      sqlstr = "Update ENROLL SET STATUS = 'ENROLLED' WHERE CID = ? AND SID = ? ";
			  	   if(judge.get(No - 1).equals("PENDING_W"))
			  	      sqlstr = "Update ENROLL SET STATUS = 'WAITING' WHERE CID = ? AND SID = ? ";
			      Stmt = Conn.prepareStatement(sqlstr);			
			      Stmt.clearParameters();
			      Stmt.setInt(1, CID);
			      Stmt.setInt(2, SID);
			      ResultSet rs1 = Stmt.executeQuery();
			      System.out.println("\n" + "DONE" + "\n");			      
			  }
			  if(selection == 2) {
			  	   int CID = CouID.get(No - 1);
			  	   int SID = StuID.get(No - 1);
			  	   sqlstr = "Update ENROLL SET STATUS = 'REJECTED' WHERE CID = ? AND SID = ? ";
			      Stmt = Conn.prepareStatement(sqlstr);			
			      Stmt.clearParameters();
			      Stmt.setInt(1, CID);
			      Stmt.setInt(2, SID);
			      ResultSet rs1 = Stmt.executeQuery();
			      System.out.println("\n" + "DONE" + "\n");	
			  }
			  System.out.println("1.continue in this part 2. return to the main menu of Admin");
			  selection = input.nextInt();
			  if(selection == 2)
			     flag = false;
		     }		
		}	
		if(num == 10) {
			System.out.println("Are you sure you want to enforce deadlines? 1. YES 2.No");
			int judge = input.nextInt();
			if(judge == 1){
			  System.out.println("Please enter the current year: " + "\n");
			  int Year = input.nextInt();	
			  System.out.println("Please enter the current month: " + "\n");
			  int Month = input.nextInt();
			  System.out.println("Please enter the current day:" + "\n");
			  int Day = input.nextInt();
			  input.nextLine();
			  System.out.println("Please enter the current semester:" + "\n");
			  String Semester = input.nextLine();
			  boolean judge10 = true;
			  if(Year_record.contains(Year)){
                for(int i = 0; i < Year_record.size(); i++) {
                     if(Year_record.get(i) == Year && Semester.equals(Semester_record.get(i))) {
                          judge10 = false;
                          break;
                     }                
                }    			  
			  }
			  if(judge10){
			  Year_record.add(Year);
			  Semester_record.add(Semester);
			  String sqlstr = "SELECT CID, DEADLINE, A_YEAR, SEMESTER, SECTION_ID FROM ARRANGEMENT";
			  Stmt = Conn.prepareStatement(sqlstr);			
			  Stmt.clearParameters();
			  ResultSet rs = Stmt.executeQuery();	
			  while(rs.next()) {
				  int CID = rs.getInt("CID");
				  String DDL = rs.getString("DEADLINE");
				  int Cyear = rs.getInt("A_YEAR");
				  String Csemester = rs.getString("SEMESTER");
				  String SECID = rs.getString("SECTION_ID");
				  String year = "";
				  String month = "";
              String day = "";
              int count = 0;
              for(count = 0; count < 4; count ++) {
                  year += DDL.charAt(count);              
              }
              count ++;
              month += DDL.charAt(count);
              count ++;
              if(DDL.charAt(count) != '.') {
                 month += DDL.charAt(count);
                 count ++;              
              }
              count ++;
              for(int i = count; i < DDL.length(); i++)
                 day += DDL.charAt(i);
              int y = 0;
              int m = 0;
              int d = 0;
              int bit = 1;
              for(int i = year.length() - 1; i >= 0; i--) {
                    y += bit * (int)(year.charAt(i) - '0');
                    bit *= 10;              
              }
              bit = 1;
              for(int i = month.length() - 1; i >= 0; i--) {
                    m += bit *(int)(month.charAt(i) - '0');
                    bit *= 10;              
              }
              bit = 1;
              for(int i = day.length() - 1; i >= 0; i--) {
                    d += bit *(int)(day.charAt(i) - '0');
                    bit *= 10;              
              }
              if(y > Year)
                 continue;
              if(y < Year) {
                  String sqlstr1 = "UPDATE ENROLL SET STATUS = 'REJECTED' WHERE CID = ? AND STATUS <> 'ENROLLED' AND A_YEAR = ? AND SEMESTER = ? AND SECTION_ID = ?";        
 			         Stmt = Conn.prepareStatement(sqlstr1);			
			         Stmt.clearParameters();
			         Stmt.setInt(1, CID);
			         Stmt.setInt(2, Cyear);
			         Stmt.setString(3, Csemester);
			         Stmt.setString(4, SECID);
			         ResultSet rs1 = Stmt.executeQuery();	
			         sqlstr1 = "UPDATE ARRANGEMENT SET WAITING_NOW = 0 WHERE CID = ? AND SEMESTER = ? AND A_YEAR = ? AND SECTION_ID = ?";
 			         Stmt = Conn.prepareStatement(sqlstr1);				         
			         Stmt.clearParameters();
			         Stmt.setInt(1,CID);
			         Stmt.setString(2, Csemester);
			         Stmt.setInt(3, Cyear);
			         Stmt.setString(4, SECID);
			         rs1 = Stmt.executeQuery();				                                       
              }
              if(y == Year) {
                 if(m > Month)
                    continue;
                 if(m < Month) {
                    String sqlstr2 = "UPDATE ENROLL SET STATUS = 'REJECTED' WHERE CID = ? AND STATUS <> 'ENROLLED' AND A_YEAR = ? AND SEMESTER = ? AND SECTION_ID = ?";        
 			           Stmt = Conn.prepareStatement(sqlstr2);			
			           Stmt.clearParameters();
			           Stmt.setInt(1, CID);
			           Stmt.setInt(2, Cyear);
			           Stmt.setString(3, Csemester);
			           Stmt.setString(4, SECID);
			           ResultSet rs2 = Stmt.executeQuery();	
			         sqlstr2 = "UPDATE ARRANGEMENT SET WAITING_NOW = 0 WHERE CID = ? AND SEMESTER = ? AND A_YEAR = ? AND SECTION_ID = ?";
 			         Stmt = Conn.prepareStatement(sqlstr2);				         
			         Stmt.clearParameters();
			         Stmt.setInt(1,CID);
			         Stmt.setString(2, Csemester);
			         Stmt.setInt(3, Cyear);
			         Stmt.setString(4, SECID);
			         rs2 = Stmt.executeQuery();              
                 }
                 if(m == Month) {
                     if(d > Day)
                       continue;
                     String sqlstr3 = "UPDATE ENROLL SET STATUS = 'REJECTED' WHERE CID = ? AND STATUS <> 'ENROLLED' AND A_YEAR = ? AND SEMESTER = ? AND SECTION_ID = ?";        
 			            Stmt = Conn.prepareStatement(sqlstr3);			
			            Stmt.clearParameters();
			            Stmt.setInt(1, CID);
			            Stmt.setInt(2, Cyear);
			            Stmt.setString(3, Csemester);
			            Stmt.setString(4, SECID);
			            ResultSet rs3 = Stmt.executeQuery();	
			            sqlstr3 = "UPDATE ARRANGEMENT SET WAITING_NOW = 0 WHERE CID = ? AND SEMESTER = ? AND A_YEAR = ? AND SECTION_ID = ?";
 			            Stmt = Conn.prepareStatement(sqlstr3);				         
			            Stmt.clearParameters();
			            Stmt.setInt(1,CID);
			            Stmt.setString(2, Csemester);
			            Stmt.setInt(3, Cyear);
			            Stmt.setString(4, SECID);
			            rs3 = Stmt.executeQuery();                  
                 }              
              }
			  }
			  System.out.println("\n" + "ALready Enforced" + "\n");
			  System.out.println("Do you want to restart? Type 0 to go back" + "\n");
			  String judge1 = input.nextLine();
		}
		else {
		   System.out.println("You or other admins have done this before, Please type 0 to go back" + "\n");
		   String judge1 = input.nextLine();
		}
		}			
		}							
	}
	catch(SQLException se) {
		//se.printStackTrace();
		System.out.println("\n" + "ERROR!");
	}
	catch(Exception e){
		//e.printStackTrace();
		System.out.println("\n" + "ERROR!");		
	}finally{
  			try{
     			if(Stmt!=null)
        		Conn.close();
  				}catch(SQLException se){
  			}
  	try{
     	if(Conn!=null)
        	Conn.close();
  		}catch(SQLException se){
     							se.printStackTrace();
  								}
		}
	}
	}
}
