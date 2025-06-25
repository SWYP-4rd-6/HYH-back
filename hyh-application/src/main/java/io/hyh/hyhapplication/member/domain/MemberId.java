package io.hyh.hyhapplication.member.domain;

import java.util.Objects;


public class MemberId {

    private final String value;
    private static final int SEQUENCE_LENGTH = 10;

    public MemberId(String value) {
        this.value = value;
    }

    public static MemberId of(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("MemberId should not be null or empty");
        }
        return new MemberId(value);
    }

    public String getValue() {
        return value;
    }

    public static MemberId fromSequence(long sequence) {
        if (sequence <= 0) {
            throw new IllegalArgumentException("Sequence must be positive");
        }
        if (sequence > 9999999999L) {
            throw new IllegalArgumentException("Sequence number too large");
        }

        String paddedSequence = String.format("%0" + SEQUENCE_LENGTH + "d", sequence);
        return new MemberId("M" + paddedSequence);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberId that = (MemberId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
