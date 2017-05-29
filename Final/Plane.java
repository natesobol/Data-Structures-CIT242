import java.text.NumberFormat;

public class Plane {
    private String Company;
    private int Speed;
    private String Model;
	private int FuelCap;
    private int WingSpan;
    private int PassCap;
    private int EngineCount;

    public Plane() {
        this("", 0, "", 0, 0, 0, 0);
    }

    public Plane(String Company, int Speed, String Model, int FuelCap,
            int WingSpan, int PassCap, int EngineCount) {
        setCompany(Company);
        setSpeed(Speed);
        setModel(Model);
		setFuelCap(FuelCap);
        setWingSpan(WingSpan);
        setPassCap(PassCap);
        setEngineCount(EngineCount);
    }

	public void setCompany(String Company) { this.Company = Company; }
	public String getCompany() { return Company; }
	public void setSpeed(int Speed) { this.Speed = Speed; }
	public int getSpeed() { return Speed; }
	public void setModel(String Model) { this.Model = Model; }
	public String getModel() { return Model; }
	public void setFuelCap(int FuelCap) { this.FuelCap = FuelCap; }
	public int getFuelCap() { return FuelCap; }
	public void setWingSpan(int WingSpan) { this.WingSpan = WingSpan; }
	public int getWingSpan() { return WingSpan; }
	public void setPassCap(int PassCap) { this.PassCap = PassCap; }
	public int getPassCap() { return PassCap; }
	public void setEngineCount(int EngineCount) { this.EngineCount = EngineCount; }
	public int getEngineCount() { return EngineCount; }
    public boolean equals(Object object) {
        if (object instanceof Plane) {
            Plane p1 = (Plane) object;

            if (
                Company.equals(p1.getCompany()) &&
                Model.equals(p1.getModel()) &&
                WingSpan == p1.getWingSpan() &&
                PassCap == p1.getPassCap() &&
                EngineCount == p1.getEngineCount()) {
                return true;
            }
        }
        return false;
    }

    public String toStringAllFields() {
        StringBuilder strbld = new StringBuilder(100);
        strbld.append("Company: ").append(Company).append(" \n").append("Model: ").append(Model).append(" \n").append("FuelCap: ").append(FuelCap).append(" \n").append("WingSpan: ").append(WingSpan).append(" \n").append("PassCap: ").append(PassCap).append(" \n").append("EngineCount: ").append(EngineCount).append("\n");

        return strbld.toString();
    }

    @Override
	public String toString() {
		return Model;
	}
}
