package br.com.fullcustom.fullsurvey.model.enumeration;

public enum AnswerStatus {
    NEW(1, "New"), 
    ANSWERED(2, "Answered"),
    SENT(3, "Sent"),
    INCONPLETE(4, "Incomplete");

    private int id;
    private String name;

    private AnswerStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AnswerStatus toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (AnswerStatus x : AnswerStatus.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }
}