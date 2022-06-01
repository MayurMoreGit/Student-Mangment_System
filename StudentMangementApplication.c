#include<stdio.h>
#include<stdlib.h>
#include<string.h>
struct Student
{
int rollNumber;
char name[36];
char gender;
};
void printLine(char g,int size)
{
int i;
for(i=1;i<=size;i++) printf("%c",g);

printf("\n");
}
void addStudent();
void editStudent();
void deleteStudent();
void searchStudent();
void displayListOfStudent();
int main ()
{
int ch;
while(1)
{
printf("1.add student\n");
printf("2.edit student\n");
printf("3.delete student\n");
printf("4.search student\n");
printf("5.displayListOf student\n");
printf("6.Exit\n");
printf("Enter your choice : ");
scanf("%d",&ch);
fflush(stdin);
if(ch==1) addStudent();
else if(ch==2) editStudent();
else if(ch==3) deleteStudent();
else if(ch==4) searchStudent();
else if(ch==5) displayListOfStudent();
else if(ch==6) break;
else printf("Invalid choice\n");
}
return 0;
}
void addStudent()
{
FILE *f;
struct Student t,g;
int rollNumber;
char name[37];
char gender;
char m;
printf("student (Add Module)\n");
printf("enter roll number : ");
scanf("%d",&rollNumber);
fflush(stdin);
if(rollNumber<=0)
{
printf("Invalid rollNumber\n");
return;
}
f=fopen("Student.dat","rb");
if(f!=NULL)
{
while(1)
{
fread(&t,sizeof(struct Student),1,f);
if(feof(f)) break;
if(rollNumber== t.rollNumber)
{
printf("That roll number has been allocated to %s\n",t.name);
fclose(f);
return;
}
}
fclose(f);
}
printf("Enter  name :");
fgets(name,37,stdin);
fflush(stdin);
name[strlen(name)-1]='\0';
printf("Enter gender (M/F):");
gender=getchar();
fflush(stdin);
if(gender !='M' && gender!='m' && gender !='F' && gender!='f')
{
printf("Invalid gender\n");
return;
}
printf("save (Y/N) : ");
m=getchar ();
fflush(stdin);
if(m!='y' && m!='Y')
{
printf("Student not added\n");
return;
}
g.rollNumber=rollNumber;
strcpy(g.name,name);
g.gender=gender;
f=fopen("Student.dat","ab");
fwrite(&g,sizeof(struct Student),1,f);
fclose(f);
printf("Student added,press enter to continue............");
getchar();
fflush(stdin);
}


void editStudent()
{
FILE *f1,*f2;
int found;
struct Student g,t;
int rollNumber;
char name[37];
char gender;
char m;
printf("Student (Edit Module\n");
printf("Enter roll number of the Student to edit : ");
scanf("%d",&rollNumber);
fflush(stdin);
if(rollNumber<=0)
{
printf("Invalid rollNumber\n");
return;
}
found=0;
f1=fopen("Student.dat","rb");
if(f1==NULL)
{
printf("Invalid roll Number\n");
return;
}
found=0;
while(1)
{
fread(&t,sizeof(struct Student),1,f1);
if(feof(f1)) break;
if(rollNumber==t.rollNumber)
{
found=1;
break;
}
}
fclose(f1);
if(found==0)
{
printf("Invalid roll number\n");
return;
}
printf("Name ; %s\n",t.name);
if(t.gender=='m' || t.gender=='M')
{
printf("Gender :Male\n");
}
else
{
printf("Gender : Feamle\n");
}
printf("Edit (Y/N):");
m=getchar();
fflush(stdin);
if(m!='y' && m!='Y')
{
printf("student not update\n");
return;
}
printf("Enter  name :");
fgets(name,37,stdin);
fflush(stdin);
name[strlen(name)-1]='\0';
printf("enter gender (M/F):");
gender=getchar();
fflush(stdin);
if(gender !='M' && gender!='m' && gender !='F' && gender!='f')
{
printf("Invalid gender\n");
return;
}
printf("Update (Y/N) : ");
m=getchar();
fflush(stdin);
if(m!='y' && m!='Y')
{
printf("student not update\n");
return;
}
g.rollNumber=rollNumber;
strcpy(g.name,name);
g.gender=gender;
f1=fopen("Student.dat","rb");
f2=fopen("tmp.tmp","wb");
while(1)
{
fread(&t,sizeof(struct Student),1,f1);
if(feof(f1)) break;
if(rollNumber!=t.rollNumber)
{
fwrite(&t,sizeof(struct Student),1,f2);
}
else
{
fwrite(&g,sizeof(struct Student),1,f2);
}
}
fclose(f1);
fclose(f2);
f1=fopen("Student.dat","wb");
f2=fopen("tmp.tmp","rb");
while(1)
{
fread(&t,sizeof(struct Student),1,f2);
if(feof(f2)) break;
fwrite(&t,sizeof(struct Student),1,f1);
}
fclose(f1);
fclose(f2);
f2=fopen("tmp.tmp","w");
fclose(f2);
printf("Student update,press enter to continue.........");
getchar();
fflush(stdin);
}

