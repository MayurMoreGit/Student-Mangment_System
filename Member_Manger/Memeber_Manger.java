import java.io.*;
class memberManger
{
private static final String DATA_FILE="member.data";
public static void main(String gg[])
{
if(gg.length==0)
{
System.out.println("input specify all operation");
System.out.println("All operation :[add,remove,update,getAll,getByContact,getByCourse]");
return;
}
String operation=gg[0];
if(!isValidOperation(operation))
{
System.out.println("Invalid operation :"+operation);
System.out.println("valid operation :[add,remove,update,getAll,getByContact,getByCourse]");
return;
}

if(operation.equalsIgnoreCase("add"))
{
add(gg);
}else
if(operation.equalsIgnoreCase("remove"))
{
remove(gg);
}else
if(operation.equalsIgnoreCase("update"))
{
update(gg);
}else
if(operation.equalsIgnoreCase("getAll"))
{
getAll(gg);
}else
if(operation.equalsIgnoreCase("getByContact"))
{
getByContact(gg);
}else
if(operation.equalsIgnoreCase("getByCourse"))
{
getByCourse(gg);
}
}//main end

public static void add(String []data)
{
if(data.length!=5)
{
System.out.println("Invalid operation length");
return;
}
String mobileNumber=data[1];
String name=data[2];
String course=data[3];
if(!isValidCourse(course))
{
System.out.println("Invalid course :"+course);
System.out.println("valid course :[c,c++,java,python]");
return;
}
int fee;
try
{
fee=Integer.parseInt(data[4]);
}catch(NumberFormatException numberFormatException)
{
System.out.println("you should be used in int type value");
return;
}
try
{
File file=new File(DATA_FILE);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
String fMobileNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
if(fMobileNumber.equalsIgnoreCase(mobileNumber))
{
randomAccessFile.close();
System.out.println("mobileNumber"+"exist");
return;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.writeBytes(mobileNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(name);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(course);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(fee));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
System.out.println("member added");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
return;
}
}

public static void remove(String [] data)
{

if(data.length!=2)
{
System.out.println("your passed element is not valid :");
System.out.println("Usage: java memberManger remove mobileNumber ");
return;
}
String mobileNumber=data[1];
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("no member");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("Invlaid mobileNumber ");
return;
}
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber))
{
found=true;
break;
}
}
if(found==false)
{
System.out.println("Invalid mobile Number : "+mobileNumber);
randomAccessFile.close();

return;
}
System.out.println("deleting data of :"+mobileNumber);
System.out.println("Name of candidate is: "+fName);
File tmpfile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpfile,"rw");
tmpRandomAccessFile.setLength(0);
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber)==false)
{
tmpRandomAccessFile.writeBytes(fMobileNumber+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fCourse+"\n");
tmpRandomAccessFile.writeBytes(fFee+"\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
System.out.println("data remove");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}

public static void update(String [] data)
{
if(data.length!=5)
{
System.out.println("your passed element is not valid :");
System.out.println("Usage: java memberManger update mobileNumber, name,course,fees : ");
return;
}
String mobileNumber=data[1];
String name=data[2];
String course=data[3];
if(!isValidCourse(course))
{
System.out.println("Invalid course :"+course);
System.out.println("valid course :[c,c++,java,python]");
return;
}
int fee;
try
{
fee=Integer.parseInt(data[4]);
}catch(NumberFormatException numberFormatException)
{
System.out.println("you should be used in int type value");
return;
}
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("no member");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("Invlaid mobileNumber ");
return;
}
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber))
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
System.out.println("Invalid mobile Number : "+mobileNumber);
return;
}
System.out.println("updating data of :"+mobileNumber);
System.out.println("Name of candidate is: "+fName);
File tmpfile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpfile,"rw");
tmpRandomAccessFile.setLength(0);
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber)==false)
{
tmpRandomAccessFile.writeBytes(fMobileNumber+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fCourse+"\n");
tmpRandomAccessFile.writeBytes(fFee+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(mobileNumber+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(course+"\n");
tmpRandomAccessFile.writeBytes(fee+"\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
System.out.println("data update\n");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}


public static void getAll(String [] data)
{
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("member no exists");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
System.out.println("member no");
return;
}
String mobileNumber;
String name;
String course;
int fee;
int Totalfee=0;
int memberCount=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
mobileNumber=randomAccessFile.readLine();
name=randomAccessFile.readLine();
course=randomAccessFile.readLine();
fee=Integer.parseInt(randomAccessFile.readLine());
System.out.printf("%s %s %s %d\n",mobileNumber,name,course,fee);
Totalfee+=fee;
memberCount++;
}
randomAccessFile.close();
System.out.println("member is registerd "+memberCount);
System.out.println("Total is fees : "+Totalfee);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
return;
}
}


public static void getByContact(String [] data)
{
if(data.length!=2)
{
System.out.println("your passed element is not valid :");
return;
}
String mobileNumber=data[1];
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("Invlaid file is not exists");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("invalid mobile number : "+ mobileNumber);
return;
}
String fMobileNumber="";
String fname="";
String fcourse="";
int fFee=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
if(fMobileNumber.equalsIgnoreCase(mobileNumber)==true)
{
fname=randomAccessFile.readLine();
fcourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
found=true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.close();
if(found==false)
{
System.out.println("Invalid mobileNumber :"+mobileNumber);
return;
}
System.out.println("name :" +fname);
System.out.println("Course :" +fcourse);
System.out.println("fee :" +fFee);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
return;
}
}


public static void getByCourse(String [] data)
{
if(data.length!=2)
{
System.out.println("your passed element is not valid ");
System.out.println("usage add,remove,update,getAll,getByconact,getbyCourse");
}
String course=data[1];
if(isValidCourse(course)==false)
{
System.out.println("Invalid course :"+course);
return;
}
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("no member");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("Invlaid course ");
return;
}
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fCourse.equalsIgnoreCase(course))
{
System.out.println("mobileNumber :" +fMobileNumber);
System.out.println("name :" +fName);
System.out.println("Course :" +fCourse);
System.out.println("fee :" +fFee);
found=true;
}
}
randomAccessFile.close();
if(found==false)
{
System.out.println("this course is not asing anyone :" + course);
return;
}
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
return;
}
}




//helper
private static  boolean isValidOperation(String operation)
{
operation=operation.trim();
String operations[ ]={"add","update","remove","getALL","getByContact","getByCourse"};
for(int e=0;e<operations.length;e++)
{
if(operations[e].equalsIgnoreCase(operation)) return true;
}
return false;
}

private static boolean isValidCourse(String course)
{
course=course.trim();
String courses[ ]={"c","c++","python","java"};
for(int e=0;e<courses.length;e++)
{
if(courses[e].equalsIgnoreCase(course)) return true;
}
return false;
}
}




Member-Module Simple application to perform CRUD operations to store, retrieve, add, update remove getALL getByContact getBycourse details of student. Used 
 File handling , User can Add details of student, where the data will store in file member.data". User can Update details of student, User can get all record,User get  a student detail by contact number and course . User can Delete any student as required. User can view Details of all student. this application calculate  total fess of all student ,this application work command line interface.
Example java memberManger add 9999999999 "xyz" c++ 5000
