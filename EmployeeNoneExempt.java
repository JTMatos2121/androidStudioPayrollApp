import java.util.Locale;

public class EmployeeNoneExempt extends EmployeeExempt
{
    public static final double MIN_HOURS=0;
    public static final double MAX_HOURS=80;
    private double OT_MULRIPLIER;
    public EmployeeNoneExempt()
    {
        super();
    }
    public Creator<EmployeeBase> CREATOR;

    @Override
    public double calcPay() throws Exception
    {
        double answer=0;
        double hours = this.getHours();
        double rate = this.getRate();
        if(rate ==0)
        {
            throw new Exception("Pay Rate has not been set");

        }
        else
        {
            if(hours<=40)
            {
                answer = hours * rate;
            }
            else
            {
                answer =(40 *rate)+(hours-40) *rate *1.5;
            }

        }
        return answer;

    }
    @Override
    public String toString()
    {
        String answer =String.format(Locale.US,"EmpID=%d, FirstName=%s, LastName=%s, Dept=%s",getEmpID() ,getFirstName() ,
                getLastName(),getDept());
        answer += ", Type=NoneExempt";
        return answer;
    }

    @Override
    public String returnPayParameters() throws Exception
    {
        //exeption
        String answer = String.format(Locale.US,"EmpID=%d, Hours=%.2f, Rate=$%.2f, Pay=$%s",getEmpID(),getHours(),getRate(),calcPay());
        answer +=", Type=NoneExempt";
        return answer;
    }

}