void deleteStudent()
{
FILE *f1,*f2;
int found;
struct Student t;
int rollNumber;
char m;
printf("Student (delete Module)\n");
printf("Enter roll number of the student to delete : ");
scanf("%d",&rollNumber);
fflush(stdin);
if(rollNumber<=0)
{
printf("Invalid rollNumber\n");
return;
}
found =0;
f1=fopen("Student.dat","rb");
if(f1==NULL)
{
printf("Invalid roll number\n");
return;
}
found=0;
while(1)
{
fread(&t,sizeof(struct Student),1,f1);
if(feof(f1)) break;
if(rollNumber==t.rollNumber)
{
found=1;
break;
}
}
fclose(f1);
if(found==0)
{
printf("Invalid rollNumber\n");
return;
}
printf("Name :%s\n",t.name);
if(t.gender=='m' || t.gender=='M')
{
printf("Gender :Male\n");
}
else
{
printf("Gender :Female\n");
}
printf("Delete (Y/N) : ");
m=getchar();
fflush(stdin);
if(m!='y' && m!='Y')
{
printf("Student not delete ");
return;
}
f1=fopen("Student.dat","rb");
f2=fopen("tmp.tmp","wb");
while(1)
{
fread(&t,sizeof(struct Student),1,f1);
if(feof(f1)) break;
if(rollNumber!=t.rollNumber)
{
fwrite(&t,sizeof(struct Student),1,f2);
}
}
fclose(f1);
fclose(f2);
f1=fopen("Student.dat","wb");
f2=fopen("tmp.tmp","rb");
while(1)
{
fread(&t,sizeof(struct Student),1,f2);
if(feof(f2)) break;
fwrite(&t,sizeof(struct Student),1,f1);
}
fclose(f1);
fclose(f2);
f2=fopen("tmp.tmp","w");
fclose(f2);
printf("Student delete,press enter to continue.........");
getchar();
fflush(stdin);
}

void searchStudent()
{
FILE *f;
struct Student t;
int rollNumber;
int found;
printf("Student (Search Modeule)\n");
printf("Enter a rollNumber");
scanf("%d",&rollNumber);
fflush(stdin);
if(rollNumber<=0)
{
printf("Invalid rollNumber\n");
return;
}
f=fopen("Student.dat","rb");
if(f==NULL)
{
printf("Invalid rollNumber\n");
return;
}
found=0;
while(1)
{
fread(&t,sizeof(struct Student),1,f);
if(feof(f)) break;
if(rollNumber==t.rollNumber)
{
found=1;
break;
}
}
fclose(f);
if(found==0)
{
printf("Invalid rollNumber\n");
return;
}
printf("Name :%s\n",t.name);
if(t.gender=='m' || t.gender=='M')
{
printf("Gender :Male\n");
}
else
{
printf("Gender :Female\n");
}
printf("Press Enter  a continue............. : ");
getchar();
fflush(stdin);
}
 

void displayListOfStudent()
{
FILE *f;
int newPage,pageSize;
int Sno;
struct Student t;
f=fopen("Student.dat","rb");
if(f==NULL)
{
printf("Student (ListModule)\n");
printLine('-',70);
printf("%10s %-12s %-35s %s\n","Sno", " rollNumber"," Name","gender");
printLine('-',70);
printf("no Student added\n");
printLine('-',70);
printf("press enter to continue.........");
getchar();
fflush(stdin);
return;
}
newPage=1;
pageSize=5;
Sno=0;
while(1)
{
if(newPage==1)
{
printf("Student (List Module)\n");
printLine('-',70);
printf("%10s %-12s %-35s  %s\n"," Sno","   rollNumber","   name","Gender");
printLine('-',70);
newPage=0;
}
fread(&t,sizeof(struct Student),1,f);
if(feof(f)) break;
Sno++;
if(t.gender=='M' || t.gender=='m')
{
printf("%6d   %10d        %-25s          %-6s\n",Sno,t.rollNumber,t.name,"Male");
}
else
{
printf("%6d   %10d        %-25s          %-6s\n",Sno,t.rollNumber,t.name,"Female");
}
if(Sno %pageSize==0)
{
printLine('-',70);
printf("press enter a to continue..............");
getchar();
fflush(stdin);
newPage=1;
}
}
if(Sno==0)
{
printf("No Student added\n");
printLine('-',70);
printf("press enter  to continue ...........");
getchar();
fflush(stdin);
}
if(Sno %pageSize!=0)
{
printLine('-',70);
printf("Press enter to continue.........");
getchar();
fflush(stdin);
}
fclose(f);
} 
