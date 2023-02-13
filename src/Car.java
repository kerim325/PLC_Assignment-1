import java.util.Calendar;

public class Car extends Vehicle
{
    private int inspectionYear;

    public Car(int id, String brand, String model, int yearBuilt, double basePrice, int inspectionYear) throws IllegalArgumentException
    {
        super(id, brand, model, yearBuilt, basePrice);
        if(inspectionYear > Calendar.getInstance().get(Calendar.YEAR))
            throw new IllegalArgumentException("Inspection year invalid.");
        this.inspectionYear = inspectionYear;
    }

    @Override
    double getDiscount()
    {
        double ret = getYearsSinceInspection()*0.02 + getAge()*0.05;
        if(ret > 0.15)
            ret=0.15;
        return getBasePrice() * ret;
    }

    public int getInspectionYear()
    {
        return inspectionYear;
    }

    public int getYearsSinceInspection()
    {
        return Calendar.getInstance().get(Calendar.YEAR) - inspectionYear;
    }

    @Override
    public String toString()
    {
        return  String.format("%-12s%s\n", "Type: ", getClass().getName())
                + String.format("%-12s%s\n", "Id: ", getId())
                + String.format("%-12s%s\n", "Brand: ", getBrand())
                + String.format("%-12s%s\n", "Model: ", getModel())
                + String.format("%-12s%s\n", "Year: ", getYearBuilt())
                + String.format("%-12s%s\n", "Inspection: ", getInspectionYear())
                + String.format("%-12s%s\n", "Base price: ", Vehicle.getDecimalFormat(getBasePrice()))
                + String.format("%-12s%s\n", "Price: ", Vehicle.getDecimalFormat(getPrice()));
    }
}