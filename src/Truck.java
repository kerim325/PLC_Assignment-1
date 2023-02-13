public class Truck extends Vehicle
{
    public Truck(int id, String brand, String model, int yearBuilt, double basePrice) throws IllegalArgumentException
    {
        super(id, brand, model, yearBuilt, basePrice);
    }

    @Override
    double getDiscount()
    {
        double ret = getAge()*0.05;
        if(ret > 0.2)
            ret = 0.2;
        return getBasePrice() * ret;
    }

    @Override
    public String toString()
    {
        return  String.format("%-12s%s\n", "Type: ", getClass().getName())
                + String.format("%-12s%s\n", "Id: ", getId())
                + String.format("%-12s%s\n", "Brand: ", getBrand())
                + String.format("%-12s%s\n", "Model: ", getModel())
                + String.format("%-12s%s\n", "Year: ", getYearBuilt())
                + String.format("%-12s%s\n", "Base price: ", Vehicle.getDecimalFormat(getBasePrice()))
                + String.format("%-12s%s\n", "Price: ", Vehicle.getDecimalFormat(getPrice()));
    }
}