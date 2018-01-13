/*** 3 Entity Tables: Course, Employee, Student;
 *** 1 Weak Entity Table: Arrangement
 *** 2 Relationships: Belong,Enroll
*/

import java.sql.*;
import java.util.*;


public class insertValues{
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
					stmt.executeUpdate("INSERT INTO ADMIN " + "VALUES (123456789, 0001, 'BOSS1', 'First1', 'PassW1')");
					System.out.println("INSERT 0001 INTO TABLE ADMIN");
					stmt.executeUpdate("INSERT INTO ADMIN " + "VALUES (123456788, 0002, 'BOSS2', 'First2', 'PassW2')");
					System.out.println("INSERT 0002 INTO TABLE ADMIN");
				}

			//insert into table course
			if(set.contains("COURSE")) {
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (519, 'CSC', 'DEVOPS', 'grad', 3.00, 3)");
					System.out.println("INSERT 519 INTO TABLE COURSE");
					stmt.executeUpdate("INSERT INTO COURSE " + "VALUES (505, 'CSC', 'ALGORITHM', 'undergrad', 3.50, 3)");
					System.out.println("INSERT 505 INTO TABLE COURSE");
				}


			//insert into table student
			if(set.contains("STUDENT")) {
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (200159001, 'FRANK', 'GRAY', 'CSC', 2.994, '50.00','password','fgray@ncsu.edu','grad','NC','500,600,502','1.00','9','30','18')");
					System.out.println("INSERT S1 INTO TABLE STUDENT");
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (200159002, 'BOB', 'WHITE', 'CSC', 3.254, '50.00','password','bwhite@ncsu.edu','undergrad','NC','400,300,502','1.00','12','40','12')");
					System.out.println("INSERT S2 INTO TABLE STUDENT");
					stmt.executeUpdate("INSERT INTO STUDENT " + "VALUES (200159003, 'ANA', 'OG', 'CSC', 3.553, '50.00','password','aog@ncsu.edu','grad','INTERNATIONAL','510,300,502','4.00','9','30','9')");
					System.out.println("INSERT S3 INTO TABLE STUDENT");
				}




			

		
			//insert into table arrangement
			if(set.contains("ARRANGEMENT")) {
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '8-9', 'SPRING', '2016', 'HUNT', '001','2016.4.20',80,20,80,16,'510','PARNIN',519)");
					System.out.println("INSERT 519S INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '8-9', 'FALL', '2016', 'HUNT', '001','2016.9.20',80,20,60,0,'510','PARNIN',519)");
					System.out.println("INSERT 519F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '8-9', 'FALL', '2016', 'EB1', '001','2016.9.20',80,20,80,3,'NO','DAVID',505)");
					System.out.println("INSERT 505F INTO TABLE ARRANGEMENT");
					stmt.executeUpdate("INSERT INTO ARRANGEMENT " + "VALUES ('MON,WED', '8-9', 'FALL', '2016', 'EB2', '002','2016.9.20',80,20,40,0,'NO','HERB',505)");
					System.out.println("INSERT 505F INTO TABLE ARRANGEMENT");
				}

			if(set.contains("ENROLL")) {
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', '2016', '505', '001', 1, 'ENROLLED',0,200159001,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 505 SECTION 001 FOR STUDENT 200159001  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', '2016', '505', '001', 1, 'ENROLLED',0,200159002,'F')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 505 SECTION 001 FOR STUDENT 200159002  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', '2016', '505', '002', 1, 'DENIED',0,200159001,'A+')");
					System.out.println("INSERT ENROLLMENT DENY RECORDS OF 2016 FALL 505 SECTION 002 FOR STUDENT 200159001  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', '2016', '519', '001', 1, 'ENROLLED',0,200159001,'B')");
					System.out.println("INSERT ENROLLMENT RECORDS OF 2016 FALL 519 SECTION 001 FOR STUDENT 200159001  INTO TABLE ENROLL");
					stmt.executeUpdate("INSERT INTO ENROLL " + "VALUES ('FALL', '2016', '519', '001', 1, 'WAITLIST',2,200159003,'C')");
					System.out.println("INSERT ENROLLMENT WAITLIST 2 RECORDS OF 2016 FALL 505 SECTION 001 FOR STUDENT 200159003  INTO TABLE ENROLL");
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