import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class EmployeeCommission extends EmployeeBase
{
    public static final double MIN_SALES=0;
    public static final double MAX_SALES=100000;
    private  double COMM_RATE=.12;
    private double pfvSales;
    public Creator<EmployeeBase> CREATOR;
    public EmployeeCommission()
    {
        super();
    }

    protected EmployeeCommission(Parcel in)
    {

        pfvSales = in.readDouble();
    }
    public void setSales(double sales)
    {
        pfvSales = sales;
    }

    public double getSales()
    {
        return pfvSales;
    }
    @Override
    public double calcPay() throws Exception
    {
        //<b>Commission</b> - A commission employee is paid based upon a percentage of the sales
        // that they make during the week.A typical commission rate is 12%.

        double answer =0;

        if (pfvSales <=0)
        {
            throw new Exception("Sales must be set");
        }
        else
        {
            answer = pfvSales*COMM_RATE;
        }
        return answer;

    }
    @Override
    public String toString()
    {
        String answer;
        answer = String.format(Locale.US,"EmpID=%d, FirstName=%s, LastName=%s, Dept=%s",getEmpID() ,getFirstName(), getLastName(),getDept());
        answer+=", Type=Commission";
        return answer;
    }
    @Override
    public String returnPayParameters() throws Exception
    {
        String answer = String.format(Locale.US,"EmpID=%d, Rate=%.2f, Sales=$%.2f, Pay=$%s",getEmpID(), COMM_RATE,getSales(),calcPay());
        answer += ", Type=Commission";
        return answer;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {

        dest.writeDouble(pfvSales);
    }
}

