import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public abstract class EmployeeBase extends MainActivity implements Parcelable
{
    //ch17 files  try catch
    //parceable need done before abstract
    //create new orientaton landscape
    //create he;p text string add to string  manager drop in to text to acchive norlam view  shift enter

    private static int ID=0;
    private final int pfvEmpID;
    private String pfvFirstName;
    private String pfvLastName;
    private DeptType pfvDept;
    public Creator<EmployeeBase> CREATOR;


    public EmployeeBase()
    {
        pfvEmpID = ++ID;

    }
    @Override
    public int describeContents()
    {
        return 0;
    }

    public enum DeptType{
        SALES,
        ENGINEERING,
        FINANCE,
        ADMIN
    }
    public EmployeeBase(String firstName,String lastName,DeptType dept) throws Exception
    {
        this();
        setFirstName(firstName);
        setLastName(lastName);
        setDept(dept);

    }

    public int getEmpID()

    {
        return pfvEmpID;
    }

    public String getFirstName()
    {
        return pfvFirstName;
    }

    public void setFirstName(String firstName) throws Exception
    {
        if (firstName == null || firstName.isEmpty())
        {
            throw new Exception("First Name can not be empty");
        }
        pfvFirstName = firstName;
    }
    public String getLastName()
    {
        return pfvLastName;
    }

    public void setLastName(String lastName) throws Exception
    {
        if (lastName == null || lastName.isEmpty())
        {
            throw new Exception("Last Name can not be empty");
        }
        pfvLastName = lastName;
    }

    public DeptType getDept()
    {
        return pfvDept;
    }

    public void setDept(DeptType dept) throws Exception
    {
        if (dept == null)
        {
            throw new Exception("Department can not be empty");
        }
        pfvDept = dept;
    }

    public double calcPay() throws Exception
    {
        //do calc calculation
        return 0;
    }
    @Override
    public String toString()
    {
        String answer = String.format(Locale.US,"EmpID=%d, FirstName=%s, LastName=%s, Dept=%s",pfvEmpID ,pfvFirstName ,pfvLastName,pfvDept);
        return answer;
    }

    public String returnPayParameters() throws Exception
    {
        return "";
    }
    public int describeContent()
    {
        int answer= 0;
        return answer;
    }
    protected EmployeeBase(Parcel in)
    {
        pfvEmpID = in.readInt();
        pfvFirstName=in.readString();
        pfvLastName=in.readString();
        pfvDept = DeptType.valueOf(in.readString());

    }

    public void writeToParcel(Parcel dest, int num )
    {
        dest.writeInt(pfvEmpID);
        dest.writeString(pfvFirstName);
        dest.writeString(pfvLastName);
        dest.writeString(String.valueOf(pfvDept));
    }


}
