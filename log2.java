import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class log2 {
	static final String DB_URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USERID = "zli36";
    static final String PASSWORD = "200159301"; 
    static int STATUS=0;
	public Query login (Map<String, String> input) {
		
		Query result =new Query();
		try{
			Class.forName(JDBC_DRIVER);
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet re=null;
			try{
				conn=DriverManager.getConnection(DB_URL, USERID, PASSWORD);
				String sql="SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.clearParameters();
				String username=input.get("SID");
				pstmt.setString(1, username);
				pstmt.setString(2, input.get("PASSWORD"));
				re = pstmt.executeQuery();
				if(!re.next()){
					result.message = "Error: userid or password is not valid!";  //no such user!
					result.status = -1;
				} else {
					result.message = "Successfully log in";  //okay
					result.status = 1;
				}
				if(result.status==-1){
					
					
					
				    sql="SELECT * FROM STUDENT WHERE SID = ? AND PASSWORD = ?";
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

	public static void main(String[] args){
		log2 logg=new log2();
		Map<String, String> loginput = new HashMap<String, String>();
    	loginput.put("SID", "0");
    	loginput.put("STATUS", "-1");
    	Query q=new Query();
		int STATUS=-2;
		while(true){
			String header =	"\n\n" + "*************************************************************\n\n" +
					"     Welcome to WolfPack" +
					"\n\n" + "*************************************************************\n";
				System.out.println(header);
				System.out.println("1.log in"+"\n"+
									"0.exit system");
    		
    		try{
    			Scanner scanner = new Scanner(System.in);
    			int s=scanner.nextInt();
    			switch(s){
    				case 1:
    					STATUS=1;
    					scanner= new Scanner(System.in);
    					System.out.println("For Admin enter your username, For student enter your student ID");
    					String x=scanner.nextLine();
    					loginput.put("SID", x);
    					//System.out.println(loginput.get("SID"));
    					System.out.println("enter PASSWORD");
    					String y=scanner.nextLine();
    					loginput.put("PASSWORD", y); 
    					//System.out.println(loginput.get("PASSWORD"));
    					q=logg.login(loginput);
    					System.out.println(q.message+"\n");
    					String[] id=new String[1];
    					//System.out.println(q.status);
    					if(q.status==1){
    						id[0]=x;
    						//id[1]=y;
    						action_of_admin.main(id);
    					}else if(q.status==2){
    						id[0]=x;
    						
    						student2.main(id);
    					}
    					break;
    				case 0:
    					STATUS=-1;
    					break;
    				
    			}
    			
    		}catch(InputMismatchException e){
    			System.out.println("Please input an integer");
    		}
    		if( STATUS==-1 ){
    			break;
    		}
    		
    	}
		
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
