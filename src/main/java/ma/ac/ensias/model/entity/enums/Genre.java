package ma.ac.ensias.model.entity.enums;

public enum Genre {
    ACTION("action"),
    DETECTIVE("detective"),
    DRAMA("drama"),
    MUSICAL("musical"),
    ADVENTURE("adventure"),
    FANTASTIC("fantastic"),
    HORROR("horror"),
    COMEDY("comedy");

    private String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
