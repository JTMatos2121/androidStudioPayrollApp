import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class EmployeeExempt extends EmployeeBase
{
    public static final double MIN_HOURS = 1;
    public static final double MAX_HOURS = 40;
    public static final double MIN_RATE = 8.45;
    public static final double MAX_RATE = 100.00;
    private double pfvHours;
    private double pfvRate;
    public Creator<EmployeeBase> CREATOR;

    public EmployeeExempt()
    {
        super();
    }

    public EmployeeExempt(String firstName, String lastName, EmployeeBase.DeptType dept) throws Exception

    {
        super(firstName, lastName, dept);
    }

    public void setHours(double hours) throws Exception
    {
        // check for valid range of values
        if (hours < MIN_HOURS || hours > MAX_HOURS)
        {
            throw new Exception(String.format(Locale.US, "Hours must be between $%.2f and $%.2f", MIN_HOURS, MAX_HOURS));
        }
        pfvHours = hours;
    }


    public double getHours()
    {
        return pfvHours;
    }

    public void setRate(double rate) throws Exception
    {
        // check for valid range of values
        if (rate < MIN_RATE || rate > MAX_RATE)
        {
            throw new Exception(String.format(Locale.US, "Pay Rate must be between $%.2f and $%.2f", MIN_RATE, MAX_RATE));
        }
        pfvRate = rate;
    }


    public double getRate()
    {
        return pfvRate;
    }

    public double calcPay() throws Exception
    {
        double answer = -1;

        // a quick sanity check... rate can not be 0
        if (pfvRate == 0)
        {
            throw new Exception("Pay Rate has not been set");
        } else
        {
            // we're assuming no Over Time...
            answer = pfvHours * pfvRate;
        }
        return answer;
    }

    @Override
    public String toString()
    {
        String answer = String.format(Locale.US, "EmpID=%d, FirstName=%s, LastName=%s, Dept=%s", getEmpID(), getFirstName(),
                getLastName(), getDept());
        answer += ", Type=Exempt";
        return answer;
    }

    @Override
    public String returnPayParameters() throws Exception
    {
        //exeption
        String answer = String.format(Locale.US, "EmpID=%d, Hours=%.2f, Rate=$%.2f, Pay=$%s", getEmpID(), getHours(), getRate(), calcPay());
        answer += ", Type=Exempt";
        return answer;
    }

    protected EmployeeExempt(Parcel in)
    {
        pfvHours = in.readDouble();
        pfvRate = in.readDouble();
    }

    public void writeToParcel(Parcel dest, int num )
    {
        dest.writeDouble(pfvHours);
        dest.writeDouble(pfvRate);
    }
}
