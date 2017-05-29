import java.io.*;
import javax.xml.stream.*; 
import java.util.*;

public class PlaneXMLFile implements PlaneDAO {
	
    private String PlanesFileModel = "Planes.xml";
    private File PlanesFile = null;

	public static void main(String[] args){
		PlaneDAO PlaneDAO = null;
		PlaneDAO = DAOFactory.getPlaneDAO();
		ArrayList<Plane> Planes = PlaneDAO.getPlanes();
		for (Plane Plane : Planes){
			System.out.println(Plane);
		}
	};

    public PlaneXMLFile() {
        PlanesFile = new File(PlanesFileModel);
    }

    private void checkFile() throws IOException {
        if (!PlanesFile.exists()) {
            PlanesFile.createNewFile();
        }
    }

    private boolean savePlanes(ArrayList<Plane> Planes) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        try {
            this.checkFile();

            FileWriter fileWriter = new FileWriter(PlanesFileModel);
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument("1.0");
            writer.writeStartElement("Planes");

            for (Plane p : Planes) {
                writer.writeStartElement("Plane");
                writer.writeAttribute("Company", p.getCompany());

                writer.writeStartElement("Speed");
                writer.writeCharacters(p.getSpeed());
                writer.writeEndElement();

                writer.writeStartElement("Model");
                writer.writeCharacters(p.getModel());
                writer.writeEndElement();

                writer.writeStartElement("FuelCap");
                writer.writeCharacters(p.getFuelCap());
                writer.writeEndElement();

                writer.writeStartElement("WingSpan");
                int WingSpan = (int) p.getWingSpan();
                writer.writeCharacters(Integer.toString(WingSpan));
                writer.writeEndElement();

                writer.writeStartElement("PassCap");
                double PassCap = p.getPassCap();
                writer.writeCharacters(Double.toString(PassCap));
                writer.writeEndElement();

                writer.writeStartElement("Engine Count");
                char EngineCount = p.getEngineCount();
                writer.writeCharacters(Character.toString(EngineCount));
                writer.writeEndElement();

                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Plane> getPlanes() {
        ArrayList<Plane> Planes = new ArrayList<Plane>();
        Plane p = null;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            this.checkFile();

            FileReader fileReader = new FileReader(PlanesFileModel);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);

            while (reader.hasNext()) {
                int eventType = reader.getEventType();

                switch (eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementModel = reader.getLocalName();
                    if (elementModel.equals("Plane")) {
                        p = new Plane();
                        String Company = reader.getAttributeValue(0);
                        p.setCompany(Company);
                    }
                    if (elementModel.equals("Speed")) {
                        String Speed = reader.getElementText();
                        p.setSpeed(Speed);
                    }
                    if (elementModel.equals("Model")) {
                        String Model = reader.getElementText();
                        p.setModel(Model);
                    }
                    if (elementModel.equals("FuelCap")) {
                        String FuelCap = reader.getElementText();
                        p.setFuelCap(FuelCap);
                    }
                    if (elementModel.equals("WingSpan")) {
                        String WingSpanString = reader.getElementText();
                        int WingSpan = Integer.parseInt(WingSpanString);
                        p.setWingSpan(WingSpan);
                    }
                    if (elementModel.equals("PassCap")) {
                        String PassCapString = reader.getElementText();
                        double PassCap = Double.parseDouble(PassCapString);
                        p.setPassCap(PassCap);
                    }
                    if (elementModel.equals("EngineCount")) {
                        String EngineCountString = reader.getElementText();
                        char EngineCount = EngineCountString.charAt(0);
                        p.setEngineCount(EngineCount);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementModel = reader.getLocalName();
                    if (elementModel.equals("Plane")) {
                        Planes.add(p);
                    }
                    break;
                default:
                    break;
                }
                reader.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            return null;
        }
        return Planes;
    }

    public Plane getPlane(String Company) {
        ArrayList<Plane> Planes = this.getPlanes();

        for (Plane p : Planes) {
            if (p.getCompany().equals(Company)) {
                return p;
            }
        }
        return null;
    }

    public boolean addPlane(Plane d) {
        ArrayList<Plane> Planes = this.getPlanes();

		for (Plane p : Planes) 
			if (p.getCompany().equals(d.getCompany()))
				return false;
		if (d.getCompany().equals("-1"))
			assignCompany(d);
        Planes.add(d);

        return this.savePlanes(Planes);
    }

	public void assignCompany(Plane d) {
		int value = 0;
		ArrayList<Plane> Planes = this.getPlanes();
		ArrayList<Integer> Planes_Company = new ArrayList<Integer>();

		for (Plane p : Planes)
			Planes_Company.add((int)Integer.parseInt(p.getCompany()));
		Collections.sort(Planes_Company);
		for (Integer i : Planes_Company) {
			value = i.intValue() + 1;
			if (! Planes_Company.contains(new Integer(value))) {
				break;
			}
		}
		if (value == 0) {
			System.out.println("Too many Planes here!! Can't add!!");
			return;
		} else {
			String Company_str = String.format("%03d", value);
			d.setCompany(Company_str);
			return;
		}
	}

    public boolean deletePlane(Plane p) {
        ArrayList<Plane> Planes = this.getPlanes();
        Planes.remove(p);

        return this.savePlanes(Planes);
    }

    public boolean updatePlane(Plane newPlane) {
        ArrayList<Plane> Planes = this.getPlanes();

        // get the old Plane and remove it
        Plane oldPlane = this.getPlane(newPlane.getCompany());
        int i = Planes.indexOf(oldPlane);
        Planes.remove(i);

        // add the updated Plane
        Planes.add(i, newPlane);

        return this.savePlanes(Planes);
    }

	// Does the Plane already exists?
    public boolean existsPlane(String Company) {
        ArrayList<Plane> Planes = this.getPlanes();

        for (Plane p : Planes) {
            if (p.getCompany().equals(Company)) {
                return true;
            }
        }
        return false;
    }

}
