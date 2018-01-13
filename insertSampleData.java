/*** 3 Entity Tables: Course, Employee, Student;
 *** 1 Weak Entity Table: Arrangement
 *** 2 Relationships: Belong,Enroll
*/

import java.sql.*;
import java.util.*;


public class insertSampleData{
	static final String DB_URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USER = "hli36";
    static final String PASS = "200110588"; 
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
        ResultSet fs = null;
        Set<String> set = new HashSet<String>();
        Set<String> fset = new HashSet<String>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			//delete existing tables
			rs = stmt.executeQuery("SELECT table_name FROM all_tables WHERE owner='HLI36'");
			System.out.println("Existed table names:");
				while(rs.next()) {
					String tableName = rs.getString("table_name");
					System.out.println(tableName);
					set.add(tableName);
				}


			//insert into table admin
			if(set.contains("ADMIN")) {
					stmt.executeUpdate("INSERT INTO ADMIN " + "VALUES (123456789, 1111,  'Dumbledore','Albus', 'hogwarts','1984.05.26','alby')");
					System.out.println("INSERT  Dumbledore Albus INTO TABLE ADMIN");
				}

			//insert into table course
			if(set.contains("COURSE")) {
				
				// Fall Courses
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (401, 'CS', 'Introduction to Computer Science', 'Under Graduate', 0,'NONE', 3)");
					System.out.println("INSERT cs401 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (510, 'CS', 'Database', 'Graduate', 0 ,'NONE', 3)");
					System.out.println("INSERT CS505 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (515, 'CS', 'Software Engineering', 'Graduate', 0 ,'NONE', 3)");
					System.out.println("INSERT CS515 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (520, 'CS', 'Internet Protocols', 'Graduate', 0 ,'NONE', 3)");
					System.out.println("INSERT CS520 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (525, 'CS', 'Independent Study', 'Graduate', 0,'SPECIAL', 103)");
					System.out.println("INSERT CS525 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (420, 'ECE', 'VLSI', 'Under Graduate', 0 , 'NONE',3)");
					System.out.println("INSERT CE420 INTO TABLE COURSE");
					
			   // Spring Courses
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (402, 'CS', 'Numerical Methods', 'Under Graduate', 0 ,'401',3)");
					System.out.println("INSERT CS402 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (505, 'CS', 'Algorithms', 'Graduate', 0 ,'NONE', 3)");
					System.out.println("INSERT CS505 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (521, 'CS', 'Cloud Computing', 'Graduate', 3.5 ,'520', 3)");
					System.out.println("INSERT CS521 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (530, 'CS', 'Dev-Ops', 'Graduate',0 ,'515', 3)");
					System.out.println("INSERT CS530 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (421, 'ECE', 'Numerical Methods', 'Under Graduate', 0 ,'420', 3)");
					System.out.println("INSERT ECE421 INTO TABLE COURSE");
					
				}


