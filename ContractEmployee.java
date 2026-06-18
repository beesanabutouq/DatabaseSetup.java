package supermarket_db;

public class ContractEmployee {

	private int employeeId;
	private double contractSalary;
	private String contractEndDate;

	public ContractEmployee(int employeeId, double contractSalary, String contractEndDate) {

		this.employeeId = employeeId;
		this.contractSalary = contractSalary;
		this.contractEndDate = contractEndDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public double getContractSalary() {
		return contractSalary;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}
}