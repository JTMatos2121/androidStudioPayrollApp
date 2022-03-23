import android.os.Parcel;

import java.util.Locale;

public class EmployeeCommPlus extends EmployeeExempt
{
    public static final double MIN_SALES=0;
    public static final double MAX_SALES=100000;
    private double pfvSales;
    private final double COMM_RATE=.12;
    public Creator<EmployeeExempt> CREATOR;
    public EmployeeCommPlus()
    {

    }

    public double getSales()
    {
        return pfvSales;
    }
    public void setSales(double sales)
    {
        pfvSales = sales;
    }
    @Override
    public double calcPay() throws Exception
    {
        //new exeption

        double answer;
        double commission;
        double hours = this.getHours();
        double rate = this.getRate();
        if (rate <=0)
        {
            throw new Exception("Rate must be set");
        }
        else
        {
            answer = rate*hours;
        }

        if (pfvSales <0)
        {
            throw new Exception("Sales must be set");
        }
        else
        {
            commission = pfvSales * COMM_RATE ;
            answer = answer + commission;
        }
//        if(rate<0)
//        {
//            new Exception("error");
//        }
//        answer = rate*hours;
//        commission = pfvSales * COMM_RATE ;
//        answer = answer + commission;
        return answer;
    }
    @Override
    public String toString()
    {
        String answer;
        answer = String.format(Locale.US, "EmpID=%d, FirstName=%s, LastName=%s, Dept=%s",getEmpID() ,getFirstName(), getLastName(),getDept());
        answer +=",Type=CommPlus";
        return answer;
    }
    @Override
    public String returnPayParameters() throws Exception
    {
        String answer = String.format(Locale.US,"EmpID=%d, Hours=%.2f,  HourlyRate=$%.2f, SalesRate=%.2f, " +
                "Sales=$%.2f, Pay=$%.2f",getEmpID(),getHours(), getRate(),COMM_RATE,getSales(),calcPay());
        answer += ", Type=CommPlus";
        return answer;
    }
    protected EmployeeCommPlus(Parcel in)
    {
        pfvSales = in.readDouble();
    }

    public void writeToParcel(Parcel dest, int num )
    {
        dest.writeDouble(pfvSales);
    }


}