			//insert into table student
			if(set.contains("STUDENT")) {
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (101, 'Harry', 'Potter','1990.01.12', 'ECE', 0.000, '1200','password','HP@ncsu.edu','Under Graduate','IN STATE','420','400','0','12','3',9191111111,'username')");
					System.out.println("INSERT HARRY POTTER INTO TABLE STUDENT");
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (102, 'Hermione', 'Granger','1989.04.16', 'CS', 0.000, '0','password','HG@ncsu.edu','Graduate','IN STATE','510,515','500','0','9','6',9192222222,'username')");
					System.out.println("INSERT HERMIONE GRANGER INTO TABLE STUDENT");
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (103, 'Ron', 'Weasley','1991.12.19', 'CS', 0.000, '0','password','RW@ncsu.edu','Graduate','OUT OF STATE','515,520,530','800','0','9','9',9193333333,'username')");
					System.out.println("INSERT RON WEASLEY INTO TABLE STUDENT");
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (104, 'Draco', 'Malfoy','1992.03.21', 'CS', 0.000, '0','password','DM@ncsu.edu','Graduate','INTERNATIONAL','510,515,525','1000','9','9','9',9194444444,'username')");
					System.out.println("INSERT RON WEASLEY INTO TABLE STUDENT");
					
				}




			

		
			//insert into table arrangement
			if(set.contains("ARRANGEMENT")) {
				
				// Enrollment of 2016fall
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '12:00-13:00', 'FALL', 2016, 'HUNT', '001','2016.4.20',2,2,0,0,'PARNIN',401)");
					System.out.println("INSERT CS401F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('TUE,THU', '13:00-14:00', 'FALL', 2016, 'EB1', '001','2016.4.20',5,2,0,0,'HEBER',510)");
					System.out.println("INSERT CS510F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('TUE,THU', '14:00-15:00', 'FALL', 2016, 'EB1', '001','2016.4.20',3,2,0,0,'DAVID',515)");
					System.out.println("INSERT CS515F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '11:00-12:00', 'FALL', 2016, 'EB2', '001','2016.4.20',2,2,0,0,'DAVIS',520)");
					System.out.println("INSERT CS520F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '15:00-16:00', 'FALL', 2016, 'EB3', '001','2016.4.20',2,0,0,0,'ROSE',525)");
					System.out.println("INSERT CS525F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('FRI', '15:00-17:00', 'FALL', 2016, 'EB2', '001','2016.4.20',4,2,0,0,'WESTBROOK',420)");
					System.out.println("INSERT CE420F INTO TABLE ARRANGEMENT");
					
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '11:00-12:00', 'FALL', 2016, 'HUNT', '001','2016.4.20',2,2,0,0,'GREEN',530)");
					System.out.println("INSERT CS530F INTO TABLE ARRANGEMENT");
					
					//Enrollment of 2017Spring
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '11:00-12:00', 'SPRING', 2017, 'HUNT', '001','2016.12.20',2,2,0,0,'PARNIN',402)");
					System.out.println("INSERT CS402S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('TUE,THU', '13:00-14:00', 'SPRING', 2017, 'EB1', '001','2016.12.20',5,2,0,0,'HEBER',510)");
					System.out.println("INSERT CS510S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '11:00-12:00', 'SPRING', 2017, 'HUNT', '001','2016.12.20',2,2,0,0,'PARNIN',505)");
					System.out.println("INSERT CS505S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('TUE,THU', '13:00-14:00', 'SPRING', 2017, 'EB2', '001','2016.12.20',3,2,0,0,'MIKE',521)");
					System.out.println("INSERT CS521S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '14:00-15:00', 'SPRING', 2017, 'HUNT', '001','2016.12.20',2,0,0,0,'JEFF',525)");
					System.out.println("INSERT CS525S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '11:00-12:00', 'SPRING', 2017, 'HUNT', '001','2016.12.20',2,2,0,0,'GREEN',530)");
					System.out.println("INSERT CS530S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('TUE,THU', '16:00-17:00', 'SPRING', 2017, 'HUNT', '001','2016.12.20',4,2,0,0,'PARNIN',421)");
					System.out.println("INSERT ECE421S INTO TABLE ARRANGEMENT");
					
					
					
				}
			
			//insert into table ENROLL

			if(set.contains("ENROLL")) {
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '420', '001', 1, 'ENROLLED',0,101,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 420 SECTION 001 FOR STUDENT 101  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '510', '001', 1, 'ENROLLED',0,102,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 510 SECTION 001 FOR STUDENT 102  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '515', '001', 1, 'ENROLLED',0,102,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 515 SECTION 001 FOR STUDENT 102  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '515', '001', 1, 'ENROLLED',0,103,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 515 SECTION 001 FOR STUDENT 103  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '520', '001', 1, 'ENROLLED',0,103,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 520 SECTION 001 FOR STUDENT 103  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '530', '001', 1, 'ENROLLED',0,103,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 510 SECTION 001 FOR STUDENT 103  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '510', '001', 1, 'ENROLLED',0,104,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 510 SECTION 001 FOR STUDENT 104  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '515', '001', 1, 'ENROLLED',0,104,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 515 SECTION 001 FOR STUDENT 104  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', 2016, '525', '001', 1, 'ENROLLED',0,104,3,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 525 SECTION 001 FOR STUDENT 104  INTO TABLE ENROLL");
					
				}
			
			
			//insert into table COST
			if(set.contains("COSTC")) {
				stmt.executeUpdate("INSERT INTO COSTC " + "VALUES ('Graduate','In State', 500)");
				System.out.println("INSERT COST RECORDS OF Graduate, In State, 500");
				stmt.executeUpdate("INSERT INTO COSTC " + "VALUES ('Graduate','Out of State', 800)");
				System.out.println("INSERT COST RECORDS OF Graduate, Out of State, 800");
				stmt.executeUpdate("INSERT INTO COSTC " + "VALUES ('Graduate','International', 1000)");
				System.out.println("INSERT COST RECORDS OF Graduate, International, 1000");
				stmt.executeUpdate("INSERT INTO COSTC " + "VALUES ('Underraduate','In State', 400)");
				System.out.println("INSERT COST RECORDS OF Undergraduate, In State, 400");
				stmt.executeUpdate("INSERT INTO COSTC " + "VALUES ('Undergraduate','Out of State', 700)");
				System.out.println("INSERT COST RECORDS OF Undergraduate, Out of State, 700");
				stmt.executeUpdate("INSERT INTO COSTC " + "VALUES ('Undergraduate','International', 900)");
				System.out.println("INSERT COST RECORDS OF Graduate, Intersnational, 900");
			}
			
			if(set.contains("CREDITREQ")) {
				stmt.executeUpdate("INSERT INTO CREDITREQ " + "VALUES ('Graduate','In State', 0, 9)");
				System.out.println("INSERT CREDITREQ RECORDS OF Graduate, In State, 0,9");
				stmt.executeUpdate("INSERT INTO CREDITREQ " + "VALUES ('Graduate','Out of State', 0, 9)");
				System.out.println("INSERT CREDITREQ RECORDS OF Graduate, Out of State, 0,9");
				stmt.executeUpdate("INSERT INTO CREDITREQ " + "VALUES ('Graduate','International', 9,9)");
				System.out.println("INSERT CREDITREQ RECORDS OF Graduate, International, 9,9");
				stmt.executeUpdate("INSERT INTO CREDITREQ " + "VALUES ('Underraduate','In State', 0,12)");
				System.out.println("INSERT CREDITREQ RECORDS OF Undergraduate, In State, 0,12");
				stmt.executeUpdate("INSERT INTO CREDITREQ " + "VALUES ('Undergraduate','Out of State', 0,12)");
				System.out.println("INSERT CREDITREQ RECORDS OF Undergraduate, Out of State, 0,12");
				stmt.executeUpdate("INSERT INTO CREDITREQ " + "VALUES ('Undergraduate','International', 9,12)");
				System.out.println("INSERT CREDITREQ RECORDS OF Graduate, Intersnational, 9,12");
			}
			
			if(set.contains("GRADESYS")) {
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('A+', 4.33)");
				System.out.println("INSERT GRADESYS RECORDS OF A+, 4.33");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('A', 4.00)");
				System.out.println("INSERT GRADESYS RECORDS OF A, 4.00");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('A-', 3.67)");
				System.out.println("INSERT GRADESYS RECORDS OF A-, 3.67");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('B+', 3.33)");
				System.out.println("INSERT GRADESYS RECORDS OF B+, 3.33");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('B', 3.00)");
				System.out.println("INSERT GRADESYS RECORDS OF B, 3.00");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('B-', 2.67)");
				System.out.println("INSERT GRADESYS RECORDS OF B-, 2.67");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('C+', 2.33)");
				System.out.println("INSERT GRADESYS RECORDS OF C+, 2.33");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('C', 2.00)");
				System.out.println("INSERT GRADESYS RECORDS OF C, 2.00");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('C-', 1.67)");
				System.out.println("INSERT GRADESYS RECORDS OF C-, 1.67");
				stmt.executeUpdate("INSERT INTO GRADESYS " + "VALUES ('F', 0.00)");
				System.out.println("INSERT GRADESYS RECORDS OF F, 0.00");
			}

			


			// //create table for entity Course
			// String createCourseSql = "CREATE TABLE COURSE" + 
			// 					   "(CID INTEGER NOT NULL,"+
			// 					   " DEPT VARCHAR(10) NOT NULL," +
			// 					   " TITLE VARCHAR(64) NOT NULL," +
			// 					   " LEV VARCHAR(13) NOT NULL," +
			// 					   " GPAREQ NUMERIC(1,2) NOT NULL," +
			// 					   " CREDIT INTEGER NOT NULL," +
			// 					   " CONSTRAINT C_CourseID_Should_be_Unique PRIMARY KEY (CID)) ";
			// stmt.executeUpdate(createCourseSql);
			// System.out.println("Created table COURSE in database");
			
			// //create tables for admin
   //  		String createAdminSql = "CREATE TABLE ADMIN" + 
			// 					   "(SSN INTEGER NOT NULL," +
			// 					   " EID INTEGER NOT　NULL," +
			// 					   " LASTNAME VARCHAR(15) NOT NULL," +
			// 					   " FIRSTNAME VARCHAR(15) NOT NULL," +
			// 					   " PASSWORD VARCHAR(20) NOT NULL," +
			// 					   " PRIMARY KEY (EID)) ";
    	
		 //    stmt.executeUpdate(createAdminSql);
			// System.out.println("Created table ADMIN in database");
			
			// //create table for entity student
			// String createStudentSql = "CREATE TABLE STUDENT" + 
			// 					   "(SID INTEGER NOT NULL," +
			// 					   " FIRSTNAME VARCHAR(15) NOT NULL," +
			// 					   " LASTNAME VARCHAR(15) NOT NULL," +
			// 					   " DEPT VARCHAR(20) NOT　NULL," +
			// 					   " GPA NUMERIC(1,3)," +
			// 					   " BILL VARCHAR(30)," +
			// 					   " PASSWORD VARCHAR(20) NOT NULL," +
			// 					   " EMAIL VARCHAR(20) NOT　NULL," +
			// 					   " LEV VARCHAR(13) NOT　NULL," +
			// 					   " RESIDENCE VARCHAR(30) NOT　NULL," +
			// 					   " COURSE_LIST VARCHAR(200)," +
			// 					   " RATE VARCHAR(15) NOT NULL," +
			// 					   " MIN_CREDIT INTEGER NOT　NULL," +
			// 					   " MAX_CREDIT INTEGER NOT　NULL," +
			// 					   " NOW_CREDIT INTEGER NOT　NULL," +
			// 					   " PRIMARY KEY (SID)) ";


			// stmt.executeUpdate(createStudentSql);
			// System.out.println("Created table STUDENT in database");


			// String createArrangementSql = "CREATE TABLE ARRANGEMENT" + 
			// 					   "(A_DAY VARCHAR(4) NOT NULL," +
			// 					   " A_HOUR VARCHAR(2) NOT NULL," +
			// 					   " SEMESTER VARCHAR(10) NOT NULL," +
			// 					   " A_YEAR VARCHAR(4) NOT　NULL," +
			// 					   " A_LOCATION VARCHAR(5)," +
			// 					   " SECTION_ID VARCHAR(3) NOT NULL," +
			// 					   " DEADLINE VARCHAR(8) NOT NULL," +
			// 					   " ENROLL_SUM INTEGER NOT　NULL," +
			// 					   " WAITING_SUM INTEGER NOT　NULL," +
			// 					   " ENROLL_NOW INTEGER NOT　NULL," +
			// 					   " WAITING_NOW INTEGER NOT NULL," +
			// 					   " PREREQUISITE VARCHAR(15) NOT NULL," +
			// 					   " FACULTY VARCHAR(20) NOT　NULL," +
			// 					   " CID INTEGER NOT NULL," +
			// 					   //" PRIMARY KEY (CID))";
			// 					   " PRIMARY KEY (CID,SEMESTER,A_YEAR,SECTION_ID)," +
			// 					   " FOREIGN KEY (CID) REFERENCES COURSE ON DELETE CASCADE)";
								   
			// stmt.executeUpdate(createArrangementSql);
			// System.out.println("Created table ARRANGEMENT in database");

			// // String createEnrollSql

			// String createEnrollSql = "CREATE TABLE ENROLL" +
			// 			" (SEMESTER VARCHAR(10) NOT NULL," +
			// 			" A_YEAR VARCHAR(4) NOT NULL, " +
			// 			" CID INTEGER NOT NULL," +
			// 			" SECTION_ID VARCHAR(4) NOT NULL," +
			// 			" PERMIT INTEGER NOT NULL," +
			// 			" STATUS VARCHAR(10) NOT NULL," +
			// 			" WL_NUM INTEGER," +
			// 			" SID INTEGER NOT NULL," +
			// 			" PRIMARY KEY (SEMESTER, A_YEAR, CID, SECTION_ID, SID)," +
			// 			" FOREIGN KEY (CID,SEMESTER,A_YEAR,SECTION_ID) REFERENCES ARRANGEMENT ON DELETE CASCADE," +
			// 			" FOREIGN KEY (SID) REFERENCES STUDENT ON DELETE CASCADE) ";
			

			// stmt.executeUpdate(createEnrollSql);
			// System.out.println("Created table ENROLL in database");

		}catch(SQLException se){
			se.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
      			try{
         			if(stmt!=null)
            		conn.close();
      				}catch(SQLException se){
      			}
      	try{
         	if(conn!=null)
            	conn.close();
      		}catch(SQLException se){
         							se.printStackTrace();
      								}
			}
		}
		
}