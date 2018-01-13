import java.sql.*;
import java.util.*;
import java.text.*;


public class student2 {

	static final String DB_URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USERID = "zli36";
    static final String PASSWORD = "200159301"; 
    static int STATUS=0;
    
    
	/*
	 * @input: key: UserId, Pw
	 * @status: 1: successfully login  
	 			0: fail to login  
	 			-1: exception
	 * @message: 1: Successfully log in  
	 			 0: Error: userid or password is not valid!  
	 			 -1: Exception message
	 * @schema
	 * @data
	 */
	public Query login (Map<String, String> input) {
		
		Query result =new Query();
		try{
			Class.forName(JDBC_DRIVER);
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet re=null;
			try{
				conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT * FROM STUDENT WHERE SID = ? AND PASSWORD = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				pstmt.setString(2, input.get("PASSWORD"));
				
				re = pstmt.executeQuery();
				if(!re.next()){
					result.message = "Error: userid or password is not valid!";  //no such user!
					result.status = -1;
				} else {
					result.message = "Successfully log in";  //okay
					result.status = 2;
				}
			}finally{
				close(re);
				close(pstmt);
				close(conn);
			}
		}catch (Throwable oop){
			result.status=-1;
			result.message = oop.getMessage();
			//return result;
		}
			
    	return result;
    }

   
    
