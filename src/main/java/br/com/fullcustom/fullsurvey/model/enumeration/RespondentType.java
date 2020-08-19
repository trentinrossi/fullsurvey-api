package br.com.fullcustom.fullsurvey.model.enumeration;

public enum RespondentType {
    EMPLOYEE(1, "Employee"), 
    VISITOR(2, "Visitor"),
    CANDIDATE(3, "Candidate"),
    INTERN(4, "Intern");

    private int id;
    private String name;

    private RespondentType(int id, String name) {
		this.id = id;
		this.name = name;
	}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RespondentType toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (RespondentType x : RespondentType.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }
}