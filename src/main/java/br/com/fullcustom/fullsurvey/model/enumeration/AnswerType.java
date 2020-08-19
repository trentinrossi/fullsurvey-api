package br.com.fullcustom.fullsurvey.model.enumeration;

public enum AnswerType {
    NPS(1, "NPS"), 
    TEXT(2, "Text"),
    CHECKBOX(3, "Checkbox"),
    RADIO(4, "Radio Button");

    private int id;
    private String name;

    private AnswerType(int id, String name) {
		this.id = id;
		this.name = name;
	}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AnswerType toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (AnswerType x : AnswerType.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }
}