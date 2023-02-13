import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class VehicleManagement
{
    private final VehicleDAO vehicleDAO;

    public VehicleManagement(VehicleDAO vehicleDAO)
    {
        if(vehicleDAO == null)
            throw new NullPointerException(" VehicleDAO-Parameter is null.");
        this.vehicleDAO = vehicleDAO;
    }

    public List<Vehicle> getAllVehicles()
    {
        return vehicleDAO.getVehicleList();
    }

    public Vehicle getVehicle(int id)
    {
        return vehicleDAO.getVehicle(id);
    }

    public void addVehicle(Vehicle veh)
    {
        this.vehicleDAO.saveVehicle(veh);
    }

    public void deleteVehicle(int id)
    {
        vehicleDAO.deleteVehicle(id);
    }

    public long count(Predicate<Vehicle> p)
    {
        return getAllVehicles().stream().filter(t -> p.test(t)).count();
    }

    public double meanPrice()
    {
        return getAllVehicles().stream().collect(Collectors.averagingDouble(Vehicle::getPrice));
    }

    public List<Integer> getIdOfOldest()
    {
        List<Vehicle> allVehicles = getAllVehicles();

        if(allVehicles.size() == 0)
            return new ArrayList<Integer>();

        try
        {
            int temp =  allVehicles
                    .stream()
                    .min(Comparator.comparing(Vehicle::getYearBuilt))
                    .get()
                    .getYearBuilt();

            return allVehicles
                    .stream()
                    .filter(t -> t.getYearBuilt()==temp)
                    .map(Vehicle::getId)
                    .collect(Collectors.toList());
        }
        catch(NoSuchElementException e) {}
        return new ArrayList<Integer>();
    }
}