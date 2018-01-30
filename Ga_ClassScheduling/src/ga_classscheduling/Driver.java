package ga_classscheduling;

/**
 *
 * @author Naduni
 */
public class Driver {
	public static final int POPULATION_SIZE = 9;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.9;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int NUMB_OF_ELITE_SCHDEULES = 1;
        private int scheduleNumb = 0;
	private Data data;

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.data = new Data();
                int generationNumber = 0;
                driver.printAvailableData();
                System.out.println("> Genaration # " +generationNumber);
                System.out.println(" Schedule # |                              ");
                System.out.println("Classes [dept,class,room,instructor,meeting-time]      ");
                System.out.println("                                                                                                                                                    "
                        + "  | Fitness    |Conflicts");
                System.out.println("................................................................"
                        + "....................................................."
                        + "...........................................................");
                GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
                Population population= new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
                population.getSchedules().forEach(schedule-> System.out.println("        "+driver.scheduleNumb++ +
                        "     |  "+ schedule + "  |  "  +
                        String.format("%.5f",schedule.getFitness())+ "   |   "+schedule.getNumbOfConflicts())) ;
	}

	private void printAvailableData() {
		System.out.println("Available Departments :");
		data.getDepartment().forEach(x -> System.out.println("Name: " + x.getName() + ", Courses offered: " + x.getCourses()));

		System.out.println("\nAvailable Courses  :");
		data.getCourse().forEach(x -> System.out.println("Course number " + x.getNumber() + ", name: " + x.getName() + ", max no of students : " + x.maxNumbOfStudents() + ", Instructors " + x.getInstructors()));

		System.out.println("Available Rooms :");
		data.getRooms().forEach(x -> System.out.println("Room: " + x.getNumber() + ", Max seating capacity: " + x.getSeatingCapacity()));

		System.out.println("Available Instructors :");
		data.getInstructors().forEach(x -> System.out.println("Id: " + x.getId() + ", Name: " + x.getName()));

		System.out.println("Available Meeting Times  :");
		data.getMeetingTime().forEach(x -> System.out.println("Id: " + x.getId() + ", Meeting Time: " + x.getTime()));

		System.out.println("............................................................." + ".........................................................................");
		System.out.println("............................................................." + ".........................................................................");

	}

}
