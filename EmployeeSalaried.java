import android.os.Parcel;

import java.util.Locale;

public class EmployeeSalaried extends EmployeeBase
{
    public static final double MIN_SALARY = 1;
    public static final double MAX_SALARY = 80000;
    private final int  WEEKS=52;
    private double pfvSalary;
    public Creator<EmployeeBase> CREATOR;

    public EmployeeSalaried()
    {
        super();
    }
    public EmployeeSalaried(String firstName, String lastName, DeptType dept) throws Exception
    {
        super(firstName, lastName, dept);
    }

    public void setSalary(double salary)
    {
        pfvSalary = salary;
    }
    public double getSalary()
    {
        return pfvSalary;
    }
    @Override
    public double calcPay() throws Exception
    {
        double answer =0;

        if (pfvSalary <=0)
        {
            throw new Exception("Salary must be set");
        }
        else
        {
            answer = pfvSalary/WEEKS;
        }
        return answer;



    }
    @Override
    public String toString()
    {
        String answer;
        answer = String.format(Locale.US,"EmpID=%d, FirstName=%s, LastName=%s, Dept=%s",getEmpID() ,getFirstName(), getLastName(),getDept());
        answer += ", Type=Salaried";
        return answer;
    }
    @Override
    public String returnPayParameters() throws Exception
    {
        String answer = String.format(Locale.US,"EmpID=%d, Weeks=%d,Salary=$%.2f, Pay=$%.2f",getEmpID(),WEEKS, getSalary(),calcPay());
        answer += ", Type=Salaried";
        return answer;
    }

    protected EmployeeSalaried(Parcel in)
    {
        pfvSalary = in.readDouble();
    }

    public void writeToParcel(Parcel dest, int num )
    {
        dest.writeDouble(pfvSalary);
    }



}