    public Query showgrade(Map<String, String> input){
    	Query result=new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
    			String sql="SELECT CID, GRADE FROM ENROLL WHERE SID = ? AND STATUS = 'ENROLLED'";
    			pstmt=conn.prepareStatement(sql);
    			pstmt.clearParameters();
    			int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				re=pstmt.executeQuery();
				System.out.println("COURES ID"+"   GPA");
				while(re.next()){
					if(!re.getString("GRADE").equals("F")){
					System.out.println(re.getInt("CID")+"      "+"   "+re.getString("GRADE"));
					}
					result.schema.add(re.getString("GRADE"));
				}
				ArrayList<Double> mark=new ArrayList<Double>();
				for(String s:result.schema){
					if(s.equals("A")){
						mark.add(4.00);
					}else if(s.equals("A+")){
						mark.add(4.33);
					}else if(s.equals("A-")){
						mark.add(3.67);
					}else if(s.equals("B+")){
						mark.add(3.33);
					}else if(s.equals("B")){
						mark.add(3.00);
					}else if(s.equals("B-")){
						mark.add(2.67);
					}else if(s.equals("C+")){
						mark.add(2.33);
					}else if(s.equals("C")){
						mark.add(2.00);
					}else if(s.equals("C-")){
						mark.add(1.67);
					}
				}
				double sum=0;
				for(Double num:mark){
					sum=sum+num;
				}
				double average=sum/mark.size();
				result.average=sum/mark.size();
				if(average>=4.33){
					result.grade="A+";
				}else if(average>=4.00){
					result.grade="A";
				}else if(average>=3.67){
					result.grade="A-";
				}else if(average>=3.33){
					result.grade="B+";
				}else if(average>=3.00){
					result.grade="B";
				}else if(average>=2.67){
					result.grade="B-";
				}else if(average>=2.33){
					result.grade="C+";
				}else if(average>=2.00){
					result.grade="C";
				}else if(average>=1.67){
					result.grade="C-";
				}
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query showProfile(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT S.FIRSTNAME, S.LASTNAME, S.EMAIL, S.LEV, S.PHONE_NUM, S.RESIDENCE FROM STUDENT S WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				
				re = pstmt.executeQuery();
				if(re.next()){
					result.schema.add(re.getString("FIRSTNAME"));
					result.schema.add(re.getString("LASTNAME"));
					result.schema.add(re.getString("EMAIL"));
					String phone=String.valueOf(re.getLong("PHONE_NUM"));
					result.schema.add(phone);
					result.schema.add(re.getString("LEV"));
					result.schema.add(re.getString("RESIDENCE"));
				}
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query changefirstname(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="UPDATE STUDENT S SET S.FIRSTNAME=? WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				pstmt.setString(1, input.get("FIRSTNAME"));
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
				
				pstmt.executeUpdate();
				result.message="Your FirstName has changed";
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query changelastname(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="UPDATE STUDENT S SET S.LASTNAME=? WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				pstmt.setString(1, input.get("LASTNAME"));
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
				
				pstmt.executeUpdate();
				result.message="Your LASTName has changed";
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query changeemail(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="UPDATE STUDENT S SET S.EMAIL=? WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				pstmt.setString(1, input.get("EMIAL"));
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
				
				pstmt.executeUpdate();
				result.message="Your LASTName has changed";
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query changephone(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="UPDATE STUDENT S SET S.PHONE_NUM=? WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int phone=Integer.parseInt(input.get("PHONE"));
				pstmt.setInt(1, phone);
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
				
				pstmt.executeUpdate();
				result.message="Your PHONE has changed";
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query pendingcourse(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT E.CID, E.STATUS FROM ENROLL E WHERE E.SID=? AND (E.STATUS='WAITING' OR E.STATUS='PENDING_E' OR E.STATUS='PENDING_W' OR E.STATUS='REJECTED' )";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				re=pstmt.executeQuery();
				System.out.println("CID   "+"STATUS");
				while(re.next()){
					int cid = re.getInt("CID");
					String status = re.getString("STATUS");
					if(status.equals("PENDING_E")){
						status="Special request for enroll";
					}else if(status.equals("PENDING_W")){
						status="Special request for waitinglist";
					}
					System.out.println(cid+"   "+status);
				}
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query showbalance(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT S.BILL FROM STUDENT S WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				re=pstmt.executeQuery();
				while(re.next()){
					result.schema.add(re.getString("BILL"));
				}
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query showenrolledcourse(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT E.A_YEAR,E.SEMESTER, C.CID, C.TITLE  FROM COURSE C,ENROLL E WHERE E.SID=? AND E.STATUS='ENROLLED' AND C.CID=E.CID";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				re=pstmt.executeQuery();
				
				System.out.println("=============================================="+"\n"+
									"YEAR   SEMESTER   COURSE ID   TITLE");
				while(re.next()){
					int year=re.getInt("A_YEAR");
					String semester=re.getString("SEMESTER");
					int cid=re.getInt("CID");
					String title=re.getString("TITLE");
					System.out.print(year+"   ");
					System.out.printf("%8s", semester);
					System.out.println("   "+cid+"         "+title);
				}
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query paybill(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="UPDATE STUDENT S SET S.BILL=? WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				
				int sid=Integer.parseInt(input.get("SID"));
				int balance=Integer.parseInt(input.get("BALANCE"));
				int pay=Integer.parseInt(input.get("BILL"));
				int still=balance-pay;
				String bill=String.valueOf(still);
				pstmt.setString(1, bill);
				pstmt.setInt(2, sid);
				pstmt.executeUpdate();
				result.status=still;
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query enrollcourse(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT A.ENROLL_SUM, A.ENROLL_NOW, A.WAITING_SUM, A.WAITING_NOW, C.PREREQUISITE,C.GPAREQ, C.CREDIT FROM ARRANGEMENT A,COURSE C WHERE A.CID=? AND A.SEMESTER='SPRING' AND A.A_YEAR='2017' AND C.CID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				
				int cid=Integer.parseInt(input.get("CID"));
				
				pstmt.setInt(1, cid);
				pstmt.setInt(2, cid);
                re=pstmt.executeQuery();
				while(re.next()){
					result.enroll_sum=re.getInt("ENROLL_SUM");
					result.enroll_now=re.getInt("ENROLL_NOW");
					result.waiting_sum=re.getInt("WAITING_SUM");
					result.waiting_now=re.getInt("WAITING_NOW");
					result.pre_course=re.getString("PREREQUISITE");
					result.pre_gpa=re.getDouble("GPAREQ");
					result.credit=re.getInt("CREDIT");
				}
				int flag=0;
				int credit=0;
				if(result.credit>100){
					while(flag!=1){
						System.out.println("Please choose the credit you want "+"\n"+
										"1.1 credit"+"\n"+
										"2.2 credit"+"\n"+
										"2.3 credit"+"\n"+
										"0. exit and not enroll this course");
						Scanner scanner=new Scanner(System.in);
						String choice = scanner.nextLine();
						if(choice.equals("1")){
							result.credit=1;
							flag=1;
						}else if(choice.equals("2")){
							result.credit=2;
							flag=1;
						}else if(choice.endsWith("3")){
							result.credit=3;
							flag=1;
						}else if (choice.equals("0")){
							result.status=-8;
							return result;
						}else{
							System.out.println("please enter right number");
						}
						
					}
				}
				sql="SELECT S.GPA,S.COURSE_LIST FROM STUDENT S WHERE S.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
                re=pstmt.executeQuery();
                while(re.next()){
					result.average=re.getDouble("GPA");
					result.message=re.getString("COURSE_LIST");
				}
                String[] course_array=result.message.split(",");
                ArrayList<String> course_list=new ArrayList<String>();
                for(int i=0;i<course_array.length;i++){
                	course_list.add(course_array[i]);
                }
                String[] pre_course=result.pre_course.split(",");
                
                boolean pre=true;
                if(result.average<result.pre_gpa){
                	pre=pre && false;
                }
                if(pre_course[0].equals("NONE")){
                	pre=pre && true;
                }else if(pre_course[0].equals("SPECIAL")){
                	pre=pre && false;
                }else{
                	for(int i=0;i<pre_course.length;i++){
                		if(course_list.contains(pre_course[i])){
                			
                			pre=pre && true;
                		}else{
                			pre=pre && false;
                		}
                	}
                }
                if(pre==false){
                	if(pre_course[0].equals("SPECIAL")){
                		sql="INSERT INTO ENROLL VALUES ('SPRING', 2017, ?, '001', 1, 'PENDING_E', 0, ?, ?,'F')";
                		pstmt=conn.prepareStatement(sql);
                		pstmt.clearParameters();
                		pstmt.setInt(1, cid);
                		pstmt.setInt(2, sid);
                		pstmt.setInt(3, result.credit);
                		re=pstmt.executeQuery();
                		result.message="The course need Admin's permission to enroll, the requst has been send, please wait for the check by Admin";
                		result.status=3;
                	}else{
                		sql="INSERT INTO ENROLL VALUES ('SPRING', 2017, ?, '001', 1, 'REJECTED', 0, ?, ?,'F')";
                		pstmt=conn.prepareStatement(sql);
                		pstmt.clearParameters();
                		pstmt.setInt(1, cid);
                		pstmt.setInt(2, sid);
                		pstmt.setInt(3, result.credit);
                		re=pstmt.executeQuery();
                		result.message="you are not meet the requirement please check";
                		result.status=-1;
                	}
                }else{
                	if(result.enroll_now<result.enroll_sum){
                		sql="INSERT INTO ENROLL VALUES ('SPRING', 2017, ?, '001', 1, 'ENROLLED', 0, ?, ?,'F')";
        				pstmt=conn.prepareStatement(sql);
        				pstmt.clearParameters();
        				pstmt.setInt(1, cid);
        				pstmt.setInt(2, sid);
        				pstmt.setInt(3, result.credit);
                        re=pstmt.executeQuery();
                        sql="UPDATE ARRANGEMENT A SET A.ENROLL_NOW=ENROLL_NOW+1 WHERE A.CID=? AND A.A_YEAR=2017";
        				pstmt=conn.prepareStatement(sql);
        				pstmt.clearParameters();
        				pstmt.setInt(1, cid);
        				pstmt.executeUpdate();
        				result.message="you have enroll the class";
                		result.status=1;
                	}else if((result.enroll_now==result.enroll_sum)&&(result.waiting_now<result.waiting_sum)){
                		
                        sql="UPDATE ARRANGEMENT A SET A.WAITING_NOW=WAITING_NOW+1 WHERE A.CID=? AND A.A_YEAR=2017";
        				pstmt=conn.prepareStatement(sql);
        				pstmt.clearParameters();
        				pstmt.setInt(1, cid);
        				pstmt.executeUpdate();
        				
                		sql="SELECT A.WAITING_NOW FROM ARRANGEMENT A WHERE A.CID=? AND A.A_YEAR=2017";
                		pstmt=conn.prepareStatement(sql);
        				pstmt.clearParameters();
        				pstmt.setInt(1, cid);
        				re=pstmt.executeQuery();
        				int waitnum=0;
        				while(re.next()){
        					waitnum=re.getInt("WAITING_NOW");
        				}
        				sql="INSERT INTO ENROLL VALUES ('SPRING', 2017, ?, '001', 1, 'WAITING', ?, ?, ?,'F')";
        				pstmt=conn.prepareStatement(sql);
        				pstmt.clearParameters();
        				pstmt.setInt(1, cid);
        				pstmt.setInt(2, waitnum);
        				pstmt.setInt(3, sid);
        				pstmt.setInt(4, result.credit);
        				pstmt.executeUpdate();
                        result.message="you are in the waiting list";
                		result.status=2;
        				
                	}else{
                		result.message="There is no sit for waiting list";
                		result.status=-2;
                	}
                }
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query showavailable(Map<String, String> input){
    	Query result= new Query();
    	ArrayList<Integer> ccc=new ArrayList<Integer>();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
    			String sql="SELECT E.CID, E.STATUS FROM ENROLL E WHERE E.SID=?";
    			pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				re=pstmt.executeQuery();
				while(re.next()){
					ccc.add(re.getInt("CID"));
				}
    			sql="SELECT A.CID,A.A_HOUR,A.A_DAY,A.A_LOCATION, C.TITLE,C.GPAREQ,C.PREREQUISITE FROM ARRANGEMENT A, COURSE C WHERE A.A_YEAR='2017' AND A.SEMESTER='SPRING' AND A.CID=C.CID AND C.LEV=(SELECT S.LEV FROM  STUDENT S WHERE S.SID=?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(1, sid);
				re=pstmt.executeQuery();
				System.out.println("=============================================="+"\n"+
						"CID   COURSE HOUR   COURSE DAY   COUSE LOCATION   GPA_REQ   COURSE_REQ    COURESE_NAME");
				while(re.next()){
					if(!ccc.contains(re.getInt("CID"))){
					int cid=re.getInt("CID");
					String hour=re.getString("A_HOUR");
					String day=re.getString("A_DAY");
					String location=re.getString("A_LOCATION");
					String title=re.getString("TITLE");
					int gpa=re.getInt("GPAREQ");
					String pre=re.getString("PREREQUISITE");
					System.out.printf("%-6s", cid);
					System.out.printf("%-14s",hour);
					System.out.printf("%-13s",day);
					System.out.printf("%-17s",location);
					System.out.printf("%-10s",gpa);
					System.out.printf("%-14s",pre);
					System.out.printf(title+"\n");
					}
				}
				
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query specialrequest2(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="INSERT INTO ENROLL VALUES ('SPRING', 2017, ?, '001', 1, 'PENDING_W', 0, ?, ?,'F')";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int cid=Integer.parseInt(input.get("CID"));
				pstmt.setInt(1, cid);
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
				pstmt.setInt(3, result.credit);
                re=pstmt.executeQuery();
				result.message="you request has been sent, please wait for the check by the Admin";
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query deletenroll(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
    			String sql="SELECT E.STATUS, E.WL_NUM FROM ENROLL E WHERE E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.CID=? AND E.SID=?";
    			pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int cid=Integer.parseInt(input.get("CID"));
				pstmt.setInt(1, cid);
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
                re=pstmt.executeQuery();
                String status="";
                int wl_num=0;
                while(re.next()){
                	status=re.getString("STATUS");
                	wl_num=re.getInt("WL_NUM");
                }
                sql="SELECT A.WAITING_NOW FROM ARRANGEMENT A WHERE A.A_YEAR=2017 AND A.SEMESTER='SPRING' AND A.CID=?";
    			pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				pstmt.setInt(1, cid);
                re=pstmt.executeQuery();
                int waitingnum=0;
                while(re.next()){
                	waitingnum=re.getInt("WAITING_NOW");
                }
                //System.out.println(waitingnum);
                if(status.equals("ENROLLED")){
                	sql="DELETE FROM ENROLL E WHERE E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.CID=? AND E.SID=?";
    				pstmt=conn.prepareStatement(sql);
    				pstmt.clearParameters();
    				cid=Integer.parseInt(input.get("CID"));
    				pstmt.setInt(1, cid);
    				sid=Integer.parseInt(input.get("SID"));
    				pstmt.setInt(2, sid);
                    pstmt.executeUpdate();
                	sql="UPDATE ARRANGEMENT A SET A.ENROLL_NOW=ENROLL_NOW-1 WHERE A.CID=? AND A.A_YEAR=2017";
                    pstmt=conn.prepareStatement(sql);
    				pstmt.clearParameters();
    				pstmt.setInt(1, cid);
    				pstmt.executeUpdate();
    				if(waitingnum>=1){
    					//System.out.println("enter");
    					sql="UPDATE ENROLL E SET E.WL_NUM=E.WL_NUM-1, E.STATUS='ENROLLED' WHERE E.CID=? AND E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.STATUS='WAITING' AND E.WL_NUM=1";
    					pstmt=conn.prepareStatement(sql);
    					pstmt.clearParameters();
    					cid=Integer.parseInt(input.get("CID"));
    					pstmt.setInt(1, cid);
    					pstmt.executeUpdate();
    					//System.out.println("1");
    					sql="UPDATE ARRANGEMENT A SET A.ENROLL_NOW=ENROLL_NOW+1 WHERE A.CID=? AND A.A_YEAR=2017";
    					pstmt=conn.prepareStatement(sql);
    					pstmt.clearParameters();
    					pstmt.setInt(1, cid);
    					pstmt.executeUpdate();
    					//System.out.println("2");
    					sql="UPDATE ARRANGEMENT A SET A.WAITING_NOW=WAITING_NOW-1 WHERE A.CID=? AND A.A_YEAR=2017";
    					pstmt=conn.prepareStatement(sql);
    					pstmt.clearParameters();
    					pstmt.setInt(1, cid);
    					pstmt.executeUpdate();
    					//System.out.println("3");
    					sql="UPDATE ENROLL E SET E.WL_NUM=E.WL_NUM-1 WHERE E.CID=? AND E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.WL_NUM<>1 AND E.STATUS='WAITING'";
    					pstmt=conn.prepareStatement(sql);
    					pstmt.clearParameters();
    					pstmt.setInt(1, cid);
    					pstmt.executeUpdate();
    					//System.out.println("4");
    					
    				}
    				
                }else
                if(status.equals("WAITING")){
                    sql="UPDATE ARRANGEMENT A SET A.WAITING_NOW=WAITING_NOW-1 WHERE A.CID=? AND A.A_YEAR=2017";
                    pstmt=conn.prepareStatement(sql);
    				pstmt.clearParameters();
    				pstmt.setInt(1, cid);
    				pstmt.executeUpdate();
    				sql="DELETE FROM ENROLL E WHERE E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.CID=? AND E.SID=?";
    				pstmt=conn.prepareStatement(sql);
    				pstmt.clearParameters();
    				cid=Integer.parseInt(input.get("CID"));
    				pstmt.setInt(1, cid);
    				sid=Integer.parseInt(input.get("SID"));
    				pstmt.setInt(2, sid);
                    pstmt.executeUpdate();
                    sql="UPDATE ENROLL E SET E.WL_NUM=E.WL_NUM-1 WHERE E.CID=? AND E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.WL_NUM>?";
    				pstmt=conn.prepareStatement(sql);
    				pstmt.clearParameters();
    				cid=Integer.parseInt(input.get("CID"));
    				pstmt.setInt(1, cid);
    				pstmt.setInt(2, wl_num);
                    pstmt.executeUpdate();
                }else{
				sql="DELETE FROM ENROLL E WHERE E.A_YEAR=2017 AND E.SEMESTER='SPRING' AND E.CID=? AND E.SID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				cid=Integer.parseInt(input.get("CID"));
				pstmt.setInt(1, cid);
				sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
                pstmt.executeUpdate();
                }
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public 	Query specialrequest1(Map<String, String> input){
    	Query result= new Query();
    	try{
    		Class.forName(JDBC_DRIVER);
    		Connection conn=null;
    		PreparedStatement pstmt = null;
    		ResultSet re=null;
    		try{
    			conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="INSERT INTO ENROLL VALUES ('SPRING', 2017, ?, '001', 1, 'PENDING_E', 0, ?, ?,'F')";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				int cid=Integer.parseInt(input.get("CID"));
				pstmt.setInt(1, cid);
				int sid=Integer.parseInt(input.get("SID"));
				pstmt.setInt(2, sid);
				pstmt.setInt(3, result.credit);
                re=pstmt.executeQuery();
				result.message="you request has been sent, please wait for the check by the Admin";
				
    		}finally{
    			close(re);
    			close(pstmt);
    			close(conn);
    		}
    	}catch(Throwable oop){
    		result.status=-1;
    		result.message = oop.getMessage();
    		return result;
    	}
    	return result;
    }
    
    public static void main(String[] args){
    	student2 stu=new student2();
    	Map<String, String> input = new HashMap<String, String>();
    	input.put("SID", args[0]);
    	STATUS=1;
    	input.put("STATUS", "-1");
    	Query q=new Query();
//    	while(true){
//    		System.out.println("Please log in or sign in as a student"+"\n"+
//    							"1.login"+"\n"+
//    							"0.exit");
//    		
//    		try{
//    			Scanner scanner = new Scanner(System.in);
//    			int s=scanner.nextInt();
//    			switch(s){
//    				case 1:
//    					scanner= new Scanner(System.in);
//    					System.out.println("enter your SID");
//    					String y=scanner.nextLine();
//    					input.put("SID", y);
//    					System.out.println(input.get("SID"));
//    					System.out.println("enter PASSWORD");
//    					y=scanner.nextLine();
//    					input.put("PASSWORD", y); 
//    					System.out.println(input.get("PASSWORD"));
//    					q=stu.login(input);
//    					System.out.println(q.message);
//    					System.out.println(q.status);
//    					
//    					if(q.status==1){
//    					STATUS=2;
//    					}
//    					
//    					break;
//    				case 0:
//    					STATUS=-1;
//    					break;
//    				
//    			}
//    			
//    		}catch(InputMismatchException e){
//    			System.out.println("Please input an integer");
//    		}
//    		if(STATUS==2 || STATUS==-1 ){
//    			break;
//    		}
//    		
//    	}
    	
        while(STATUS!=-1){
        		
        	
        	while(true){
        		if(STATUS==-1){
        			break;
        		}
        		mainmenu();
        		
        		try{
        			Scanner scanner = new Scanner(System.in);
        			int s=scanner.nextInt();
        			
        			switch(s){
        				case 1:
        					STATUS=21;
        					q=stu.showProfile(input);
        					System.out.println(q.schema.size());
        					System.out.println("==============================================");
        					System.out.println("This is your profile detail"+"\n"+
        										"First Name: " + q.schema.get(0)+"\n"+
        										"Last Name: "+ q.schema.get(1)+"\n"+
        										"Email: " + q.schema.get(2)+"\n"+
        										"Phone: "+ q.schema.get(3)+"\n"+
        										"Level: "+ q.schema.get(4)+"\n"+
        										"Status: "+ q.schema.get(5));
        					System.out.println("==============================================");
        						System.out.println(
        											"1.update your firstname"+"\n"+
        											"2.update your lastname"+"\n"+
        											"3.update your email"+"\n"+
        											"4.update your phone"+"\n"+
        											"0.go to previous menus");
        						System.out.println("==============================================");
        						s=scanner.nextInt();
        						switch(s){
        							case 1:
        								scanner=new Scanner(System.in);
        								System.out.println("Please input your firstname");
        								String y1 = scanner.nextLine();
        								input.put("FIRSTNAME", y1);
        								
        								q=stu.changefirstname(input);
        								System.out.println("update ok");
        								break;
        							case 2:
        								scanner=new Scanner(System.in);
        								System.out.println("Please input your lastname");
        								String y2 = scanner.nextLine();
        								input.put("LASTNAME", y2);
        								
        								q=stu.changelastname(input);
        								System.out.println("update ok");
        								break;
        							case 3:
        								scanner=new Scanner(System.in);
        								System.out.println("Please input your new email");
        								String y3 = scanner.nextLine();
        								input.put("EMIAL", y3);
        								
        								q=stu.changeemail(input);
        								System.out.println("update ok");
        								break;
        							case 4:
        								scanner=new Scanner(System.in);
        								System.out.println("Please input your new phone number");
        								String y4 = scanner.nextLine();
        								input.put("PHONE", y4);
        								
        								q=stu.changephone(input);
        								System.out.println("update ok");
        								break;
        							default:
        								break;
        						}
        						
        					break;
        				case 2:
        					STATUS=22;
        					System.out.println("==============================================");
        					System.out.println("This is your added course");
        					q=stu.showenrolledcourse(input);
        					System.out.println("==============================================");
        					System.out.println("This is your available course");
        					q=stu.showavailable(input);
        					System.out.println("==============================================");
        					System.out.println("1.enroll"+"\n"+
        									   "2.drop"+"\n"+
        										"0.previous menus");
        					s=scanner.nextInt();
        					switch(s){
        						case 1:
        							scanner=new Scanner(System.in);
        							System.out.println("enter your couse id");
        							int cid=scanner.nextInt();
        							input.put("CID", String.valueOf(cid));
        							q=stu.enrollcourse(input);
        							if(q.status==-8)break;
        							System.out.println(q.message);
        							if(q.status==2){
        								scanner=new Scanner(System.in);
        								System.out.println("Do do want a special enrollment request for this course"+"\n"+
        												   "1.yes, please ask Admin to put me into enrolled list"+"\n"+
        												   "ohter input: no");
        								String confirm=scanner.nextLine();
        								if(confirm.equals("1")){
        									q=stu.deletenroll(input);
        									q=stu.specialrequest1(input);
        									System.out.println(q.message);
        								}
        							}else if(q.status==-2){
        								scanner=new Scanner(System.in);
        								System.out.println("Do do want a special enrollment request for this course"+"\n"+
        												   "1.yes, please ask Admin to put me into enrolled list"+"\n"+
        												   "2.yes, please ask Admin to put me into waiting list"+"\n"+
        												   "ohter input: no");
        								String confirm=scanner.nextLine();
        								if(confirm.equals("2")){
        									q=stu.specialrequest2(input);
        									System.out.println(q.message);
        								}else if (confirm.endsWith("1")){
        									q=stu.specialrequest1(input);
        									System.out.println(q.message);
        								}
        								
        							}
        							break;
        						case 2:
        							scanner=new Scanner(System.in);
        							System.out.println("enter your couse id that you want to drop");
        							int cid2=scanner.nextInt();
        							input.put("CID", String.valueOf(cid2));
        							q=stu.deletenroll(input);
        							System.out.println("course has been dropped");
        							break;
        						case 0:
        							break;
        					}
        					break;
        				case 3:
        					STATUS=23;
        					System.out.println("==============================================");
        					System.out.println("your pending course");
        					q=stu.pendingcourse(input);
        					System.out.println("==============================================");
        					while(true){
        						System.out.println("press 0 to previous menue");
        						scanner=new Scanner(System.in);
        						String line=scanner.nextLine();
        						if(line.equals("0"))break;
        					}
        					break;
        				case 4:
        					STATUS=24;
        					System.out.println("==============================================");
        					q=stu.showgrade(input);
        					System.out.print("your grade of all the courses is "+q.grade+ " or ");
        					System.out.printf("%3.2f", q.average);
        					System.out.println("");
        					System.out.println("==============================================");
        					while(true){
        						System.out.println("press 0 to previous menue");
        						scanner=new Scanner(System.in);
        						String line=scanner.nextLine();
        						if(line.equals("0"))break;
        					}
        					break;
        				case 5:
        					STATUS=25;
        					q=stu.showbalance(input);
        					input.put("BALANCE",q.schema.get(0));
        					System.out.println("==============================================");
        					System.out.println("your balence: you still need to pay "+ q.schema.get(0));
        					System.out.println("==============================================");
        					System.out.println("");
        					System.out.println("1.pay"+"\n"+
									"0.previous menus");
        					s=scanner.nextInt();
        					switch(s){
        						case 1:
        							scanner=new Scanner(System.in);
        							System.out.println("enter your payment");
        							String payacount=scanner.nextLine();
        							input.put("BILL", payacount);
        							q=stu.paybill(input);
        							System.out.println("you have paid: "+payacount+" and you still need to pay: "+ q.status);
        							System.out.println("");
        							break;
        						case 0:
        							break;
        					}
        					break;
        				case 0:
        					STATUS=-1;
        				default:
        					break;
        			}
        			
        		}catch(InputMismatchException e){
        			System.out.println("Please input an integer");
        		}
        	}
        	
        	
        }
    	
    }
   
	/****************************************************************************/

	static void mainmenu(){
		System.out.println("Please input a number"+"\n"+
				"1.View/Edit Profile"+"\n"+
				"2.View Courses/Enroll/Drop course"+"\n"+
				"3.View Pending courses"+"\n"+
				"4.View Grades"+"\n"+
				"5.View/Pay Bill"+"\n"+
				"0.Exit");
	}

	static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }

    // static void close(ResultSetMetaData rsmd) {
    //     if(rsmd != null) {
    //         try { rsmd.close(); } catch(Throwable whatever) {}
    //     }
    // }

}

