import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

public abstract class Vehicle implements Serializable {
    private int id, yearBuilt;
    private String brand, model;
    private double basePrice;
    private static DecimalFormat df = new DecimalFormat("#0.00");

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Vehicle(int id, String brand, String model, int yearBuilt, double basePrice) throws IllegalArgumentException
    {
        if(brand == null || model == null || brand.isBlank() || model.isBlank())
            throw new IllegalArgumentException("Invalid parameter.");

        if(basePrice < 0)
            throw new IllegalArgumentException("Invalid base price.");

        if(yearBuilt > Calendar.getInstance().get(Calendar.YEAR))
            throw new IllegalArgumentException("Year built invalid.");

        if(id < 0)
            throw new IllegalArgumentException("Invalid ID.");

        this.brand = brand;
        this.model = model;
        this.basePrice = basePrice;
        this.id = id;
        this.yearBuilt = yearBuilt;
    }

    public int getAge()
    {
        return Calendar.getInstance().get(Calendar.YEAR) - yearBuilt;
    }

    public int getId()
    {
        return id;
    }

    public double getPrice()
    {
        return this.basePrice - getDiscount();
    }

    abstract double getDiscount();

    public double getBasePrice()
    {
        return basePrice;
    }

    public int getYearBuilt()
    {
        return yearBuilt;
    }

    public static String getDecimalFormat(double d)
    {
        return df.format(d);
    }

}