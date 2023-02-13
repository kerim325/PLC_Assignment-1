import java.util.List;

public class VehicleCLI
{
	public static void main(String[] args)
	{
		try
		{
			VehicleManagement vm = new VehicleManagement(new SerializedVehicleDAO(args[0]));
			switch (args[1])
			{
				case "show":
					if (args.length > 2)
					{
						Vehicle temp = vm.getVehicle(Integer.parseInt(args[2]));
						if(temp == null)
							return;
						System.out.println(temp);
						break;
					}
					List<Vehicle> temp1 = vm.getAllVehicles();
					for (Vehicle t : temp1)
						System.out.println(t);
					break;

				case "add":
					if (args[2].equalsIgnoreCase("truck"))
						vm.addVehicle(new Truck(Integer.parseInt(args[3]), args[4], args[5],
								Integer.parseInt(args[6]), Double.parseDouble(args[7])));
					else if (args[2].equalsIgnoreCase("car"))
						vm.addVehicle(new Car(Integer.parseInt(args[3]), args[4], args[5],
								Integer.parseInt(args[6]), Double.parseDouble(args[7]),
								Integer.parseInt(args[8])));
					else
						throw new IllegalArgumentException("Invalid parameter.");
					break;

				case "del":
					vm.deleteVehicle(Integer.parseInt(args[2]));
					break;

				case "count":
					if (args.length > 2)
					{
						if (args[2].equalsIgnoreCase("truck"))
							System.out.println(vm.count(p->p instanceof Truck));
						else if (args[2].equalsIgnoreCase("car"))
							System.out.println(vm.count(p->p instanceof Car));
						else
							throw new IllegalArgumentException("Invalid parameter.");
						break;
					}
					System.out.println(vm.count(p->p instanceof Vehicle));
					break;

				case "meanprice":
					System.out.println(Vehicle.getDecimalFormat(vm.meanPrice()));
					break;

				case "oldest":
					List<Integer> temp = vm.getIdOfOldest();
					for (Integer t : temp)
						System.out.println("Id: " + t.toString());
					break;

				default:
					throw new IllegalArgumentException("Invalid parameter.");
			}
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Error: Invalid parameter.");
			System.err.println("Error: " + e.getMessage());
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Error: " + e.getMessage());
			System.err.println("Error: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}
}