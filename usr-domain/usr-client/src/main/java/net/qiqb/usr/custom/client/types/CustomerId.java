package net.qiqb.usr.custom.client.types;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author
 * @since
 */
@NoArgsConstructor
@Setter
public class CustomerId implements Serializable {

    private String val;

    public CustomerId(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerId thatId = (CustomerId) o;

        return Objects.equals(val, thatId.val);
    }

    @Override
    public String toString() {
        return val;
    }
}