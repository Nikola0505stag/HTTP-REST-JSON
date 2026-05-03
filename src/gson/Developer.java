package gson;

import com.google.gson.annotations.SerializedName;

public class Developer {

    private String name;
    private int age;
    @SerializedName("employer") // this sets custom name to the serialized field
    private transient String company; // forbit for serialization

    public Developer(String name, int age, String company) {
        setName(name);
        setAge(age);
        setCompany(company);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be null or blank!");
        }

        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Ages can't be less than zero!");
        }

        this.age = age;
    }

    public void setCompany(String company) {
        if (company == null || company.isBlank()) {
            throw new IllegalArgumentException("Company name can't be null or blank!");
        }

        this.company = company;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCompany() {
        return company;
    }
}
