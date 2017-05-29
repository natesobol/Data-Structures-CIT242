public class DAOFactory {
    // this method maps the PlaneDAO interface
    // to the appropriate data storage mechanism
    public static PlaneDAO getPlaneDAO() {
        PlaneDAO DAO = new PlaneXMLFile();
        return DAO;
    }
}
