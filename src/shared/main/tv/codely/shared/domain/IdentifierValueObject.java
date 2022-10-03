package tv.codely.shared.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class IdentifierValueObject {
    private String value;

    public IdentifierValueObject(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentifierValueObject)) {
            return false;
        }
        IdentifierValueObject that = (IdentifierValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
