import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO
{
    private String file;

    public SerializedVehicleDAO(String file) throws IllegalArgumentException
    {
        if (file == null || file.isBlank())
            throw new IllegalArgumentException("Invalid parameter.");

        this.file = file;
    }

    @Override
    public List<Vehicle> getVehicleList() throws IllegalArgumentException, NullPointerException
    {
        List<Vehicle> ret = deserializeVehicles();
        if(ret==null)
            throw new NullPointerException("Inconsistent file content.");
        return ret;
    }

    @Override
    public Vehicle getVehicle(int id)
    {
        return getVehicleList()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveVehicle(Vehicle vehicle) throws NullPointerException, IllegalArgumentException
    {
        if(vehicle == null)
            throw new IllegalArgumentException("Invalid parameter.");

        List<Vehicle> allVehicles = getVehicleList();

        if(allVehicles.stream().anyMatch(t->t.getId()==vehicle.getId()))
            throw new IllegalArgumentException("Vehicle already exists. (id=" + vehicle.getId() + ")");

        allVehicles.add(vehicle);
        serializeVehicles(allVehicles);
    }

    @Override
    public void deleteVehicle(int id) throws IllegalArgumentException
    {
        List<Vehicle> allVehicles = getVehicleList();
        Vehicle l = allVehicles.stream().filter(t -> t.getId()==id).findAny().orElse(null);

        if(l == null)
            throw new IllegalArgumentException("Vehicle not found. (id=" + id + ")");

        allVehicles.remove(l);
        serializeVehicles(allVehicles);
    }

    @SuppressWarnings("unchecked")
    private List<Vehicle> deserializeVehicles()
    {
        try
        {
            if(!(new File(file)).exists())
                return new ArrayList<Vehicle>();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fis);
            Object temp = reader.readObject();
            if(!(temp instanceof ArrayList<?>))
                throw new Exception("The deserialized Object is not an ArrayList.");
            List<Vehicle> ret = (List<Vehicle>) temp;
            reader.close();
            fis.close();
            return ret;
        }
        catch(Exception e)
        {
            exitWith("Error during deserialization: " + e.getMessage());
        }
        return null;
    }

    private void serializeVehicles(List<Vehicle> allVehicles)
    {
        try
        {
            FileOutputStream fil = new FileOutputStream(file, false);
            ObjectOutputStream writer = new ObjectOutputStream(fil);
            writer.writeObject(allVehicles);
            writer.close();
            fil.close();
        }
        catch(Exception e)
        {
            exitWith("Error during serialization: " + e.getMessage());
        }
    }

    private void exitWith(String s)
    {
        System.err.println(s);
        System.exit(1);
    }

}