package supermarket_db;

public class HourlyEmployee {

	private int employeeId;
	private double hourlyWage;
	private int hoursWorked;

	public HourlyEmployee(int employeeId, double hourlyWage, int hoursWorked) {

		this.employeeId = employeeId;
		this.hourlyWage = hourlyWage;
		this.hoursWorked = hoursWorked;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public double getHourlyWage() {
		return hourlyWage;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}
}