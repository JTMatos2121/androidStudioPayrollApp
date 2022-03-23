import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private ArrayList<EmployeeBase> empList = new ArrayList<>();
    private Spinner spType;
    private EditText etFirstName;
    private EditText etLastName;
    private Spinner spDept;
    private EditText etHours;
    private EditText etRate;
    private EditText etSales;
    private EditText etSalary;
    private TextView etList;
    private String empType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etHours = (EditText) findViewById(R.id.etHours);
        etRate = (EditText) findViewById(R.id.etRate);
        etSales = (EditText) findViewById(R.id.etSales);
        etList = findViewById(R.id.etList);
        etSalary = (EditText) findViewById(R.id.etSalary);
        spType = (Spinner) findViewById(R.id.spType);
        spDept = (Spinner) findViewById(R.id.spDept);




// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spType_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spType.setAdapter(adapter);
        spType.setOnItemSelectedListener(this);

        spDept.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, EmployeeBase.DeptType.values()));

        spType.setSelection(0);
        spDept.setSelection(1);


    }

    //Spinner method to work with selection
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //enable idsable fields per type
        //set employee type
        empType = parent.getItemAtPosition(position).toString();
        // empDept= (DeptType.Dept) spDept.getItemAtPosition(position);
        switch (empType)
        {
            case "NoneExempt":
            case "Exempt":
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");
                etHours.setEnabled(true);
                etRate.setEnabled(true);
                etSalary.setEnabled(false);
                etSales.setEnabled(false);

                break;
            case "Salaried":
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");
                etHours.setEnabled(false);
                etRate.setEnabled(false);
                etSalary.setEnabled(true);
                etSales.setEnabled(false);
                break;
            case "Commissioned":
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");
                etHours.setEnabled(false);
                etRate.setEnabled(false);
                etSalary.setEnabled(false);
                etSales.setEnabled(true);
                break;
            case "CommPlus":
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");
                etHours.setEnabled(true);
                etRate.setEnabled(true);
                etSalary.setEnabled(false);
                etSales.setEnabled(true);
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {


    }



    public void addButton(View v) throws Exception
    {

        String empDept = String.valueOf(spDept.getSelectedItem());
        double hours=0;
        double rate=0;
        double sales=0;
        double salary=0;

        String firstName = validateString(etFirstName, "First Name");
        if (firstName.isEmpty())
            //stop
             return;
        String lastName = validateString(etLastName,"Last Name");
        if(lastName.isEmpty())
            //stop
            return;


        if (etHours.isEnabled())
        {
            if(empType.equals("Exempt")||empType.equals("CommPlus"))
            {
                hours = validateDouble(etHours, "Hours", EmployeeExempt.MIN_HOURS, EmployeeExempt.MAX_HOURS);
                if (hours < EmployeeExempt.MIN_HOURS)
                {
                    return;
                }
            }
            if(empType.equals("NoneExempt"))
            {
                hours = validateDouble(etHours, "Hours", EmployeeNoneExempt.MIN_HOURS, EmployeeNoneExempt.MAX_HOURS);
                if (hours < EmployeeNoneExempt.MIN_HOURS)
                {
                    return;
                }
            }

        }



        if (etRate.isEnabled())
        {
           rate = validateDouble(etRate, "Rate", EmployeeExempt.MIN_RATE, EmployeeExempt.MAX_RATE);
            if (rate < EmployeeExempt.MIN_RATE)

                return;
        }






        if (etSalary.isEnabled())
        {
            salary = validateDouble(etSalary, "Salary", EmployeeSalaried.MIN_SALARY, EmployeeSalaried.MAX_SALARY);
            if(salary<EmployeeSalaried.MIN_SALARY)
            {
                return;
            }
        }

        if (etSales.isEnabled())
        {
            sales = validateDouble(etSales, "Sales", EmployeeCommission.MIN_SALES, EmployeeCommission.MAX_SALES);
            if (sales < EmployeeCommission.MIN_SALES)
            {
                return;
            }
        }










        switch (empType)
        {
            case "Exempt":
                EmployeeExempt emp = new EmployeeExempt();
                emp.setFirstName(firstName);
                emp.setLastName(lastName);
                emp.setHours(hours);
                emp.setRate(rate);
                emp.setDept(convertToType(empDept));
                empList.add(emp);
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");

                break;

            case "NoneExempt":
                EmployeeExempt empN = new EmployeeNoneExempt();
                empN.setFirstName(firstName);
                empN.setLastName(lastName);
                empN.setHours(hours);
                empN.setRate(rate);
                empN.setDept(convertToType(empDept));
                empList.add(empN);
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");

                break;

            case "CommPlus":
                EmployeeCommPlus empCP = new EmployeeCommPlus();
                empCP.setFirstName(firstName);
                empCP.setLastName(lastName);
                empCP.setHours(hours);
                empCP.setRate(rate);
                empCP.setSales(sales);
                empCP.setDept(convertToType(empDept));
                empList.add(empCP);
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");

                break;

            case "Commissioned":
                EmployeeCommission empC = new EmployeeCommission();
                empC.setFirstName(firstName);
                empC.setLastName(lastName);
                empC.setSales(sales);
                empC.setDept(convertToType(empDept));
                empList.add(empC);
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");

                break;
            case "Salaried":
                EmployeeSalaried empS = new EmployeeSalaried();
                empS.setFirstName(firstName);
                empS.setLastName(lastName);
                empS.setSalary(salary);
                empS.setDept(convertToType(empDept));
                empList.add(empS);
                etLastName.setText("");
                etFirstName.setText("");
                etHours.setText("");
                etRate.setText("");
                etSalary.setText("");
                etSales.setText("");

                break;
        }
    }





    private String validateString(EditText et, String fieldString)
    {
        String answer = et.getText().toString();
        //Is it blank?Test
        if (answer.isEmpty())
        {
            et.setError(fieldString + " can't be blank\n");
            et.requestFocus();
            beep();

        }

        return answer;

    }

    private double validateDouble(EditText et, String fieldString, double minValue, double maxValue) throws Exception
    {
        // let's start with a bogus value that is 1 less than minValue
        double answer = minValue -1;
        String buffer = et.getText().toString();

        // case 1 - is it empty?
        if (buffer.isEmpty())
        {
            et.setError(fieldString + " can't be blank\n");
            et.requestFocus();
            beep();
        }
        else
        {
            try
            {
                double num = Double.parseDouble(buffer);
                // case 2 - can convert


                // case 3 - is it inside the range
                if (num < minValue || num > maxValue)
                {
                    et.setError(String.format(Locale.US, "%s must be between %.2f and %.0f\n", fieldString, minValue, maxValue));
                    et.requestFocus();
                    beep();
                } else
                {
                    answer = num;
                }
            }
            catch(Exception e)
            {
                throw new Exception("Cannot convert to number");

            }
        }
        return answer;

    }

    // make an audible beep
    private void beep()
    {
        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
        tg.startTone(ToneGenerator.TONE_SUP_PIP, 150);
    }

    public void listButton(View view)throws Exception
    {
        etList.setText("");
        if(empList.isEmpty())
        {
            return;
        }else
        {

            for (int employee = 0; employee < empList.size(); employee++)
            {
                etList.append(empList.get(employee).toString() + "\n");
            }
        }



    }

    public void payButton(View v) throws Exception
    {
        etList.setText("");
        if(empList.isEmpty())
        {
            return;
        }else
        {
            for (int employee = 0; employee < empList.size(); employee++)
            {
                etList.append(empList.get(employee).returnPayParameters() + "\n");
            }
        }



    }

    public boolean onCreateOptionMenu(Menu menu)
    {
        boolean answer = true;
        return answer;
    }

    public boolean onItemSelected(MenuItem item)
    {
        boolean answer = true;
        return answer;
    }

    private void alertDialog(String alert)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(alert);
        dialog.setTitle("Error");
        dialog.setIconAttribute(android.R.attr.alertDialogIcon);
        dialog.setPositiveButton("OK", null);

        AlertDialog alertMess = dialog.create();
        alertMess.show();
    }


    public EmployeeBase.DeptType convertToType(String type)
    {
        EmployeeBase.DeptType answer = null;
        boolean foundIt = false;

        for (EmployeeBase.DeptType tp : EmployeeBase.DeptType.values())
        {
            if (tp.toString().equalsIgnoreCase(type))
            {
                foundIt = true;
                answer = tp;
                break;
            }
        }
        if (!foundIt)
        {
            alertDialog("Invalid student type");
        }
        return answer;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu,menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.help:
                Intent help = new Intent(this,HelpActivity.class);
                startActivity(help);

                break;
            case R.id.list_employees:
                etList.setText("");

                for (int employee = 0; employee < empList.size(); employee++)
                {
                    etList.append(empList.get(employee).toString() + "\n");
                }
                break;
            case R.id.pay_employees:
                etList.setText("");
                for (int employee = 0; employee < empList.size(); employee++)
                {
                    try
                    {
                        etList.append(empList.get(employee).returnPayParameters() + "\n");
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelableArrayList("EmpArray",empList);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {

        super.onRestoreInstanceState(savedInstanceState);
        if(empList==null)
        {
            return;
        }
        else
        {
            empList = savedInstanceState.getParcelableArrayList("EmpArray");
        }



    }

}
