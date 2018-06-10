package ga_classscheduling;
import ga_classscheduling.domain.Class;
import java.util.ArrayList;

/**
 *
 * @author Naduni
 */
public class Driver {
	public static final int POPULATION_SIZE = 10;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.9;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int NUMB_OF_ELITE_SCHDEULES = 1;
        private int scheduleNumb = 0;
        private int classNumb = 1;
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
                driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
                driver.classNumb=1;
                
                while (population.getSchedules().get(0).getFitness()!=1.0){
                    System.out.println("> Genaration # " + ++generationNumber);
                System.out.print(" Schedule # |                              ");
                System.out.print("Classes [dept,class,room,instructor,meeting-time]      ");
                System.out.println("                                           "
                        + "         | Fitness    |Conflicts");
                System.out.println("................................................................"
                        + "....................................................."
                        + "...........................................................");
                population = geneticAlgorithm.evolve(population).sortByFitness();
                driver.scheduleNumb =0;
                population.getSchedules().forEach(schedule-> System.out.println("        "+driver.scheduleNumb++ +
                        "     |  "+ schedule + "  |  "  +
                        String.format("%.5f",schedule.getFitness())+ "   |   "+schedule.getNumbOfConflicts())) ;
                driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
                driver.classNumb=1;
                
                }
	}
        
        private void printScheduleAsTable(Schedule schedule, int generation){
            ArrayList<Class> classes =schedule.getClasses();
            System.out.print("\n               ");
            System.out.println("Class # |     Department | Course"
                    + "(number,max students) | Room (Capacity)   |        Instructor (Id)      | Meeting Time(Id)");
            System.out.print("               ");
            System.out.print("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
            classes.forEach(x -> {
                int majorIndex= data.getDepartment().indexOf(x.getDept());
                int coursesIndex = data.getCourse().indexOf(x.getCourse());
                int roomsIndex = data.getRooms().indexOf(x.getRoom());
                int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
                int meetingTimeIndex = data.getMeetingTime().indexOf(x.getMeetingTime());
                System.out.print("             ");
                System.out.print(String.format("  %1$02d  ", classNumb) +"     |          ");
                System.out.print(String.format("%1$4s", data.getDepartment().get(majorIndex).getName()) +" |  ");
                System.out.print(String.format("%1$21s", data.getCourse().get(coursesIndex).getName()+
                        "("+data.getCourse().get(coursesIndex).getNumber()+ ","  +
                              x.getCourse().maxNumbOfStudents())+")      |  ");
                System.out.print(String.format("%1$10s", data.getRooms().get(roomsIndex).getNumber()+
                        "("+x.getRoom().getSeatingCapacity()) + ")     | ");
                System.out.print(String.format("%1$25s", data.getInstructors().get(instructorsIndex).getName()+
                        " ("+data.getInstructors().get(instructorsIndex).getId()+")")+ "  |  ");
                System.out.println(data.getMeetingTime().get(meetingTimeIndex).getTime()+
                        " (" +data.getMeetingTime().get(meetingTimeIndex).getId()+ ")");
                classNumb++;
            });
            if(schedule.getFitness()==1) System.out.println("Solution found in " + (generation +1) + " generations");
             System.out.print("------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------");
        
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
