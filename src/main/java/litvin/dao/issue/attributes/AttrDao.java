package litvin.dao.issue.attributes;

public interface AttrDao {
    void addAttribute(String fileName, String attr);

    void editAttribute(String fileName, String old, String attr);

    String[] getAllAttributes(String fileName);
}
