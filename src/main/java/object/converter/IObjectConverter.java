package object.converter;

public interface IObjectConverter<TO> {

    Class<TO> getToClass();

    default TO convertTo() {
        TO to = null;
        try {
            to = getToClass().newInstance();
            to = (TO) Converter.convert(this, to);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return to;
    }

    default TO convertFrom() {
        TO to = null;
        try {
            to = getToClass().newInstance();
            to = (TO) Converter.convert(this, to);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return to;
    }
}
